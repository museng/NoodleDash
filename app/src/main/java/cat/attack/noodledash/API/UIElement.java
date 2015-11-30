package cat.attack.noodledash.API;

import android.graphics.Bitmap;

import cat.attack.noodledash.MainView;

/**
 * Created by kegan on 11/12/2015.
 */
public class UIElement extends Element {
    private MainView mView;
    private Bitmap display;
    private GamePoint position;
    public UIElement(MainView _view,int _x, int _y, int _w, int _h) {
        super(_x,_y/*_view.getHeight()-_y*/, _w, _h);
        position = new GamePoint(_x,_view.getHeight()-_y);
        mView = _view;
    }

    public void setDisplay(Bitmap image)
    {
        display = image;
    }
    public Bitmap getDisplay()
    {
        return display;
    }
    public void setPos(GamePoint point)
    {
        setPos(point.x,point.y);
    }
    public void setPos(int x,int y)
    {
        position.x = x;
        position.y = y;
    }
    public GamePoint getPosition()
    {
        return position;
    }
    public void setBounds(int _x,int _y,int _w,int _h)
    {
        this.setPos(_x, _y);
        super.setBounds(_x,_y/*mView.getHeight()-_y*/, _w, _h);
    }
}
