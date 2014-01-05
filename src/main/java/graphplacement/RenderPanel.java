package graphplacement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.NoSuchElementException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class RenderPanel extends JPanel {
    PlacementManager placementManager = null;
    AffineTransform lastTransform = null;
    Node nodeToDrag = null;
    int recalcBoundsCount = 0;
    Rectangle2D.Float currentBounds = null;

    public RenderPanel() {
        this.setBackground(Color.black);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RenderPanel(PlacementManager placementManager) {
        this();
        this.placementManager = placementManager;
    }

    public void paint(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;

        Dimension d = this.getSize();
        g.setColor(Color.black);
        Rectangle.Float rectangle = new Rectangle.Float(
                0.0f, 0.0f, (float) d.width - 1, (float) d.height - 1);
        g.fill(rectangle);

        if (placementManager == null)
            return;


        //if( recalcBoundsCount-- <= 0 )
        //  {
        currentBounds = placementManager.getBounds();
        //  recalcBoundsCount = 2;
        //  }

        //System.out.println("bounds=" + bounds );
        AffineTransform tf = calcBoundsTF(d, currentBounds);
        g.transform(tf);
        lastTransform = g.getTransform();

        g.setColor(Color.white);
        g.setStroke(new BasicStroke(0.0f));
        //g.draw(bounds);
        placementManager.draw(g);
    }


    static AffineTransform calcBoundsTF(Dimension viewWin, Rectangle.Float bounds) {
        AffineTransform tf = new AffineTransform();

        float boundsAspect = bounds.height / bounds.width;
        float windowAspect = (float) viewWin.height / viewWin.width;

        float scale;
        if (windowAspect > boundsAspect)
            scale = viewWin.width / bounds.width;
        else
            scale = viewWin.height / bounds.height;

        scale *= 0.9f;

        float centerX = bounds.x + bounds.width / 2.0f;
        float centerY = bounds.y + bounds.height / 2.0f;

        tf.translate(viewWin.width / 2, viewWin.height / 2);
        tf.scale(1.0, -1.0);
        tf.scale(scale, scale);
        tf.translate(-centerX, -centerY);

        return tf;
    }

    private void jbInit() throws Exception {
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                this_mousePressed(e);
            }

            public void mouseReleased(MouseEvent e) {
                this_mouseReleased(e);
            }
        });
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                this_mouseDragged(e);
            }
        });
    }

    void this_mouseDragged(MouseEvent e) {
        if (nodeToDrag == null)
            return;
//System.out.println("e=" + e);
        Point point = e.getPoint();
        Point2D p = new Point2D.Float((float) point.x, (float) point.y);
        if (lastTransform == null)
            return;

        AffineTransform inverse = null;
        try {
            inverse = lastTransform.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
            return;
        }


        System.out.println("pb=" + p);
        inverse.transform(p, p);
        System.out.println("p=" + p);

        nodeToDrag.setPosition(new Vector2D((float) p.getX(), (float) p.getY()));
        repaint();
    }


    void this_mousePressed(MouseEvent e) {
//System.out.println("pressed" + e);
        Point point = e.getPoint();
        Point2D p = new Point2D.Float((float) point.x, (float) point.y);
        if (lastTransform == null)
            return;

        AffineTransform inverse = null;
        try {
            inverse = lastTransform.createInverse();
        } catch (NoninvertibleTransformException ex) {
            System.out.println("NoninvertibleTransformException");
            return;
        }

        System.out.println("pb=" + p);
        inverse.transform(p, p);
        System.out.println("p=" + p);
        try {
            nodeToDrag = placementManager.findNode(p);
            nodeToDrag.selected = true;
            nodeToDrag.printInfo();
        } catch (NoSuchElementException ex) {
            nodeToDrag = null;
        }


    }

    void this_mouseReleased(MouseEvent e) {
        if (nodeToDrag == null)
            return;

        nodeToDrag.selected = false;
    }

}