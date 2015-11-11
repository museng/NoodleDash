package cat.attack.noodledash;

import android.graphics.*;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;

import java.util.Objects;

import cat.attack.noodledash.API.GameThread;
/**
 * Created by kegan on 11/8/2015.
 */
public class Controller extends GameThread {
    private MainView view;
    private Paint basePaint;
    private Paint textPaint;

    private Player player;
    private long start;
    private SurfaceHolder holder;
    private WindowManager windowManager;
    private Point WindowSize;

    private double targetFPS = 60.0;
    private long delay;
    private boolean loading;

    private Bitmap temploading;

    private long execTime;

    // --- Access game options with these accessors
    public Player getPlayer()
    {
        return player;
    }
    // --- Mutators
    public void setWindowManager(WindowManager _windowManager)
    {
        windowManager = _windowManager;
    }

    // --- GUI Controllers Below Here
    public Controller(MainView _view)
    {
        view = _view;

        // --- Initialize the player object & other objects
        player = new Player(_view);

        // --- Initialize the paints used for drawing
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(60.0f);
        textPaint.setColor(Color.RED);

        // --- Define more constants
        start = System.currentTimeMillis() + 10000;
        loading = true;
        holder = view.getHolder();
        delay = (long)Math.floor(1000.0 / targetFPS);
        execTime = 0;
    }
    @Override
    protected void loop()
    {
        long temp = System.currentTimeMillis();



        if(WindowSize == null && windowManager != null)
        {
            Display display = windowManager.getDefaultDisplay();
            WindowSize = new Point();
            display.getSize(WindowSize);
            onSizeDetermined();
        }




        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if(canvas != null)
            {
                prepScreen(canvas);
                drawFPS(canvas);
            }
        } finally {
            if(canvas != null)
            {
                holder.unlockCanvasAndPost(canvas);
            }
        }
        execTime = System.currentTimeMillis() - temp;
        if(execTime < delay)
        {
            try {
                Thread.sleep(delay - execTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //--- recalculate exec time (as we hit a sleep)
            execTime = System.currentTimeMillis() - temp;
        }
        if(player.started)
        {
            player.update(execTime);
        }
    }
    // --- Here we can modify what determines when loading is over
    private boolean doneLoading()
    {
        return (System.currentTimeMillis() >= (start + 3000.0));
    }

    // --- Use this to initialize bitmaps once we have the correct screen size(should be almost immediate)
    private void onSizeDetermined()
    {
        temploading = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.test), WindowSize.x, WindowSize.y, false);
    }
    // --- Use this to initialize the player object map ect
    private void onGameInit()
    {
        player.start();
    }
    // --- Ignore this (triggers drawLoading / drawGame)
    private void prepScreen(Canvas canvas)
    {
        if(loading)
        {
            drawLoading(canvas);
            if(doneLoading())
            {
                loading = false;
                onGameInit();
            }
        }
        else
        {
            drawGame(canvas);
        }
    }
    // --- Used for development, calculates FPS & draws it
    private void drawFPS(Canvas canvas)
    {
        double FPS = Math.floor(10000.0 / execTime) / 10.0;
        canvas.drawText("FPS: " + Objects.toString(FPS), 0, 60,textPaint);
    }


    // --- This is our loading screen. This is run while the loading screen is active
    private void drawLoading(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        if(temploading != null)
            canvas.drawBitmap(temploading,0,0,basePaint);
    }
    // --- This is the game. This is run while the game is active (after loading screen)
    private void drawGame(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.drawBitmap(player.getDisplay(), player.getPosition().x,player.getPosition().y,basePaint);
    }

}
