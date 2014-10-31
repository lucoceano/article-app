package challenge.ckl;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import challenge.ckl.adapter.ArticleAdapter;
import challenge.ckl.database.Article;
import se.emilsjolander.sprinkles.CursorList;

public class ArticleListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;
    private ArticleAdapter mListAdapter;

    public ArticleListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListAdapter = new ArticleAdapter(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        mListView = (AbsListView) view.findViewById(android.R.id.list);
        mListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(position + "");
        }
    }

    /**
     * Set the empty textView.
     *
     * @param emptyText String
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * Set the adapter for the list if there isn' one
     * and reload the content of the adapter
     *
     * @param newContent CursorList<Article>
     */
    public void updateList(CursorList<Article> newContent) {

        mListAdapter.setContent(newContent);

        if (mListView.getAdapter() == null) {
            SwingBottomInAnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(mListAdapter);
            animationAdapter.setAbsListView(mListView);
            mListView.setAdapter(animationAdapter);
        }
        mListAdapter.notifyDataSetChanged();
    }


    /**
     * Interface for the listClickListener
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
