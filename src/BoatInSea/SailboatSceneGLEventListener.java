package BoatInSea;

import ShapesApp.ShapesEventListener;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.*;

public class SailboatSceneGLEventListener implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.529f, 0.808f, 0.922f, 1.0f); // Light blue sky

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 600.0, 0.0, 400.0, -1.0, 1.0);
    }

    double x1 = 0, x2 = 0, x3 = 0;

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();

        drawSea(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
//        gl.glTranslated(x3++, 0, 0);
        drawWave(gl);
        gl.glPopMatrix();

        if (x1 > 600) x1 = -200;
        if (x2 > 600) x2 = -100;

//        x2 += 4;
        gl.glPushMatrix();

        gl.glTranslated(x2, 100, 0);
        gl.glScaled(.4, .4, 1);
        drawBoat(gl);
        gl.glPopMatrix();

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

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }

    private void drawSea(GL gl) {
        // Draw sea
        gl.glColor3f(1f / 255, 100f / 255, 202f / 255); // Deep blue
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(0, 0);
        gl.glVertex2i(600, 0);
        gl.glVertex2i(600, 200);
        gl.glVertex2i(0, 200);
        gl.glEnd();
    }

    private void drawBoat(GL gl) {
        // Draw boat hull
        gl.glColor3f(153f / 255, 75f / 255, 26f / 255); // Brown
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(200, 150);
        gl.glVertex2i(400, 150);
        gl.glVertex2i(450, 200);
        gl.glVertex2i(150, 200);
        gl.glEnd();

        // Draw boat deck
        gl.glColor3f(204f / 255, 126f / 255, 51f / 255); // Light brown
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(200, 200);
        gl.glVertex2i(400, 200);
        gl.glVertex2i(390, 210);
        gl.glVertex2i(210, 210);
        gl.glEnd();

        // Draw sail
        gl.glColor3f(1.0f, 1.0f, 1.0f); // White
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(300, 210);
        gl.glVertex2i(300, 350);
        gl.glVertex2i(400, 210);
        gl.glEnd();

        // Draw mast
        gl.glColor3f(102f / 255, 50f / 255, 0f); // Brown
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2i(295, 210);
        gl.glVertex2i(305, 210);
        gl.glVertex2i(305, 360);
        gl.glVertex2i(295, 360);
        gl.glEnd();
    }

    double degree = 0;

    private void drawWave(GL gl) {
        // Draw waves
        gl.glColor3f(34f / 255, 140f / 255, 242f / 255); // Lighter blue
        gl.glLineWidth(2.0f); // Make lines slightly thicker for visibility

        int numberOfWaves = 5;
        int waveSpacing = 200 / numberOfWaves; // Divide the sea height into equal parts

        for (int wave = 0; wave < numberOfWaves; wave++) {
            int baseHeight = wave * waveSpacing;
            gl.glBegin(GL.GL_LINE_STRIP);
            for (int x = 0; x < 600; x++) {
                double y = baseHeight + (Math.sin(x * Math.PI / 180.0 + degree) * (waveSpacing / 4.0));
                degree += Math.PI / 99000;
                gl.glVertex2d(x, y);
            }
            gl.glEnd();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                x2 -= 4;
                break;
            case KeyEvent.VK_RIGHT:
                 x2+= 4;
                break;
//            case KeyEvent.VK_UP:
//                 -= 10.0 / zoomLevel;
//                break;
//            case KeyEvent.VK_DOWN:
//                 += 10.0 / zoomLevel;
//                break;
        }
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