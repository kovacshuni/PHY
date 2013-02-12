package com.kovacshuni.phy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.Math;
import java.util.Vector;

/**
 * The viewport of the animation.
 * <p>
 * A frame of referece where the user can observe the movement of objects.
 * Zooming is possible. Translating is possible. 
 */
public class DrawPanel extends JPanel
{   
    /**
     * The model of the simulation, important for knowing what objects to draw, where and how.
     */
    private PHYModel phymodel;
    /**
     * The view of the simulation, important for knowing which objects are selected in the view's list, to draw them white.
     */
    private PHYView parentview;
    /**
     * The distance between the coordinates of the center of the panel and the origin.
     */
    private Dimension offset;
    /**
     * Temporary variable holding the coordinate where the user pressed the mouse button.
     */
    private Dimension refCoord;
    /**
     * The zoom multiplier. 1 meter is equivlent to 1 pixel when this variable's value is 1.
     */
    private double zoom;    
    
    /**
     * Perfect definition of the DrawPanel. Always use this constructor, otherwise it will not be able to show you the living objects.
     * @phymodel model object of the simulation
     * @parentview view object of the simulation
     */
    DrawPanel(PHYModel phymodel, PHYView parentview)
    {
        this.phymodel = phymodel;
        this.parentview = parentview;
        offset = new Dimension();
        refCoord = new Dimension();
        zoom = 100;
    }
    
    /**
     * Draws the X and Y axes, the grid, the coordinates on the sides and the objects in function of offset and zoom.
     */
    public void paintComponent(Graphics g)
    {
        int t1, i, j;
        double ii;
        Dimension t2;
        
        
        Graphics2D g2 = (Graphics2D)g;
        
        //background
        g2.setColor(Color.black);
        g2.fillRect(0,0,this.getWidth(),this.getHeight());
    
        //divisions - very hard to understand :P
        g2.setFont(new Font("Arial", 0, 10));        
        ii = 10;
        if (zoom >= 10) ii = 1.0;
        if (zoom >= 100) ii = 0.1;
        for (i=0; i<2; i++){
            if (i==0) g2.setColor(new Color(30,30,30));
            else g2.setColor(new Color(70,70,70));
            //above and to the right of the center point            
            j = 0;           
            while(((t2 = convert(new Dimension(Math.round(offset.getX()/ii)*ii+j*ii, Math.round(offset.getY()/ii)*ii+j*ii),offset,zoom)).getX() <= getWidth())
            || (t2.getY() >= 0)){
                if (t2.getX() <= getWidth()){
                    if (Math.round(offset.getX()/ii)*ii+j*ii-(int)(Math.round(offset.getX()/ii)*ii+j*ii) != 0){
//                        g2.drawString(new Double(Math.round((Math.round(offset.getX()/ii)*ii+j*ii)/ii)*ii).toString(), (int)t2.getX()+3, getHeight()-10);                
                    }
                    else
                        g2.drawString(new Integer((int)(Math.round(offset.getX()/ii)*ii+j*ii)).toString(), (int)t2.getX()+3, getHeight()-10);
                }
                if (t2.getY() >= 0){
                    if (Math.round(offset.getY()/ii)*ii+j*ii-(int)(Math.round(offset.getY()/ii)*ii+j*ii) == 0) 
                        g2.drawString(new Integer((int)(Math.round(offset.getY()/ii)*ii+j*ii)).toString(), getWidth()-30, (int)t2.getY());                
                }
                if (t2.getX() <= getWidth()) g2.drawLine((int)t2.getX(),0,(int)t2.getX(),getHeight());
                if (t2.getY() >= 0) g2.drawLine(0,(int)t2.getY(),getWidth(),(int)t2.getY());
                j++;                
            }  
            //below and to the left of the center point
            j = 1;
            while(((t2 = convert(new Dimension(Math.round(offset.getX()/ii)*ii-j*ii, Math.round(offset.getY()/ii)*ii-j*ii),offset,zoom)).getX() >= 0)
            || (t2.getY() <= getHeight())){
                if (t2.getX() >= 0){
                    if (Math.round(offset.getX()/ii)*ii-j*ii-(int)(Math.round(offset.getX()/ii)*ii-j*ii) == 0)                     
                        g2.drawString(new Integer((int)(Math.round(offset.getX()/ii)*ii-j*ii)).toString(), (int)t2.getX()+3, getHeight()-10);
                }
                if (t2.getY() <= getHeight()){
                    if (Math.round(offset.getY()/ii)*ii-j*ii-(int)(Math.round(offset.getY()/ii)*ii-j*ii) == 0)                     
                        g2.drawString(new Integer((int)(Math.round(offset.getY()/ii)*ii-j*ii)).toString(), getWidth()-30, (int)t2.getY());                
                }
                if (t2.getX() >= 0) g2.drawLine((int)t2.getX(),0,(int)t2.getX(),getHeight());
                if (t2.getY() <= getHeight()) g2.drawLine(0,(int)t2.getY(),getWidth(),(int)t2.getY());
                j++;                
            }            
            ii *= 10;            
        }   

        //axes
        g2.setColor(new Color(180,180,180));
        //x axis
        t1 = (int)java.lang.Math.round(getHeight()/2+offset.getY()*zoom);
        if ((t1 <= getHeight())&&(t1 >= 0)){                
            g2.drawLine(0,t1,getWidth(),t1);
            g2.drawLine(getWidth()-8,t1-3,getWidth(),t1);
            g2.drawLine(getWidth()-8,t1+3,getWidth(),t1);
        }
        //y axis
        t1 = (int)java.lang.Math.round(getWidth()/2-offset.getX()*zoom);
        if ((t1 <= getWidth())&&(t1 >= 0)){                
            g2.drawLine(t1,0,t1,getHeight());
            g2.drawLine(t1-3,8,t1,0);
            g2.drawLine(t1+3,8,t1,0);
        }    
        //Selected names        
        int[] li = parentview.getSelectedListIndices();
        Vector selectedNames = new Vector();
        selectedNames.clear();
        for (i=0; i<li.length; i++) selectedNames.add((String)phymodel.getListElementAt(li[i]));
        //PointlikeBodys
        g2.setColor(new Color(240,225,20));
        for (i=0; i<phymodel.getnB(); i++) drawB(g2, phymodel.getBAt(i));
        //Selected PointlikeBodys
        g2.setColor(new Color(255,255,255));        
        for (i=0; i<selectedNames.size(); i++){
            PointlikeBody pb = phymodel.getB((String)selectedNames.get(i));
            if (pb != null) drawB(g2, pb);
        }
        //RectangularIsolatorSpaces
        g2.setColor(new Color(149,105,154));
        for (i=0; i<phymodel.getnRIS(); i++) drawRIS(g2, phymodel.getRISAt(i));
        //Selected RectangularIsolatorSpaces
        g2.setColor(new Color(255,255,255));
        for (i=0; i<selectedNames.size(); i++){
            RectangularIsolatorSpace ris = phymodel.getRIS((String)selectedNames.get(i));
            if (ris != null) drawRIS(g2, ris);
        }
        //GravityFields
        g2.setFont(new Font("Arial", 0, 14));
        g2.setColor(new Color(31,148,108));
        for (i=0; i<phymodel.getnGF(); i++) drawGF(g2, phymodel.getGFAt(i));
        g2.setColor(new Color(255,255,255));
        for (i=0; i<selectedNames.size(); i++){
            GravityField gf = phymodel.getGF((String)selectedNames.get(i));
            if (gf != null) drawGF(g2, gf);
        }     
    }
    
