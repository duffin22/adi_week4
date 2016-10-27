package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getCanonicalName();
    ListView shoppingListView;
    ShoppingCursorAdapter adapty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBAssetHelper db = DBAssetHelper.getInstance(this);

        shoppingListView = (ListView) findViewById(R.id.shopping_list_view);

        db.insert("Banana", "Long and yellow and kind of bent. You have to peel it to get to the yummy goodness inside.","$3.95","Fruit");
        db.insert("Lettuce", "Green and spherical. The layers can be peeled or you can just chop the whole thing! Very refreshing and enjoyed by rabbits.","$1.95","Salad");
        db.insert("Rump Steak", "Delicious cow-based food. Tastes great with beer.","$8.95","Meat");
        db.insert("Bacon", "Yum. Yum. Yum. Mmmmmmmmm Bacon.","$5.45","Meat");


        adapty = new ShoppingCursorAdapter(this,db.getData(),0);

        shoppingListView.setAdapter(adapty);

    }
}
