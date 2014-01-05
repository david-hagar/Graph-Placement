package graphplacement;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class Node {

    static final float r = 1.5f;
    static final float r2 = r * 2.0f;

    private ArrayList forces = new ArrayList();
    private Vector2D position = new Vector2D();
    private float resistance = 1.0f;
    public boolean selected = false;

    public Node() {
    }

    public void addForce(Force force) {
        forces.add(force);
    }


    public boolean moveToLocalMinimum(float minimumForceCutOff) {
        if (selected)
            return false;

        //recalcForces();
        Vector2D totalForces = calcTotalForces();
        float mag = totalForces.mag();
        //System.out.println( this + "*** totalMag="+ mag );
        //if( mag < minimumForceCutOff )
        //  return false;

        float minimumForceMag = mag;
        Vector2D minimumForce = totalForces;

        //int count =0;
        while (true) {
            //if( count++ >10 )
            //  return true;
            Vector2D force = Vector2D.mult(minimumForce, resistance);
            Vector2D oldPosition = (Vector2D) position.clone();
            position.add(force);

            recalcForces();
            Vector2D newTotalForces = calcTotalForces();
            float newTotalForcesMag = newTotalForces.mag();
            //System.out.println("newTotalForcesMag=" + newTotalForcesMag );
            if (newTotalForcesMag < minimumForceMag) {
                resistance *= 2.0;
                minimumForceMag = newTotalForcesMag;
                minimumForce = newTotalForces;
                //System.out.println("hit");
                if (minimumForceMag < minimumForceCutOff)
                    return true;
            } else {
                float newResistance = resistance * 0.5f;
                if (newResistance > 1e-4)
                    resistance = newResistance;
                else
                    return true;

                position = oldPosition;
                //System.out.println("miss");

            }

            //System.out.println("resistance=" + resistance );

        }

    }


    public Vector2D calcTotalForces() {

        Vector2D totalForces = new Vector2D();

        Iterator iter = forces.iterator();
        while (iter.hasNext()) {
            totalForces.add(((Force) iter.next()).getValue());
        }

        return totalForces;
    }


    public void recalcForces() {

        Iterator iter = forces.iterator();
        while (iter.hasNext()) {
            ((Force) iter.next()).update();
        }

    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        recalcForces();
    }

    public void randomizePosition() {
        position.x = (float) (Math.random() * 100.0);
        position.y = (float) (Math.random() * 100.0);
    }


    public void draw(Graphics2D g) {
        g.fill(new Ellipse2D.Float(position.x - r, position.y - r, r2, r2));
    }

    public Rectangle2D.Float getBounds() {
        return new Rectangle2D.Float(position.x - r, position.y - r, r2, r2);
    }


    public void printInfo() {
        System.out.println("forces=" + forces.size());
        System.out.println("resistance=" + resistance);
        System.out.println("pos=" + position);
        System.out.println("");
    }


}