package cat.attack.noodledash.API;

/**
 * Created by kegan on 11/8/2015.
 */
import android.graphics.*;
import android.graphics.drawable.*;

import cat.attack.noodledash.MainView;

public abstract class Character {
    private double vX;
    private double vY;
    private double vXms;
    private double vYms;

    private double aX;
    private double aY;
    private double aXms;
    private double aYms;

    private double x;
    private double y;

    private void updateVms()
    {
        vXms = vX / 1000.0;
        vYms = vY / 1000.0;
    }
    private void updateAms()
    {
        aXms = aX / 1000.0;
        aYms = aY / 1000.0;
    }
    private void updateImage()
    {
        display = BitmapFactory.decodeResource(view.getResources(),ResID);
    }
    protected Bitmap display;
    protected int ResID;
    protected MainView view;

    public Character(MainView _view,int resource,int _x,int _y)
    {
        view = _view;
        updateResource(resource);
        x = _x;
        y = _y;
        vX = 0;
        vY = 0;
        aX = 0;
        aY = 0;
        updateVms();
        updateAms();
    }
    public void setPosition(double _x,double _y)
    {
        x = _x;
        y = _y;
    }
    public void setVelocity(double _vX,double _vY)
    {
        vX = _vX;
        vY = -1*_vY;
        updateVms();
    }
    public void setAcceleration(double _aX,double _aY)
    {
        aX = _aX;
        aY = -1*_aY;
        updateAms();
    }
    public void update(long frameTime)
    {
        vXms += aXms*frameTime;
        vYms += aYms*frameTime;

        vX = vXms * 1000.0;
        vY = vYms * 1000.0;

        x += vXms*frameTime;
        y += vYms*frameTime;


        onUpdate(frameTime);
    }
    public Bitmap getDisplay()
    {
        return display;
    }
    public Point getPosition()
    {
        return new Point((int)Math.ceil(x),(int)Math.ceil(y));
    }
    public double[] getVelocity()
    {
        return new double[] {vX,vY};
    }
    public double[] getAcceleration()
    {
        return new double[] {aX,aY};
    }
    public void updateResource(int resource)
    {
        ResID = resource;
        updateImage();
    }
    protected abstract void onUpdate(long frameTime);
}
