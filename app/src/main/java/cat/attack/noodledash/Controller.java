package cat.attack.noodledash;

import android.graphics.*;
import android.view.SurfaceHolder;

import java.util.Objects;

import cat.attack.noodledash.API.GameThread;
/**
 * Created by kegan on 11/8/2015.
 */
public class Controller extends GameThread {
    private MainView view;
    private Paint basePaint;
    private Paint textPaint;

    private Player player;
    private long start;
    private SurfaceHolder holder;

    private double targetFPS = 60.0;
    private long delay;


    private long execTime;


    public Player getPlayer()
    {
        return player;
    }

    public Controller(MainView _view)
    {
        view = _view;
        player = new Player(_view);
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(60.0f);
        textPaint.setColor(Color.RED);

        start = System.currentTimeMillis() + 10000;
        holder = view.getHolder();
        delay = (long)Math.floor(1000.0 / targetFPS);
        execTime = 0;
    }
    @Override
    protected void loop()
    {
        start = System.currentTimeMillis();
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if(canvas != null)
            {
                prepScreen(canvas);
                drawFPS(canvas);
            }
        } finally {
            if(canvas != null)
            {
                holder.unlockCanvasAndPost(canvas);
            }
        }
        execTime = System.currentTimeMillis() - start;
        if(execTime < delay)
        {
            try {
                Thread.sleep(delay - execTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void prepScreen(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.test), view.getWidth(), view.getHeight(), false),0,0,basePaint);
    }
    private void drawFPS(Canvas canvas)
    {
        double FPS = Math.floor(10000.0 / execTime) / 10.0;
        canvas.drawText("FPS: " + Objects.toString(FPS), 0, 60,textPaint);
    }
}
