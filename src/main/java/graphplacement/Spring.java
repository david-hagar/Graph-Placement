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

public class Spring extends TwoNodeForce {

    static float idealDistance = 0.0f;
    static float springConstant = 1.0f;

    public Spring(Node node1, Node node2) {
        super(node1, node2);

    }


    public void updateForces() {
        Vector2D d = Vector2D.sub(node2.getPosition(), node1.getPosition());
        float mag = (d.mag() - idealDistance);

        d.unit();
        d.mult(mag * mag * springConstant);

        force1.force.set(d);
        d.neg();
        force2.force.set(d);
    }


}