package itacademy.snowadv.javaedu.data;

import java.util.ArrayList;
import java.util.List;

import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.ui.progress.QuickTask;

public class UserData {
    private int theoryReadAmount = 0;
    private int tasksDoneAmount = 0;
    private int dailyGoal = 3;

    public static final int THEORY_AMOUNT = 5;
    public static final int TASKS_AMOUNT = 6;

    private final Boolean[] theoryStatuses = {false,false,false,false,false};
    private final Boolean[] tasksStatuses = {false,false,false,false,false,false};

    private String lastDailyResetDate;

    private int tasksDoneToday = 0;

    List<QuickTask> quickTaskList = new ArrayList<>();


    public int getTheoryReadAmount() {
        return theoryReadAmount;
    }

    public void setTheoryReadAmount(int theoryReadAmount) {
        this.theoryReadAmount = theoryReadAmount;
    }

    public int getTasksDoneAmount() {
        return tasksDoneAmount;
    }

    public void increaseDailyTasksDoneCounter() {
        tasksDoneToday++;
        tasksDoneAmount++;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public int getDailyGoalPercent() {
        return (int) ((((double)tasksDoneToday)/ dailyGoal) * 100);
    }

    public int getTheoryPercent() {
        return (int) ((((double)theoryReadAmount)/THEORY_AMOUNT) * 100);
    }
    public int getTasksPercent() {
        return (int) ((((double)tasksDoneAmount)/TASKS_AMOUNT) * 100);
    }

    public void setTaskStatus(int n, boolean isDone) {
        tasksStatuses[n] = isDone;
        countDonePracticeTasks();
    }

    public void setTheoryStatus(int n, boolean isDone) {
        theoryStatuses[n] = isDone;
        countDoneTheoryTasks();
    }




    private void countDoneTheoryTasks() {
        theoryReadAmount = 0;
        for (Boolean bool :
                theoryStatuses) {
            if (bool) {
                theoryReadAmount++;
            }
            }
    }

    private void countDonePracticeTasks() {
        tasksDoneAmount = 0;
        for (Boolean bool :
                tasksStatuses) {
            if (bool) {
                tasksDoneAmount++;
            }
        }
    }

    public void updateDailyData(String date) {
        if (!date.equals(lastDailyResetDate)) {
            lastDailyResetDate = date;
            tasksDoneToday = 0;
        }
    }

    public UserData(String lastDailyResetDate) {
        this.lastDailyResetDate = lastDailyResetDate;
        initQuickTasks();
    }

    /**
     * Returns string resource id based on 0-4 id
     * @param id no of string resource [0-4]
     * @return String resource id
     */
    public static int getTheoryStringResByID(int id) {
        switch(id) {
            default:
            case 0:
                return R.string.theory_1;
            case 1:
                return R.string.theory_2;
            case 2:
                return R.string.theory_3;
            case 3:
                return R.string.theory_4;
            case 4:
                return R.string.theory_5;
        }
    }

    private void initQuickTasks() {
        quickTaskList.add(new QuickTask("Какая функция используется для вывода текста без" +
                " перехода на следующую строку?", "System.out.print", "System.out.println",
                "System.out.write", "System.out.writeln"));
        quickTaskList.add(new QuickTask("Какая функция используется для вывода текста с" +
                " переходом на следующую строку?", "System.out.println", "System.out.print",
                "System.out.write", "System.out.writeln"));
        quickTaskList.add(new QuickTask("Какой тип данных из нижеперечисленных позволит хранить " +
                "вещественные числа?", "float", "boolean",
                "int", "int[]"));
        quickTaskList.add(new QuickTask("Какой тип данных из нижеперечисленных позволит хранить " +
                "только целочисленные значения?", "int", "boolean",
                "float", "int[]"));
        quickTaskList.add(new QuickTask("Какой тип данных из нижеперечисленных позволит хранить " +
                "целочисленные значения?", "Несколько из перечисленных", "int",
                "char", "int[]"));
        quickTaskList.add(new QuickTask("Объект какого класса позволяет работать с вводом из " +
                "консоли?", "Scanner", "ConsoleReader",
                "InputStreamer", "NextDouble"));
        quickTaskList.add(new QuickTask("Какой метод объекта типа Scanner нужно вызвать для " +
                "ввода из консоли целого числа?", ".nextInt()", ".nextLine()",
                ".next()", ".nextRoundNumber()"));
        quickTaskList.add(new QuickTask("Какой из нижеперечисленных циклов сначала выполняется," +
                " а потом проверяет условие?", "do..while", "while",
                "for", "Несколько вариантов из вышеперечисленных"));
        quickTaskList.add(new QuickTask("Какой оператор позволяет прерывать цикл полностью?"
                , "break", "while",
                "continue", "Ни один из вышеперечисленных"));
        quickTaskList.add(new QuickTask("Какой логический оператор выдаст истину только в случае, " +
                "если оба проверямых условия верны?"
                , "&", "|",
                "^", "Ни один из вышеперечисленных"));

        quickTaskList.add(new QuickTask("Какой логический оператор выдаст ложь только в случае, " +
                "если оба проверямых условия неверны?"
                , "|", "&",
                "^", "Ни один из вышеперечисленных"));
        quickTaskList.add(new QuickTask("Какой оператор позволяет прервать текущую итерацию, " +
                "но продолжить цикл после этого?"
                , "continue", "while",
                "break", "Ни один из вышеперечисленных"));
        quickTaskList.add(new QuickTask("Какой тип данных из нижеперечисленных позволит хранить " +
                "несколько целочисленных значенияй?", "int[]", "boolean",
                "float", "int"));

    }

    public QuickTask getRandomQuickTask() {
        int n = (int) (Math.random() * quickTaskList.size());
        return quickTaskList.get(n);
    }

    public void finishQuickTask(QuickTask quickTask) {
        quickTaskList.remove(quickTask);
    }

    public Boolean[] getTheoryStatuses() {
        return theoryStatuses;
    }

    public Boolean getTheoryStatus(int position) {
        return theoryStatuses[position];
    }
    public Boolean getTaskStatus(int position) {
        return tasksStatuses[position];
    }

    private static UserData userDataInstance;
    public static void saveUserDataInstance(UserData newUserDataInstance) {
        userDataInstance = newUserDataInstance;
    }

    public static UserData getUserDataInstance() {
        return userDataInstance;
    }
}
