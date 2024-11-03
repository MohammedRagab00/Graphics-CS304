package Turtle;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class TurtleGLEventListener implements GLEventListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // White background

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-300.0, 300.0, -300.0, 300.0, -1.0, 1.0);
    }

    double x1 = 1, x2 = 1, x3 = 1, x4 = 1, x5 = 1;

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();
        gl.glRotated(x1+=1.3,0,0,1);
        gl.glScaled(.7, .7, 1);
        drawTurtle(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-200, 200, 0);
        gl.glRotated(x2++,0,0,1);
        gl.glScaled(.2, .2, 1);
        drawTurtle(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(200, 200, 0);
        gl.glRotated(x3++,0,0,1);
        gl.glScaled(.2, .2, 1);
        drawTurtle(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(200, -200, 0);
        gl.glRotated(x4++,0,0,1);
        gl.glScaled(.2, .2, 1);
        drawTurtle(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-200, -200, 0);
        gl.glRotated(x5++,0,0,1);
        gl.glScaled(.2, .2, 1);
        drawTurtle(gl);
        gl.glPopMatrix();

//        gl.glTranslated(-100, 100, 0);

//        gl.glRotated(x++,0,0,1);
//        gl.glScaled(.3, .3, 1);
//        x-=0.01;

//        drawTurtle(gl);

//        gl.glScaled(.3, .3, 1);


    }

    private void drawCircle(GL gl, int centerX, int centerY, int radius, int segments) {
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i += 360 / segments) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(centerX + radius * Math.cos(angle),
                    centerY + radius * Math.sin(angle));
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    private void drawTurtle(GL gl) {
        // Draw turtle shell (outer circle)
        gl.glColor3f(41f/255, 88f/255, 37f/255); // Dark green
        drawCircle(gl, 0, 0, 200, 60);

        // Draw turtle shell
        gl.glColor3f(49f/255, 207f/255, 47f/255);
        drawCircle(gl, 0, 0, 195, 60);

        // Draw turtle shell
        gl.glColor3f(24f/255, 153f/255, 26f/255);
        drawCircle(gl, 0, 0, 175, 60);

        // Draw turtle shell (inner circle)
        gl.glColor3f(76f/255, 229f/255, 76f/255); // Light green
        drawCircle(gl, 0, 0, 145, 60);

        // Draw head
        gl.glColor3f(0.7f, 1.0f, 0.7f); // Pale green
        drawCircle(gl, 0, 230, 60, 30);

        // Draw eyes
        gl.glColor3f(0.0f, 0.0f, 0.0f); // Black
        drawCircle(gl, -20, 250, 10, 20);
        drawCircle(gl, 20, 250, 10, 20);

        // Draw smile (corrected to be right-side up)
        gl.glBegin(GL.GL_LINE_STRIP);
        for (int i = 0; i <= 180; i += 5) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(0 + 20 * Math.cos(angle), 210 + 10 * Math.sin(angle));
        }
        gl.glEnd();

        // Draw legs
        gl.glColor3f(0.6f, 0.8f, 0.6f); // Greenish gray
        drawCircle(gl, -150, -150, 50, 30); // Bottom left
        drawCircle(gl, 150, -150, 50, 30); // Bottom right
        drawCircle(gl, -150, 150, 50, 30); // Top left
        drawCircle(gl, 150, 150, 50, 30); // Top right

        // Draw tail
        gl.glColor3f(103f/255, 153f/255, 102f/255);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(0, -300+40);
        gl.glVertex2i(260-300, -300+102);
        gl.glVertex2i(340-300, -300+102);
        gl.glEnd();

        // Draw foots
        drawCircle(gl, -180, -180, 20, 20);
        drawCircle(gl, 180, -180, 20, 20);
        drawCircle(gl, -180, 180, 20, 20);
        drawCircle(gl, 180, 180, 20, 20);
    }
}