package challenge.ckl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import challenge.ckl.database.Article;
import se.emilsjolander.sprinkles.CursorList;
import se.emilsjolander.sprinkles.ModelList;

/**
 * Created by
 * Lucas Oceano Martins on 30/10/14.
 */
public class ArticleAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    protected ModelList<Article> mContent;

    public ArticleAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setContent(CursorList<Article> content) {
        mContent = ModelList.from(content);
    }

    @Override
    public int getCount() {
        return mContent.size();
    }

    @Override
    public Article getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.article_list_item, parent, false);
            assert convertView != null;
            holder.title = (TextView) convertView.findViewById(R.id.article_title_textView);
            holder.authors = (TextView) convertView.findViewById(R.id.article_authors_textView);
            holder.date = (TextView) convertView.findViewById(R.id.article_date_textView);
            holder.website = (TextView) convertView.findViewById(R.id.article_website_textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Article article = getItem(position);

        holder.title.setText(article.getTitle());
        holder.authors.setText(article.getAuthors());
        holder.date.setText(article.getDate());
        holder.website.setText(article.getWebsite());

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView authors;
        TextView date;
        TextView website;
    }
}
