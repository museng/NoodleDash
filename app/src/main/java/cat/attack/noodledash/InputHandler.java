package cat.attack.noodledash;

import android.view.*;

/**
 * Created by kegan on 11/7/2015.
 */
public class InputHandler implements View.OnTouchListener {
    MainView target;
    public InputHandler(MainView targetView)
    {
        target = targetView;
    }
    @Override
    public boolean onTouch(View view,MotionEvent event) {
        boolean result = false;
        if (view.equals(target)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                target.controller.player.jump();
            }
        }
        return result;
    }
}
