package SimpleCircleCollision;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;
import com.sun.opengl.util.FPSAnimator;

public class SimpleCircleCollision extends JFrame {

    private GLCanvas glcanvas;
    private SimpleCircleCollisionGLEventListener listener = new SimpleCircleCollisionGLEventListener();
    static FPSAnimator animator = null;

    public static void main(String[] args) {
        new SimpleCircleCollision();
        animator.start();

    }

    public SimpleCircleCollision() {

        super("Rotating Triangle");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        animator = new FPSAnimator(glcanvas, 60);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(600, 400);
        setLocationRelativeTo(this);
        setVisible(true);
        glcanvas.requestFocusInWindow();
    }
}


