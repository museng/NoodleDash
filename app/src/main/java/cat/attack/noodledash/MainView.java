package cat.attack.noodledash;

import android.content.*;
import android.renderscript.ScriptGroup;
import android.util.*;
import android.view.*;
import android.graphics.*;

import java.util.Objects;
import java.util.Random;


/**
 * Created by kegan on 11/7/2015.
 */
public class MainView extends View {
    private Paint mTextPaint;
    private Paint mTitlePaint;
    private float fontSizeDP = 60.0f;

    private Updater updater;
    private InputHandler input;

    private int x = 0;

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
        input = new InputHandler(this);

        this.setOnTouchListener(input);

        mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitlePaint.setColor(Color.GRAY);
        mTitlePaint.setStyle(Paint.Style.FILL_AND_STROKE);



        updater.start();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Random r = new Random();

        mTitlePaint.setColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        canvas.drawRect(0f, 0f, this.getWidth(), this.getHeight(), mTitlePaint);
        x = x + 1;
        if(x > this.getWidth()) {
            x = 0;
        }
        canvas.drawText(Objects.toString(System.currentTimeMillis()), x, 100,mTextPaint);
    }

}
