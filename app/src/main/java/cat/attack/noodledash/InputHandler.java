package cat.attack.noodledash;

import android.graphics.Point;
import android.location.Location;
import android.view.*;
import android.widget.Toast;

import cat.attack.noodledash.API.GamePoint;

/**
 * Created by kegan on 11/7/2015.
 * Literally ignore everything in here except the SWITCH (that is the controls for the screen)
 */
public class InputHandler implements View.OnTouchListener {
    private MainView target;
    private float y1, y2, x1, x2;
    private int MIN_SWIPE_DIST = 150;

    public InputHandler(MainView targetView)
    {
        target = targetView;
    }
    @Override
    public boolean onTouch(View view,MotionEvent event) {
        boolean result = false;
        if (view.equals(target)) {
            result = true;
            switch (event.getAction())
            {
                //--- On finger down
                case MotionEvent.ACTION_DOWN:
                {
                    //--- capture start xy
                    y1 = event.getY();
                    x1 = event.getX();
                    break;
                }
                //--- On finger move while down (animating sliders would use dis, use x1,y1 to get offset and use the dx dy to calcuate object newx and newy)
                case MotionEvent.ACTION_MOVE:
                {
                    break;
                }
                //--- On finger up
                case MotionEvent.ACTION_UP:
                {
                    //--- capture end xy
                    y2 = event.getY();
                    x2 = event.getX();
                    float dy = y1 - y2;
                    float dx = x1 - x2;
                    if(dy > MIN_SWIPE_DIST) //--- Swiping up
                    {
                        target.controller.getPlayer().jump();
                        Toast.makeText(target.getContext(), "Swipe Up", Toast.LENGTH_SHORT).show();
                    }
                    else if(-dy > MIN_SWIPE_DIST) //--- Swiping down
                    {
                        Toast.makeText(target.getContext(),"Swipe Down",Toast.LENGTH_SHORT).show();
                    }
                    else if(dx > MIN_SWIPE_DIST) //--- Swiping left
                    {
                        Toast.makeText(target.getContext(),"Swipe Left",Toast.LENGTH_SHORT).show();
                    }
                    else if (-dx > MIN_SWIPE_DIST) //--- Swiping right
                    {
                        Toast.makeText(target.getContext(),"Swipe Right",Toast.LENGTH_SHORT).show();
                    }
                    else //--- Tapping
                    {
                        GamePoint location = new GamePoint((int)x1,(int)y1);
                        if(location.isWithin(target.controller.getPlayer()))
                        {
                            Toast.makeText(target.getContext(),"Player Tap",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(target.getContext(),"Screen Tap",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
            }
        }
        return result;
    }
}
