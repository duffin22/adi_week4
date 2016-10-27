package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

/**
 * Created by matthewtduffin on 19/07/16.
 */
public class ShoppingCursorAdapter extends CursorAdapter {
    public ShoppingCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.shopping_list_items, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);
        TextView textView4 = (TextView) view.findViewById(R.id.textView4);

        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBAssetHelper.COL_ITEM_NAME));
        String desc = cursor.getString(cursor.getColumnIndexOrThrow(DBAssetHelper.COL_DESC));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(DBAssetHelper.COL_PRICE));
        String type = cursor.getString(cursor.getColumnIndexOrThrow(DBAssetHelper.COL_TYPE));

        // Populate fields with extracted properties
        textView1.setText(name);
        textView2.setText(price);
        textView3.setText(type);
        textView4.setText(desc);

    }
}

