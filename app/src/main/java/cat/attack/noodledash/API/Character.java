package cat.attack.noodledash.API;

/**
 * Created by kegan on 11/8/2015.
 */
import android.graphics.*;
import android.graphics.drawable.*;

import cat.attack.noodledash.MainView;

public abstract class Character extends UIElement {
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

        super.setBounds((int) x, (int) y, display.getWidth(), display.getHeight());
        super.setDisplay(display);
    }
    protected Bitmap display;
    protected int ResID;
    protected MainView view;

    public void setImage(Bitmap image)
    {
        display = image;
        super.setBounds((int) x, (int) y, display.getWidth(), display.getHeight());
        super.setDisplay(display);
    }

    public Character(MainView _view,int resource,int _x,int _y)
    {
        super(_view,_x,_y,0,0);
        view = _view;
        x = _x;
        y = _y;
        vX = 0;
        vY = 0;
        aX = 0;
        aY = 0;
        updateVms();
        updateAms();
        updateResource(resource);
        //--- update bounds after element is finished
        super.setBounds(_x,_y,display.getWidth(),display.getHeight());
    }
    public void setPosition(double _x,double _y)
    {
        x = _x;
        y = _y;
        super.setBounds((int) _x, (int) _y, display.getWidth(), display.getHeight());
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

        super.setBounds((int)x,(int)y,display.getWidth(),display.getHeight());


        onUpdate(frameTime);
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
