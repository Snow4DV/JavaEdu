package itacademy.snowadv.javaedu.data;

public class Test {
    private String input;
    private String waitedResult;

    public Test(String input, String waitedResult) {
        this.input = input;
        this.waitedResult = waitedResult;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getWaitedResult() {
        return waitedResult;
    }

    public void setWaitedResult(String waitedResult) {
        this.waitedResult = waitedResult;
    }
}
