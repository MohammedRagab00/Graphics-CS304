package Traffic;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class quizEventListener implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(4f / 255, 51f / 255, 12f / 255, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-300.0, 300.0, -300.0, 300.0, -1.0, 1.0);
    }

    ArrayList<cars> cars = new ArrayList<>();

    double deg = 0.0, deg1 = 0.0;

    boolean flag = false;

//    int x1 = -340, x2 = 340;

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawRoad(gl);
        gl.glPushMatrix();
        gl.glTranslated(-200, 200, 0);
        gl.glScaled(.4, .4, 1);
        drawTree(gl, 0, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(200, 200, 0);
        gl.glScaled(.4, .4, 1);
        drawTree(gl, 0, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-200, -250, 0);
        gl.glScaled(.4, .4, 1);
        drawTree(gl, 0, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(200, -250, 0);
        gl.glScaled(.4, .4, 1);
        drawTree(gl, 0, 0);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(107f / 255, 107f / 255, 107f / 255);
        gl.glTranslated(-30, 130, 0);
        drawRect(gl, 0, 0, 60, 80);

        if (flag) {
            gl.glColor3f(107f / 255, 8f / 255, 15f / 255);

        } else {
            gl.glColor3f(32f / 255, 107f / 255, 44f / 255);
        }
        drawCircle(30, 50, 20, gl);
        gl.glPopMatrix();

//        gl.glPushMatrix();
//        gl.glColor3f(0f, 18f / 255, 154f / 255);
//        if (!flag)
//            x1 += 10;
//        gl.glTranslated(x1, 20, 0);
//        drawRect(gl, 0, 0, 60, 40);
//        gl.glPopMatrix();
//        if (x1 >= 320)
//            x1 = -360;
//
//        if (!flag)
//            x2 -= 10;
//        gl.glPushMatrix();
//        gl.glColor3f(199f / 255, 58f / 255, 60f / 255);
//        gl.glTranslated(x2, -90, 0);
//        drawRect(gl, 0, 0, 60, 40);
//        gl.glPopMatrix();
//        if (x2 <= -320)
//            x2 = 360;

        for (cars car : cars) {
            if (!flag)
                car.x += car.s;
            gl.glPushMatrix();
            gl.glColor3f(car.r, car.g, car.b);
            gl.glTranslated(car.x , car.y, 0);
            drawRect(gl, 0, 0, 60, 40);
            gl.glPopMatrix();
            if (car.s <= 0 && car.x <= -320)
                car.x = 320;
            else if (car.x >= 320 && car.s >= 0)
                car.x = -320;
        }
//        double r = 70, n = 21, startAngle, steps = 5;
//
//        r = 100;
//        n = 360;
//        startAngle = 90;
//        steps = 1;
//        drawPolyLines(gl, r, new Color(1f, 0f, 0f, 1f), n, startAngle, steps);
//
//        gl.glPushMatrix();
//        gl.glRotated(deg += .9, 0, 0, 1);
//        gl.glTranslated(0, 100, 1);
//        gl.glColor3f(1, 1, 1);
////        gl.glScaled();
//        drawCircle(0, 0, 10, gl);
//        gl.glPopMatrix();
//
//        /* Circle */
//
//        r = 200;
//
//        drawPolyLines(gl, r, new Color(1f, 1f, 0f, 1f), n, startAngle, steps);
//
//        gl.glPushMatrix();
//        gl.glRotated(deg1 -= .9, 0, 0, 1);
//        gl.glTranslated(0, 200, 1);
////        gl.glScaled();
//        gl.glColor3f(1, 1, 1);
//        drawCircle(0, 0, 40, gl);
//        gl.glPopMatrix();


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

//    void drawCar(GL gl, int x, int y) {}

    private void drawCircle(GL gl, int centerX, int centerY, int radius, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(centerX + radius * Math.cos(angle), centerY + radius * Math.sin(angle));
        }
        gl.glEnd();
    }

    private void drawRect(GL gl, int x, int y, int width, int height) {
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x + width, y);
        gl.glVertex2i(x + width, y + height);
        gl.glVertex2i(x, y + height);
        gl.glEnd();
    }

    private void drawTree(GL gl, int x, int y) {
        gl.glColor3f(0.545f, 0.271f, 0.075f);
        drawRect(gl, x - 10, y, 20, 90); // Trunk
        drawCircle(gl, x, y + 115, 50, 0.0f, 1f, 1f / 255); // Leaves
        drawCircle(gl, x, y + 145, 41, 0.0f, 1f, 1f / 255); // Leaves
        drawCircle(gl, x, y + 170, 33, 0.0f, 1f, 1f / 255); // Leaves
    }

    void drawRoad(GL gl) {
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(41f / 255, 40f / 255, 41f / 255);
        gl.glVertex2i(-300, -150);
        gl.glVertex2i(300, -150);
        gl.glVertex2i(300, 150);
        gl.glVertex2i(-300, 150);
        gl.glEnd();

        gl.glColor3f(1f, 1f, 1f);
        for (int i = -300; i < 300; i += 50) {
            gl.glBegin(GL.GL_POLYGON);
            gl.glVertex2i(i, -10);
            gl.glVertex2i(i + 35, -10);
            gl.glVertex2i(i + 35, 10);
            gl.glVertex2i(i, 10);
            gl.glEnd();
        }
    }

    void drawStars(GL gl) {
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1, 1, 1);
        for (int i = 0; i < 100; i++) {
            gl.glVertex2d(-300 + Math.random() * 600, -300 + Math.random() * 600);
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            flag = !flag;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        cars.add(new cars(x, y, (float) Math.random(), (float) Math.random(), (float) Math.random(), Math.random() * 20 - 10));
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

    double x, y;

    // convert the x coordinate to the range of the viewport
    public double convertX(double x, double width) {
        return (x / width) * 600 - 300;
    }

    // convert the y coordinate to the range of the viewport
    public double convertY(double y, double height) {
        return (1 - y / height) * 600 - 300;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = convertX(e.getX(), e.getComponent().getWidth());
        y = convertY(e.getY(), e.getComponent().getHeight());
    }
}