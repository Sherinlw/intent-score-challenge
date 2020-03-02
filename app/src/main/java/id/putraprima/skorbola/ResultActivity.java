package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView result;
    TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView resultText, messageText, scorerText;
        String result, messages, scorer;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.result);
        messageText = findViewById(R.id.winner);
        scorerText = findViewById(R.id.resultName);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            result = bundle.getString("result");
            messages = bundle.getString("messages");
            scorer = bundle.getString("scorer");

            scorerText.setText(scorer);
            messageText.setText(messages);
            resultText.setText(result);
            System.out.println(scorer);
        }
    }
}
