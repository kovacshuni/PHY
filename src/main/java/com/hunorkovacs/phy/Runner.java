package com.hunorkovacs.phy;

/**
 * This class is responsible for the continous rendering of the DrawPanel object.
 */
public class Runner extends Thread
{
    /**
     * The model of the simulation, important for knowing when the simulation is on or off.
     */
    private PHYModel phymodel;
    /**
     * The view of the simulation, important because we call for the repaint of DrawPanel through the PHYView object.
     */
    private PHYView phyview;

    /**
     * Perfect definition of a Runner object. Always use this, otherwise there will be no continous rendering of the viewport.
     * @phymodel model of the simulation
     * @phyview view of the simulation
     */
    public Runner(PHYModel phymodel, PHYView phyview)
    {
        this.phyview = phyview;
        this.phymodel = phymodel;
    }

    /**
     * Calls for a repaint in the DrawPanel through the view object of the simulation. Also calls for the repaint of the clock in the toolbar.
     */
    public void run()
    {
        while (true){
           if (phymodel.isPlaying()){
               phyview.redraw();
               phyview.reclock();
            }
           try{
               this.sleep(33);
           }
           catch(InterruptedException e){
              System.out.println(e.getStackTrace().toString());
           }                  
        }
    }
}
