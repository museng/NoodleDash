package cat.attack.noodledash;

import android.graphics.*;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import cat.attack.noodledash.API.ButtonClick;
import cat.attack.noodledash.API.ButtonHandler;
import cat.attack.noodledash.API.Element;
import cat.attack.noodledash.API.GameThread;

/**
 * Created by kegan on 11/8/2015.
 */

public class Controller extends GameThread {
    private MainView view;
    private Paint basePaint;
    private Paint textPaint;

    private Player player;
    public Background background;

    private long start;
    private SurfaceHolder holder;
    private Point WindowSize;

    private ArrayList<Button> permButtons = new ArrayList<Button>();
    private ArrayList<Button> buttons = new ArrayList<Button>();

    private double targetFPS = 60.0;
    private long delay;
    private boolean loading;
    private long looptime;
    public double percentScale;

    private Bitmap temploading;

    private long execTime;

    public MediaPlayer mp;

    // --- Access game options with these accessors
    public Player getPlayer()
    {
        return player;
    }
    // --- Mutators

    // --- GUI Controllers Below Here
    public Point getWindowSize()
    {
        return WindowSize;
    }
    public Controller(MainView _view)
    {
        view = _view;
        percentScale = 0.0;
        looptime = 0;
        // --- Initialize the player object & other objects
        player = new Player(_view);

        // --- Start music
        mp = MediaPlayer.create(_view.getContext(),R.raw.music);
        mp.setLooping(true);
        mp.start();
        // --- Initialize the paints used for drawing
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(60.0f);
        textPaint.setColor(Color.RED);

        // --- Define more constants
        start = System.currentTimeMillis();
        loading = true;
        holder = view.getHolder();
        delay = (long)Math.floor(1000.0 / targetFPS);
        execTime = 0;
    }
    public ArrayList<Button> getButtons()
    {
        return buttons;
    }
    public void hideButtons()
    {
       buttons.clear();
    }
    public void initMainMenu()
    {
        //--- add mute buttons (always visible)
        permButtons.add(new Button(this.view, R.drawable.sound, 5, 5, new ButtonClick() {
            @Override
            public void onClick(Element e, MainView v) {
                Button self = (Button)e;
                self.toggled = !self.toggled;
                if(self.toggled)
                {
                    self.setDisplay(R.drawable.sounddark);
                    mp.pause();
                }
                else
                {
                    self.setDisplay(R.drawable.sound);
                    mp.start();
                }
            }
        }, new ButtonHandler() {
            @Override
            public void OnDown(Element e, MainView v) { }

            @Override
            public void OnUp(Element e, MainView v) { }
        }));


        // --- Define buttons
        buttons.add(new Button(this.view, R.drawable.dash, 200, 200, new ButtonClick() {
            @Override
            public void onClick(Element e, MainView v) {
                v.controller.onGameStarted();
                v.controller.hideButtons();
            }
        }, new ButtonHandler() {
            @Override
            public void OnDown(Element e, MainView v) {
                Button b = (Button) e;
                b.setDisplay(R.drawable.dashdark); //--- LMFAO
            }

            @Override
            public void OnUp(Element e, MainView v) {
                Button b = (Button) e;
                b.rollbackDisplay();
            }
        }));
    }

    @Override
    protected void loop()
    {
        long temp = System.currentTimeMillis();




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

        looptime += execTime;
        //--- TODO: Figure out how to detect when the screen finishes resizing instead of
        if(looptime > 1000 && WindowSize == null)
        {
            WindowSize = new Point(0,0);
            WindowSize.set(view.getWidth(),view.getHeight());
            onSizeDetermined();
        }

        if(player.started)
        {
            player.update(execTime);
            background.update(execTime);
        }
    }
    // --- Here we can modify what determines when loading is over
    private boolean doneLoading()
    {
        return (System.currentTimeMillis() >= (start + 5000.0));
    }

    private void onGameStarted()
    {
        player.start();
        background.start();
    }

    // --- Use this to initialize bitmaps once we have the correct screen size(should be almost immediate)
    private void onSizeDetermined()
    {
        //TINKERING ABOOT
        temploading = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.test), WindowSize.x, WindowSize.y, false);
        percentScale = (double) WindowSize.y / 450;

        int h = player.getDisplay().getHeight();
        Bitmap newD =  Bitmap.createScaledBitmap(player.getDisplay(),(int) (player.getDisplay().getWidth()*percentScale),(int) (player.getDisplay().getHeight()*percentScale),false);
        player.setImage(newD);
        background = new Background(view);
    }
    // --- Use this to initialize the player object map ect
    private void onGameInit()
    {
        initMainMenu();
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
        canvas.drawBitmap(background.getDisplay(), background.getPosition().x, background.getPosition().y, basePaint);
        if(player.started)
        {
            canvas.drawBitmap(player.getDisplay(), player.getPosition().x,player.getPosition().y,basePaint);
        }
        if(buttons.size() > 0)
        {
            for(Button button : buttons)
            {
                canvas.drawBitmap(button.getDisplay(),button.getLocation().x,button.getLocation().y,basePaint);
            }
        }

        for(Button button : permButtons)
        {
            canvas.drawBitmap(button.getDisplay(),button.getLocation().x,button.getLocation().y,basePaint);
        }

    }

    public ArrayList<Button> getPermButtons() {
        return permButtons;
    }
}
