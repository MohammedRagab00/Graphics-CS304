package BoatInSea;

import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class BoatInSea extends JFrame {

    private GLCanvas glcanvas;
    private SailboatSceneGLEventListener listener = new SailboatSceneGLEventListener();
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
        animator = new FPSAnimator(glcanvas, 60);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(600, 400);
        setLocationRelativeTo(this);
        setVisible(true);
        glcanvas.requestFocusInWindow();
    }


}
