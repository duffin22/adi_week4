package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import ly.generalassemb.drewmahrt.cursoradapterdemo.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        ListView listView = (ListView)findViewById(R.id.example_list_view);

        ExampleSQLiteOpenHelper helper = ExampleSQLiteOpenHelper.getInstance(MainActivity.this);

        List<GroceryItem> items = helper.getExampleList();

        final ArrayAdapter<GroceryItem> simpleAdapter1 = new ArrayAdapter<GroceryItem>(MainActivity.this, android.R.layout.simple_list_item_1, items);

        //SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_list_item_1,cursor,new String[]{ExampleSQLiteOpenHelper.COL_ITEM_NAME},new int[]{android.R.id.text1},0);

        listView.setAdapter(simpleAdapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(MainActivity.this,DetailActivity.class);
                //cursor.moveToPosition(position);
                intent.putExtra("id",position);
                startActivity(intent);
            }
        });

    }


}
