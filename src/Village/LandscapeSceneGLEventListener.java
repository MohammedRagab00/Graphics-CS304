package Village;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class LandscapeSceneGLEventListener implements GLEventListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.529f, 0.808f, 0.922f, 1.0f); // Sky blue background

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0.0, 1010.0, 0.0, 600.0, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Draw ground
        drawRect(gl, 0, 0, 1010, 150, 0.133f, 0.545f, 0.133f);

        // Draw mountains
        drawTriangle(gl, 160, 150, 260, 450, 360, 150, 114f / 255, 79f / 255, 11f / 255);
        drawTriangle(gl, 330, 150, 430, 450, 530, 150, 114f / 255, 79f / 255, 11f / 255);
        drawTriangle(gl, 510, 150, 610, 450, 710, 150, 114f / 255, 79f / 255, 11f / 255);
        drawTriangle(gl, 690, 150, 790, 450, 890, 150, 114f / 255, 79f / 255, 11f / 255);

        // Draw snow caps
        drawTriangle(gl, 210, 330, 260, 450, 310, 330, 1.0f, 1.0f, 1.0f);
        drawTriangle(gl, 380, 330, 430, 450, 480, 330, 1.0f, 1.0f, 1.0f);
        drawTriangle(gl, 560, 330, 610, 450, 660, 330, 1.0f, 1.0f, 1.0f);
        drawTriangle(gl, 740, 330, 790, 450, 840, 330, 1.0f, 1.0f, 1.0f);

        // Draw some like water
        drawRect(gl, 95, 150, 800, 10, 0f / 255, 135f / 255, 200f / 255);

        // Draw house
        drawRect(gl, 375, 150, 260, 100, 178f / 255, 126f / 255, 76f / 255); // Main body
        drawTriangle(gl, 365, 250, 505, 340, 645, 250, 0.545f, 0.0f, 0.0f); // Roof
        drawRect(gl, 400, 200, 50, 50, 204f / 255, 204f / 255, 254f / 255); // Window
        drawRect(gl, 560, 200, 50, 50, 204f / 255, 204f / 255, 254f / 255); // Window
        drawRect(gl, 480, 150, 50, 80, 77f / 255, 51f / 255, 26f / 255); // Window

        // Draw trees
        drawTree(gl, 100, 160);
        drawTree(gl, 140, 150);

        drawTree(gl, 780, 150);
        drawTree(gl, 740, 150);
        drawTree(gl, 700, 160);

        // Draw Grass
        for (int i = 0; i < 1010; i += 10) {
            drawGrass(gl, i, 150);
        }

        // Draw a flower
        drawFlower(gl, 90, 180, 5);
        drawFlower(gl, 110, 200, 5);
        drawFlower(gl, 820, 200, 5);

        // Draw fence
        for (int x = 45; x <= 945; x += 50) {
            drawRect(gl, x, 150, 10, 60, 153f / 255, 101f / 255, 51f / 255);
        }
        drawRect(gl, 45, 160, 920, 10, 153f / 255, 101f / 255, 51f / 255);
        drawRect(gl, 45, 180, 920, 10, 153f / 255, 101f / 255, 51f / 255);

        // Draw sun
        int centerX = 900, centerY = 500, radius = 80;

        gl.glColor3f(126f / 255, 127f / 255, 0f / 255);
        gl.glPointSize(1.2f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(centerX + 1.6 * radius, centerY);
        gl.glVertex2d(centerX - 1.6 * radius, centerY);

        gl.glVertex2d(centerX, centerY + 1.6 * radius);
        gl.glVertex2d(centerX, centerY - 1.6 * radius);

        gl.glVertex2d(centerX + 1.1 * radius, centerY + 1.1 * radius);
        gl.glVertex2d(centerX - 1.1 * radius, centerY - 1.1 * radius);

        gl.glVertex2d(centerX - 1.1 * radius, centerY + 1.1 * radius);
        gl.glVertex2d(centerX + 1.1 * radius, centerY - 1.1 * radius);
        gl.glEnd();
        drawCircle(gl, centerX, centerY, radius, 1.0f, 1.0f, 0.0f);

        // Draw clouds
        drawCloud(gl, 100, 500);

        drawCloud(gl, 600, 550);
        drawCloud(gl, 550, 510);

        // Draw birds
        drawBird(gl, 250, 500);
        drawBird(gl, 300, 520);
        drawBird(gl, 350, 490);
        drawBird(gl, 850, 400);
    }

    private void drawRect(GL gl, int x, int y, int width, int height, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x + width, y);
        gl.glVertex2i(x + width, y + height);
        gl.glVertex2i(x, y + height);
        gl.glEnd();
    }

    private void drawTriangle(GL gl, int x1, int y1, int x2, int y2, int x3, int y3, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(x1, y1);
        gl.glVertex2i(x2, y2);
        gl.glVertex2i(x3, y3);
        gl.glEnd();
    }

    private void drawCircle(GL gl, int centerX, int centerY, int radius, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(centerX + radius * Math.cos(angle), centerY + radius * Math.sin(angle));
        }
        gl.glEnd();
    }

    private void drawTree(GL gl, int x, int y) {
        drawRect(gl, x - 10, y, 20, 90, 0.545f, 0.271f, 0.075f); // Trunk
        drawCircle(gl, x, y + 115, 50, 0.0f, 1f, 1f / 255); // Leaves
        drawCircle(gl, x, y + 145, 41, 0.0f, 1f, 1f / 255); // Leaves
        drawCircle(gl, x, y + 170, 33, 0.0f, 1f, 1f / 255); // Leaves
    }

    private void drawCloud(GL gl, int x, int y) {
        drawCircle(gl, x, y, 30, 1.0f, 1.0f, 1.0f);
        drawCircle(gl, x + 20, y + 10, 30, 1.0f, 1.0f, 1.0f);
        drawCircle(gl, x + 40, y, 30, 1.0f, 1.0f, 1.0f);
    }

    private void drawBird(GL gl, int x, int y) {
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x - 10, y + 10);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x + 10, y + 10);
        gl.glEnd();
    }

    private void drawGrass(GL gl, int x, int y) {
        gl.glColor3f(108f / 255, 134f / 255, 36f / 255);
        gl.glPointSize(1.2f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(x, y);
        gl.glVertex2i(x - 2, y + 10);
        gl.glEnd();
    }

    private void drawFlower(GL gl, int centerX, int centerY, int radius) {
        gl.glColor3f(255f/255, 190f/255, 201f/255); // Pink color
        for (int i = 0; i < 360; i += 90) {
            double angle = Math.toRadians(i);
            int petalCenterX = centerX + (int) (radius * Math.cos(angle));
            int petalCenterY = centerY + (int) (radius * Math.sin(angle));
            drawCircle(gl, petalCenterX, petalCenterY, radius, 1.0f, 0.0f, 1.0f);
        }
        drawCircle(gl, centerX, centerY, radius, 252f/255, 1.0f, 0.0f); // Yellow color

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }
}