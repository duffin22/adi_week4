package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.database.Cursor;

/**
 * Created by matthewtduffin on 22/07/16.
 */
public class GroceryItem {

    int id;
    String name;
    String description;

    public GroceryItem(int id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;

    }

    @Override
    public String toString() {
        return this.name;
    }
}
