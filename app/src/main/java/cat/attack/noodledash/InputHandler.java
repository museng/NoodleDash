package cat.attack.noodledash;

import android.view.*;
import android.widget.Toast;

/**
 * Created by kegan on 11/7/2015.
 */
public class InputHandler implements View.OnTouchListener {
    private MainView target;
    private float y1, y2;
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
                case MotionEvent.ACTION_DOWN:
                {
                    y1 = event.getY();
                    break;
                }
                case MotionEvent.ACTION_UP:
                {
                    y2 = event.getY();
                    float dy = y1 - y2;
                    if(dy > MIN_SWIPE_DIST)
                    {
                        target.controller.getPlayer().jump();
                        Toast.makeText(target.getContext(), "Swipe Up", Toast.LENGTH_SHORT).show();
                    }
                    else if(-dy > MIN_SWIPE_DIST)
                    {
                        Toast.makeText(target.getContext(),"Swipe Down",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(target.getContext(),"Screen Tap",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                target.controller.getPlayer().jump();
            }
        }
        return result;
    }
}
