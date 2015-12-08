package cat.attack.noodledash;

import cat.attack.noodledash.API.Character;

import android.graphics.*;

import java.util.ArrayList;

/**
 * Created by kegan on 11/8/2015.
 */
public class Player extends Character {
    public boolean started;
    public boolean jumping;
    public boolean canDJump;

    private double vi;
    public Player(MainView _view)
    {
        super(_view,R.drawable.man,0,0);
        started = false;
        jumping = false;
        canDJump = false;

    }

    @Override
    public Bitmap getDisplay()
    {
        return super.getDisplay();
    }


    public void start()
    {
        vi = (2 * (m2p(1.0) - (0.5) * m2p(-9.81) * 0.25)) * view.controller.percentScale;

        this.setPosition(50, view.getHeight() - display.getHeight() - 16);
        this.setVelocity(0, 0);
        this.setAcceleration(0, 0);
        started = true;
    }


    private double m2p(double meter)
    {
        return 36.0*meter;
    }


    public void dJump()
    {
        canDJump = false;
        this.setVelocity(this.getVelocity()[0], vi * 2);
    }
    public void jump()
    {
        canDJump = true;
        jumping = true;
        //vi is in pixles per second scaled
        //-m2p(-9.81)*0.5 = vi

        //--- TODO: solve for vi from equaltion


        if(this.getVelocity()[1] == 0) {
            this.setVelocity(this.getVelocity()[0], vi * 2 * 1.5);
            this.setAcceleration(this.getAcceleration()[0], -0.981 * 2);
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
            jumping = false;
            newP.y = view.getHeight() - display.getHeight() - 16;
            this.setVelocity(velocity[0], 0);
            this.setAcceleration(acceleration[0], 0);
        }

        if(!newP.equals(position)) {

            this.setPosition(newP.x,newP.y);

        }
    }
}
