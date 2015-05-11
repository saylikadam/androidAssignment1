package datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poojap on 08/05/15.
 */
public class EstimationReader extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table "
            + EstimationReaderContract.IterationEntry.TABLE_ITERATION
            + "(" + EstimationReaderContract.IterationEntry.ITERATION_ID
            + " integer primary key autoincrement, " + EstimationReaderContract.IterationEntry.POINT
            + " text not null, "+ EstimationReaderContract.IterationEntry.PROJECT_VELOCITY + " text not null, "+
            EstimationReaderContract.IterationEntry.ITERATION+ " text not null "+")";

    public EstimationReader(Context context) {
        super(context, EstimationReaderContract.IterationEntry.DATABASE_NAME, null, EstimationReaderContract.IterationEntry.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }


}
