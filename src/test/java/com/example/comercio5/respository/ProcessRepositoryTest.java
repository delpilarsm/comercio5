package com.example.comercio5.respository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProcessRepositoryTest {

  @Mock
  ProcessRepository processRepository;

  @Test
  void getOutput(){
    // Given
    Mockito.when( processRepository.getOutput()).thenReturn("5,2,1,4,3");
    // When
   String  result = processRepository.getOutput();
    // Then
    assertEquals("5,2,1,4,3",result);
  }
}