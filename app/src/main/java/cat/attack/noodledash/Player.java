package cat.attack.noodledash;

import cat.attack.noodledash.API.Character;

import android.graphics.*;

import java.util.ArrayList;

/**
 * Created by kegan on 11/8/2015.
 */
public class Player extends Character {
    public boolean started;

    public Player(MainView _view)
    {
        super(_view,R.drawable.test,0,0);
        started = false;
    }

    @Override
    public Bitmap getDisplay()
    {
        if(started) {
            return super.getDisplay();
        } else {
            return Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);
        }
    }

    public void start()
    {
        this.setPosition(50, view.getHeight() - display.getHeight());
        this.setVelocity(0, 0);
        this.setAcceleration(0, 0);
        started = true;
    }
    public void jump()
    {
        if(this.getVelocity()[1] == 0) {
            this.setVelocity(this.getVelocity()[0], 400);
            this.setAcceleration(this.getAcceleration()[0], -0.36);
        }
    }
    protected void onUpdate(long frameTime) {
        Point position = this.getPosition();
        double[] velocity = this.getVelocity();
        double[] acceleration = this.getAcceleration();

        Point newP = new Point(position);
        if(position.x > view.getWidth()) {
            newP.x = 0;
        }
        if(position.y + display.getHeight() > view.getHeight())
        {
            newP.y = view.getHeight() - display.getHeight();
            this.setVelocity(velocity[0], 0);
            this.setAcceleration(acceleration[0], 0);
        }

        if(!newP.equals(position)) {

            this.setPosition(newP.x,newP.y);

        }
    }
}
