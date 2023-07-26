package sg.edu.rp.c346.id22012433.demorevision2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "pokemons.db";

    private static final String TABLE_POKEMON = "pokemon";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_POWER = "power";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table "pokemon"

        String createTableSql = "CREATE TABLE " + TABLE_POKEMON +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_POWER + " REAL )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);
        // Create table(s) again
        onCreate(db);
    }

    //Create, Retrieve, Update, Delete
    public void insertPokemon(String type, double power){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();

        // Store the column name as key and the description as value
        values.put(COLUMN_TYPE, type);
        // Store the column name as key and the date as value
        values.put(COLUMN_POWER, power);
        // Insert the row into the TABLE_POKEMON
        db.insert(TABLE_POKEMON, null, values);

        // Close the database connection
        db.close();
    }

    //Retrieve
    public ArrayList<Pokemon> getPokemons() {
        ArrayList<Pokemon> plist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TYPE, COLUMN_POWER};//need to be in same order
        Cursor cursor = db.query(TABLE_POKEMON, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);//need to be in same order
                String type = cursor.getString(1);
                double power = cursor.getDouble(2);
                //creating a new object of pokemon
                Pokemon obj = new Pokemon(id, type, power);
                plist.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return plist;
    }

//update
public int updatePokemon(Pokemon data){// The method takes in Pokemon object named data
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    //Value to be update, in this case "type"
    values.put(COLUMN_TYPE, data.getType());
    //Condition
    String condition = COLUMN_ID + "= ?";
    String[] args = {String.valueOf(data.getId())};

    int result = db.update(TABLE_POKEMON, values, condition, args);
    db.close();
    return result;
}

public void updatePowerByType(String type, double power){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_POWER,power);
        //condition
    String condition = COLUMN_TYPE + "= ?";
    String[] args = {type};

    int result = db.update(TABLE_POKEMON, values, condition, args);
    db.close();
}

//Delete
    public int deletePokemon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_POKEMON, condition, args);
        db.close();
        return result;
    }

}
