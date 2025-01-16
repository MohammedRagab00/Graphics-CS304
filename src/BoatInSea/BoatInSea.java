package BoatInSea;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoatInSea extends JFrame {

    private GLCanvas glcanvas;
    private SailboatGLEventListener listener = new SailboatGLEventListener();
    static FPSAnimator animator = null;

    public static void main(String[] args) {
        new BoatInSea();
        animator.start();
    }

    public BoatInSea() {

        super("Two Boats Racing");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener((MouseListener) listener);
        glcanvas.addMouseMotionListener((MouseMotionListener) listener);
        glcanvas.addKeyListener((KeyListener) listener);
        animator = new FPSAnimator(glcanvas, 60);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(600, 400);
        setLocationRelativeTo(this);
        setVisible(true);
        glcanvas.requestFocusInWindow();
    }


}
