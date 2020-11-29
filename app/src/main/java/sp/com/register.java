package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private EditText nametext;
    private EditText admissiontext;
    private EditText emailtext;
    private Button buttonsave;
    private BakeryDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nametext = findViewById(R.id.nametext);
        admissiontext = findViewById(R.id.admissiontext);
        emailtext = findViewById(R.id.emailtext);
        buttonsave = findViewById(R.id.buttonsave);
        buttonsave.setOnClickListener(onSave);

        db = new BakeryDB(this);


    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        db.insert(nametext.getText().toString(),emailtext.getText().toString(),admissiontext.getText().toString());
            Intent intent = new Intent(view.getContext(), Bakerylist.class);
            startActivity(intent);
            Toast.makeText(register.this, "Registered", Toast.LENGTH_SHORT).show();
            finish();
        }
    };
}
