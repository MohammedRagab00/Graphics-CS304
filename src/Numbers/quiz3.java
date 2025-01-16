package Numbers;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class quiz3 extends JFrame {
    private GLCanvas glcanvas;
    public static FPSAnimator animator = null;
    private NumbersGLEventListener listener = new NumbersGLEventListener();

    public static void main(String[] args) {
        new quiz3();
        animator.start();
    }

    public quiz3() {
        super("Numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        animator = new FPSAnimator(glcanvas, 60);

        // Add all listeners
        glcanvas.addGLEventListener(listener);
//        glcanvas.addKeyListener((KeyListener) listener);
//        glcanvas.addMouseListener((MouseListener) listener);
//        glcanvas.addMouseMotionListener((MouseMotionListener) listener);

        getContentPane().add(glcanvas, BorderLayout.CENTER);
        glcanvas.setFocusable(true);
        glcanvas.requestFocus(); // Request focus immediately

        setSize(600, 600);
        setLocationRelativeTo(this);
        setVisible(true);
    }
}

