package ir.artapps.gamebrowser.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Navid Komijani
 * on 27,August,2020
 */
class CustomVideoView extends VideoView {
    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public void setVideoAspect(int w,int h){
//        wVideo=w;
//        hVideo=h;
//        onMeasure(w, h);
//    }
//    @Override
//    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
//    {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if(wVideo!=0 && hVideo!=0)
//            setMeasuredDimension(wVideo,hVideo);
//    }
}
