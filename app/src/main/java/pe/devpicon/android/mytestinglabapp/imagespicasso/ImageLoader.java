package pe.devpicon.android.mytestinglabapp.imagespicasso;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoTools;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import java.io.IOException;

public class ImageLoader {

    private ImageLoader() {

    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public static class Builder {

        private Context context;

        private ImageView target;
        private String imageUrl;
        private Target customTarget;
        private Uri imageUri;
        private int imageResource;

        private Bitmap.Config config;

        private boolean fitCenter;
        private boolean centerCrop;
        private boolean noFade;
        private boolean centerInside;
        private boolean enableLogging;

        private int imagePlaceholder;
        private int imageError;

        private int sizeX;
        private int sizeY;

        private Callback callback;

        public Builder(Context context) {
            this.context = context;
        }

        public ImageLoader.Builder load(String imageUrl) {
            // Only set imageUrl if not empty, otherwise Picasso or Glide have troubles managing an empty url instead of null
            if (!TextUtils.isEmpty(imageUrl)) {
                this.imageUrl = imageUrl;
            }

            return this;
        }

        public ImageLoader.Builder load(Uri imageUri) {
            this.imageUri = imageUri;
            return this;
        }

        public ImageLoader.Builder load(int imageResource) {
            this.imageResource = imageResource;
            return this;
        }

        public ImageLoader.Builder fitCenter() {
            this.fitCenter = true;
            return this;
        }

        public ImageLoader.Builder noFade() {
            this.noFade = true;
            return this;
        }

        public ImageLoader.Builder centerCrop() {
            this.centerCrop = true;
            return this;
        }

        public ImageLoader.Builder centerInside() {
            this.centerInside = true;
            return this;
        }

        public ImageLoader.Builder enableLogging() {
            this.enableLogging = true;
            return this;
        }

        public ImageLoader.Builder resize(int sizeX, int sizeY) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            return this;
        }

        public ImageLoader.Builder config(Bitmap.Config config) {
            this.config = config;
            return this;
        }

        public ImageLoader.Builder placeholder(int imagePlaceholder) {
            this.imagePlaceholder = imagePlaceholder;
            return this;
        }

        public ImageLoader.Builder error(int imageError) {
            this.imageError = imageError;
            return this;
        }

        public void into(ImageView target) {
            this.target = target;

            loadImage();
        }

        public void into(Target customTarget) {
            this.customTarget = customTarget;

            loadImage();
        }

        public void into(ImageView target, Callback callback) {
            this.target = target;
            this.callback = callback;

            loadImage();
        }


        /**
         * This should be done in background under your own risk
         *
         * @return Bitmap
         * @throws IOException
         */
        public Bitmap loadImageSync() throws IOException {
            RequestCreator requestCreator = setupRequestCreator();

            return requestCreator.get();
        }

        private static final String TAG = "Builder";

        private void loadImage() {

            RequestCreator requestCreator = setupRequestCreator();

            //load into target
            if (callback != null) {
                requestCreator.into(target, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError() {
                        callback.onError();
                    }
                });
            } else if (customTarget != null) {
                requestCreator.into(customTarget);
            } else {
                requestCreator.into(target);
            }
        }

        private RequestCreator setupRequestCreator() {
            RequestCreator requestCreator;
            if (imageUri != null) {
                requestCreator = Picasso.with(context).load(imageUri);
            } else if (imageResource != 0) {
                requestCreator = Picasso.with(context).load(imageResource);
            } else {
                requestCreator = Picasso.with(context).load(imageUrl);
            }

            if (sizeX != 0 && sizeY != 0) {
                requestCreator.resize(sizeX, sizeY);
            }

            if (centerCrop) {
                requestCreator.centerCrop();
            }

            if (fitCenter) {
                requestCreator.fit();
            }

            if (noFade) {
                requestCreator.noFade();
            }

            if (centerInside) {
                requestCreator.centerInside();
            }

            if (imagePlaceholder != 0) {
                requestCreator.placeholder(imagePlaceholder);
            }

            if (imageError != 0) {
                requestCreator.error(imageError);
            }

            if (config != null) {
                requestCreator.config(config);
            }

            return requestCreator;
        }
    }


    public static void clearCache(Context context) {
        PicassoTools.clearCache(Picasso.with(context));
    }

    public interface Callback {
        void onSuccess();

        void onError();
    }
}