package sp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Bakerylist extends AppCompatActivity {
    private ListView list;
    private String BakeryName[] = {"Breadtalk", "Fourleaves", "Swee Heng", "BREAD CRÉATEUR"};
    private Integer drawables[] = {R.drawable.breadtalk, R.drawable.fourleaves, R.drawable.sweeheng, R.drawable.breadcreateur};
    private String addr[] = {"3155 Commonwealth Ave W, #01 - 01 / 02, Singapore 129588",
            "63 Jurong West Central 3, JP2, #01-42, Jurong Point Shopping Centre, Singapore 648331",
            "252 Jurong East Street 24, Singapore 600252",
            "182 Jln Jurong Kechil, #01-51 The Hillford, Singapore 596152"};
    private String description[] = {
    "Providing wide range of confectioneries by applying latest Japanese technology",
    "Internationally recognize bakery brand over the last 16 years",
    "Opened in 1989 with stores all over Singapore at convenient locations",
    "Quality fresh artisan bakes at affordable price"};
    private double lat[] ={1.314963, 1.338782, 1.343011, 1.343565};
    private double lon[] ={103.764734, 103.705081, 103.737547, 103.769869};
    private Cursor model = null;
    private BakeryDB helper = null;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakerylist);

        builder = new AlertDialog.Builder(this);

        list = (ListView) findViewById(R.id.bakerylist);

        helper = new BakeryDB(this);
        model = helper.getAll();

        final bakeryAdapter bakeryAdapter = new bakeryAdapter(Bakerylist.this, BakeryName, drawables, addr, description, lat, lon);
        list.setAdapter(bakeryAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String combinestr = lat[i]+ ", " + lon[i];
                //Toast.makeText(Bakerylist.this,combinestr, Toast.LENGTH_SHORT).show(); //DELETE LATER

                Intent intent = new Intent(Bakerylist.this, bakerymap.class);
                intent.putExtra("lat", lat[i]);
                intent.putExtra("lon", lon[i]);
                intent.putExtra("BakeryName", BakeryName[i]);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.register:
                Intent intent = new Intent(this,register.class);
                startActivity(intent);
                finish();
                break;

            case R.id.about:
                model = helper.getAll();
                if(model.getCount() ==0){
                    dialogmessage("You have not registered");
                }else {
                    intent = new Intent(this, about.class);
                    startActivity(intent);
                }
                break;

            case R.id.unregister:
                helper.delete();
                model = helper.getAll();
                closeOptionsMenu();
                Toast.makeText(this, "Unregistered", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(model.getCount() == 0){
            menu.findItem(R.id.unregister).setVisible(false);
            menu.findItem(R.id.register).setVisible(true);
        } else{
            menu.findItem(R.id.register).setVisible(false);
            menu.findItem(R.id.unregister).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void dialogmessage(String message){

        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Alert");
        alert.show();
    }

    @Override
    protected void onDestroy() {
        helper.close();

        super.onDestroy();
    }
}
