package cat.attack.noodledash;

import android.content.*;
import android.util.*;
import android.view.*;
import android.graphics.*;


/**
 * Created by kegan on 11/7/2015.
 */
public class MainView extends View {
    private Paint mTextPaint;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        System.out.println("TEST INIT");
    }
    private void init()
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLUE);

    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawText("Test",10,10,mTextPaint);
    }

}
