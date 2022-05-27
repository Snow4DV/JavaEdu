package itacademy.snowadv.javaedu.ui.progress;

import java.util.ArrayList;
import java.util.List;

public class QuickTask {
    private String text;
    private List<String> choices = new ArrayList<>();
    private String rightAnswer;

    public QuickTask(String text, String rightAnswer, String wrongAnswer1, String wrongAnswer2,
                     String wrongAnswer3) {
        this.text = text;
        this.rightAnswer = rightAnswer;
        choices.add(wrongAnswer1);
        choices.add(wrongAnswer2);
        choices.add(wrongAnswer3);
        choices.add(rightAnswer);
    }

    public String getText() {
        return text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
