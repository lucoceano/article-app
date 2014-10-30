package challenge.ckl.services;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
public class ArticlesParser {

    private static String sUrl = "http://ckl.io/challenge/";

    public static void get(Context context, FutureCallback<ArticleContent> callback) {
        Ion.with(context)
                .load(sUrl)
                .as(new TypeToken<ArticleContent>() {
                })
                .setCallback(callback);
    }
}
