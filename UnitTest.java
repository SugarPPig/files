package com.ygkj;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import cn.hutool.core.util.NumberUtil;

/**
 * @author liaotian
 * @date 2021/8/7 21:06
 */
public class UnitTest {

  // 工作日天数
  private static final int WORK_DAYS = 22;
  // 人数
  private static final int PEOPLE_NUM = 28;

  public static double calculateAverage(double[] array) {
    if (array == null || array.length == 0) {
      throw new IllegalArgumentException("Array must not be null or empty");
    }

    double sum = 0.0;
    for (double value : array) {
      sum += value;
    }
    return sum / array.length;
  }

  public static void main(String[] args) {
    for (int i = 0; i < PEOPLE_NUM; i++) {
      randNums();
      System.out.println(); // 换行
    }
  }

  // 打印表格
  private static void printTable(double[] firstRow, double[] secondRow) {
    // 根据随机布尔值，先打印第一行的数据，再打印第二行的数据，或者先打印第二行的数据，再打印第一行的数据。
    // 使用 ThreadLocalRandom 确保线程安全
    if (ThreadLocalRandom.current().nextBoolean()) {
      //// 计算整行平均值，保留一位小数
      //double average1 = calculateAverage(firstRow);
      //System.out.printf("%.1f", average1);
      //System.out.print("\t"); // 列之间用制表符分隔

      for (int i = 0; i < firstRow.length; i++) {
        System.out.printf(" %.1f", firstRow[i]);
        if (i < firstRow.length - 1) {
          System.out.print("\t"); // 列之间用制表符分隔
        }
      }
      System.out.println(); // 换行

      // 计算整行平均值，保留一位小数
      //double average2 = calculateAverage(secondRow);
      //System.out.printf("%.1f", average2);
      //System.out.print("\t"); // 列之间用制表符分隔

      for (int i = 0; i < secondRow.length; i++) {
        System.out.printf(" %.1f", secondRow[i]);
        if (i < secondRow.length - 1) {
          System.out.print("\t"); // 列之间用制表符分隔
        }
      }
    } else {
      //double average2 = calculateAverage(secondRow);
      //System.out.printf("%.1f", average2);
      //System.out.print("\t"); // 列之间用制表符分隔

      for (int i = 0; i < secondRow.length; i++) {
        System.out.printf(" %.1f", secondRow[i]);
        if (i < secondRow.length - 1) {
          System.out.print("\t"); // 列之间用制表符分隔
        }
      }
      System.out.println(); // 换行

      //double average1 = calculateAverage(firstRow);
      //System.out.printf("%.1f", average1);
      //System.out.print("\t"); // 列之间用制表符分隔

      for (int i = 0; i < firstRow.length; i++) {
        System.out.printf(" %.1f", firstRow[i]);
        if (i < firstRow.length - 1) {
          System.out.print("\t");
        }
      }
    }

  }

  @Test
  public static void randNums() {
    final int COLUMNS = WORK_DAYS; // 列数
    Random random = new Random();

    // 生成第一行数据
    double[] firstRow = new double[COLUMNS];
    for (int i = 0; i < COLUMNS; i++) {
      // 随机生成一个大于等于0.6且小于等于1.0的数字，保留1位小数
      firstRow[i] = Math.round((0.6 + random.nextDouble() * 0.4) * 10) / 10.0;
    }

    // 根据第一行数据生成第二行数据
    double[] secondRow = new double[COLUMNS];
    for (int i = 0; i < COLUMNS; i++) {
      secondRow[i] = 1.0 - firstRow[i];
    }

    // 输出结果
    printTable(firstRow, secondRow);
  }

}
