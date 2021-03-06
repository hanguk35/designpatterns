/*
 * @(#)CmdGardener.java   2011-11-01
 *
 * Copyright (c) 2011 Giorgio Peron giorgio.peron@gmail.com
 * All Rights Reserved.
 *
 * Redistribution and use of this script, with or without modification, is
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of this script must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */



package cooper.designpatterns.creational.factorymethod;

import java.awt.*;
import java.awt.event.*;

//illustrates use of Abstract Factory pattern

/**
 * Class description
 *
 *
 * @version        0.1.1, 2011-11-01
 * @author         <a href="mailto:giorgio.peron@gmail.com">Giorgio Peron</a>
 */
public class CmdGardener extends Frame implements ActionListener, ItemListener {
    private CheckCommand Vegie, Annual, Peren;
    private Button Center, Border, Shade, Quit;
    private Garden garden = null;
    private GardenPanel gardenPlot;
    private String borderPlant = "", centerPlant = "", shadePlant = "";

    /**
     * Constructs ...
     *
     */
    public CmdGardener() {
        setGUI();
    }

    private void setGUI() {
        setLayout(new GridLayout(1, 2));
        Panel left = new Panel();
        add(left);
        Panel right = new Panel();
        add(right);
        // create label and 3 radio buttons on left side
        left.setLayout(new GridLayout(4, 1));
        left.add(new Label("Garden type"));
        CheckboxGroup grp = new CheckboxGroup();
        Vegie = new CheckCommand("Vegetable", grp, new CommandGardener("Vegie", this));
        Annual = new CheckCommand("Annual", grp, new CommandGardener("Annual", this));
        Peren = new CheckCommand("Perennial", grp, new CommandGardener("Peren", this));
        left.add(Vegie);
        left.add(Annual);
        left.add(Peren);
        Vegie.addItemListener(this);
        Peren.addItemListener(this);
        Annual.addItemListener(this);
        // now create right side
        right.setLayout(new GridLayout(2, 1));
        gardenPlot = new GardenPanel();
        Panel botRight = new Panel();
        right.add(gardenPlot);
        right.add(botRight);
        Center = new Button("Central");
        Border = new Button("Border");
        Shade = new Button("Shade");
        Quit = new Button("Quit");
        botRight.add(Center);
        Center.addActionListener(this);
        botRight.add(Border);
        Border.addActionListener(this);
        botRight.add(Shade);
        Shade.addActionListener(this);
        botRight.add(Quit);
        Quit.addActionListener(this);
        setBounds(200, 200, 400, 300);
        setVisible(true);
    }

    /**
     * Method description
     *
     *
     * @param g
     */
    public void setGarden(Garden g) {
        garden = g;
        shadePlant = "";
        centerPlant = "";
        borderPlant = "";
        gardenPlot.repaint();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == Center) {
            setCenter();
        }
        if (obj == Border) {
            setBorder();
        }
        if (obj == Shade) {
            setShade();
        }
        if (obj == Quit) {
            System.exit(0);
        }
    }

    private void setCenter() {
        centerPlant = garden.getCenter().getName();
        gardenPlot.repaint();
    }

    private void setBorder() {
        borderPlant = garden.getBorder().getName();
        gardenPlot.repaint();
    }

    private void setShade() {
        shadePlant = garden.getShade().getName();
        gardenPlot.repaint();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void itemStateChanged(ItemEvent e) {
        Command cmd = (Command) e.getSource();
        cmd.execute();
    }

    /**
     * Method description
     *
     *
     * @param argv
     */
    static public void main(String argv[]) {
        new CmdGardener();
    }

    class GardenPanel extends Panel {

        /**
         * Method description
         *
         *
         * @param g
         */
        public void paint(Graphics g) {
            Dimension sz = getSize();
            g.setColor(Color.lightGray);
            g.fillArc(0, 0, 80, 80, 0, 360);
            g.setColor(Color.black);
            g.drawRect(0, 0, sz.width - 1, sz.height - 1);
            g.drawString(centerPlant, 100, 50);
            g.drawString(borderPlant, 75, 120);
            g.drawString(shadePlant, 10, 20);
        }
    }
}    // end of Gardener class


class CommandGardener implements Command {
    private String garden_type;
    private CmdGardener gard;

    /**
     * Constructs ...
     *
     *
     * @param gtype
     * @param g
     */
    public CommandGardener(String gtype, CmdGardener g) {
        garden_type = gtype;
        gard = g;
    }

    /**
     * Method description
     *
     */
    public void execute() {
        Garden gd = new VegieGarden();    // default
        if (garden_type.equals("Peren")) {
            gd = new PerennialGarden();
        }
        if (garden_type.equals("Annual")) {
            gd = new AnnualGarden();
        }
        gard.setGarden(gd);
    }
}
