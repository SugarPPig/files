package com.ygkj;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCusNumUtilTest {
  // 工作日天数
  private static final int WORK_DAYS = 22;
  // 人数
  private static final int PEOPLE_NUM = 30;
  // 每列的行数
  private static final int ROWS = 7;

  public static void main(String[] args) {
    for (int i = 0; i < PEOPLE_NUM; i++) {
      randNums();
    }
  }

  // 打印表格
  private static void printTable(double[][] rows) {
    // 使用 ThreadLocalRandom 确保线程安全，生成 0 到 ROWS - 1 的全排列，以随机打印行的顺序
    int[] order = new int[ROWS];
    for (int i = 0; i < ROWS; i++) {
      order[i] = i;
    }
    // 打乱顺序
    for (int i = order.length - 1; i > 0; i--) {
      int j = ThreadLocalRandom.current().nextInt(i + 1);
      int temp = order[i];
      order[i] = order[j];
      order[j] = temp;
    }

    for (int idx : order) {
      printRow(rows[idx]);
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
    double[][] rows = new double[ROWS][COLUMNS];

    // 生成前 ROWS - 1 行数据
    for (int i = 0; i < ROWS - 1; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        double remaining = 1;
        // 计算当前列前面已经生成的数字之和
        for (int k = 0; k < i; k++) {
          remaining -= rows[k][j];
        }
        if (i == ROWS - 2) {
          // 倒数第二行，直接使用剩余的值
          rows[i][j] = Math.round(remaining * 10) / 10.0;
        } else {
          // 随机生成一个小于等于 remaining 的数字，保留1位小数
          rows[i][j] = Math.round(random.nextDouble() * remaining * 10) / 10.0;
        }
      }
    }

    // 计算最后一行的数据，确保每列之和为1
    for (int j = 0; j < COLUMNS; j++) {
      double sum = 0;
      for (int i = 0; i < ROWS - 1; i++) {
        sum += rows[i][j];
      }
      rows[ROWS - 1][j] = Math.round((1 - sum) * 10) / 10.0;
    }

    // 输出结果
    printTable(rows);
  }
}