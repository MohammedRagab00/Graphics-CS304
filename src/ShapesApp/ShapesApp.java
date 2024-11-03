package ShapesApp;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class ShapesApp extends JFrame {
    private GLCanvas glcanvas;
    public static FPSAnimator animator = null;
    private ShapesEventListener listener = new ShapesEventListener();

    public static void main(String[] args) {
        new ShapesApp();
        animator.start();
    }

    public ShapesApp() {
        super("Shapes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        animator = new FPSAnimator(glcanvas, 60);

        // Add all listeners
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);
        glcanvas.addMouseMotionListener(listener);
        glcanvas.addMouseWheelListener(listener);

        getContentPane().add(glcanvas, BorderLayout.CENTER);
        glcanvas.setFocusable(true);
        glcanvas.requestFocus(); // Request focus immediately

        setSize(500, 300);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}
