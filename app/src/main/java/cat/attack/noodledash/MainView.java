package cat.attack.noodledash;

import android.content.*;
import android.util.*;
import android.view.*;
import android.graphics.*;

import java.util.Objects;


/**
 * Created by kegan on 11/7/2015.
 */
public class MainView extends View {
    private Paint mTextPaint;
    private Paint mTitlePaint;
    private float fontSizeDP = 60.0f;
    private Updater updater;
    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void endGame()
    {
        updater.killThread();
    }

    private void init()
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(fontSizeDP);

        updater = new Updater(this);

        mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitlePaint.setColor(Color.GRAY);
        mTitlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        updater.start();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawRect(0f, 0f, this.getWidth(), 70, mTitlePaint);

        canvas.drawText(Objects.toString(System.currentTimeMillis()), 50, 60,mTextPaint);
    }

}
