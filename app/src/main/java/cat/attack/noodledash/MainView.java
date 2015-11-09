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
public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint mTextPaint;
    private Paint mFramePaint;
    private float fontSizeDP = 60.0f;

    public Controller controller;
    private InputHandler input;

    private Bitmap frame;

    public boolean drawn = false;
    public boolean Updated = false;

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        controller.killThread();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height)
    {
        //--- On screen update
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(fontSizeDP);

        mFramePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFramePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        controller = new Controller(this);
        input = new InputHandler(this);

        this.setOnTouchListener(input);
        controller.start();

        Canvas UI = holder.lockCanvas();
        UI.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.test),this.getWidth(),this.getHeight(),false),0,0,mFramePaint);
        holder.unlockCanvasAndPost(UI);

    }
   /*

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

    */
}
