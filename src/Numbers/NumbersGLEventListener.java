package Numbers;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.util.ArrayList;

public class NumbersGLEventListener implements GLEventListener {

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // White background

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-300.0, 300.0, -300.0, 300.0, -1.0, 1.0);

        numbers.add(new Numbers(7, -300, 270, 1, 1, 1));
        numbers.add(new Numbers(1, -300 + 50, 270 - 85, 1, 0, 0));
        numbers.add(new Numbers(8, -300 + 50 * 2, 270 - 85 * 2, 0, 1, 1));
        numbers.add(new Numbers(5, -300 + 50 * 3, 270 - 85 * 3, 1, 1, 0));
    }

    double x1 = 1, x2 = 1, x3 = 1, x4 = 1, x5 = 1;
    int angle = 0;

    ArrayList<Numbers> numbers = new ArrayList<>();

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        drawPlane(gl);

        for (Numbers n : numbers) {
            gl.glPushMatrix();
            gl.glTranslated(n.x += 5, n.y, 1);
            if (n.x >= 300) {
                n.x = -300;
                n.y -= 85;
            }
            if (n.y <= -300)
                n.y = 270;

            switch (n.t) {
                case 7:

                    gl.glScaled(1.4, 1.4, 1);

                    drawSeven(gl, n.r, n.g, n.b);
                    gl.glPopMatrix();

                    break;
                case 1:

                    gl.glScaled(2f, 2f, 1f);
                    drawOne(gl, n.r, n.g, n.b);
                    gl.glPopMatrix();

                    break;
                case 5:

                    drawFive(gl, n.r, n.g, n.b);
                    gl.glPopMatrix();

                    break;
                case 8:
                    gl.glPopMatrix();
                    drawEight(gl, n.r, n.g, n.b, n.x, n.y);
                    break;
            }
        }


    }

    void drawPlane(GL gl) {
        for (int i = -270; i < 300; i += 85) {
            drawLine(gl, -300, i, 300, i, 0f, 1f, 0f);
        }
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

    private void drawLine(GL gl, double x1, double y1, double x2, double y2, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);
        gl.glEnd();
    }

    private void drawLetterA(GL gl, double size, float r, float g, float b) {
        double halfSize = size / 2;
        // Center the base of A on the origin
        drawLine(gl, 0, halfSize / 2, -halfSize / 2, -size / 2, r, g, b); // Left diagonal
        drawLine(gl, 0, halfSize / 2, halfSize / 2, -size / 2, r, g, b);  // Right diagonal
        drawLine(gl, -halfSize / 4, -size / 4, halfSize / 4, -size / 4, r, g, b); // Horizontal bar
    }

    private void drawOne(GL gl, float r, float g, float b) {
        // Center the base of A on the origin
        drawLine(gl, 0, 5, 0, -5, r, g, b); // Left diagonal
        drawLine(gl, 0, 5, -1.7, 3, r, g, b);  // Right diagonal
    }


    private void drawSeven(GL gl, float r, float g, float b) {
        // Center the base of A on the origin
        gl.glPushMatrix();
        gl.glScaled(2, 2, 1);
        drawLine(gl, 2, 3, -1, -7, r, g, b); // Left diagonal
        drawLine(gl, 2, 3, -2, 3, r, g, b);  // Right diagonal
        drawLine(gl, -1, 0, 3, 0, r, g, b);  // Right diagonal
        gl.glPopMatrix();
    }


    private void drawEight(GL gl, float r, float g, float b, int x, int y) {

        gl.glColor3f(r, g, b);
        gl.glPushMatrix();
        gl.glTranslated(x, y - 7, 1);
        drawPolyLines(gl, 7, 360, 90, 1);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(x, y + 7, 1);
        drawPolyLines(gl, 7, 360, 90, 1);

        gl.glPopMatrix();
    }

    void drawPolyLines(GL gl, double r, double sides, double startAngle, double steps) {
        gl.glBegin(GL.GL_LINE_LOOP);
        for (double i = startAngle; i < 360 * steps + startAngle; i += steps * 360.0 / sides) {
            gl.glVertex2d(r * Math.cos(Math.toRadians(i)), r * Math.sin(Math.toRadians(i)));
        }
        gl.glEnd();
    }


    private void drawFive(GL gl, float r, float g, float b) {
        // Center the base of A on the origin
        drawLine(gl, 7, 12, -4, 12, r, g, b); // Left diagonal
        drawLine(gl, -4, 12, -4, 7, r, g, b);  // Right diagonal
        gl.glPushMatrix();
        gl.glRotated(-180, 0, 0, 1);
        drawLetterC(gl, 15, r, g, b); // Horizontal bar

        gl.glPopMatrix();


    }

    private void drawLetterC(GL gl, double size, float r, float g, float b) {
        double radius = size / 2;
        for (int i = 60; i <= 300; i++) { // Partial circle from 60° to 300°
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, radius * Math.cos(angle1), radius * Math.sin(angle1),
                    radius * Math.cos(angle2), radius * Math.sin(angle2),
                    r, g, b);
        }
    }

    private void drawLetterB(GL gl, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        // Center the vertical line on the origin
        drawLine(gl, -halfSize, halfSize, -halfSize, -halfSize, 0.0f, 0.0f, 1.0f); // Vertical line
        drawLine(gl, -halfSize, halfSize, halfSize, quarterSize, 0.0f, 0.0f, 1.0f); // Upper curve
        drawLine(gl, halfSize, quarterSize, -halfSize, 0, 0.0f, 0.0f, 1.0f); // To middle
        drawLine(gl, -halfSize, 0, halfSize, -quarterSize, 0.0f, 0.0f, 1.0f); // Lower curve
        drawLine(gl, halfSize, -quarterSize, -halfSize, -halfSize, 0.0f, 0.0f, 1.0f); // To bottom
    }

    private void drawLetterM(GL gl, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        // Center the letter M around the origin
        drawLine(gl, -halfSize, halfSize, -halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Left vertical line
        drawLine(gl, -halfSize, halfSize, 0, 0, 1.0f, 1.0f, 1.0f); // Left diagonal
        drawLine(gl, 0, 0, halfSize, halfSize, 1.0f, 1.0f, 1.0f); // Right diagonal
        drawLine(gl, halfSize, halfSize, halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Right vertical line
    }

    private void drawLetterH(GL gl, double x, double y, double size) {
        double halfSize = size / 2;
        drawLine(gl, x, y, x, y - size, 1.0f, 1.0f, 1.0f); // Left vertical line
        drawLine(gl, x, y - size / 2, x + size, y - size / 2, 1.0f, 1.0f, 1.0f); // Horizontal line
        drawLine(gl, x + size, y, x + size, y - size, 1.0f, 1.0f, 1.0f); // Right vertical line
    }

    private void drawLetterH(GL gl, double size) {
        double halfSize = size / 2;
        // Center the letter H around the origin
        drawLine(gl, -halfSize, halfSize, -halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Left vertical line
        drawLine(gl, -halfSize, 0, halfSize, 0, 1.0f, 1.0f, 1.0f); // Horizontal line in the center
        drawLine(gl, halfSize, halfSize, halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Right vertical line
    }

    private void drawNumber0(GL gl, double size) {
        double radius = size / 2;
        for (int i = 0; i < 360; i++) { // Full circle
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, radius * Math.cos(angle1), radius * Math.sin(angle1),
                    radius * Math.cos(angle2), radius * Math.sin(angle2),
                    1.0f, 1.0f, 1.0f);
        }
    }

    private void drawNumber3(GL gl, double size) {
        double halfSize = size / 2;
        double radius = size / 4;
        // Top semi-circle
        for (int i = 0; i <= 180; i++) {
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, halfSize * Math.cos(angle1), radius + radius * Math.sin(angle1),
                    halfSize * Math.cos(angle2), radius + radius * Math.sin(angle2),
                    1.0f, 1.0f, 1.0f);
        }
        // Bottom semi-circle
        for (int i = 0; i <= 180; i++) {
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, halfSize * Math.cos(angle1), -radius + radius * Math.sin(angle1),
                    halfSize * Math.cos(angle2), -radius + radius * Math.sin(angle2),
                    1.0f, 1.0f, 1.0f);
        }
    }

    private void drawNumber2(GL gl, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        drawLine(gl, -halfSize, halfSize, halfSize, halfSize, 1.0f, 1.0f, 1.0f); // Top horizontal
        drawLine(gl, halfSize, halfSize, -halfSize, 0, 1.0f, 1.0f, 1.0f);        // Diagonal
        drawLine(gl, -halfSize, 0, halfSize, -halfSize, 1.0f, 1.0f, 1.0f);       // Bottom horizontal
    }

    private void drawNumber1(GL gl, double size) {
        double halfSize = size / 2;
        drawLine(gl, 0, halfSize, 0, -halfSize, 1.0f, 1.0f, 1.0f); // Vertical line
    }

    private void drawLetterO(GL gl, double size) {
        double radius = size / 2;
        for (int i = 0; i < 360; i++) { // Full circle
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, radius * Math.cos(angle1), radius * Math.sin(angle1),
                    radius * Math.cos(angle2), radius * Math.sin(angle2),
                    1.0f, 1.0f, 1.0f);
        }
    }

    private void drawLetterE(GL gl, double size) {
        double halfSize = size / 2;
        drawLine(gl, -halfSize, halfSize, -halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Vertical line
        drawLine(gl, -halfSize, halfSize, halfSize, halfSize, 1.0f, 1.0f, 1.0f);   // Top horizontal
        drawLine(gl, -halfSize, 0, halfSize / 2, 0, 1.0f, 1.0f, 1.0f);             // Middle horizontal
        drawLine(gl, -halfSize, -halfSize, halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Bottom horizontal
    }

    private void drawLetterD(GL gl, double size) {
        double halfSize = size / 2;
        double radius = halfSize;
        drawLine(gl, -halfSize, halfSize, -halfSize, -halfSize, 1.0f, 1.0f, 1.0f); // Vertical line
        for (int i = -90; i <= 90; i++) { // Semi-circle for the rounded part
            double angle1 = Math.toRadians(i);
            double angle2 = Math.toRadians(i + 1);
            drawLine(gl, 0 + radius * Math.cos(angle1), radius * Math.sin(angle1),
                    0 + radius * Math.cos(angle2), radius * Math.sin(angle2),
                    1.0f, 1.0f, 1.0f);
        }
    }

    private void drawTurtle(GL gl) {
        // Draw turtle shell (outer circle)
        gl.glColor3f(41f / 255, 88f / 255, 37f / 255); // Dark green
        drawCircle(gl, 0, 0, 200, 60);

        // Draw turtle shell
        gl.glColor3f(49f / 255, 207f / 255, 47f / 255);
        drawCircle(gl, 0, 0, 195, 60);

        // Draw turtle shell
        gl.glColor3f(24f / 255, 153f / 255, 26f / 255);
        drawCircle(gl, 0, 0, 175, 60);

        // Draw turtle shell (inner circle)
        gl.glColor3f(76f / 255, 229f / 255, 76f / 255); // Light green
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
        gl.glColor3f(103f / 255, 153f / 255, 102f / 255);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2i(0, -300 + 40);
        gl.glVertex2i(260 - 300, -300 + 102);
        gl.glVertex2i(340 - 300, -300 + 102);
        gl.glEnd();

        // Draw foots
        drawCircle(gl, -180, -180, 20, 20);
        drawCircle(gl, 180, -180, 20, 20);
        drawCircle(gl, -180, 180, 20, 20);
        drawCircle(gl, 180, 180, 20, 20);
    }
}