    /**
     * Converts real world coordinates to computer graphics coordinates.
     * @param p point to be converted
     * @param o distance between viewport's center and the origin
     * @param z zoom
     * return the coordinates of the given point in the computer graphics system
     */
    private Dimension convert(Dimension p, Dimension o, double z)
    {
        return new Dimension(this.getWidth()/2+(p.getX()-o.getX())*z, this.getHeight()/2-(p.getY()-o.getY())*z);
    }
   
    /**
     * Moves the viewport canvas.
     * @param x how much to move in x direction
     * @param y how much to move in y direction
     */
    public void moveOffset(int x, int y)
    {
        offset.setXY(offset.getX()-(x-refCoord.getX())/zoom, offset.getY()+(y-refCoord.getY())/zoom);
        refCoord.setXY((double)x,(double)y);
    }
    
    /**
     * Sets the reference coordinates from where to consider the move of the viewport canvas.
     * @param x x coordinate from where the move starts
     * @param y y coordinate from where the move starts
     */
    public void setRefCoord(int x, int y)
    {
        refCoord.setXY((double)x,(double)y);
    }
    
    /**
     * Zooms up the viewport exponentially.
     * zoom value is increased by k times the one tenth of the current zoom value.
     * @param k how much? positive to zoom in, negative to zoom out. Usually 1 or -1.
     */
    public void scrollZoom(int k)
    {
        zoom += ((double)k)*zoom/10;
        if (zoom < 1) zoom = 1;
        if (zoom >1000) zoom = 1000;
    }
    
