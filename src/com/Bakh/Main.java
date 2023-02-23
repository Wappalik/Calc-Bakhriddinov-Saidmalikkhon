package com.Bakh;

import java.util.Scanner;

class Main {
//Бахриддинов Саидмаликхон 89663745271
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input: ");
        String input = scanner.nextLine();
        System.out.println(test(input));

    }


    static String test(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String[] operands = expression.split(" ");
        boolean isRoman;
        String result;
        if (operands.length != 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }

        oper = detectOperation(expression);
        if (oper == null) {
            throw new Exception("Неподдерживаемая математическая операция");
        }
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[2])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[2]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[2])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
            isRoman = false;
        } else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {

            if (arabian <= 0) {
                throw new Exception("В Римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }

    static String detectOperation(String expression) throws Exception {
        if (expression.contains("+"))
            return "+";
        else if (expression.contains("-"))
            return "-";
        else if (expression.contains("*"))
            return "*";
        else if (expression.contains("/"))
            return "/";

        else throw new Exception("строка не является математическим действием");

    }

    static int calc(int a, int b, String oper) {

        return switch (oper) {
            case "+" ->
                    a + b;

            case "-" ->
                    a - b;
            case "*" ->
                    a * b;
            default -> a / b;
        };
    }

}

class Roman { //создаем класс Roman, где римские цифры будут помещены в массив стринг, где мы будем вычислять их по индексу массива
    //Подстраиваем массив так,чтобы римская фира I подходила номеру массива 1, то есть начинаем первый индекс с обычного нуля, мы можем римские цифры писать бесконечно
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    static boolean isRoman(String val) { //создаем boolean isRoman, чтобы мы могли вычислять римские цифры по индексу массива
        for (int i = 0; i < romanArray.length; i++) { //запускаем цикл, где цикл должен остановиться при вычислении индекса
            if (val.equals(romanArray[i])) { //создаем условие что, если введенная строка находит римское число под индексом массива
                return true; //то boolean будет выдавать true и цикл остановится
            }
        }
        return false; //если пользователь введет что-то неправильно, цикл остановится и перейдет к методам исключения
    }

    static int convertToArabian(String roman) { //создаем некий конвертер с римского на арабский с помощью метода int (метод int относится к типу возвращаемого значения)
        for (int i = 0; i < romanArray.length; i++) { //запускаем цикл, где цикл должен остановиться при вычислении индекса, length используется для того, чтобы узнать длину romanArray
            if (roman.equals(romanArray[i])) { //создаем условие, если введенная то возвращает число индекса, под которой находится римская цифра
                return i;
            }
        }
        int i = -1;
        return i;
    }

    static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
