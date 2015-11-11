package cat.attack.noodledash;

import android.content.*;
import android.renderscript.ScriptGroup;
import android.util.*;
import android.view.*;
import android.graphics.*;

import java.util.Objects;
import java.util.Random;


/**
 * Created by kegan on 11/7/2015.
 */
public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    public MainView(Context context, AttributeSet attrs) {

        super(context, attrs);

        // --- Initialize other classes
        controller = new Controller(this);
        input = new InputHandler(this);


        this.setBackgroundColor(Color.argb(0,0,0,0)); // --- Remove this & surfaceview will not be visible
        this.getHolder().setKeepScreenOn(true); // --- just so the screen doesnt turn off in game
        this.getHolder().addCallback(this); // --- Initialize the callback
    }

    public Controller controller;
    private InputHandler input;


    // --- On game exit, kill threads
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        controller.killThread();
    }

    // --- we may be able to do something with this, idk
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height)
    {
        //--- On screen update

    }

    // --- On game start, init threads & eventhandlers
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        this.setOnTouchListener(input);
        controller.start();
    }
}
