package wordlist.example.com.commons.mvpviews;

import java.util.List;

import wordlist.example.com.commons.server.entities.Word;

/**
 * MVP view for populating word list view
 *
 * @author Anand Shinde
 */
public interface WordListView extends BaseMVPView {
    /**
     * Populate contents of list
     *
     * @param words - response received as arraylist
     */
    void populateList(List<Word> words);

    /**
     * Show loading dialog
     *
     * @param show - true to display loader and false to hide
     */
    void showLoading(boolean show);

    /**
     * Show error message
     */
    void showError();
}
