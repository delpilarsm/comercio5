package com.example.comercio5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
  @Id
  private Long id;
  private Long sequence;
}
