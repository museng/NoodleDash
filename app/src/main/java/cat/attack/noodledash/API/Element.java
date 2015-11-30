package cat.attack.noodledash.API;

/**
 * Created by kegan on 11/10/2015.
 *
 * THIS IS A UI ELEMENT CLASS, THIS IS USED AS A BASE FOR ALL UI ELEMENTS
 */
public class Element {
    private int x;
    private int y;
    private int w;
    private int h;
    public Element(int _x,int _y,int _w,int _h)
    {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
    }
    public void setBounds(int _x,int _y,int _w,int _h)
    {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
    }
    public boolean contains(GamePoint point)
    {
        if(point.x >= x && point.x <= (x + w))
        {
            if(point.y >= y && point.y <= (y+h))
            {
                return true;
            }
        }
        return false;
    }
}
