package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    EditText nameScorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        nameScorer = findViewById(R.id.name);
    }

    public void backToMatch(View view) {
        String name_scorer = nameScorer.getText().toString();
        Intent resultIntent = new Intent();

        resultIntent.putExtra("inputScore", name_scorer);
        setResult(MatchActivity.RESULT_OK, resultIntent);
        finish();
    }


}