    /**
     * Draws a PointlikeBody to the screen.
     * Small x at the exact position.
     * @param g2 on which graphics object to draw
     * @param pb the body object to be drawn
     */
    private void drawB(Graphics2D g2, PointlikeBody pb)
    {
           Dimension td = convert(pb.getPosition(), offset, zoom);
           g2.drawLine((int)(Math.round(td.getX())-2), (int)(Math.round(td.getY())-2), (int)(Math.round(td.getX())+2), (int)(Math.round(td.getY())+2));
           g2.drawLine((int)(Math.round(td.getX())+2), (int)(Math.round(td.getY())-2), (int)(Math.round(td.getX())-2), (int)(Math.round(td.getY())+2));
    }
    
    /**
     * Draws a RectangularIsolatorSpace to the screen.
     * A rectangle having the bottom left corner at the objects exact position.
     * @param g2 on which graphics object to draw
     * @param ris the space object to be drawn
     */    
    private void drawRIS(Graphics2D g2, RectangularIsolatorSpace ris)
    {
            Dimension td = convert(ris.getPosition(), offset, zoom);
            Dimension td2 = convert(new Dimension(ris.getPosition().getX()+ris.getDimension().getX(), ris.getPosition().getY()+ris.getDimension().getY()), offset, zoom);
            Dimension td3;
            if ((td.getX()<=td2.getX())&&(td.getY()<=td2.getY())){
                td3 = new Dimension(td2.getX()-td.getX(), td2.getY()-td.getY());
                g2.drawRect((int)Math.round(td.getX()), (int)Math.round(td.getY()), (int)Math.round(td3.getX()), (int)Math.round(td3.getY()));
            }
            if ((td.getX()<=td2.getX())&&(td.getY()>td2.getY())){
                td3 = new Dimension(td2.getX()-td.getX(), td.getY()-td2.getY());
                g2.drawRect((int)Math.round(td.getX()), (int)Math.round(td2.getY()), (int)Math.round(td3.getX()), (int)Math.round(td3.getY()));
            }
            if ((td.getX()>td2.getX())&&(td.getY()<=td2.getY())){
                td3 = new Dimension(td.getX()-td2.getX(), td2.getY()-td.getY());
                g2.drawRect((int)Math.round(td2.getX()), (int)Math.round(td.getY()), (int)Math.round(td3.getX()), (int)Math.round(td3.getY()));
            }            
            if ((td.getX()>td2.getX())&&(td.getY()>td2.getY())){
                td3 = new Dimension(td.getX()-td2.getX(), td.getY()-td2.getY());
                g2.drawRect((int)Math.round(td2.getX()), (int)Math.round(td2.getY()), (int)Math.round(td3.getX()), (int)Math.round(td3.getY()));
            }        
    }
    
    /**
     * Draws a GravityField to the screen.
     * An x at its exact position. A line representing the g vector. A capital letter G in a circle near the x.
     * @param g2 on which graphics object to draw
     * @param gf the force object to be drawn
     */    
    private void drawGF(Graphics2D g2, GravityField gf)
    {
            Dimension td = convert(gf.getPosition(), offset, zoom);
            
            g2.drawLine((int)(Math.round(td.getX())-2), (int)(Math.round(td.getY())-2), (int)(Math.round(td.getX())+2), (int)(Math.round(td.getY())+2));
            g2.drawLine((int)(Math.round(td.getX())+2), (int)(Math.round(td.getY())-2), (int)(Math.round(td.getX())-2), (int)(Math.round(td.getY())+2));

            Dimension td2 = convert(new Dimension(gf.getPosition().getX()+gf.getG().getX(), gf.getPosition().getY()+gf.getG().getY()), offset, zoom);
            g2.drawLine((int)Math.round(td.getX()), (int)Math.round(td.getY()), (int)Math.round(td2.getX()), (int)Math.round(td2.getY()));
            
            g2.drawOval((int)Math.round(td.getX())+5, (int)Math.round(td.getY())+5 /*szia kapd be*/, 30, 30);
            g2.drawString("G",(int)Math.round(td.getX())+15, (int)Math.round(td.getY())+25);        
    }
    
    /**
     * Returns the offset of the viewport.
     * @return the distance between the coordinates of the center of the viewport and the origin of the system
     */
    public Dimension getOffset()
    {
        return offset;
    }
    
    /**
     * Returns the dimensions of the panel.
     * @return the width and height of the black panel
     */
    public Dimension getDimension()
    {
        return new Dimension(getWidth(), getHeight());
    }
    
    /**
     * Returns the zoom value.
     * @return how many pixels represent 1 meter from the real world
     */
    public double getZoom()
    {
        return zoom;
    }
}
