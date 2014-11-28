package com.hunorkovacs.phy;

import java.awt.event.*;

/**
 * Defines the actions to be perfromed when editing a RectangularIsolatorSpace.
 */
public class EditRISController extends EditXController
{
    /**
     * Perfect definition. Always use this constructor. Otherwise edit window will not work properly at the buttons and neither at the text fields.
     * @param phymodel the model object on which actions like add_() can be performed later
     * @param phyview the view object on which actions like redraw_() can be performed later
     * @param editview the edit window on which actions like setVisible(false) can be performed later
     * @param indexToEdit the index of the object we edit in the Vector in the PHYModel, useful for requesting data from it
     * @param isEditing true if already created object is edited, false if we are talking about a now newly created object
     */
    public EditRISController(PHYModel phymodel, PHYView phyview, PHYController phycontrol, EditRISView editview, int indexToEdit, boolean editExisting)
    {
        super(phymodel, phyview, phycontrol, indexToEdit);
        this.editview = editview;        
        if (editExisting){
            editview.addOKListener(new EditRISOKListener());
            editview.addCancelListener(new EditRISCancelListener());                 
        }
        else{
            editview.addOKListener(new CreateRISOKListener());
            editview.addCancelListener(new CreateRISCancelListener());                 
        }     
    }
    
    /**
     * Sets the position of edited space.
     * This function is usually called when the user presses the right mousebutton on the DrawPanel during editing.
     * @param r the new position to be assigned.
     */    
    public void clickedEdit(Dimension r)
    {
        phymodel.getRISAt(indexToEdit).setPosition(r);
        editview.refreshFields();
    }
    
    /**
     * Sets the dimension of the edited space.
     * This function is usually called when the user moves the mouse on the DrawPanel during editing.
     * @param r the new width and height to be assigned relative to the position of the body
     */    
    public void movedEdit(Dimension r)
    {
        RectangularIsolatorSpace ris = phymodel.getRISAt(indexToEdit);
        ris.setDimension(new Dimension(r.getX() - ris.getPosition().getX() , r.getY() - ris.getPosition().getY()));
        editview.refreshFields();        
    }    

    /**
     * Subclass for creating a new RectangularIsolatorSpace
     */    
    private class CreateRISOKListener implements ActionListener
    {   
        /**
         * Creates a new RectangularIsolatorSpace with properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editRISAt(new Double(((EditRISView)editview).getMassField()),new Dimension(new Double(((EditRISView)editview).getPosxField()),new Double(((EditRISView)editview).getPosyField())),new Dimension(new Double(((EditRISView)editview).getVelxField()),new Double(((EditRISView)editview).getVelyField())), new Dimension(new Double(((EditRISView)editview).getdxField()),new Double(((EditRISView)editview).getdyField())), indexToEdit, ((EditRISView)editview).getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);
        }
    }
          
    /**
     * Subclass for leaving the creation of a new RectangularIsolatorSpace
     */    
    protected class CreateRISCancelListener implements ActionListener
    {
        /**
         * Returns from the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.removeRISAt(indexToEdit);
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();
            editview.setVisible(false);            
        }
    }

    /**
     * Subclass for editing a new RectangularIsolatorSpace
     */    
    private class EditRISOKListener implements ActionListener
    {         
        /**
         * Edits a RectangularIsolatorSpace to properties given in the editview window.
         */        
        public void actionPerformed(ActionEvent e)
        {
            phymodel.editRISAt(new Double(((EditRISView)editview).getMassField()),new Dimension(new Double(((EditRISView)editview).getPosxField()),new Double(((EditRISView)editview).getPosyField())),new Dimension(new Double(((EditRISView)editview).getVelxField()),new Double(((EditRISView)editview).getVelyField())), new Dimension(new Double(((EditRISView)editview).getdxField()),new Double(((EditRISView)editview).getdyField())), indexToEdit, ((EditRISView)editview).getNameField());
            enableAllButtons();
            phyview.repaint();
            phycontrol.terminateEditing();            
            editview.setVisible(false);
        }
    }
    
    /**
     * Subclass for leaving the editing of a RectangularIsolatorSpace
     */    
    protected class EditRISCancelListener implements ActionListener
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
