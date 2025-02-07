package com.ygkj;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumUtilTest {

  // 工作日天数
  private static final int WORK_DAYS = 23;
  // 人数
  private static final int PEOPLE_NUM = 28;

  public static void main(String[] args) {
    for (int i = 0; i < PEOPLE_NUM; i++) {
      randNums();
    }
  }

  // 打印表格
  private static void printTable(double[] firstRow, double[] secondRow, double[] thirdRow) {
    // 根据随机数，先打印第一行的数据，再打印第二行的数据，或者先打印第二行的数据，再打印第一行的数据。
    // 使用 ThreadLocalRandom 确保线程安全
    int order = ThreadLocalRandom.current().nextInt(3); // 生成0, 1, 2中的一个随机数

    switch (order) {
      case 0:
        printRow(firstRow);
        printRow(secondRow);
        printRow(thirdRow);
        break;
      case 1:
        printRow(secondRow);
        printRow(thirdRow);
        printRow(firstRow);
        break;
      case 2:
        printRow(thirdRow);
        printRow(firstRow);
        printRow(secondRow);
        break;
    }
  }

  private static void printRow(double[] row) {
    for (int i = 0; i < row.length; i++) {
      System.out.printf(" %.1f", Math.max(row[i], 0.0));
      if (i < row.length - 1) {
        System.out.print("\t"); // 列之间用制表符分隔
      }
    }
    System.out.println(); // 换行
  }

  @Test
  public static void randNums() {
    final int COLUMNS = WORK_DAYS; // 列数
    Random random = new Random();

    // 生成第一行数据
    double[] firstRow = new double[COLUMNS];
    for (int i = 0; i < COLUMNS; i++) {
      // 随机生成一个大于等于0.4且小于等于1.0的数字，保留1位小数
      firstRow[i] = Math.round((0.4 + random.nextDouble() * 0.6) * 10) / 10.0;
    }

    // 根据第一行数据生成第二行数据
    double[] secondRow = new double[COLUMNS];
    for (int i = 0; i < COLUMNS; i++) {
      // 随机生成一个小于等于  1-firstRow[i] 的数字，保留1位小数
      secondRow[i] = Math.round(random.nextDouble() * (1-firstRow[i]) * 10) / 10.0;
    }

    double[] thirdRow = new double[COLUMNS];
    for (int i = 0; i < COLUMNS; i++) {
      thirdRow[i] = 1 - firstRow[i] - secondRow[i];
    }

    // 输出结果
    printTable(firstRow, secondRow, thirdRow);
  }

}
