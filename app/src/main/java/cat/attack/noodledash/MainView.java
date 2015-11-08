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
    private Paint mFramePaint;
    private float fontSizeDP = 60.0f;

    private Updater updater;
    public Controller controller;
    private InputHandler input;

    private Bitmap frame;

    public boolean drawn = false;
    public boolean Updated = false;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void endGame()
    {
        controller.killThread();
        updater.killThread();
    }

    public void setFrame(Bitmap nextFrame)
    {
        frame = nextFrame;
    }

    private void paint(Canvas canvas)
    {
        double FPS = Math.floor(10000.0 / updater.lastFrame) / 10.0;
        double FPSDraw = Math.floor(10000.0 / controller.execTime) / 10.0;
        if(FPSDraw < FPS)
        {
            FPS = FPSDraw;
        }
        canvas.drawBitmap(frame,0,0,mFramePaint);
        canvas.drawText("FPS: " + Objects.toString(FPS), 0, 60,mTextPaint);
    }

    private void init()
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(fontSizeDP);

        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        updater = new Updater(this);
        controller = new Controller(this);
        input = new InputHandler(this);

        this.setOnTouchListener(input);
        updater.start();
        controller.start();
    }

    @Override
    public void postInvalidate()
    {
        drawn = false;
        super.postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        drawn = true;

        super.onDraw(canvas);

        if(frame != null)
            paint(canvas);


    }

}
