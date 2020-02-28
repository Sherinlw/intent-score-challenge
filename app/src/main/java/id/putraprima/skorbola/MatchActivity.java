package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity{
    TextView homeName;
    TextView awayName;
    TextView awayScore;
    TextView homeScore;
    ImageView homeLogo;
    ImageView awayLogo;
    EditText homeScoreName;
    EditText awayScoreName;
    Uri uri1;
    Uri uri2;
    Bitmap bitmap1;
    Bitmap bitmap2;
    String homeTeam;
    String awayTeam;
    String returnNameHome;
    String returnNameAway;
    String result, message, scorerName;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        Bundle extras = getIntent().getExtras();
        homeTeam = extras.getString("inputHome");
        awayTeam = extras.getString("inputAway");

        if(extras != null){
            uri1 = Uri.parse(extras.getString("logoHome"));
            uri2 = Uri.parse(extras.getString("logoAway"));
            bitmap1 = null;
            bitmap2 = null;

            try{
                bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            }catch(IOException e){
                e.printStackTrace();
            }

            homeName.setText(homeTeam);
            awayName.setText(awayTeam);
            homeLogo.setImageBitmap(bitmap1);
            awayLogo.setImageBitmap(bitmap2);
        }
    }

    public void scoreHomeHandler(View view){
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }
    public void scoreAwayHandler(View view){
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                scoreHome = scoreHome + 1;
                homeScore.setText(String.valueOf(scoreHome));
                returnNameHome = data.getStringExtra("inputScore");

                String goalers = homeScoreName.getText().toString();
                homeScoreName.setText(String.valueOf(goalers + " \n "+goalers));

            }
        }else if (requestCode == 2){
            if (resultCode == RESULT_OK) {
                scoreAway = scoreAway + 1;
                awayScore.setText(String.valueOf(scoreAway));
                returnNameAway = data.getStringExtra("scorerName");

                String goalers1 = returnNameAway;
                String goalers2 = awayScoreName.getText().toString();
                awayScoreName.setText(String.valueOf(goalers1 + " \n " + goalers2));
            }
        }
    }
    //cek hasil
    public void resultHandler(View view){
        if (scoreHome > scoreAway){
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = homeTeam + " adalah Pemenang";
            scorerName = homeScoreName.getText().toString();
        } else if(scoreHome < scoreAway){
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = awayTeam + " adalah Pemenang";
            scorerName = awayScoreName.getText().toString();
        } else {
            result = String.valueOf(scoreHome) + " - " + String.valueOf(scoreAway);
            message = "DRAW";
            scorerName = "";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", result);
        intent.putExtra("messages", message);
        intent.putExtra("scorer", scorerName);
        startActivity(intent);
    }
//        Intent intent = new Intent(this, ResultActivity.class);
//        intent.putExtra("homeScore", scoreHome);
//        intent.putExtra("awayScore", scoreAway);
//        intent.putExtra("homeName", homeTeam);
//        intent.putExtra("awayName", awayTeam);
//
//        startActivity(intent);

}
