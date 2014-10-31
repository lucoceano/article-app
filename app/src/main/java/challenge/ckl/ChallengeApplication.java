package challenge.ckl;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
public class ChallengeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

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
