package cat.attack.noodledash;

import cat.attack.noodledash.API.GameThread;

/**
 * Created by kegan on 11/7/2015.
 */
public class Updater extends GameThread {
    private MainView view;
    public Updater(MainView _view)
    {
        view = _view;
    }
    protected void loop()
    {
        view.postInvalidate();
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
