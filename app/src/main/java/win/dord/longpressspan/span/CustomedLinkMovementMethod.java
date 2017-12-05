package win.dord.longpressspan.span;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by wuguohu on 2017/12/5.
 */

public class CustomedLinkMovementMethod extends LinkMovementMethod {
    private LongPressSpan pressSpan;
    private static final int LONG_PRESS_TIMEOUT = 500;
    private long lastDownTime;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);
            LongPressSpan[] links = buffer.getSpans(off, off, LongPressSpan.class);

            if (links.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    if(links[0] == pressSpan && (System.currentTimeMillis() - lastDownTime) > LONG_PRESS_TIMEOUT){
                        links[0].onLongPress(widget);
                    }else{
                        links[0].onClick(widget);
                    }
                    pressSpan = null;
                    lastDownTime = 0;
                    Selection.removeSelection(buffer);
                } else if (action == MotionEvent.ACTION_DOWN) {
                    Selection.setSelection(buffer,
                            buffer.getSpanStart(links[0]),
                            buffer.getSpanEnd(links[0]));
                    pressSpan = links[0];
                    lastDownTime = System.currentTimeMillis();
                }
                return true;
            } else {
                Selection.removeSelection(buffer);
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }


    public static MovementMethod getInstance() {
        if (sInstance == null)
            sInstance = new CustomedLinkMovementMethod();

        return sInstance;
    }

    private static CustomedLinkMovementMethod sInstance;
}
