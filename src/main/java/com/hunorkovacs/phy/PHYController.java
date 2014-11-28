package com.hunorkovacs.phy;

import java.awt.event.*;
import javax.swing.event.*;
import java.util.Vector;
import java.util.Enumeration;
import java.lang.Math;

/**
 * Class for performing actions on PHYModel by analizing events that happen on the PHYView.
 */
public class PHYController
{
    /**
     * model object of the simulation
     */
    private PHYModel phymodel;
    /**
     * view object of the simulation
     */
    private PHYView phyview;
    /**
     * this object
     */
    private PHYController SELF;
    /**
     * the editcontrol object with which we are actually assigning new values to a given simulation object
     */
    private EditXController editcontrol;
    /**
     * states whether we are editing mode or not
     */
    private boolean isEditing;
    
    /**
     * Perfect definition. Always use this constructor. Otherwise actions made by the user will not be perfomed correctly.
     * @param m the model object of the simulation
     * @param v the view object of the simulation
     */
    PHYController(PHYModel m, PHYView v)
    {
        this.phymodel = m;
        this.phyview = v;
        SELF = this; 
        isEditing = false;
        
        phyview.addPlayListener(new PlayListener());
        phyview.addStopListener(new StopListener());
        phyview.addDeleteListener(new DeleteListener());  
        phyview.addEditListener(new EditListener());        
        phyview.addCreateBListener(new CreateBListener());     
        phyview.addCreateGFListener(new CreateGFListener());
        phyview.addCreateRISListener(new CreateRISListener());
        phyview.addSelectListener(new SelectListener());      
        phyview.addTimeSpeedListener(new TimeSpeedListener());
        
        phyview.addDrawPanelMouseListener(new DrawPanelMouseListener());
        phyview.addDrawPanelMouseMotionListener(new DrawPanelMouseMotionListener());
        phyview.addDrawPanelMouseWheelListener(new DrawPanelMouseWheelListener());        
    }

    /**
     * Forwards the clickedEdit(Dimension) method to the edit controller we are editing with right now
     * @see EditXController
     */
    public void clickedEdit(Dimension r)
    {
        editcontrol.clickedEdit(r);
    }
    
    /**
     * Forwards the movedEdit(Dimension) method to the edit controller we are editing with right now
     * @see EditXController
     */    
    public void movedEdit(Dimension r)
    {
        editcontrol.movedEdit(r);
    }
    
    /**
     * Sets isEditing to false.
     */
    public void terminateEditing()
    {
        isEditing = false;
    }
    
    /**
     * Listner object for the play button in the view.
     */
    class PlayListener implements ActionListener
    {
        /**
         * Calls for play in the model if it was not playing already, or pause if it was.
         * Also copes with disabling/enabling buttons on the view.
         */
        public void actionPerformed(ActionEvent e)
        {
            if (phymodel.isPlaying()){
                phymodel.pause();
                enableAllButtons();
                phyview.setPlayButton(true);
            }
            else{
                phymodel.play();
                disableAllButtons();
                phyview.setPlayButtonEnabled(true);
                phyview.setStopButton(true);                
                phyview.setPlayButton(false);
            }           
        }
    }
    
    /**
     * Listener object for the stop button in the view.
     */
    class StopListener implements ActionListener
    {
        /**
         * Calls for stop in the model. Also enables buttons on the view.
         */
        public void actionPerformed(ActionEvent e)
        {
            phymodel.stop();
            phyview.setPlayButton(!phymodel.isPlaying());
            phyview.redraw();
            phyview.reclock();
            enableAllButtons();            
        }
    }  
  
    /**
     * Listener object for the delete button in the view.
     */
    class DeleteListener implements ActionListener
    {
        /**
         * Calls for a delete in the model for all objects with names that are selected in the view.
         */
        public void actionPerformed(ActionEvent e)
        {
            int i;            
            int[] li;
            Vector namesToDelete = new Vector();
            Enumeration en;
            String ss;            

            for (i=0; i<(li = phyview.getSelectedListIndices()).length; i++)
                namesToDelete.add((String)phymodel.getListElementAt(li[i]));
            en = namesToDelete.elements();           
outer:      while(en.hasMoreElements()){
                ss = (String)en.nextElement();
                if (phymodel.removeB(ss)) continue outer;
                if (phymodel.removeGF(ss)) continue outer;
                phymodel.removeRIS(ss);
            }
            namesToDelete.clear();
           
            if (phymodel.getListSize() <= 0){
                phyview.setDeleteButton(false);
                phyview.setEditButton(false);
            }
            phyview.repaint();
        }
    }
   
