package challenge.ckl;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;

import challenge.ckl.database.Article;
import challenge.ckl.services.ArticleContent;
import challenge.ckl.services.ArticlesParser;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.ModelList;


public class ListActivity extends ActionBarActivity implements ArticleListFragment.OnFragmentInteractionListener {

    protected ArticleListFragment mArticleListFragment;
    protected String mOrderBy = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        toolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(toolbar);

        mArticleListFragment = (ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list_fragment);

        loadContent();
        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spinner_menu, menu);

        final String[] strings = getResources().getStringArray(R.array.filter_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getSupportActionBar().getThemedContext(),
                R.layout.actionbar_spinner,
                android.R.id.text1, strings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setAdapter(adapter); // set the adapter to provide layout of rows and content
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mOrderBy = strings[position].toLowerCase();
                ListActivity.this.refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return true;
    }

    /**
     * Try to download the content.
     */
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

    /**
     * If there is no content or no internet set the empty message.
     */
    private void noContent() {
        mArticleListFragment.setEmptyText("No Content Loaded ;(");
    }

    /**
     * Verify if there is content
     * If so refresh the list,
     * Otherwise call noContent.
     */
    private void refreshList() {
        CursorList<Article> articles = Article.getAll(mOrderBy);
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
