package cat.attack.noodledash.API;

import android.graphics.Point;

/**
 * Created by kegan on 11/10/2015.
 */
public class GamePoint extends Point {
    public GamePoint(int _x,int _y)
    {
        super(_x,_y);
    }
    public GamePoint(Point p)
    {
        super(p);
    }
    public GamePoint(GamePoint p)
    {
        super(p.x,p.y);
    }
    public Point asPoint()
    {
        return new Point(this.x,this.y);
    }
    public boolean isWithin(Element element)
    {
        return element.contains(this);
    }
}
