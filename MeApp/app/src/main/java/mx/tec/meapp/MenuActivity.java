package mx.tec.meapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    TextView hi;
    TextView hobbyTextView;
    DBHelper db;
    String name;

    private static final int MyHobbies_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intentote = getIntent();
        name = intentote.getStringExtra("name");
        hi = findViewById(R.id.saludo);
        hobbyTextView = findViewById(R.id.textViewHobby);
        String Hi = "Hi " + name;
        hi.setText(Hi);
        db = new DBHelper(this);
        String hobby = db.find(name);
        hobbyTextView.setText(hobby);

    }
    public void hobby(View v){


        Intent intentito = new Intent(this, MyHobbies.class);
        intentito.putExtra("name", name);
        startActivityForResult(intentito, MyHobbies_CODE);

    }
    public void friends(View v){


        Intent intentito = new Intent(this, MyFriends.class);


        startActivityForResult(intentito, 2);

    }
    public void message(View v){


        Intent intentito = new Intent(this, Message.class);


        startActivityForResult(intentito, 3);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MyHobbies_CODE && resultCode == Activity.RESULT_OK){

            String hobby = db.find(name);
            hobbyTextView.setText(hobby);
        }
    }

}