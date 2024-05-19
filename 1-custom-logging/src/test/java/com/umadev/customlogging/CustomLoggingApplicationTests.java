package com.umadev.customlogging;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.umadev.customlogging.service.CustomMath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomLoggingApplicationTests {

  private final List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
  private final List<Integer> emptyList = new ArrayList<Integer>();

  @Test
  void assertAverageIsWorking() {
    Integer averageExpected = 4;
    Integer averageObtained = CustomMath.getAverage(list);
    assertEquals(averageExpected, averageObtained);
  }

  @Test
  void assertMaxIsWorking() {
    Integer maxExpected = 7;
    Integer maxObtained = CustomMath.getMax(list);
    assertEquals(maxExpected, maxObtained);
  }

  @Test
  void assertMinIsWorking() {
    Integer minExpected = 1;
    Integer minObtained = CustomMath.getMin(list);
    assertEquals(minExpected, minObtained);
  }

  @Test
  void assertEmptyListAverageIsZero() {
    Integer averageExpected = 0;
    Integer averageObtained = CustomMath.getAverage(emptyList);
    assertEquals(averageExpected, averageObtained);
  }
}
