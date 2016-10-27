package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import ly.generalassemb.drewmahrt.cursoradapterdemo.ExampleSQLiteOpenHelper;
import ly.generalassemb.drewmahrt.cursoradapterdemo.R;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        int position = intent.getIntExtra("id",0);

        TextView itemId = (TextView) findViewById(R.id.itemId);
        itemId.setText("id: "+(position+1));


        ExampleSQLiteOpenHelper db = ExampleSQLiteOpenHelper.getInstance(getApplicationContext());
        Cursor cursor = db.getDescription(position);

        cursor.moveToFirst();
        String description = cursor.getString(0);

        TextView itemDescription = (TextView) findViewById(R.id.itemDescription);
        itemDescription.setText("description:  "+description);


    }
}
