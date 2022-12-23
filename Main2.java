import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String exp = scanner.nextLine();
        String result = calcExpression(exp);
        System.out.println(result);
    }

    static String calcExpression(String exp) throws Exception {
        String[] data;
        char action;

        if (exp.contains("+")) {
            data = exp.split("\\+");
            action = '+';
        } else if (exp.contains("-")) {
            data = exp.split("-");
            action = '-';
        } else if (exp.contains("*")) {
            data = exp.split("\\*");
            action = '*';
        } else if (exp.contains("/")) {
            data = exp.split("/");
            action = '/';
        } else {
            throw new Exception("Ошибка");
        }
        checkValid(data);
        checkAction(action, data);
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }
        String result = findResult(action, data);
        return result;
    }
    static String findResult(char action, String[] data){
        String result="";
        if (action == '+') {
            result = cutString(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            for (int i = 0; i < multiplier; i++) {
                result += data[0];
            }
            result = cutString(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                result = data[0];
            } else {
                result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
            }
            result = cutString(result);

        } else {
            int newLen = data[0].length() / Integer.parseInt(data[1]);
            result = data[0].substring(0, newLen);
            result = cutString(result);
        }
        return "\""+result+"\"";
    }
    static void checkAction(char action, String[] data) throws Exception {
        if (action == '*' || action == '/') {
            if (data[1].contains("\"")) throw new Exception("Ошибка: не верный ввод данных");
        }
    }

    static void checkValid(String[] data) throws Exception {
        for (int i = 0; i < data.length; i++) {
            if (data[i].chars().allMatch(Character::isDigit)) { // если число
                if (Integer.parseInt(data[i]) < 1 || Integer.parseInt(data[i]) > 10 ) {
                    throw new Exception("Ошибка число должно быть от 1 до 10!");
                }
            } else { // если строка
                if (data[i].length() > 10) {
                    throw new Exception("Ошибка строка не может быть больше 10ти символов!");
                }
            }

        }
    }

    static String cutString(String str) {
        if (str.length() > 40) {
            str = str.substring(0, 40) + "...";
        }
        return str;
    }
}