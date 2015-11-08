package cat.attack.noodledash;

import cat.attack.noodledash.API.GameThread;

/**
 * Created by kegan on 11/7/2015.
 */
public class Updater extends GameThread {
    private MainView view;

    private double targetFPS = 60.0;
    private long delay;
    public long lastFrame;


    public Updater(MainView _view)
    {
        lastFrame = 0;
        delay = 0;
        view = _view;
    }
    @Override
    protected void loop()
    {
        long frameLength = System.currentTimeMillis();
        view.postInvalidate();
        while(!view.drawn) { }
        view.Updated = true;
        lastFrame = System.currentTimeMillis() -  frameLength;
        delay = ((long)Math.floor(1000.0/targetFPS)) - lastFrame;
        try {
            if(delay > 0) {
                lastFrame += delay;
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            //--- last Frame took longer than the target 60fps
        }
    }
}
