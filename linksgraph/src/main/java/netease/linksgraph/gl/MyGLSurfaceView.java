package netease.linksgraph.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by hzsunjianshun on 17/5/13.
 */

public class MyGLSurfaceView extends GLSurfaceView {

    public MyGLSurfaceView(Context context) {
        this(context, null);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextFactory(new ContextFactory());
        setEGLConfigChooser(true);
        setRenderer(new Renderer());
    }

    private static class ContextFactory implements EGLContextFactory {

        private static int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

        @Override
        public EGLContext createContext(EGL10 egl10, EGLDisplay eglDisplay, EGLConfig eglConfig) {
            int[] attrib_list = {EGL_CONTEXT_CLIENT_VERSION, 2, EGL10.EGL_NONE};
            return egl10.eglCreateContext(eglDisplay, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list);
        }

        @Override
        public void destroyContext(EGL10 egl10, EGLDisplay eglDisplay, EGLContext eglContext) {
            egl10.eglDestroyContext(eglDisplay, eglContext);
        }
    }

    private static class Renderer implements GLSurfaceView.Renderer {

        private final String vertexShaderCode =

                // This matrix member variable provides a hook to manipulate
                // the coordinates of objects that use this vertex shader.
                "uniform mat4 uMVPMatrix;   \n" +

                        "attribute vec4 vPosition;  \n" +
                        "void main(){               \n" +
                        // The matrix must be included as part of gl_Position
                        // Note that the uMVPMatrix factor *must be first* in order
                        // for the matrix multiplication product to be correct.
                        " gl_Position = uMVPMatrix * vPosition; \n" +

                        "}  \n";


        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);

            float ratio = (float) width / height;
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            GLU.gluLookAt(gl, 0, 0, -5f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

//            gl.glVertexPointer();
        }
    }
}
