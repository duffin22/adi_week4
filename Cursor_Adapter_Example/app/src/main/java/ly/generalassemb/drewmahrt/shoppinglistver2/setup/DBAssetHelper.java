package ly.generalassemb.drewmahrt.shoppinglistver2.setup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by drewmahrt on 12/29/15.
 */
public class DBAssetHelper extends SQLiteOpenHelper {

    private static final String TAG = DBAssetHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String SHOPPING_LIST_TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_DESC = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String[] SHOPPING_COLUMNS = {COL_ID, COL_ITEM_NAME, COL_DESC, COL_PRICE, COL_PRICE, COL_TYPE};

    private static final String CREATE_SHOPPING_LIST_TABLE =
            "create table " + SHOPPING_LIST_TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEM_NAME + " TEXT,"
                    + COL_DESC + " TEXT," + COL_PRICE + " TEXT," + COL_TYPE + " TEXT);";


    private static DBAssetHelper instance;

    private DBAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_SHOPPING_LIST_TABLE);
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SHOPPING_LIST_TABLE_NAME);
        this.onCreate(db);
    }


    public void insert(String name, String desc, String price, String type){
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME, name);
        values.put(COL_DESC, desc);
        values.put(COL_PRICE, price);
        values.put(COL_TYPE, type);

        // Insert the row into the games table
        db.insert(SHOPPING_LIST_TABLE_NAME, null, values);
    }

    public Cursor getData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+SHOPPING_LIST_TABLE_NAME+";", null);
        return res;
    }


    public static DBAssetHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBAssetHelper(context);
        }
        return instance;
    }
}
