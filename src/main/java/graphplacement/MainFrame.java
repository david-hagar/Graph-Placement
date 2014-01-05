package graphplacement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class MainFrame extends JFrame {
    JPanel contentPane;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileExit = new JMenuItem();
    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JToolBar jToolBar = new JToolBar();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton runButton = new JButton();
    ImageIcon image1;
    ImageIcon image2;
    ImageIcon image3;
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    RenderPanel renderPanel = new RenderPanel(Startup.placementManager);
    BorderLayout borderLayout2 = new BorderLayout();
    JButton run10Button = new JButton();
    JButton run100Button = new JButton();
    JButton restButton = new JButton();
    JButton runContinuousButton = new JButton();
    JButton noiseButton = new JButton();
    JMenu jMenu1 = new JMenu();
    JMenuItem wsMenuItem = new JMenuItem();
    JMenuItem mlwsMenuItem = new JMenuItem();
    JMenuItem mlwrMenuItem = new JMenuItem();
    JMenuItem lhs2MenuItem = new JMenuItem();
    JMenuItem lhs3MenuItem = new JMenuItem();
    JMenuItem rcMenuItem = new JMenuItem();
    JMenuItem rsMenuItem = new JMenuItem();
    JMenuItem gridMenuItem = new JMenuItem();

    //Construct the frame
    public MainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Component initialization
    private void jbInit() throws Exception {
        image1 = new ImageIcon(graphplacement.MainFrame.class.getResource("openFile.gif"));
        image2 = new ImageIcon(graphplacement.MainFrame.class.getResource("closeFile.gif"));
        image3 = new ImageIcon(graphplacement.MainFrame.class.getResource("help.gif"));
        //setIconImage(Toolkit.getDefaultToolkit().createImage(MainFrame.class.getResource("[Your Icon]")));
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(600, 500));
        this.setTitle("Graph Placement");
        jMenuFile.setText("File");
        jMenuFileExit.setText("Exit");
        jMenuFileExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuFileExit_actionPerformed(e);
            }
        });
        jMenuHelp.setText("Help");
        jMenuHelpAbout.setText("About");
        jMenuHelpAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
        jButton1.setIcon(image1);
        jButton1.setEnabled(false);
        jButton1.setToolTipText("Open File");
        jButton2.setIcon(image2);
        jButton2.setEnabled(false);
        jButton2.setToolTipText("Close File");
        runButton.setToolTipText("Help");
        runButton.setText("  Run  ");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runButton_actionPerformed(e);
            }
        });
        jPanel1.setLayout(borderLayout2);
        run10Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run10Button_actionPerformed(e);
            }
        });
        run10Button.setText("  Run 10 ");
        run10Button.setToolTipText("Help");
        run100Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run100Button_actionPerformed(e);
            }
        });
        run100Button.setText("  Run 100  ");
        run100Button.setToolTipText("Help");
        restButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButton_actionPerformed(e);
            }
        });
        restButton.setText("  Run  ");
        restButton.setToolTipText("Help");
        restButton.setText("  Reset  ");
        runContinuousButton.setText("  Continuous  ");
        runContinuousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runContinuousButton_actionPerformed(e);
            }
        });
        noiseButton.setText("Noise");
        noiseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                noiseButton_actionPerformed(e);
            }
        });
        jMenu1.setText("Patterns");
        wsMenuItem.setText("Wheel Spokes");
        wsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wsMenuItem_actionPerformed(e);
            }
        });
        mlwsMenuItem.setText("MultiLayer Wheel Spokes");
        mlwsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mlwsMenuItem_actionPerformed(e);
            }
        });
        mlwrMenuItem.setText("MultiLayer with random");
        mlwrMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mlwrMenuItem_actionPerformed(e);
            }
        });
        lhs2MenuItem.setText("2 layer High spokes");
        lhs2MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lhs2MenuItem_actionPerformed(e);
            }
        });
        lhs3MenuItem.setText("3 layer high spokes");
        lhs3MenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lhs3MenuItem_actionPerformed(e);
            }
        });
        rcMenuItem.setText("Random Cycles");
        rcMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rcMenuItem_actionPerformed(e);
            }
        });
        rsMenuItem.setText("Random Spokes");
        rsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rsMenuItem_actionPerformed(e);
            }
        });
        gridMenuItem.setText("Grid");
        gridMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gridMenuItem_actionPerformed(e);
            }
        });
        jToolBar.add(jButton1);
        jToolBar.add(jButton2);
        jToolBar.add(runButton);
        jToolBar.add(run10Button, null);
        jToolBar.add(run100Button, null);
        contentPane.add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(renderPanel, BorderLayout.CENTER);
        jMenuFile.add(jMenuFileExit);
        jMenuHelp.add(jMenuHelpAbout);
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenuHelp);
        this.setJMenuBar(jMenuBar1);
        contentPane.add(jToolBar, BorderLayout.NORTH);
        jToolBar.add(restButton, null);
        jToolBar.add(runContinuousButton, null);
        jToolBar.add(noiseButton, null);
        jMenu1.add(wsMenuItem);
        jMenu1.add(mlwsMenuItem);
        jMenu1.add(mlwrMenuItem);
        jMenu1.add(lhs2MenuItem);
        jMenu1.add(lhs3MenuItem);
        jMenu1.add(rcMenuItem);
        jMenu1.add(rsMenuItem);
        jMenu1.add(gridMenuItem);
    }

    //File | Exit action performed
    public void jMenuFileExit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //Help | About action performed
    public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.show();
    }

    //Overridden so we can exit when window is closed
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jMenuFileExit_actionPerformed(null);
        }
    }

    void runButton_actionPerformed(ActionEvent e) {
        Startup.placementManager.stopRunThread();

        Startup.placementManager.moveAllToApparentLocalMinima();
        renderPanel.repaint();
    }


    void run10Button_actionPerformed(ActionEvent e) {
        Startup.placementManager.stopRunThread();
        for (int i = 0; i < 10; i++) {
            runButton_actionPerformed(null);
        }

    }


    void run100Button_actionPerformed(ActionEvent e) {
        Startup.placementManager.stopRunThread();
        for (int i = 0; i < 100; i++) {
            runButton_actionPerformed(null);
        }

    }


    void resetButton_actionPerformed(ActionEvent e) {
        Startup.placementManager.clear();
        Startup.placementManager.newRandom(350, 360);
        //Startup.placementManager.addBranches( 50 );
        renderPanel.repaint();
    }

    void runContinuousButton_actionPerformed(ActionEvent e) {
        Startup.placementManager.startNewRunThread();
    }

    void noiseButton_actionPerformed(ActionEvent e) {
        float mag = renderPanel.currentBounds.width / 5.0f;
        Startup.placementManager.addNoise(mag);
    }

    void wsMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheels(1, 200, 0);
        renderPanel.repaint();
    }

    void mlwsMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheels(5, 20, 0);
        renderPanel.repaint();

    }

    void mlwrMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheels(5, 20, 0);
        Startup.placementManager.newRandom(0, 20);
        renderPanel.repaint();

    }

    void lhs2MenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheels(1, 5, 2);
        renderPanel.repaint();

    }

    void lhs3MenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheels(1, 2, 6);
        renderPanel.repaint();
    }

    void rcMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newCycles(10);
        Startup.placementManager.newRandom(0, 5);
        renderPanel.repaint();
    }

    void rsMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.newWheelsRandom(6, 10, 2);
        renderPanel.repaint();
    }

    void gridMenuItem_actionPerformed(ActionEvent e) {
        Startup.placementManager.clear();
        Startup.placementManager.newGrid(20, 20);
        renderPanel.repaint();

    }

}