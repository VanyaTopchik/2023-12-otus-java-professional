package ru.otus;

import com.google.common.base.CaseFormat;

public class HelloOtus {
  public static void main(String[] args) {
    String text = "Hello otus!";
    String uppercaseText = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, text);
    System.out.println(uppercaseText);
  }
}
