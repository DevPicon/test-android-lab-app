package pe.devpicon.android.mytestinglabapp.roundedimage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_rounded_image.*
import pe.devpicon.android.mytestinglabapp.R

class RoundedImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rounded_image)

        Picasso.with(this@RoundedImageActivity)
                .load("http://www.studio8apps.com/wp-content/uploads/2014/05/square_instapic_200-200x200.png")
                .transform(CircleTransform())
                .into(picasso_rounded_image)

    }
}
