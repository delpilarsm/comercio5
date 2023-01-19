package com.example.comercio5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "size")
public class Size {
  @Id
  private Long id;
  private Long productid;
  private Boolean backsoon;
  private Boolean special;
}
