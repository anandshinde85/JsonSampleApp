package json.anand.com.jsonsampleapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import json.anand.com.jsonsampleapp.R;
import json.anand.com.jsonsampleapp.viewholders.WordsListViewHolder;
import wordlist.example.com.commons.server.entities.Word;

/**
 * Adapter for word list
 *
 * @author Anand Shinde
 */
public class WordListAdapter extends RecyclerView.Adapter<WordsListViewHolder> {
    private List<Word> words;
    private final Context context;

    public WordListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public WordsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_list_card_item, parent, false);
        return new WordsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordsListViewHolder holder, int position) {
        Word word = words.get(position);
        holder.tvValue1.setText(word.getId());
        holder.tvValue2.setText(word.getWord());
        holder.tvValue3.setText("" + word.getVariant());
        holder.tvValue4.setText(word.getMeaning());
        holder.tvValue5.setText(word.getRatio());
    }

    @Override
    public int getItemCount() {
        return (null != words ? words.size() : 0);
    }

    public void setWords(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }
}
