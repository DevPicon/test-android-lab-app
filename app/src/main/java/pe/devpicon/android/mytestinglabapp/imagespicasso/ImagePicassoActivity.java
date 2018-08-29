package pe.devpicon.android.mytestinglabapp.imagespicasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import pe.devpicon.android.mytestinglabapp.R;

public class ImagePicassoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView1, imageView2;
    EditText editText1, editText2;
    Button buttonLoadImage1, buttonLoadImage2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picasso);

        imageView1 = findViewById(R.id.image_view_image_1);
        imageView2 = findViewById(R.id.image_view_image_2);

        editText1 = findViewById(R.id.edit_text_image_1);
        editText1.setText("http://shopping-devel.s3.amazonaws.com/product-images/402.jpg");

        editText2 = findViewById(R.id.edit_text_image_2);
        editText2.setText("https://s.cornershopapp.com/product-images/376152.jpg");

        buttonLoadImage1 = findViewById(R.id.button_load_image_1);
        buttonLoadImage1.setOnClickListener(this);
        buttonLoadImage2 = findViewById(R.id.button_load_image_2);
        buttonLoadImage2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_load_image_1:
                String imageUrl1 = editText1.getText().toString();
                loadImage(imageUrl1, imageView1);
                break;

            case R.id.button_load_image_2:
                String imageUrl2 = editText2.getText().toString();
                loadImage(imageUrl2, imageView2);
                break;

        }
    }

    private void loadImage(final String imageUrl, ImageView imageView) {
        ImageLoader.with(this)
                .load(imageUrl)
                .into(imageView, new ImageLoader.Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ImagePicassoActivity.this, "onSuccess " + imageUrl, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(ImagePicassoActivity.this, "onError  " + imageUrl, Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
