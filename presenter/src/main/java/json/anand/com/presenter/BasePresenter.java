package json.anand.com.presenter;

/**
 * Base presenter for all the presenters
 *
 * @author Anand Shinde
 */
public interface BasePresenter {
    /**
     * To be called in onStart method of activity/fragment
     */
    void start();

    /**
     * To be called in onStop method of activity/fragment
     */
    void stop();
}
