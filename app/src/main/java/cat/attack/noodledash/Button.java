package cat.attack.noodledash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cat.attack.noodledash.API.ButtonClick;
import cat.attack.noodledash.API.ButtonHandler;
import cat.attack.noodledash.API.Element;
import cat.attack.noodledash.API.GamePoint;
import cat.attack.noodledash.API.UIElement;

/**
 * Created by kegan on 11/11/2015.
 */
public class Button extends UIElement {
    private ButtonClick onClickCallback;
    private ButtonHandler onHandleCallback;
    private MainView view;
    private boolean down;
    private GamePoint location;
    private Bitmap display;
    private Bitmap oldDisplay;
    public Button(MainView _view,int resource,int x,int y,ButtonClick callback, ButtonHandler callback2)
    {
        super(_view,x,y,0,0);
        view = _view;
        down = false;
        display = BitmapFactory.decodeResource(view.getResources(),resource);
        display = Bitmap.createScaledBitmap(display,display.getWidth()*2,display.getHeight()*2,false);
        location = new GamePoint(x,y);
        onClickCallback = callback;
        onHandleCallback = callback2;
        super.setBounds(x,y,display.getWidth(),display.getHeight());
        super.setDisplay(display);
        oldDisplay = display;
    }
    public boolean isDown() { return down; }
    public void onDown() { down = true; onHandleCallback.OnDown(this,view); }
    public void onUp() { down = false; onHandleCallback.OnUp(this, view); }
    public void setOnClickEventHandler(ButtonClick onClick)
    {
        onClickCallback = onClick;
    }
    public void onClick()
    {
        onClickCallback.onClick(this, view);
    }
    public void setDisplay(int resource)
    {
        oldDisplay = display;
        display = BitmapFactory.decodeResource(view.getResources(),resource);
        super.setBounds(location.x,location.y,display.getWidth(),display.getHeight());
        super.setDisplay(display);
    }
    public void rollbackDisplay()
    {
        display = oldDisplay;
        super.setBounds(location.x,location.y,display.getWidth(),display.getHeight());
        super.setDisplay(display);
    }
    public void setLocation(int x,int y)
    {
        location.x = x;
        location.y = y;
        super.setBounds(x,y,display.getWidth(),display.getHeight());
    }
    public GamePoint getLocation()
    {
        return location;
    }
}
