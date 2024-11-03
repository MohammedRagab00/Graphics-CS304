package Owl;

import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;

import com.sun.opengl.util.FPSAnimator;

public class Owl extends JFrame {

    private GLCanvas glcanvas;
    private OwlGLEventListener listener = new OwlGLEventListener();
    static FPSAnimator animator = null;

    public static void main(String[] args) {
        new Owl();
        animator.start();

    }

    public Owl() {

        super("Owl");

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


