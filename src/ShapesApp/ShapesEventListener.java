package ShapesApp;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ShapesEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private double zoomLevel = 1.0;
    private double panX = 0.0;
    private double panY = 0.0;
    private double currentX = 0;
    private double currentY = 0;
    private boolean isDragging = false;
    private double lastMouseX;
    private double lastMouseY;

    private GLAutoDrawable glDrawable;  // Store the GLAutoDrawable for coordinate conversion


    // Store shapes with their positions
    private static class Shape {
        String type;
        double x, y;

        Shape(String type, double x, double y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    private List<Shape> shapes = new ArrayList<>();

    // Convert screen coordinates to world coordinates
    private double screenToWorldX(double screenX) {
        if (glDrawable == null) return 0;  // Add null check
        double normalizedX = screenX / glDrawable.getWidth() * 500;
        return (normalizedX - 250) * zoomLevel - panX;
    }

    private double screenToWorldY(double screenY) {
        if (glDrawable == null) return 0;  // Add null check
        double normalizedY = (glDrawable.getHeight() - screenY) / glDrawable.getHeight() * 300;
        return (normalizedY - 150) * zoomLevel - panY;
    }


    /**
     * The methods glAutoDrawable.getSurfaceWidth()
     * and glAutoDrawable.getSurfaceHeight() return
     * the width and height of the drawable surface,
     * respectively. This is useful for setting up the
     * viewport and ensuring your rendering scales
     * correctly with the window size.
     */

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        this.glDrawable = glAutoDrawable;  // Store the GLAutoDrawable
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glViewport(0, 0, glAutoDrawable.getWidth(), glAutoDrawable.getHeight());
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 500.0, 0.0, 300.0, -1, 1);

        // Enable smooth lines
        gl.glEnable(GL.GL_LINE_SMOOTH);
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        this.glDrawable = glAutoDrawable;  // Keep the reference updated
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Set up the view with zoom and pan
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-250.0 * zoomLevel, 250.0 * zoomLevel, -150.0 * zoomLevel, 150.0 * zoomLevel, -1, 1);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(panX, panY, 0);

        // Draw all shapes
        for (Shape shape : shapes) {
            gl.glPushMatrix();
            switch (shape.type) {
                case "house":
                    drawHouse(gl, shape.x, shape.y);
                    break;
                case "fish":
                    drawFish(gl, shape.x, shape.y);
                    break;
                case "triangle":
                    drawTriangle(gl, shape.x, shape.y);
                    break;
                case "flower":
                    drawFlower(gl, shape.x, shape.y);
                    break;
            }
            gl.glPopMatrix();
        }

