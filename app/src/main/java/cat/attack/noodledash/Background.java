package cat.attack.noodledash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import cat.attack.noodledash.API.*;
import cat.attack.noodledash.API.Character;

/**
 * Created by kegan on 11/11/2015.
 */
public class Background extends Character {
    private Paint bmpPaint;
    public Background(MainView _view)
    {
        super(_view,R.drawable.palace,0,0);
        bmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bmpPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        int gameWidth = 10;

        //--- initialize the actual display (so random order images & extend them ect
        display = Bitmap.createBitmap(view.controller.getWindowSize().x*gameWidth,view.controller.getWindowSize().y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(display);
        for(int i = 0; i < gameWidth; i++)
        {
            canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.palace), view.controller.getWindowSize().x, view.controller.getWindowSize().y,false),view.controller.getWindowSize().x*i,0,bmpPaint);
        }
        super.setBounds(0,0,display.getWidth(),display.getHeight());

    }

    public void start()
    {
        this.setVelocity(-100,0);
        this.setPosition(0,0);
    }
    public void speedup()
    {
        double[] velocity = this.getVelocity();
        velocity[0] *= 1.2;
        this.setVelocity(velocity[0],velocity[1]);
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
