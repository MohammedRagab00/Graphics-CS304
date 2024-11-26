package Quiz_02;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelListener;

public class quiz extends JFrame {
    private GLCanvas glcanvas;
    public static FPSAnimator animator = null;
    private quizEventListener listener = new quizEventListener();

    public static void main(String[] args) {
        new quiz();
        animator.start();
    }

    public quiz() {
        super("Shapes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        animator = new FPSAnimator(glcanvas, 60);

        // Add all listeners
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);
        glcanvas.addMouseMotionListener(listener);

        getContentPane().add(glcanvas, BorderLayout.CENTER);
        glcanvas.setFocusable(true);
        glcanvas.requestFocus(); // Request focus immediately

        setSize(600, 600);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}
