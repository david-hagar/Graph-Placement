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

public class Repulsor extends TwoNodeForce {

    static float k = 100.0f;


    public Repulsor(Node node1, Node node2) {
        super(node1, node2);
    }


    public void updateForces() {
        Vector2D d = Vector2D.sub(node2.getPosition(), node1.getPosition());
        float mag = d.mag();
        mag = k / (mag * mag);

        d.unit();
        d.mult(mag);

        force2.force.set(d);
        d.neg();
        force1.force.set(d);
    }


}