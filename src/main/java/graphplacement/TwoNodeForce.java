package graphplacement;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

abstract public class TwoNodeForce {

    Node node1 = null;
    Node node2 = null;
    NodeForce force1 = new NodeForce();
    NodeForce force2 = new NodeForce();

    public class NodeForce implements Force {
        Vector2D force = new Vector2D();

        public NodeForce() {

        }

        public Vector2D getValue() {
            return force;
        }


        public void update() {
            updateForces();
        }

    }

    // end internal class

    public TwoNodeForce(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;

        node1.addForce(force1);
        node2.addForce(force2);
    }


    abstract public void updateForces();


    public void draw(Graphics2D g) {
        g.draw(new Line2D.Float(node1.getPosition(), node2.getPosition()));
    }

}