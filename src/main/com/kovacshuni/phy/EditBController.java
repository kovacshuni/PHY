package com.kovacshuni.phy;

import java.awt.event.*;

/**
 * Defines the actions to be perfromed when editing a PointlikeBody.
 */
public class EditBController extends EditXController
{
    /**
     * Perfect definition. Always use this constructor. Otherwise edit window will not work properly at the buttons and neither at the text fields.
     * @param phymodel the model object on which actions like add_() can be performed later
     * @param phyview the view object on which actions like redraw_() can be performed later
     * @param editview the edit window on which actions like setVisible(false) can be performed later
     * @param indexToEdit the index of the object we edit in the Vector in the PHYModel, useful for requesting data from it
     * @param isEditing true if already created object is edited, false if we are talking about a now newly created object
     */
    public EditBController(PHYModel phymodel, PHYView phyview, PHYController phycontrol, EditBView editview, int indexToEdit, boolean editExisting)
    {
        super(phymodel, phyview, phycontrol, indexToEdit);
        this.editview = editview;           
        if (editExisting){
            editview.addOKListener(new EditBOKListener());
            editview.addCancelListener(new EditBCancelListener());                 
        }
        else{
            editview.addOKListener(new CreateBOKListener());
            editview.addCancelListener(new CreateBCancelListener());                 
        }
    }
    
    /**
     * Sets the position of edited body.
     * This function is usually called when the user presses the right mousebutton on the DrawPanel during editing.
     * @param r the new position to be assigned.
     */
    public void clickedEdit(Dimension r)
    {
        phymodel.getBAt(indexToEdit).setPosition(r);
        editview.refreshFields();
    }
    
    /**
     * Sets the velocity of edited body.
     * This function is usually called when the user moves the mouse on the DrawPanel during editing.
     * @param r the new velocity to be assigned relative to the position of the body
     */    
    public void movedEdit(Dimension r)
    {
        PointlikeBody pb = phymodel.getBAt(indexToEdit);
        pb.setVelocity(new Dimension(r.getX() - pb.getPosition().getX() , r.getY() - pb.getPosition().getY()));
        editview.refreshFields();
    }
  
    /**
     * Subclass for creating a new PointlikeBody
     */
    private class CreateBOKListener implements ActionListener
    {     
        /**
         * Creates a new PointlikeBody with properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editBAt(new Double(editview.getMassField()),new Dimension(new Double(editview.getPosxField()),new Double(editview.getPosyField())),new Dimension(new Double(editview.getVelxField()),new Double(editview.getVelyField())), indexToEdit, editview.getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);
        }
    }
            
    /**
     * Subclass for leaving the creation of a new PointlikeBody
     */    
    protected class CreateBCancelListener implements ActionListener
    {
        /**
         * Returns from the editview window.
         */
        public void actionPerformed(ActionEvent e)
        {
            phymodel.removeBAt(indexToEdit);
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);            
        }
    }

    /**
     * Subclass for editing a new PointlikeBody
     */    
    private class EditBOKListener implements ActionListener
    {     
        /**
         * Edits the PointlikeBody to properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editBAt(new Double(editview.getMassField()),new Dimension(new Double(editview.getPosxField()),new Double(editview.getPosyField())),new Dimension(new Double(editview.getVelxField()),new Double(editview.getVelyField())), indexToEdit, editview.getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();            
            editview.setVisible(false);
        }
    }
    
    /**
     * Subclass for leaving the editing of a new PointlikeBody
     */    
    protected class EditBCancelListener implements ActionListener
    {
        /**
         * Returns from the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            enableAllButtons();
            phycontrol.terminateEditing();            
            editview.setVisible(false);
        }
    }   
}
