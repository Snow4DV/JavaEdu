package itacademy.snowadv.javaedu.data;

public class CompilationRequest {
    private String clientId;
    private String clientSecret;
    private String script;
    private String stdin;
    private String language = "java";

    public CompilationRequest(String clientId, String clientSecret, String script, String stdin) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.script = script;
        this.stdin = stdin;
    }
}
