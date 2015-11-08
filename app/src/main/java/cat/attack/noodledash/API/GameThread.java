package cat.attack.noodledash.API;

/**
 * Created by kegan on 11/7/2015.
 */
public abstract class GameThread extends Thread {
    private boolean exit = false;
    public GameThread()
    {
        super("ThreadB");
    }
    public void run() {
        while(!exit)
        {
            loop();
        }
    }
    protected abstract void loop();
    public void killThread()
    {
        exit = true;
        try {
            this.join();
        } catch(Exception ex) {
            this.stop();
        }
    }
}