    /**
     * Listener object for the edit button in the view.
     */    
    class EditListener implements ActionListener
    {
        /**
         * Creates a new edit controller and new edit view. Using these the user can edit the object first selected on the view's list.
         */
        public void actionPerformed(ActionEvent e)
        {
            String ss; 
            int pb, gf, ris;
            
            if (phyview.getSelectedListIndices().length > 0){                              
                ss = (String)phymodel.getListElementAt(phyview.getSelectedListIndices()[0]);
                if ((pb = phymodel.whereB(ss)) != -1){
                    EditBView editview = new EditBView(phymodel, pb);
                    editcontrol = new EditBController(phymodel, phyview, SELF, editview, pb, true);
                    isEditing = true;
                    editview.setVisible(true);
                }
                if ((gf = phymodel.whereGF(ss)) != -1){
                    EditGFView editview = new EditGFView(phymodel, gf);
                    editcontrol = new EditGFController(phymodel, phyview, SELF, editview, gf, true);
                    isEditing = true;
                    editview.setVisible(true);
                }
                if ((ris = phymodel.whereRIS(ss)) != -1){
                    EditRISView editview = new EditRISView(phymodel, ris);
                    editcontrol = new EditRISController(phymodel, phyview, SELF, editview, ris, true);
                    isEditing = true;
                    editview.setVisible(true);
                }                
                disableAllButtons();
            }            
        }
    }
    
    /**
     * Listener object for the 'Pointlike Body' button in the view.
     */
    class CreateBListener implements ActionListener
    {
        /**
         * Calls for the creation of a new PointlikeBody object in the model, and brings up an editing view where we can edit its properties.
         */
        public void actionPerformed(ActionEvent e)
        {
            disableAllButtons();
            phymodel.addB();
            EditBView editview = new EditBView(phymodel, phymodel.getnB()-1);
            editcontrol = new EditBController(phymodel, phyview, SELF, editview, phymodel.getnB()-1, false);
            isEditing = true;
            editview.setVisible(true);                                  
        }
    }     

    /**
     * Listener object for the 'Gravity Field' button in the view.
     */    
    class CreateGFListener implements ActionListener
    {
        /**
         * Calls for the creation of a new GravityField object in the model, and brings up an editing view where we can edit its properties.
         */        
        public void actionPerformed(ActionEvent e)
        {
            disableAllButtons();
            phymodel.addGF();
            EditGFView editview = new EditGFView(phymodel, phymodel.getnGF()-1);
            editcontrol = new EditGFController(phymodel, phyview, SELF, editview, phymodel.getnGF()-1, false);            
            isEditing = true;
            editview.setVisible(true);
        }
    }    

    /**
     * Listener object for the 'Rectangular Isolator Space' button in the view.
     */    
    class CreateRISListener implements ActionListener
    {
        /**
         * Calls for the creation of a new RectangularIsolatorSpace object in the model, and brings up an editing view where we can edit its properties.
         */        
        public void actionPerformed(ActionEvent e)
        {
            disableAllButtons();
            phymodel.addRIS();
            EditRISView editview = new EditRISView(phymodel, phymodel.getnRIS()-1);
            editcontrol = new EditRISController(phymodel, phyview, SELF, editview, phymodel.getnRIS()-1, false);            
            isEditing = true;
            editview.setVisible(true);                                  
        }
    }   
    
    /**
     * Listener object for selecting objects in the list in the view.
     */
    class SelectListener implements ListSelectionListener
    {
        /**
         * Calls for a repaint in the DrawPanel through the view when new list elements are selected to draw them white.
         */
        public void valueChanged(ListSelectionEvent e)
        {
            phyview.redraw();
        }
    }
    
