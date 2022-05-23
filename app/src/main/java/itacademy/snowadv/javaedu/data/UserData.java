package itacademy.snowadv.javaedu.data;

import itacademy.snowadv.javaedu.R;

public class UserData {
    private int theoryReadAmount = 0;
    private int tasksDoneAmount = 0;
    private int sessionGoal = 3;

    public static final int THEORY_AMOUNT = 5;
    public static final int TASKS_AMOUNT = 5;

    private final Boolean[] theoryStatuses = {false,false,false,false,false};
    private final Boolean[] tasksStatuses = {false,false,false,false,false};


    public int getTheoryReadAmount() {
        return theoryReadAmount;
    }

    public void setTheoryReadAmount(int theoryReadAmount) {
        this.theoryReadAmount = theoryReadAmount;
    }

    public int getTasksDoneAmount() {
        return tasksDoneAmount;
    }

    public void setTasksDoneAmount(int tasksDoneAmount) {
        this.tasksDoneAmount = tasksDoneAmount;
    }

    public int getSessionGoal() {
        return sessionGoal;
    }

    public void setSessionGoal(int sessionGoal) {
        this.sessionGoal = sessionGoal;
    }

    public int getDailyGoalPercent() {
        return (int) ((((double)tasksDoneAmount)/ sessionGoal) * 100);
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
