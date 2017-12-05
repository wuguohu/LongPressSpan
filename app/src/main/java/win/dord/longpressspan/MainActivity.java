package win.dord.longpressspan;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import win.dord.longpressspan.span.CustomedLinkMovementMethod;
import win.dord.longpressspan.span.LongPressSpan;

public class MainActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView.setMovementMethod(CustomedLinkMovementMethod.getInstance());

        SpannableString str = new SpannableString("我是测试文字-我可以点击-我可以长按-我可以点击也可以长按");
        //测试文字
        str.setSpan(new ForegroundColorSpan(Color.RED), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //我可以点击
        str.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "on click", Toast.LENGTH_LONG).show();
            }
        },7, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //我可以长按
        str.setSpan(new LongPressSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#00cc00"));
            }

            @Override
            public void onClick(View view) {
            }

            @Override
            public void onLongPress(View widget) {
                Toast.makeText(MainActivity.this, "on long press", Toast.LENGTH_SHORT).show();
            }
        }, 13, 18, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //我可以点击也可以长按
        str.setSpan(new LongPressSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "on click", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongPress(View widget) {
                Toast.makeText(MainActivity.this, "on long press", Toast.LENGTH_LONG).show();
            }
        }, 19, 29, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        textView.setText(str);
    }
}
