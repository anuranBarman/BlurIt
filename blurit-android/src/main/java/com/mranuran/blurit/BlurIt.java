package com.mranuran.blurit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Anuran Barman on 22/1/18.
 */

public class BlurIt {

    private static  final float BITMAP_SCALE=0.3f;
    private static final float BLUR_RADIUS=7f;
    private Bitmap image;

    private Context context;
    private float intensity = 08f;
    private float MAX_RADIUS=25;

    private float MIN_RADIUS=0;
    private boolean async = false;

    BlurIt(Context context) {
        this.context = context;
    }


    private  Bitmap blur(){

        if (image == null) {
            return image;
        }

        int width= Math.round(image.getWidth());
        int height = Math.round(image.getHeight());

        Bitmap input= Bitmap.createScaledBitmap(image,width,height,false);

        Bitmap output= Bitmap.createBitmap(input);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));


        Allocation inputallocation= Allocation.createFromBitmap(rs,input);
        Allocation outputallocation= Allocation.createFromBitmap(rs,output);
        intrinsicBlur.setRadius(intensity);
        intrinsicBlur.setInput(inputallocation);
        intrinsicBlur.forEach(outputallocation);

        outputallocation.copyTo(output);

        return output;
    }

    @NonNull
    public static BlurIt with(Context context) {
        return new BlurIt(context);
    }



    public BlurIt load(Bitmap bitmap) {
        this.image = bitmap;
        return this;
    }

    public BlurIt load(int res) {
        image = BitmapFactory.decodeResource(context.getResources(), res);
        return this;
    }

    public BlurIt intensity(float intensity) {
        if (intensity<MAX_RADIUS && intensity > 0)
            this.intensity = intensity;
        else
            this.intensity = MAX_RADIUS;
        return this;
    }

    public BlurIt Async(boolean async) {
        this.async = async;
        return this;
    }

    public void into(ImageView imageView) {

        if (async) {
            new AsyncBlurImage(imageView).execute();
        }
        else {
            try {
                imageView.setImageBitmap(blur());


            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Bitmap getImageBlur() {

        return blur();
    }



    private class AsyncBlurImage extends AsyncTask<Void, Void, Bitmap> {
        private WeakReference<ImageView> weakReference;

        public AsyncBlurImage(ImageView image) {
            this.weakReference = new WeakReference<ImageView>(image);
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return blur();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            ImageView imageView = weakReference.get();

            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }

        }
    }

}
