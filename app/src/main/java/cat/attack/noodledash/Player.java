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
        super(_view,R.drawable.man,0,0);
        started = false;
    }

    @Override
    public Bitmap getDisplay()
    {
        return super.getDisplay();
    }


    public void start()
    {

        this.setPosition(50, view.getHeight() - display.getHeight() - 16);
        this.setVelocity(0, 0);
        this.setAcceleration(0, 0);
        started = true;
    }
    public void jump()
    {


        double dy = 36*view.controller.percentScale;
        double gav = -9.81*36*view.controller.percentScale;
        double time = 0.5;
        //--- TODO: solve for vi from equaltion

        if(this.getVelocity()[1] == 0) {
            this.setVelocity(this.getVelocity()[0], 400);
            this.setAcceleration(this.getAcceleration()[0], );
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
        if(position.y + display.getHeight() > view.getHeight() - 16)
        {
            newP.y = view.getHeight() - display.getHeight() - 16;
            this.setVelocity(velocity[0], 0);
            this.setAcceleration(acceleration[0], 0);
        }

        if(!newP.equals(position)) {

            this.setPosition(newP.x,newP.y);

        }
    }
}
