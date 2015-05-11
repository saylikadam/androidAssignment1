package datastorage;

import android.provider.BaseColumns;

/**
 * Created by poojap on 08/05/15.
 */
public class EstimationReaderContract {
    public EstimationReaderContract() {
    }

    public static abstract class IterationEntry implements BaseColumns{
        public static final String TABLE_ITERATION = "iteration";
        public static final String ITERATION_ID = "id";
        public static final String POINT = "point";
        public static final String PROJECT_VELOCITY = "project_velocity";
        public static final String ITERATION = "total_iteration";
        public static final String DATABASE_NAME = "iteration.db";
        public static final int DATABASE_VERSION = 1;
    }
}
