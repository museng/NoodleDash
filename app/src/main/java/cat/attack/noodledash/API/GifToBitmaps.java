package cat.attack.noodledash.API;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.media.*;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

import cat.attack.noodledash.MainView;

/**
 * Created by kegan on 11/11/2015.
 */
public class GifToBitmaps {
    public static ArrayList<Bitmap> getBitmaps(MainView view, int resource)
    {
        AssetFileDescriptor afd = view.getResources().openRawResourceFd(resource);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        long timeInMicro = Long.parseLong( time ) * 1000;
        ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
        for(int i = 0; i < timeInMicro; i++)
        {
            Bitmap frame = retriever.getFrameAtTime(i,MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            if(!frames.contains(frame))
            {
                frames.add(frame);
            }
        }
        return frames;
    }
}
