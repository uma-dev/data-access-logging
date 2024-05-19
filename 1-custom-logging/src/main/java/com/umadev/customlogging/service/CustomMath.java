package com.umadev.customlogging.service;

import java.util.List;
import org.apache.log4j.Logger;

public class CustomMath {

  private static Logger LOGGER = Logger.getLogger(CustomMath.class);

  public static Integer getAverage(List<Integer> l) {
    Integer size = l.size();
    // Custom logs
    if (size > 5) {
      LOGGER.info("More than 5 elements in list");
    }

    if (size == 0) {
      LOGGER.info("Empty list");
    }

    Integer sum = l.stream().mapToInt(Integer::intValue).sum();
    return size > 0 ? sum / size : 0;
  }

  public static Integer getMax(List<Integer> l) {
    Integer max = Integer.MIN_VALUE;
    for (Integer e : l) {
      max = e > max ? e : max;
    }
    return max;
  }

  public static Integer getMin(List<Integer> l) {
    Integer min = Integer.MAX_VALUE;
    for (Integer e : l) {
      min = e < min ? e : min;
    }
    return min;
  }
}
