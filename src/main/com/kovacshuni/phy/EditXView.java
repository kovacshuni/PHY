package com.kovacshuni.phy;

import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * This is a window where the user can edit the properties of a simulation object.
 */
public abstract class EditXView extends javax.swing.JFrame
{
    /**
     * With this attribute, the window is able to show us the properties of a given object. The address of that object is held only in the simulation's model.
     */
    private PHYModel phymodel;
    /**
     * The object we are editing.
     */
    protected PointlikeBody XToEdit;
    
    protected javax.swing.JLabel titleLabel;
    protected javax.swing.JLabel nameLabel;
    protected javax.swing.JLabel posxLabel;
    protected javax.swing.JLabel posyLabel;
    protected javax.swing.JLabel velxLabel;
    protected javax.swing.JLabel velyLabel;
    protected javax.swing.JLabel massLabel;  
    
    protected javax.swing.JTextField nameField;
    protected javax.swing.JTextField posxField;
    protected javax.swing.JTextField velxField;
    protected javax.swing.JTextField velyField;    
    protected javax.swing.JTextField posyField;    
    protected javax.swing.JTextField massField;

    protected javax.swing.JButton cancelButton;    
    protected javax.swing.JButton okButton;
    
    protected javax.swing.JSeparator jSeparator1;
    protected javax.swing.JSeparator jSeparator2;  

    /**
     * Always use this constructor. Without the right PHYModel, the frame will not be able to display any element's attributes.
     * @param phymodel the simulation's model object, where all element's addresses are held
     */
    public EditXView(PHYModel phymodel)
    {
        this.phymodel = phymodel;
    }
    
    /**
     * The initialization of the window. Specifies how does it look like. This is different for every type of element.
     */
    protected abstract void initComponents();
    
    /**
     * Assigns a listener to the cancel button, to declare what to do when clicked.
     * @param cl the listener in which is specified the performed action
     */
    public void addCancelListener(ActionListener cl)
    {
        cancelButton.addActionListener(cl);
    }
    
    /**
     * Assigns a listener to the OK button, to declare what to do when clicked.
     * @param kl the listener in which is specified the performed action
     */    
    public void addOKListener(ActionListener kl)
    {
        okButton.addActionListener(kl);
    }
    
    /**
     * Returns Position X field's value.
     * @return the value specified at the text field posxField.
     */
    public String getPosxField()
    {
        try{
            return posxField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    }

    /**
     * Returns Position Y field's value.
     * @return the value specified at the text field posyField.
     */    
    public String getPosyField()
    {
        try{
            return posyField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    }  
    
    /**
     * Returns Velocity X field's value.
     * @return the value specified at the text field velxField.
     */    
    public String getVelxField()
    {
        try{
            return velxField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    }  
    
    /**
     * Returns Velocity Y field's value.
     * @return the value specified at the text field velyField.
     */    
    public String getVelyField()
    {
        try{
            return velyField.getText();
        }
        catch (NullPointerException e){
            return "0";
        }
    } 
    
    /**
     * Returns Mass field's value.
     * @return the value specified at the text field massField.
     */    
    public String getMassField()
    {
        try{
            return massField.getText();
        }
        catch (NullPointerException e){
            return "1";
        }
    }  
    
    /**
     * Returns Name field's value.
     * @return the value specified at the text field nameField.
     */     
    public String getNameField()
    {
        try{
            return nameField.getText();
        }
        catch (NullPointerException e){
            return "noname";
        }
    }
   
    /**
     * Fills all the text fields with the corresponding value extracted from the edited object.
     */
    public void refreshFields()
    {
        posxField.setText(strPre(XToEdit.getPosition().getX()));             
        posyField.setText(strPre(XToEdit.getPosition().getY()));     
        velxField.setText(strPre(XToEdit.getVelocity().getX()));           
        velyField.setText(strPre(XToEdit.getVelocity().getY()));    
        massField.setText(strPre(XToEdit.getMass()));    
        nameField.setText(XToEdit.getMyName());
    }
    
    /**
     * Converts a decimal number to a string with 3 digits precision after the decimal point.
     * @param inValue the decimal number to be converted
     * @return the conversion's result, with 3 decimal digits precision
     */
    protected String strPre(double inValue)
    {
        DecimalFormat threeDec = new DecimalFormat("0.000");
        threeDec.setGroupingUsed(false);
        return threeDec.format(inValue).replace(',','.');
    }    
}
