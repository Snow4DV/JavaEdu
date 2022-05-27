package itacademy.snowadv.javaedu.data;

public class TaskData {
    private String text;
    private String waitedResult;
    private String input = "nothng";
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWaitedResult() {
        return waitedResult;
    }

    public void setWaitedResult(String waitedResult) {
        this.waitedResult = waitedResult;
    }

    private TaskData(String name, String text, String waitedResult, int id) {
        this.text = text;
        this.waitedResult = waitedResult;
        this.name = name;
        this.id = id;
    }

    private TaskData(String name, String text, String waitedResult, String input, int id) {
        this.text = text;
        this.waitedResult = waitedResult;
        this.input = input;
        this.name = name;
        this.id = id;
    }

    public static TaskData getTaskByID(int id)  {
        switch(id) {
            case 0:
                return new TaskData("Задача 1. Привет, мир!",
                        "Напишите программу, которая выводит \"Hello world!\" (без кавычек и переносов строк) в консоль с" +
                        " помощью System.out.print, передав в качестве параметра нужную вам строку.", "Hello world!", 0);
            case 1:
                return new TaskData("Задача 2. Работа с переменными.",
                        "Сохраните следующие числа для нахождения суммы в переменные " +
                        "целочисленного типа: 1 и 2. Выведите их сумму.", "3", 1);
            case 2:
                return new TaskData("Задача 3. Сумма двух чисел",
                        "Напишите программу для нахождения суммы двух целых чисел.\n" +
                        "На входе: два числа, разделённых пробелом.\nНа выходе: их сумма",
                        "45", "10 35", 2);
            case 4:
                return new TaskData("Задача 5. Работа с логическими операциями.",
                        "Напишите программу, которая определяет, светло ли в комнате. " +
                        "\nВ качестве входных данных предоставляется следующая информация: включен " +
                        "ли свет в комнате и светло ли на улице. Если условие верно, передайте 1, " +
                        "в противном случае - 0. Входные значения разделяются пробелом. Программа " +
                        "должна выполняться в цикле до тех пор, пока ей не будет передана цифра \"" +
                        "2\" в качестве входных данных. \nВыходные значения: 1 - если светло, 0 -" +
                        "если темно. (без символов переноса строки или пр.)", "1011",
                        "1 0 0 0 0 1 1 0 2", 4);
            case 3:
                return new TaskData("Задача 4. Работа с циклом.",
                        "Выведите все числа от 0 до введённого пользователем целого" +
                        " числа без пробелов.\nПример ввода: 7\nПример вывода: \"0123456\" " +
                        "(без кавычек и пробелов)", "012345678910", "10", 3);
            case 5:
                return new TaskData("Задача 6. Работа с циклом 2.",
                        "Создайте массив целых чисел, размер которого будет задан пользователем." +
                                " Найдите сумму всех введённых чисел.\nПример ввода: 3 1 2 3\nПример" +
                                " вывода: 6 (без переноса на след. строку, пробелов)", "9",
                        "6 1 1 1 5 1 0", 5);
        }
        return null;
    }
}
