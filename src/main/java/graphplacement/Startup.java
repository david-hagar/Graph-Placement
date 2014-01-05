package graphplacement;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class Startup {
    boolean packFrame = false;

    static PlacementManager placementManager = new PlacementManager();
    static MainFrame frame = null;

    //Construct the application
    public Startup() {

        //initPlacement();
        //placementManager.newRandom( 200, 205 );

        frame = new MainFrame();
        //Validate frames that have preset sizes
        //Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }
        //Center the window


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frameSize.height = screenSize.height - 50;
        frameSize.width = frameSize.height + 200;
        frame.setSize(frameSize);

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);


        frame.setVisible(true);

        frame.resetButton_actionPerformed(null);

        placementManager.startNewRunThread();
    }

    //Main method
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Startup();
    }


    void initPlacement() {
        Node n1 = new Node();
        n1.randomizePosition();
        placementManager.addNode(n1);
        Node n2 = new Node();
        n2.randomizePosition();
        placementManager.addNode(n2);
        Node n3 = new Node();
        n3.randomizePosition();
        placementManager.addNode(n3);
        Node n4 = new Node();
        n4.randomizePosition();
        placementManager.addNode(n4);

        Spring s1 = new Spring(n1, n2);
        placementManager.addSpring(s1);
        Spring s2 = new Spring(n1, n3);
        placementManager.addSpring(s2);
        Spring s3 = new Spring(n2, n3);
        placementManager.addSpring(s3);
        Spring s4 = new Spring(n1, n4);
        placementManager.addSpring(s4);

    }


}