package cat.attack.noodledash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

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

        int gameWidth = 2;

        int[] backgrounds = {R.drawable.palace,R.drawable.lightfield,R.drawable.darkfield,R.drawable.cave};

        Random r = new Random();
        int id = backgrounds[r.nextInt(backgrounds.length-1)];

        updateResource(id);

        //--- initialize the actual display (so random order images & extend them ect
        display = Bitmap.createBitmap(view.controller.getWindowSize().x*gameWidth,view.controller.getWindowSize().y, Bitmap.Config.ARGB_8888);

        Bitmap background = BitmapFactory.decodeResource(view.getResources(),id);
        Bitmap scaledBackground = Bitmap.createScaledBitmap(background,view.controller.getWindowSize().x,view.controller.getWindowSize().y,false);

        Canvas canvas = new Canvas(display);
        //canvas.drawBitmap(scaledBackground,view.controller.getWindowSize().x*gameWidth,0,bmpPaint);
        for(int i = 0; i < gameWidth; i++)
        {
            canvas.drawBitmap(scaledBackground,view.controller.getWindowSize().x*i,0,bmpPaint);
        }
        super.setImage(display);
    }

    public void start()
    {
        this.setVelocity(-200,0);
        this.setPosition(0,0);
    }
    public void speedup()
    {
        double[] velocity = this.getVelocity();
        velocity[0] *= 1.2;
        this.setVelocity(velocity[0],velocity[1]);
    }
    protected void onUpdate(long frameTime) { }
}
