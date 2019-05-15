package pe.devpicon.android.mytestinglabapp.scanner

import android.hardware.Camera
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_scan_product.*
import pe.devpicon.android.mytestinglabapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ScanProductActivity : AppCompatActivity(), OnFocusListener {

    private var currentCameraId: Int = Camera.CameraInfo.CAMERA_FACING_BACK
    private lateinit var cameraPreview: CameraPreview
    private lateinit var camera: Camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_product)
        if (intent.hasExtra("camera_id")) {
            currentCameraId = intent.getIntExtra("camera_id", Camera.CameraInfo.CAMERA_FACING_BACK)
        }

        button_capture.setOnClickListener {
            it.isEnabled = false
            cameraPreview.isNeedToTakePic = true
            val downTime: Long = SystemClock.uptimeMillis()
            val evenTime: Long = SystemClock.uptimeMillis() + 100;
            val x: Float = cameraPreview.width / 2f
            val y: Float = cameraPreview.height / 2f
            var metaState: Int = 0
            val motionEvent: MotionEvent = MotionEvent.obtain(downTime, evenTime, MotionEvent.ACTION_DOWN, x, y, metaState)
            cameraPreview.dispatchTouchEvent(motionEvent)

        }

        button_switch_camera.visibility = if (Camera.getNumberOfCameras() > 1) View.VISIBLE else View.GONE
        button_switch_camera.setOnClickListener {
            camera.stopPreview()
            cameraPreview.holder.removeCallback(cameraPreview)
            camera.release()

            if (currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
                currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT
            } else {
                currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK
            }

            camera = getCameraInstance(currentCameraId)
            cameraPreview = CameraPreview(this@ScanProductActivity, camera)
            camera_preview.removeAllViews()
            camera_preview.addView(cameraPreview)
        }
    }

    override fun onResume() {
        super.onResume()
        camera = getCameraInstance(currentCameraId)
        cameraPreview = CameraPreview(this, camera)
        camera_preview.addView(cameraPreview)
    }

    private fun getCameraInstance(currentCameraId: Int) = Camera.open(currentCameraId)

    val pictureCallback = Camera.PictureCallback { data, _ ->
        run {
            val pictureFile = getOutputMediaFile() ?: return@run
            try {
                val fos = FileOutputStream(pictureFile)
                fos.write(data)
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getOutputMediaFile(): File? {
        val mediaStorageDir = File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyCameraApp")
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory")
                return null
            }
        }

        val mediaFile = File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + "DEMO_" + ".jpg")

        if (mediaFile.exists()) mediaFile.delete()

        return mediaFile
    }

    override fun onFocused() {
        Handler().postDelayed({
            camera.takePicture(null, null, pictureCallback)
            cameraPreview.isNeedToTakePic = false
            button_capture.isEnabled = true
        }, 1500)
    }
}
