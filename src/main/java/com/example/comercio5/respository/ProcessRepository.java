package com.example.comercio5.respository;

import com.example.comercio5.model.Out;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    return Collections.emptyList();
  }
}
