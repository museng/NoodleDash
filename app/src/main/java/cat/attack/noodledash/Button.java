package cat.attack.noodledash;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cat.attack.noodledash.API.ButtonClick;
import cat.attack.noodledash.API.Element;
import cat.attack.noodledash.API.GamePoint;

/**
 * Created by kegan on 11/11/2015.
 */
public class Button extends Element {
    private ButtonClick onClickCallback;
    private MainView view;
    private GamePoint location;
    private Bitmap display;
    public Button(MainView _view,int resource,int x,int y,ButtonClick callback)
    {
        super(x,y,0,0);
        view = _view;
        display = BitmapFactory.decodeResource(view.getResources(),resource);
        location = new GamePoint(x,y);
        onClickCallback = callback;
        super.setBounds(x,y,display.getWidth(),display.getHeight());
    }
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
        display = BitmapFactory.decodeResource(view.getResources(),resource);
        super.setBounds(location.x,location.y,display.getWidth(),display.getHeight());

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
    public Bitmap getDisplay()
    {
        return display;
    }
}
