package itacademy.snowadv.javaedu.ui.task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.noties.markwon.Markwon;
import itacademy.snowadv.javaedu.R;
import itacademy.snowadv.javaedu.api.CompilationApiInterface;
import itacademy.snowadv.javaedu.data.CompilationRequest;
import itacademy.snowadv.javaedu.data.CompilationResponse;
import itacademy.snowadv.javaedu.data.TaskData;
import itacademy.snowadv.javaedu.data.UserData;
import itacademy.snowadv.javaedu.databinding.ActivityTaskBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.jdoodle.com/";
    private TaskData task;
    private ActivityTaskBinding binding;
    private int taskID;


    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private CompilationApiInterface api = retrofit.create(CompilationApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        taskID = getIntent().getIntExtra("taskID", 0);
        task = TaskData.getTaskByID(taskID);
        binding.taskDescription.setText(task.getText());
        binding.taskName.setText(task.getName());
        setTitle(R.string.task_title);
        binding.compileButton.setOnClickListener(v -> {
           compile(binding.codeEditText.getText().toString(), task.getWaitedResult(),
                   task.getInput());
        });
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(TaskActivity.this,
                new String[]{Manifest.permission.INTERNET},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Разрешение на использование интернета получено!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TaskActivity.this, "Интернет нужен для компиляции " +
                            "вашего приложения. Пожалуйста, откройте задачу еще раз и разрешите приложению " +
                            "доступ к интернету.", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void displayCompilationResult(CompilationResponse response, boolean isResultValid) {
        LayoutInflater inflater= LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.compilation_dialog, null);

        TextView textview= view.findViewById(R.id.textmsg);
        final Markwon markwon = Markwon.create(this);
        final Spanned markdown = markwon.toMarkdown(response.toMarkdownString(isResultValid));
        markwon.setParsedMarkdown(textview, markdown);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Компиляция завершена");
        alertDialog.setView(view);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onTaskFinish();
            }
        });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onTaskFinish();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }


    private void onTaskFinish() {
        finish();
        UserData.getUserDataInstance().setTaskStatus(taskID, true);
        UserData.getUserDataInstance().increaseDailyTasksDoneCounter();
    }

    private void compile(String script, String waitedResult, String input) {
        try {
            api.compile(new CompilationRequest(getString(R.string.client_id), getString(R.string.client_secret), script,
                    input)).enqueue(new Callback<CompilationResponse>() {
                @Override
                public void onResponse(Call<CompilationResponse> call, Response<CompilationResponse> response) {
                    try {
                        boolean isValid = response.body().getOutput().equals(waitedResult);
                        displayCompilationResult(response.body(), isValid);

                    } catch (NullPointerException ex) {
                        Toast.makeText(TaskActivity.this, "Ошибка при получении результата " +
                                "компиляции", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<CompilationResponse> call, Throwable t) {
                    Toast.makeText(TaskActivity.this, "Ошибка компиляции. Нет доступа к " +
                                    "серверу.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        } catch(SecurityException ex) {
            Toast.makeText(this, "Для работы компилятора нужен интернет.", Toast.LENGTH_LONG).show();
            requestInternetPermission();
        }
    }
}