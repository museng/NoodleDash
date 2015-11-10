package cat.attack.noodledash;

import android.graphics.*;
import android.view.SurfaceHolder;

import cat.attack.noodledash.API.GameThread;
/**
 * Created by kegan on 11/8/2015.
 */
public class Controller extends GameThread {
    private MainView view;
    private Paint basePaint;
    public Player player;
    private long start;
    private SurfaceHolder holder;

    public long execTime;

    public Controller(MainView _view)
    {
        view = _view;
        player = new Player(_view);
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        start = System.currentTimeMillis() + 10000;
        holder = view.getHolder();
    }
    @Override
    protected void loop()
    {

        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if(canvas != null)
            {
                prepScreen(canvas);
            }
        } finally {
            if(canvas != null)
            {
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void prepScreen(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.test), view.getWidth(), view.getHeight(), false),0,0,basePaint);
    }
}
