# LongPressSpan
一个Android自定义View，可以让SpannableString支持长按操作

### 效果图

![长按](https://github.com/wuguohu/LongPressSpan/blob/master/Screenshot_2017-12-05.png)

### 使用说明

```java
TextView textView = (TextView)findViewById(R.id.textView);
textView.setMovementMethod(CustomedLinkMovementMethod.getInstance()); //添加事件支持

SpannableString str = new SpannableString("我是测试文字-我可以点击-我可以长按-我可以点击也可以长按");
str.setSpan(new LongPressSpan() {   //添加事件
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
```
