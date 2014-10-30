package challenge.ckl;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class ListActivity extends ActionBarActivity implements ArticleListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, "Clicked " + id, Toast.LENGTH_SHORT).show();
    }
}
