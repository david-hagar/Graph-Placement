package graphplacement;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
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

public class PlacementManager {
    ArrayList nodes = new ArrayList();
    ArrayList springs = new ArrayList();
    ArrayList repulsiveForces = new ArrayList();
    float minimumForce = 100.0f;

    RunThread runThread;

    public PlacementManager() {
    }


    synchronized public void addNode(Node node) {

        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            Repulsor repulsor = new Repulsor((Node) iter.next(), node);
            repulsor.updateForces();
            repulsiveForces.add(repulsor);
        }

        nodes.add(node);
    }


    synchronized public void addSpring(Spring spring) {
        spring.updateForces();
        springs.add(spring);
    }


    synchronized public void draw(Graphics2D g) {
        g.setColor(Color.green);
        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            ((Node) iter.next()).draw(g);
        }

        g.setColor(Color.cyan);
        iter = springs.iterator();
        while (iter.hasNext()) {
            ((Spring) iter.next()).draw(g);
        }

    }


    synchronized public Rectangle2D.Float getBounds() {
        if (nodes.size() == 0)
            return new Rectangle2D.Float(0.0f, 0.0f, 1.0f, 1.0f);

        Iterator iter = nodes.iterator();
        Node first = (Node) iter.next();
        Rectangle2D.Float bounds = first.getBounds();

        while (iter.hasNext()) {
            bounds.add(((Node) iter.next()).getBounds());
        }

        return bounds;
    }


    synchronized public void moveAllToApparentLocalMinima() {
        int movedCount = 0;

        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            boolean moved = ((Node) iter.next()).moveToLocalMinimum(minimumForce);
            if (moved)
                movedCount++;
        }

        //System.out.println("movedCount=" + movedCount);
/*
  if( movedCount == 0 )
    minimumForce/=2;

  if( movedCount == nodes.size() )
    minimumForce*=2;
*/

        minimumForce *= (0.5f + 2.0f * movedCount / nodes.size());

        if (minimumForce < 1e-4f)
            minimumForce = 1e-4f;

        //System.out.println("minimumForce=" + minimumForce);
    }


    synchronized public void newRandom(int nodeCount, int springCount) {
        for (int i = 0; i < nodeCount; i++) {
            Node n = new Node();
            n.randomizePosition();
            addNode(n);
        }

        for (int i = 0; i < nodeCount; i++) {
            Node n1 = (Node) nodes.get(i);
            Node n2 = (Node) nodes.get((int) (Math.random() * nodes.size()));
            if (n1 == n2) {
                i--;
                continue;
            }

            Spring spring = new Spring(n1, n2);
            addSpring(spring);
        }

        for (int i = nodeCount; i < springCount; i++) {
            Node n1 = (Node) nodes.get((int) (Math.random() * nodes.size()));
            Node n2 = (Node) nodes.get((int) (Math.random() * nodes.size()));
            if (n1 == n2) {
                i--;
                continue;
            }

            Spring spring = new Spring(n1, n2);
            addSpring(spring);
        }

    }


    synchronized public void addBranches(int count) {
        for (int i = 0; i < count; i++) {
            Node n1 = new Node();
            n1.randomizePosition();
            Node n2 = (Node) nodes.get((int) (Math.random() * nodes.size()));
            if (n1 == n2) {
                i--;
                continue;
            }

            addNode(n1);
            Spring spring = new Spring(n1, n2);
            addSpring(spring);
        }

    }


    public void clear() {
        nodes = new ArrayList();
        springs = new ArrayList();
        repulsiveForces = new ArrayList();
        minimumForce = 100.0f;
    }


    synchronized public Node findNode(Point2D point) throws NoSuchElementException {
        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            Node node = (Node) iter.next();
            Rectangle2D.Float bounds = node.getBounds();
            if (bounds.contains(point))
                return node;
        }

        throw new NoSuchElementException();
    }


    public void addNoise(float magnitude) {
        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            Node node = (Node) iter.next();
            node.getPosition().x += Math.random() * magnitude;
            node.getPosition().y += Math.random() * magnitude;
        }

    }


    synchronized public void newWheels(int level, int spokesCount, int spokelevel) {
        clear();
        Node n = new Node();
        n.randomizePosition();
        addNode(n);
        for (int i = 0; i < level; i++) {
            addSpokes(n, spokesCount, spokelevel);
            n = (Node) nodes.get((int) (Math.random() * nodes.size()));
        }

    }

    synchronized public void newWheelsRandom(int level, int spokesCount, int spokelevel) {
        clear();
        Node n = new Node();
        n.randomizePosition();
        addNode(n);
        for (int i = 0; i < level; i++) {
            addSpokes(n, (int) (spokesCount * Math.random()), (int) (spokelevel * Math.random()));
            n = (Node) nodes.get((int) (Math.random() * nodes.size()));
        }

    }

    private void addSpokes(Node node, int count, int level) {
        for (int i = 0; i < count; i++) {
            Node n = new Node();
            n.randomizePosition();
            addNode(n);
            Spring spring = new Spring(node, n);
            addSpring(spring);
            if (level > 0)
                addSpokes(n, count, level - 1);
        }

    }


    synchronized public void newCycles(int count) {

        clear();
        Node n1 = new Node();
        n1.randomizePosition();
        addNode(n1);
        Node n2 = new Node();
        n2.randomizePosition();
        addNode(n2);
        Spring spring = new Spring(n1, n2);
        addSpring(spring);

        for (int i = 0; i < count; i++) {
            Spring baseSpring = (Spring) springs.get((int) (Math.random() * springs.size()));

            ArrayList list = makeCycle(10);
            spring = new Spring(baseSpring.node1, (Node) list.get(0));
            addSpring(spring);

        }


    }


    private ArrayList makeCycle(int count) {
        ArrayList list = new ArrayList(count);

        Node n1 = new Node();
        n1.randomizePosition();
        addNode(n1);
        list.add(n1);
        Node first = n1;
        for (int i = 0; i < count - 1; i++) {
            Node n2 = new Node();
            n2.randomizePosition();
            addNode(n2);
            list.add(n1);
            Spring spring = new Spring(n1, n2);
            addSpring(spring);
            n1 = n2;
        }

        Spring spring = new Spring(n1, first);
        addSpring(spring);

        return list;
    }


    public void newGrid(int w, int h) {
        Node grid[][] = new Node[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                addNode(grid[i][j] = new Node());
                grid[i][j].randomizePosition();
            }

        }


        for (int i = 0; i < w - 1; i++) {
            for (int j = 0; j < h; j++) {
                Spring spring = new Spring(grid[i][j], grid[i + 1][j]);
                addSpring(spring);
            }

        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h - 1; j++) {
                Spring spring = new Spring(grid[i][j], grid[i][j + 1]);
                addSpring(spring);
            }

        }


    }


    public void startNewRunThread() {
        if (runThread == null) {
            runThread = new RunThread( this);
            runThread.start();
        }
    }


    public void stopRunThread() {
        if (runThread != null)
            runThread.interrupt();
    }
}