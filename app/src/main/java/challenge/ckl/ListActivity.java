package challenge.ckl;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;

import challenge.ckl.database.Article;
import challenge.ckl.services.ArticleContent;
import challenge.ckl.services.ArticlesParser;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.ModelList;


public class ListActivity extends ActionBarActivity implements ArticleListFragment.OnFragmentInteractionListener {

    protected ArticleListFragment mArticleListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);

        mArticleListFragment = (ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list_fragment);

        loadContent();
        refreshList();
    }

    private void loadContent() {
        ArticlesParser.get(this, new FutureCallback<ArticleContent>() {
            @Override
            public void onCompleted(Exception e, ArticleContent result) {


                if (result != null && e == null) {
                    ModelList<Article> modelList = new ModelList<Article>(result);

                    modelList.saveAllAsync(new ModelList.OnAllSavedCallback() {
                        @Override
                        public void onAllSaved() {
                            ListActivity.this.refreshList();
                        }
                    });
                } else {
                    Log.e(":/", ":( - " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void noContent() {
        mArticleListFragment.setEmptyText("No Content Loaded ;(");
    }

    private void refreshList() {
        CursorList<Article> articles = Article.getAll();
        if (articles.size() > 0) {
            mArticleListFragment.updateList(articles);
        } else {
            ListActivity.this.noContent();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, "Clicked " + id, Toast.LENGTH_SHORT).show();
    }
}
