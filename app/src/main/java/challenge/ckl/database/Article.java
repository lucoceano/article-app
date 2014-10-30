package challenge.ckl.database;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
public class Article extends Model {

    @Key
    @Column("title")
    protected String mTitle;
    
    @Column("authors")
    protected String mAuthors;

    @Column("webPage")
    protected String mWebPage;

    @Column("date")
    protected String mDate;


    public String getWebPage() {
        return mWebPage;
    }

    public void setWebPage(String mWebPage) {
        this.mWebPage = mWebPage;
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
}
