package com.epam.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task10 {

    /**
     * Ввести число с консоли.
     * С помощью битовых, логических и арифметических операций определить, является ли указанное значение степенью двойки.
     *
     * Формат входных данных:
     * value - целое число (Integer.MIN_VALUE <= value <= Integer.MAX_VALUE)
     *
     * Формат выходных данных:
     * YES - число является степенью двойки
     * NO - число не является степенью двойки
     *
     * ---------------------------------------------------------------------------------------------------
     * Примеры выполнения задания:
     *
     * Входные данные:
     * -128
     *
     * Выходные данные:
     * NO
     *
     *
     * Входные данные:
     * 256
     *
     * Выходные данные:
     * YES
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());

        if ((number > 0 && (number & (number - 1)) == 0)) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }
}
