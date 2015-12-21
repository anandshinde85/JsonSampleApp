package wordlist.example.com.commons.mvpviews;

import android.content.Context;

/**
 * Root class for all MVP views
 */
public interface BaseMVPView {
    /**
     * Returns Context of holding view
     *
     * @return context
     */
    Context getViewContext();
}
