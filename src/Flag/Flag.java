package Flag;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Flag extends JFrame {

    public static void main(String[] args) {
        new Flag();
    }

    public Flag() {
        //set the JFrame title
        super("Simple Application");

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new FlagGLEventListener());

        // add the GLCanvas just like we would any Component
        add(glcanvas, BorderLayout.CENTER);

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        setLocationRelativeTo(this);
        setVisible(true);
    }


}
