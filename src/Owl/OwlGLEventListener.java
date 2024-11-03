package Owl;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

class OwlGLEventListener implements GLEventListener {

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(130 / 255f, 0.0f, 1.0f, 1.0f);

        gl.glViewport(0, 0, 600, 400);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 600.0, 0.0, 400.0, -1.0, 1.0);

    }

    double movingTheCircle = 0, deg = 0;

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        rgb(255, 220, 1, gl);

        // Nose
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(380, 250);
        gl.glVertex2i(220, 250);
        gl.glVertex2i(300, 130);
        gl.glEnd();

        // Eye
        rgb(255, 255, 255, gl);
        circle(400, 280, 120, gl);
        circle(200, 280, 120, gl);


        // Red Ball
        rgb(245, 1, 8, gl);
        gl.glPushMatrix();
        gl.glTranslated(movingTheCircle += 6, 0, 0);
        if (movingTheCircle >= 650) {
            movingTheCircle = -20;
            deg = 0;
        }
        circle(0, 0, 50, gl);
        gl.glPopMatrix();

        // nen el 3een
        rgb(0, 0, 0, gl);
        gl.glPushMatrix();
        gl.glTranslated(400, 400, 0);
        gl.glRotated(deg, 0, 0, 1);
        deg += .5;
//        if (deg >= 55) {
//            deg = 0;
//        }
        circle(-70, -130, 35, gl);
        gl.glPopMatrix();

        // nen el 3een el tany
        gl.glPushMatrix();
        gl.glTranslated(200, 400, 0);
        gl.glRotated(deg, 0, 0, 1);
        gl.glScaled(1, 1, 1);
        circle(-70, -130, 35, gl);
        gl.glPopMatrix();
    }

    public void rgb(float red, float green, float blue, GL gl) {
        gl.glColor3f(red / 255, green / 255, blue / 255);
    }

    public void circle(int a, int b, double r, GL gl) {
        gl.glBegin(GL.GL_POLYGON);
        for (double i = 0; i <= 360; i++) {
            double x = r * Math.cos(i);
            double y = r * Math.sin(i);
            gl.glVertex2d(x += a, y += b);
        }
        gl.glEnd();
    }


    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {
    }

    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {
    }

    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }
}