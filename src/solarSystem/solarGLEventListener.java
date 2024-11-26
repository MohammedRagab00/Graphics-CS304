package solarSystem;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.*;

public class solarGLEventListener implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0f, 0f, 0f, 1.0f); // Black

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-300.0, 300.0, -300.0, 300.0, -1.0, 1.0);
    }

    double deg = 0.0, deg1 = 0.0;

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawStars(gl);


        double r = 70, n = 21, startAngle, steps = 5;

        r = 100;
        n = 360;
        startAngle = 90;
        steps = 1;
        drawPolyLines(gl, r, new Color(1f, 0f, 0f, 1f), n, startAngle, steps);

        gl.glPushMatrix();
        gl.glRotated(deg += .9, 0, 0, 1);
        gl.glTranslated(0, 100, 1);
        gl.glColor3f(1, 1, 1);
//        gl.glScaled();
        drawCircle(0, 0, 10, gl);
        gl.glPopMatrix();

        /* Circle */

        r = 200;

        drawPolyLines(gl, r, new Color(1f, 1f, 0f, 1f), n, startAngle, steps);

        gl.glPushMatrix();
        gl.glRotated(deg1 -= .9, 0, 0, 1);
        gl.glTranslated(0, 200, 1);
//        gl.glScaled();
        gl.glColor3f(1, 1, 1);
        drawCircle(0, 0, 40, gl);
        gl.glPopMatrix();


//        gl.glPushMatrix();
////        gl.glTranslated(x3++, 0, 0);
//        drawWave(gl);
//        gl.glPopMatrix();


//        x2 += 4;
//        gl.glPushMatrix();
//
//        gl.glTranslated(x2, 100, 0);
//        gl.glScaled(.4, .4, 1);
//        drawBoat(gl);
//        gl.glPopMatrix();

//        x1 += 7;
//        gl.glPushMatrix();
//        gl.glTranslated(x1, -40, 0);
//        gl.glScaled(.7, .7, 1);
//        drawBoat(gl);
//        gl.glPopMatrix();


//        gl.glPushMatrix();
//        gl.glScaled(.7, .7, 1);
//        gl.glRotated(x1+=1.3,0,0,1);
//        drawTurtle(gl);
//        gl.glPopMatrix();

    }

    void drawStars(GL gl) {
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1, 1, 1);
        for (int i = 0; i < 100; i++) {
            gl.glVertex2d(-300+Math.random() * 600, -300+Math.random() * 600);
        }
        gl.glEnd();
    }

    void drawPolyLines(GL gl, double r, Color c, double sides, double startAngle, double steps) {
        gl.glColor3fv(c.getColorComponents(null), 0);
        gl.glBegin(GL.GL_LINE_LOOP);
        for (double i = startAngle; i < 360 * steps + startAngle; i += steps * 360.0 / sides) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }

    public void drawCircle(int a, int b, double r, GL gl) {
        gl.glBegin(GL.GL_POLYGON);
        for (double i = 0; i <= 360; i++) {
            double x = r * Math.cos(i);
            double y = r * Math.sin(i);
            gl.glVertex2d(x + a, y + b);
        }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}