package cn.com.tianyudg.rxretrofitmvpdemo.basic.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 解决横向晃动问题
 */

public class SolveRefreshLayout extends NestedRefreshLayout {
    private float mPrevY;

    public SolveRefreshLayout(Context context) {
        super(context);
    }

    public SolveRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPrevY = MotionEvent.obtain(event).getY();
                break;

            case MotionEvent.ACTION_MOVE:
                final float eventX = event.getX();
                float xDiff = Math.abs(eventX - mPrevY);

                if (xDiff > mTouchSlop  + 50) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(event);
    }
}
