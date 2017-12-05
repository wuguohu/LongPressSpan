package win.dord.longpressspan.span;

import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by wuguohu on 2017/12/5.
 */

public abstract class LongPressSpan extends ClickableSpan {

    /**
     * 长按
     * @param widget
     */
    public abstract void onLongPress(View widget);
}
