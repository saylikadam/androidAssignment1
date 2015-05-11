package datastorage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by saylik on 11/05/15.
 */
public class DataProcessor {

    private final DatabaseCreator databaseCreator;

    public DataProcessor(DatabaseCreator databaseCreator) {
        this.databaseCreator = databaseCreator;
    }

    public void putData(int point ,int velocity,int iteration){
        SQLiteDatabase db = databaseCreator.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseCreationContract.IterationEntry.POINT,point);
        values.put(DatabaseCreationContract.IterationEntry.PROJECT_VELOCITY,velocity);
        values.put(DatabaseCreationContract.IterationEntry.ITERATION,iteration);
        db.insert(DatabaseCreationContract.IterationEntry.TABLE_ITERATION,null,values);
        db.close();
    }
}
