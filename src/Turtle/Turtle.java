package Turtle;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Turtle extends JFrame {

    private GLCanvas glcanvas;
    private TurtleGLEventListener listener = new TurtleGLEventListener();
    static FPSAnimator animator = null;

    public static void main(String[] args) {
        new Turtle();
        animator.start();
    }

    public Turtle() {

        super("Turtle Application");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        animator = new FPSAnimator(glcanvas, 60);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(600, 600);
        setLocationRelativeTo(this);
        setVisible(true);
        glcanvas.requestFocusInWindow();
    }


}
