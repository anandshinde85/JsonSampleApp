package json.anand.com.presenter;

import java.util.List;

import json.anand.com.model.internal.service.WordsListServiceImpl;
import json.anand.com.model.service.WordsListService;
import wordlist.example.com.commons.mvpviews.WordListView;
import wordlist.example.com.commons.server.entities.Word;

/**
 * Presenter used for supplying API data to WordListFragment
 *
 * @author Anand Shinde
 */
public class WordListPresenter implements BasePresenter, WordsListServiceImpl.CallbackHandler {
    private final WordListView wordListView;
    private final WordsListService wordsListService;
    private List<Word> wordsResponse;

    public WordListPresenter(WordListView wordListView) {
        this.wordListView = wordListView;
        wordsListService = new WordsListServiceImpl(this, 1);
    }

    @Override
    public void start() {
        wordListView.showLoading(true);

        if (wordsResponse == null) {
            wordsListService.getWordList();
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void onSuccess(List<Word> response, int uniqueId) {
        wordsResponse = response;
        wordListView.showLoading(false);
        wordListView.populateList(wordsResponse);
    }

    @Override
    public void onFailure(int uniqueId) {
        wordListView.showError();
    }
}
