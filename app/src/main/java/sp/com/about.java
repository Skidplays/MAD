package sp.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class about extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView useradmission;
    private BakeryDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        name = findViewById(R.id.aboutname);
        email = findViewById(R.id.aboutemail);
        useradmission = findViewById(R.id.aboutadmission);
        db = new BakeryDB(this);
        Cursor cursor = db.getAll();
        if(cursor.getCount() !=0) {
            cursor.moveToFirst();
            name.setText(cursor.getString(1));
            email.setText(cursor.getString(2));
            useradmission.setText(cursor.getString(3));
        }


    }

}