    /**
     * Listener object for changes on the time speed slider in the view.
     */
    class TimeSpeedListener implements ChangeListener
    {
        /**
         * Calls for a setTimeSpeed in the timer of the simulation through the model. Also updates the view's time speedmeter.
         */
        public void stateChanged(ChangeEvent e)
        {
            phymodel.setTimeSpeed(Math.exp(((double)phyview.getSliderValue())/100));
            phyview.updateTimeSpeed();
        }
    }
    
    /**
     * Listner object for mouse clicks in the DrawPanel
     */
    class DrawPanelMouseListener implements MouseListener
    {
        /**
         * Calls for a clickedEdit on the edit controller if we are currently editing an object.
         */
        public void mousePressed(MouseEvent e)
        {
            int LEFT_BUTTON_MASK = InputEvent.BUTTON1_MASK;
            int RIGHT_BUTTON_MASK = InputEvent.BUTTON3_MASK;
            if ((e.getModifiers() & LEFT_BUTTON_MASK) == LEFT_BUTTON_MASK){
                phyview.setRefCoord(e.getX(), e.getY());
            }
            if (((e.getModifiers() & RIGHT_BUTTON_MASK) == RIGHT_BUTTON_MASK)&&(isEditing)){
                editcontrol.clickedEdit(convertBack(new Dimension(e.getX(), e.getY()), phyview.getOffset(), phyview.getDimension(), phyview.getZoom()));
                phyview.redraw();
            }
        }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseClicked(MouseEvent e){}
    }
    
    /**
     * Listner object for mouse drags in the DrawPanel
     */    
    class DrawPanelMouseMotionListener implements MouseMotionListener
    {
        /**
         * Calls for a moveOffset(Dimension) in the DrawPanel or a movedEdit in the edit controller if we are currently editing.
         */
        public void mouseDragged(MouseEvent e)
        {
            int LEFT_BUTTON_MASK = InputEvent.BUTTON1_MASK;
            int RIGHT_BUTTON_MASK = InputEvent.BUTTON3_MASK;
            if ((e.getModifiers() & LEFT_BUTTON_MASK) == LEFT_BUTTON_MASK){
                phyview.moveOffset(e.getX(), e.getY());
                phyview.redraw();     
            }
            if (((e.getModifiers() & RIGHT_BUTTON_MASK) == RIGHT_BUTTON_MASK)&&(isEditing)){
                editcontrol.movedEdit(convertBack(new Dimension(e.getX(), e.getY()), phyview.getOffset(), phyview.getDimension(), phyview.getZoom()));
                phyview.redraw();     
            }            
        }
        public void mouseMoved(MouseEvent e){}
    }
    
    /**
     * Listner object for mouse wheel rolls.
     */     
    class DrawPanelMouseWheelListener implements MouseWheelListener
    {
        /**
         * Calls for a zoom(int) in the DrawPanel through the view.
         */
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            phyview.scrollZoom(-1*e.getWheelRotation());
            phyview.redraw();
        }
    }
    
    /**
     * Converts computer graphic system's coordinates to standard mathematical coordinates in fucntion of zoom and offset.
     * @param p point to be converted
     * @param o distance between the DrawPanel's center point and the origin of the system.
     * @param z zoom...how many pixels represent 1 meter in real
     */
    private Dimension convertBack(Dimension p, Dimension o, Dimension w, double z)
    {
        return new Dimension(p.getX()/z - w.getX()/(2*z) + o.getX(), -p.getY()/z + w.getY()/(2*z) + o.getY());
    }    
    
    private void disableAllButtons()
    {
            phyview.setPlayButtonEnabled(false);
            phyview.setStopButton(false);
            phyview.setDeleteButton(false);
            phyview.setEditButton(false);
            phyview.setCreateBButton(false);
            phyview.setCreateGFButton(false);
            phyview.setCreateRISButton(false);
    }    
    
    private void enableAllButtons()
    {
            phyview.setPlayButtonEnabled(true);
            phyview.setStopButton(true);

            if (phymodel.getListSize() > 0){
                phyview.setDeleteButton(true);
                phyview.setEditButton(true);            
            }
            phyview.setCreateBButton(true);
            phyview.setCreateGFButton(true);
            phyview.setCreateRISButton(true);
    }    
}
