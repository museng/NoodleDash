package cat.attack.noodledash;

import android.graphics.*;
import cat.attack.noodledash.API.GameThread;
/**
 * Created by kegan on 11/8/2015.
 */
public class Controller extends GameThread {
    private MainView view;
    private Paint basePaint;
    public Player player;

    public long execTime;

    public Controller(MainView _view)
    {
        view = _view;
        player = new Player(_view);
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    @Override
    protected void loop()
    {
        long time = System.currentTimeMillis();
        //--- dont draw the Ui while the app is in the process of opening
        if(view.getWidth() > 0 && view.getHeight() > 0) {
            Bitmap  b = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(b);
            prepScreen(canvas);
            view.setFrame(b);
        }
        //--- wait until the next loop is processed
        while(!view.Updated) {}
        view.Updated = false;
        execTime = System.currentTimeMillis() - time;
        player.update(execTime);
    }

    private void prepScreen(Canvas canvas)
    {
        //--- Draw elements onto the screen here
        canvas.drawBitmap(player.getDisplay(),player.getPosition().x,player.getPosition().y,basePaint);
    }
}
