package com.hunorkovacs.phy;

/**
 * Defines the actions to be perfromed on different user events.
 */
public abstract class EditXController
{
    /**
     * model object of the simulation, important to call for actions in it
     */
    protected PHYModel phymodel;
    /**
     * view object of the simulation, important to call for actions in it
     */
    protected PHYView phyview;  
    /**
     * control object of the simulation, important to call for actions in it
     */
    protected PHYController phycontrol;
    /**
     * edit window of the object to be edited.
     */
    protected EditXView editview;    
    /**
     * the index of the element to be edited in the Vecor in the PHYModel
     */
    protected int indexToEdit;

    /**
     * Perfect definition. Always use this constructor. Otherwise actions will be performed only partially or they will not be performed at all.
     * @param m model object of the simulation
     * @param v view object of the simulation
     * @param c controller object of the simulation
     * @indexToEdit index of the element the Vector in the model
     */
    EditXController(PHYModel m, PHYView v, PHYController c, int indexToEdit)
    {
        this.phymodel = m;
        this.phyview = v;
        this.phycontrol = c;
        this.indexToEdit = indexToEdit;
    }
    
    /**
     * Specifies what to happen when the user presses the mouse on the DrawPanel.
     * @param r coordinate of the click in computer graphics system
     */
    public abstract void clickedEdit(Dimension r);
    /**
     * Specifies what to happen when the user moves the pressed mouse on the DrawPanel.
     * @param r coordinate of the mouse in computer graphics system
     */
    public abstract void movedEdit(Dimension r);
    
    /**
     * Enables all buttons on the view. Edit and Delete buttons will be enabled only if the list is not empty.
     */
    protected void enableAllButtons()
    {
            phyview.setPlayButtonEnabled(true);
            phyview.setStopButton(true);
            if (this.phymodel.getListSize() > 0){
                phyview.setDeleteButton(true);
                phyview.setEditButton(true);
            }             
            phyview.setCreateBButton(true);
            phyview.setCreateGFButton(true);
            phyview.setCreateRISButton(true);
    }
}
