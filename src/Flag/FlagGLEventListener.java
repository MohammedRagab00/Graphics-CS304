package Flag;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class FlagGLEventListener implements GLEventListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 600.0, 0.0, 300.0, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        /* Flag */
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(600, 0);
        gl.glVertex2i(600, 80);
        gl.glVertex2i(0, 80);
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 180);
        gl.glVertex2i(600, 180);
        gl.glVertex2i(600, 300);
        gl.glVertex2i(0, 300);
        gl.glEnd();

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 80);
        gl.glVertex2i(600, 80);
        gl.glVertex2i(600, 220);
        gl.glVertex2i(0, 220);
        gl.glEnd();

        gl.glColor3f(24f / 255, 62f / 255, 12f / 255);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(260, 190);
        gl.glVertex2i(340, 190);
        gl.glVertex2i(300, 220);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(240, 170);
        gl.glVertex2i(360, 170);
        gl.glVertex2i(300, 200);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(220, 150);
        gl.glVertex2i(380, 150);
        gl.glVertex2i(300, 180);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(200, 130);
        gl.glVertex2i(400, 130);
        gl.glVertex2i(300, 160);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(170, 110);
        gl.glVertex2i(430, 110);
        gl.glVertex2i(300, 140);
        gl.glEnd();

//        gl.glColor3f(59f / 255, 47f / 255, 29f / 255);
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(270, 80);
        gl.glVertex2i(330, 80);
        gl.glVertex2i(330, 110);
        gl.glVertex2i(270, 110);
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
