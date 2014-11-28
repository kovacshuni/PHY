package com.hunorkovacs.phy;

import java.awt.event.*;

/**
 * Defines the actions to be perfromed when editing a GravityField.
 */
public class EditGFController extends EditXController
{
    /**
     * Perfect definition. Always use this constructor. Otherwise edit window will not work properly at the buttons and neither at the text fields.
     * @param phymodel the model object on which actions like add_() can be performed later
     * @param phyview the view object on which actions like redraw_() can be performed later
     * @param editview the edit window on which actions like setVisible(false) can be performed later
     * @param indexToEdit the index of the object we edit in the Vector in the PHYModel, useful for requesting data from it
     * @param isEditing true if already created object is edited, false if we are talking about a now newly created object
     */
    public EditGFController(PHYModel phymodel, PHYView phyview, PHYController phycontrol, EditGFView editview, int indexToEdit, boolean editExisting)
    {
        super(phymodel, phyview, phycontrol, indexToEdit);
        this.editview = editview;        
        if (editExisting){
            editview.addOKListener(new EditGFOKListener());
            editview.addCancelListener(new EditGFCancelListener());                 
        }
        else{
            editview.addOKListener(new CreateGFOKListener());
            editview.addCancelListener(new CreateGFCancelListener());                 
        }     
    }
    
    /**
     * Sets the position of edited force.
     * This function is usually called when the user presses the right mousebutton on the DrawPanel during editing.
     * @param r the new position to be assigned.
     */    
    public void clickedEdit(Dimension r)
    {
        phymodel.getGFAt(indexToEdit).setPosition(r);
        editview.refreshFields();
    }
    
    /**
     * Sets the g vector of edited force.
     * This function is usually called when the user moves the mouse on the DrawPanel during editing.
     * @param r the new g vector to be assigned relative to the position of the body
     */    
    public void movedEdit(Dimension r)
    {
        GravityField gf = phymodel.getGFAt(indexToEdit);
        gf.setG(new Dimension(r.getX() - gf.getPosition().getX() , r.getY() - gf.getPosition().getY()));
        editview.refreshFields();
    }    

    /**
     * Subclass for creating a new GravityField
     */    
    private class CreateGFOKListener implements ActionListener
    {      
        /**
         * Creates a new GravityField with properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editGFAt(new Double(((EditGFView)editview).getMassField()),new Dimension(new Double(((EditGFView)editview).getPosxField()),new Double(((EditGFView)editview).getPosyField())),new Dimension(new Double(((EditGFView)editview).getVelxField()),new Double(((EditGFView)editview).getVelyField())), new Dimension(new Double(((EditGFView)editview).getgxField()),new Double(((EditGFView)editview).getgyField())), indexToEdit, ((EditGFView)editview).getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);
        }
    }
      
    /**
     * Subclass for leaving the creation of a new GravityField
     */    
    protected class CreateGFCancelListener implements ActionListener
    {
        /**
         * Returns from the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.removeGFAt(indexToEdit);
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);            
        }
    }

    /**
     * Subclass for editing a GravityField
     */    
    private class EditGFOKListener implements ActionListener
    {         
        /**
         * Edits a GravityField to properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editGFAt(new Double(((EditGFView)editview).getMassField()),new Dimension(new Double(((EditGFView)editview).getPosxField()),new Double(((EditGFView)editview).getPosyField())),new Dimension(new Double(((EditGFView)editview).getVelxField()),new Double(((EditGFView)editview).getVelyField())), new Dimension(new Double(((EditGFView)editview).getgxField()),new Double(((EditGFView)editview).getgyField())), indexToEdit, ((EditGFView)editview).getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();            
            editview.setVisible(false);
        }
    }
    
    /**
     * Subclass for leaving the editing of a new GravityField
     */    
    protected class EditGFCancelListener implements ActionListener
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
