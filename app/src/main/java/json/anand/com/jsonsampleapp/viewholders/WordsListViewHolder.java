package json.anand.com.jsonsampleapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import json.anand.com.jsonsampleapp.R;

/**
 * View holder for words list
 *
 * @author Anand Shinde
 */
public class WordsListViewHolder extends RecyclerView.ViewHolder {
    public TextView tvValue1, tvValue2, tvValue3, tvValue4, tvValue5;

    public WordsListViewHolder(View itemView) {
        super(itemView);
        LinearLayout cardItem;
        TextView tvKey1, tvKey2, tvKey3, tvKey4, tvKey5;
        cardItem = (LinearLayout) itemView.findViewById(R.id.card_item_one);
        tvKey1 = (TextView) cardItem.findViewById(R.id.tv_key);
        tvValue1 = (TextView) cardItem.findViewById(R.id.tv_value);
        cardItem = (LinearLayout) itemView.findViewById(R.id.card_item_two);
        tvKey2 = (TextView) cardItem.findViewById(R.id.tv_key);
        tvValue2 = (TextView) cardItem.findViewById(R.id.tv_value);
        cardItem = (LinearLayout) itemView.findViewById(R.id.card_item_three);
        tvKey3 = (TextView) cardItem.findViewById(R.id.tv_key);
        tvValue3 = (TextView) cardItem.findViewById(R.id.tv_value);
        cardItem = (LinearLayout) itemView.findViewById(R.id.card_item_four);
        tvKey4 = (TextView) cardItem.findViewById(R.id.tv_key);
        tvValue4 = (TextView) cardItem.findViewById(R.id.tv_value);
        cardItem = (LinearLayout) itemView.findViewById(R.id.card_item_five);
        tvKey5 = (TextView) cardItem.findViewById(R.id.tv_key);
        tvValue5 = (TextView) cardItem.findViewById(R.id.tv_value);

        tvKey1.setText("id :");
        tvKey2.setText("word :");
        tvKey3.setText("variant :");
        tvKey4.setText("meaning :");
        tvKey5.setText("ratio :");
    }
}
