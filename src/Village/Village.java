package Village;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Village extends JFrame {

    public static void main(String[] args) {
        new Village();
    }

    public Village() {
        //set the JFrame title
        super("Village");

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new LandscapeSceneGLEventListener());

        // add the GLCanvas just like we would any Component
        add(glcanvas, BorderLayout.CENTER);

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1010, 600);

        setLocationRelativeTo(this);
        setVisible(true);
    }


}
