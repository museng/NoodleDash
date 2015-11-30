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
                    GamePoint location = new GamePoint((int)x1,(int)y1);

                    //--- check buttons
                    for(Button button : target.controller.getButtons())
                    {
                        if(location.isWithin(button))
                        {
                            button.onDown();
                        }
                    }
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
                    for(Button button : target.controller.getButtons())
                    {
                        if(button.isDown())
                        {
                            button.onUp();
                        }
                    }
                    if(dy > MIN_SWIPE_DIST) //--- Swiping up
                    {
                        target.controller.getPlayer().jump();
                    }
                    else if(-dy > MIN_SWIPE_DIST) //--- Swiping down
                    {

                    }
                    else if(dx > MIN_SWIPE_DIST) //--- Swiping left
                    {

                    }
                    else if (-dx > MIN_SWIPE_DIST) //--- Swiping right
                    {

                    }
                    else //--- Tapping
                    {
                        GamePoint location = new GamePoint((int)x1,(int)y1);

                        //--- check buttons
                        for(Button button : target.controller.getButtons())
                        {
                            if(location.isWithin(button))
                            {
                                button.onClick();
                            }
                        }
                        if(location.isWithin(target.controller.background))
                        {
                            Toast.makeText(target.getContext(),"YOLO",Toast.LENGTH_SHORT);
                        }
                    }
                    break;
                }
            }
        }
        return result;
    }
}
