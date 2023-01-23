package com.example.comercio5.respository;

import com.example.comercio5.model.Out;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

  private final String selectOut = "select productid from product inner join (\n"
      + "select DISTINCT size.productId from (\n"
      + "select * from\n"
      + "(select * from size inner join  stock on size.id = stock.sizeid and size.special = true and stock.quantity >0) b\n"
      + "    inner join\n"
      + " (select * from size inner join  stock on size.id = stock.sizeid and size.special = false and stock.quantity >0) c on b.productid = c.productid\n"
      + "    ) d, size where size.backsoon = true\n"
      + "    ) f on product.id = f.productid  order by sequence";

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

  public void saveStock(int sizeid, int quantity) {
    Map<String, Object> map = Map.of("sizeid", sizeid, "quantity", quantity);
    namedParameterJdbcTemplate.update(insertStockQuery, map);
  }

  public List<Out> getOutput(){
   List<Out> result= namedParameterJdbcTemplate.query(selectOut, BeanPropertyRowMapper.newInstance(Out.class));
    System.out.println(Arrays.toString(result.toArray()));
    for (Out st : result){
      System.out.println(st.getProductId()+",");
    }
    return result; //Collections.emptyList();
  }
}
