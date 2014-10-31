package challenge.ckl.database;

import com.google.gson.annotations.SerializedName;

import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
@Table("article")
public class Article extends Model {

    @Key
    @Column("title")
    @SerializedName("title")
    protected String mTitle;

    @Column("authors")
    @SerializedName("authors")
    protected String mAuthors;

    @Column("website")
    @SerializedName("website")
    protected String mWebsite;

    @Column("date")
    @SerializedName("date")
    protected String mDate;


    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String mWebPage) {
        this.mWebsite = mWebPage;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public void setAuthors(String mAuthors) {
        this.mAuthors = mAuthors;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public static CursorList<Article> getAll(String orderBy) {
        return Query.many(Article.class, "SELECT * FROM article ORDER BY " + orderBy + " ASC").get();
    }
}
