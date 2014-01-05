package graphplacement;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class RunThread extends Thread {

    private PlacementManager placementManager;

    public RunThread(PlacementManager placementManager) {
        this.placementManager = placementManager;
    }

    public void run() {
        try {
            while (true) {
                Startup.placementManager.moveAllToApparentLocalMinima();
                //Startup.placementManager.moveAllToApparentLocalMinima();
                //Startup.placementManager.moveAllToApparentLocalMinima();
                Startup.frame.renderPanel.repaint();
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            // do nothing
        } catch (Exception ex) {
            ex.printStackTrace();
        }

         placementManager.runThread = null;
    }
}