package com.kovacshuni.phy;

/**
 * Project for Mr. Joldos, Faculty of Automation and Computer Science, Technical University of Cluj-Napoca, Romania
 *
 * @author Kov�cs Hunor
 * @version 1.00 last modification 2009.02.09. 8:00 PM
 */
public class PHYMain
{
    public static void main(String args[])
    {
        PHYModel model = new PHYModel();
        PHYView view = new PHYView(model);
        PHYController controller = new PHYController(model,view);
        Runner runner = new Runner(model, view);
        runner.start();
        view.setVisible(true);
    }
}