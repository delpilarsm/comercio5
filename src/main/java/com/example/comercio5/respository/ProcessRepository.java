package com.example.comercio5.respository;

import com.example.comercio5.model.Out;
import java.util.Iterator;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessRepository {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final String insertProductQuery = "insert into product (id, sequence)"
      + " values (:id,:sequence);";
  private final String insertSizeQuery = "insert into public.size (id, productid, backsoon, special)"
      + "values (:id, :productid, :backsoon, :special);";
  private final String insertStockQuery = "insert into stock(sizeid, quantity)"
      + "values (:sizeid, :quantity);";

  private final String selectOut = "select productid from product inner join ( select DISTINCT size.productId from "
      + "(select * from (select * from size inner join  stock on size.id = stock.sizeid and size.special = true and stock.quantity >0) b inner join"
      + "(select * from size inner join  stock on size.id = stock.sizeid and size.special = false and stock.quantity >0) c "
      + " on b.productid = c.productid) d, size where size.backsoon = true) f on product.id = f.productid  order by sequence";

  public ProcessRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = jdbcTemplate;
  }

  public void saveProduct(int id, int sequence) {
    Map<String, Object> map = Map.of("id", id, "sequence", sequence);
    namedParameterJdbcTemplate.update(insertProductQuery, map);
  }

  public void saveSize(int id, int productId, boolean backsoon, boolean special) {

    Map<String, Object> map = Map.of("id", id, "productid", productId, "backsoon",
        backsoon, "special", special);
    namedParameterJdbcTemplate.update(insertSizeQuery, map);
  }

  public void saveStock(int sizeId, int quantity) {
    Map<String, Object> map = Map.of("sizeid", sizeId, "quantity", quantity);
    namedParameterJdbcTemplate.update(insertStockQuery, map);
  }

  public String getOutput() {
    // Desde el query obtener la lista de productsId en un iterator, con el objetivo de facilitar iterar el output separado por ,
    Iterator<Out> result = namedParameterJdbcTemplate.query(selectOut, BeanPropertyRowMapper.newInstance(Out.class)).iterator();
    StringBuffer line = new StringBuffer();
    if (result.hasNext()) {
      line.append(result.next().getProductId());
    }
    while (result.hasNext()) {
      line.append("," + result.next().getProductId());
    }
    System.out.print(line);
    return line.toString();
  }
}
