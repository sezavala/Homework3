package csumb.cst338.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import csumb.cst338.homework3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Button createSecret;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createSecret = binding.secretButton;
        createSecret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText secretText = binding.secretText;
                String secret = secretText.getText().toString();
                EditText userKey = binding.key;
                String key = userKey.getText().toString();
                String encryptedMessage = encrypt(secret, key);

                Bundle extraInfo = new Bundle();
                extraInfo.putString("encrypted message", encryptedMessage);

                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                i.putExtras(extraInfo);
                startActivity(i);
            }
        });
    }
    private String encrypt(String message, String key){
        // Check if empty
        if(message.isEmpty() || key.isEmpty()){
            return "";
        }

        // Create key array
        StringBuffer sb = new StringBuffer();
        ArrayList<Integer> keyArr = new ArrayList<>();
        key = key.toLowerCase();
        for (int i = 0; i < key.length(); i++) {
            int curr = key.charAt(i) - 97;
            if(key.charAt(i) != ' '){
                keyArr.add(curr);
            }
        }

        // Using key array, shift the message
        int keyIndex = 0;
        for (int i = 0; i < message.length(); i++) {
            char currChar = message.charAt(i);
            if(currChar >= 97 && currChar <= 122){
                int newNum = (((currChar - 97) + keyArr.get(keyIndex)) % 26);
                keyIndex = (keyIndex + 1) % keyArr.size();
                char curr = (char) (97 + newNum);
                sb.append(curr);
            } else if(currChar >= 65 && message.charAt(keyIndex) <= 90){
                int newNum = (((currChar - 65) + keyArr.get(keyIndex)) % 26);
                keyIndex = (keyIndex + 1) % keyArr.size();
                char curr = (char) (65 + newNum);
                sb.append(curr);
            } else{
                sb.append(currChar);
            }
        }
        return sb.toString();
    }
}
