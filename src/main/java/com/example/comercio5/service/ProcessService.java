package com.example.comercio5.service;

import com.example.comercio5.respository.ProcessRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessService {

  private final ProcessRepository processRepository;

  public ProcessService(ProcessRepository processRepository) {
    this.processRepository = processRepository;
  }

  @RequestMapping("/stock")
  public String handle() throws Exception {
    processProduct();
    processSize();
    processStock();
    return "Batch job has been invoked";
  }

  @RequestMapping("/out")
  public String out() throws Exception {
    processRepository.getOutput();
    return "Batch job has been invoked";
  }

  public void processProduct() {
    try (BufferedReader fileReader
             = new BufferedReader(new FileReader("product.csv"))) {
      String line = "";

      while ((line = fileReader.readLine()) != null) {
        String[] tokens = line.split(",");
        processRepository.saveProduct(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void processSize() {
    try (BufferedReader fileReader
             = new BufferedReader(new FileReader("size.csv"))) {
      String line = "";

      while ((line = fileReader.readLine()) != null) {
        String[] tokens = line.split(",");
        System.out.println(Arrays.toString(tokens));
        processRepository.saveSize(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
            Boolean.parseBoolean(tokens[2]), Boolean.parseBoolean(tokens[3]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void processStock() {
    try (BufferedReader fileReader
             = new BufferedReader(new FileReader("stock.csv"))) {
      String line = "";

      while ((line = fileReader.readLine()) != null) {
        String[] tokens = line.split(",");
        System.out.println(Arrays.toString(tokens));
        processRepository.saveStock(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
