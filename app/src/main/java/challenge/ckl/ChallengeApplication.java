package challenge.ckl;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
public class ChallengeApplication extends Application {

    public static final String FONT_ASSET_PATH = "fonts/Roboto-Regular.ttf";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(FONT_ASSET_PATH, R.attr.fontPath);


        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());

        sprinkles.addMigration(new Migration() {
            @Override
            protected void onPreMigrate() {
                // do nothing
            }

            @Override
            protected void doMigration(SQLiteDatabase db) {
                db.execSQL(
                        "CREATE TABLE article (" +
                                "title PRIMARY KEY," +
                                "authors TEXT," +
                                "date TEXT," +
                                "website TEXT" +
                                ")"
                );
            }

            @Override
            protected void onPostMigrate() {
                // do nothing
            }
        });
    }
}
