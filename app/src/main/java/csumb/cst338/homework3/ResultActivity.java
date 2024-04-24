package csumb.cst338.homework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import csumb.cst338.homework3.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity{
    Button returnButton;
    String passedString;
    private ActivityResultBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            passedString = extras.getString("encrypted message");
        }
        binding.result.setText(passedString);

        returnButton = binding.returnButton;
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
