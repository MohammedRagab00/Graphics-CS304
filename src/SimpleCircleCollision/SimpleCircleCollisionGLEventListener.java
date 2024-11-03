package SimpleCircleCollision;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

class SimpleCircleCollisionGLEventListener implements GLEventListener {
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(0f, 0.0f, 0.0f, 1.0f);

        gl.glViewport(0, 0, 600, 400);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 600.0, 0.0, 400.0, -1.0, 1.0);

    }

    double x = 0, y = 0, deg = 0, ix = 2.5, iy = 2.5;
//    double x1 = Math.random()*600,y1 = Math.random()*400;
    double x1 = 100, y1 =70;
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        gl.glLineWidth(0.001f);
        rgb(100, 100, 100, gl);
        for (int i = 0; i <= 400; i += 20) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2i(0, i);
            gl.glVertex2i(600, i);
            gl.glEnd();
        }
        for (int i = 0; i <= 600; i += 20) {
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2i(i, 0);
            gl.glVertex2i(i, 400);
            gl.glEnd();
        }

        rgb(0, 0, 255, gl);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(0, 200);
        gl.glVertex2i(600, 200);

        gl.glVertex2i(300, 0);
        gl.glVertex2i(300, 400);
        gl.glEnd();

        // The Logic
        rgb(255, 220, 1, gl);
        gl.glPushMatrix();
        gl.glTranslated(100 + x, 70 + y, 0);
        gl.glRotated(deg += 70, 0, 0, 1);
        if (y-70+y1 >= 280 || y-70+y1 <= -20) {
            iy *= -1;
        }
        if (x-100+x1 >= 450 || x-100+x1 <= -50) {
            ix *= -1;
        }
        x += ix;
        y += iy;

        ring(gl, 0f, 0f, 50f, 100);
        // Triangle
        gl.glBegin(GL.GL_POLYGON);
        gl.glVertex2d(-50, 0);
        gl.glVertex2d(25, 43.30); // y should be 25 * sqrt(3).
        gl.glVertex2d(25, -43.30);
        gl.glEnd();

        gl.glPopMatrix();

    }

    public void rgb(float red, float green, float blue, GL gl) {
        gl.glColor3f(red / 255, green / 255, blue / 255);
    }

    private void ring(GL gl, float centerX, float centerY, float radius, int segments) {
        gl.glBegin(GL.GL_LINE_LOOP);
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float x = centerX + radius * (float) Math.cos(angle);
            float y = centerY + radius * (float) Math.sin(angle);
            gl.glVertex2f(x, y);
        }
        gl.glEnd();
    }


    public void circle(GL gl, int a, int b, double r) {
        gl.glBegin(GL.GL_POLYGON);
        for (double i = 0; i <= 360; i++) {
            double x = r * Math.cos(i) + a;
            double y = r * Math.sin(i) + b;
            gl.glVertex2d(x, y);
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