package pe.devpicon.android.mytestinglabapp.scanner


import android.content.Context
import android.graphics.Rect
import android.hardware.Camera
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.io.IOException
import java.util.*

/**
 * Created by Jaydipsinh Zala on 22/2/16.
 */
class CameraPreview// Constructor that obtains context and camera
(context: Context, private val mCamera: Camera) : SurfaceView(context), SurfaceHolder.Callback {
    private val mSurfaceHolder: SurfaceHolder = this.holder
    private val onFocusListener: OnFocusListener

    var isNeedToTakePic = false

    private val myAutoFocusCallback = Camera.AutoFocusCallback { success, camera ->
        if (success) {
            //mCamera.cancelAutoFocus()
        }
    }

    init {
        this.mSurfaceHolder.addCallback(this)
        this.mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        this.onFocusListener = context as OnFocusListener
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        try {
            mCamera.setPreviewDisplay(surfaceHolder)
            mCamera.parameters.focusMode = Camera.Parameters.FOCUS_MODE_AUTO
            mCamera.setDisplayOrientation(90)
            mCamera.startPreview()
        } catch (e: IOException) {
            // left blank for now
            e.printStackTrace()
        }

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        mCamera.stopPreview()
        this.mSurfaceHolder.removeCallback(this)
        mCamera.release()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {
        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(surfaceHolder)
            mCamera.startPreview()
        } catch (e: Exception) {
            // intentionally left blank for a test
            e.printStackTrace()
        }

    }

    /**
     * Called from PreviewSurfaceView to set touch focus.
     *
     * @param - Rect - new area for auto focus
     */
    fun doTouchFocus(tfocusRect: Rect) {
        try {
            val focusList = ArrayList<Camera.Area>()
            val focusArea = Camera.Area(tfocusRect, 1000)
            focusList.add(focusArea)

            val param = mCamera.parameters
            param.focusAreas = focusList
            param.meteringAreas = focusList
            mCamera.parameters = param

            mCamera.autoFocus(myAutoFocusCallback)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (isNeedToTakePic) {
            onFocusListener.onFocused()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            val touchRect = Rect(
                    (x - 100).toInt(),
                    (y - 100).toInt(),
                    (x + 100).toInt(),
                    (y + 100).toInt())


            val targetFocusRect = Rect(
                    touchRect.left * 2000 / this.width - 1000,
                    touchRect.top * 2000 / this.height - 1000,
                    touchRect.right * 2000 / this.width - 1000,
                    touchRect.bottom * 2000 / this.height - 1000)

            doTouchFocus(targetFocusRect)
        }

        return false
    }
}