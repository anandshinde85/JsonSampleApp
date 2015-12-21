package wordlist.example.com.commons.server.entities;

import java.util.List;

/**
 * Entity class holding words response
 *
 * @author Anand Shinde
 */
public class WordsResponse {
    private String version;
    private List<Word> words;

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
