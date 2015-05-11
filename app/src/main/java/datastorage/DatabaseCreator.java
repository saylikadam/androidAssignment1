package datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poojap on 08/05/15.
 */
public class DatabaseCreator extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table "
            + DatabaseCreationContract.IterationEntry.TABLE_ITERATION
            + "(" + DatabaseCreationContract.IterationEntry.ITERATION_ID
            + " integer primary key autoincrement, " + DatabaseCreationContract.IterationEntry.POINT
            + " text not null, "+ DatabaseCreationContract.IterationEntry.PROJECT_VELOCITY + " text not null, "+
            DatabaseCreationContract.IterationEntry.ITERATION+ " text not null "+")";

    public DatabaseCreator(Context context) {
        super(context, DatabaseCreationContract.IterationEntry.DATABASE_NAME, null, DatabaseCreationContract.IterationEntry.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }


}
