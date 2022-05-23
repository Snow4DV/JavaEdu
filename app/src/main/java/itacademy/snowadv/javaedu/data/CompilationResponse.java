package itacademy.snowadv.javaedu.data;

public class CompilationResponse {
    private String output;
    private int statusCode;
    private String memory;
    private String cpuTime;

    public String getOutput() {
        return output;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMemory() {
        return memory;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public String toMarkdownString(boolean isResultCorrect) {
        return "# Вывод [" + (isResultCorrect ? "корректный" : "некорректный") + "]:\n```\n" +
                (isResultCorrect ? output : "[Скрыто]") + "\n```\n # Статистика:\nПамять: " + memory + " байт\n <br> Время: " + cpuTime
                + " секунд";
    }
}