//
//        gl.glPushMatrix();
//        drawTriangle(gl, 40, 40);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//        drawFlower(gl, 20, 20);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//        drawHouse(gl, 100, 100);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//        drawFish(gl, 80, 80);
//        gl.glPopMatrix();
    }


    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_H: // Press H for House
                shapes.add(new Shape("house", currentX, currentY));
                break;
            case KeyEvent.VK_F: // Press F for Fish
                shapes.add(new Shape("fish", currentX, currentY));
                break;
            case KeyEvent.VK_T: // Press T for Triangle
                shapes.add(new Shape("triangle", currentX, currentY));
                break;
            case KeyEvent.VK_L: // Press L for Flower
                shapes.add(new Shape("flower", currentX, currentY));
                break;
            case KeyEvent.VK_C: // Press C to clear
                shapes.clear();
                break;
            case KeyEvent.VK_LEFT:
                panX += 10.0 / zoomLevel;
                break;
            case KeyEvent.VK_RIGHT:
                panX -= 10.0 / zoomLevel;
                break;
            case KeyEvent.VK_UP:
                panY -= 10.0 / zoomLevel;
                break;
            case KeyEvent.VK_DOWN:
                panY += 10.0 / zoomLevel;
                break;
        }

        // Handle zoom with Ctrl + Plus/Minus
        if (e.isControlDown()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_EQUALS: // Ctrl + =
                    zoomLevel /= 1.1;
                    break;
                case KeyEvent.VK_MINUS: // Ctrl + -
                    zoomLevel *= 1.1;
                    break;
            }
        }

        if (glDrawable != null) {
            glDrawable.display();
        }
    }


    private void drawTriangle(GL gl, double x, double y) {
        gl.glColor3f(0f, 1f, 0f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(x + 10, y - 14);
        gl.glVertex2d(x - 10, y - 14);
        gl.glVertex2d(x, y + 10);
        gl.glEnd();
    }

    private void drawCircle(GL gl, double centerX, double centerY, double radius, float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(centerX + radius * Math.cos(angle), centerY + radius * Math.sin(angle));
        }
        gl.glEnd();
    }

    private void drawFlower(GL gl, double centerX, double centerY) {
        gl.glColor3f(255f / 255, 190f / 255, 201f / 255); // Pink color
        int radius = 6;
        for (int i = 0; i < 360; i += 90) {
            double angle = Math.toRadians(i);
            double petalCenterX = centerX + (int) (radius * Math.cos(angle));
            double petalCenterY = centerY + (int) (radius * Math.sin(angle));
            drawCircle(gl, petalCenterX, petalCenterY, radius, 1.0f, 0.0f, 1.0f);
        }
        drawCircle(gl, centerX, centerY, radius, 252f / 255, 1.0f, 0.0f); // Yellow color

    }

    private void drawFish(GL gl, double x, double y) {
        // Draw the body of the fish (ellipse)
        gl.glColor3f(0.0f, 0.5f, 1.0f); // Blue color
        gl.glBegin(GL.GL_POLYGON);
        for (int i = 0; i < 360; i++) {
            double angle = Math.toRadians(i);
            gl.glVertex2d(x + 10 * Math.cos(angle), y + 5 * Math.sin(angle));
        }
        gl.glEnd();

        // Draw the tail of the fish (triangle)
        gl.glColor3f(0.0f, 0.5f, 1.0f); // Blue color
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(x - 10, y);
        gl.glVertex2d(x - 15, y + 5);
        gl.glVertex2d(x - 15, y - 5);
        gl.glEnd();

        // Draw the eye of the fish (small circle)
        gl.glColor3f(1.0f, 1.0f, 1.0f); // White color
        drawCircle(gl, x + 5, y + 2, 1, 1.0f, 1.0f, 1.0f);
    }

    private void drawHouse(GL gl, double x, double y) {
        // Draw the base of the house (rectangle)
        gl.glColor3f(0.5f, 0.35f, 0.05f); // Brown color
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2d(x - 10, y - 10);
        gl.glVertex2d(x + 10, y - 10);
        gl.glVertex2d(x + 10, y + 10);
        gl.glVertex2d(x - 10, y + 10);
        gl.glEnd();

        // Draw the roof of the house (triangle)
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Red color
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glVertex2d(x - 12, y + 10);
        gl.glVertex2d(x + 12, y + 10);
        gl.glVertex2d(x, y + 20);
        gl.glEnd();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            double deltaX = e.getX() - lastMouseX;
            double deltaY = e.getY() - lastMouseY;

            panX += deltaX * zoomLevel;
            panY -= deltaY * zoomLevel;

            lastMouseX = e.getX();
            lastMouseY = e.getY();

            updateCurrentPosition(e);

            if (glDrawable != null) {
                glDrawable.display();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateCurrentPosition(e);
        if (glDrawable != null) {
            glDrawable.display();
        }
    }

    private void updateCurrentPosition(MouseEvent e) {
        currentX = screenToWorldX(e.getX());
        currentY = screenToWorldY(e.getY());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMouseX = e.getX();
        lastMouseY = e.getY();
        isDragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        this.glDrawable = glAutoDrawable;
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            zoomLevel /= 1.1;
        } else {
            zoomLevel *= 1.1;
        }
        if (glDrawable != null) {
            glDrawable.display();
        }
    }
}
