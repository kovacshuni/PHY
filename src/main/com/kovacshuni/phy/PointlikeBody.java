package com.kovacshuni.phy;

import java.util.Vector;
import java.util.Date;

/**
 * This object is the basic physical object that can be created in the PHY software.
 * <p>
 * It represents a point like body with mass, position and velocity.
 * The body has no dimensions, it is like a point in mathematics.
 * <p>
 * This objects lives its life independently. Whenever play is set
 * in its PHYModel object, it calculates its position and velocity
 * from time to time, depending on the surroundig conditions.
 */

public class PointlikeBody extends Thread
{
    /**
     * The model, holding addresses of all other bodies, spaces and forces.
     * <p>
     * The body will be able to connect to other forces and fields if it sees them.
     * Their addresses are stored in a class PHYModel. Only one instance of this
     * object should be allocated, and assinged to all living bodies, in order to
     * make them visible to each other.
     */
    protected PHYModel phymodel;
    /**
     * Mass of the body, measured in kilograms.
     */
    protected double m; 
    /**
     * Position of the body, measured in meters, relative to the center of a mathematical coordinate system.
     */
    protected Dimension p;
    /**
     * Velocity vector of the body, measured in meters/seconds.
     */
    protected Dimension v;
    /**
     * Name of the body.
     */
    protected String name;
    
    /**
     * Perfect definition of the object.
     * <p>
     * It gets the main attributes, and also an instance of the PHYModel class.
     * Very important to use this constructor and to specify the phymodel object.
     * Otherwise the body will have no connection to other objects, declared
     * to live inside a different system.
     * @param m mass to be assigned
     * @param p position to be assigned
     * @param v speed to be assigned
     * @param phymodel the model object which holds all the other bodies
     * @param name name to be assigned
     */
    PointlikeBody(double m, Dimension p, Dimension v, PHYModel phymodel, String name)
    {
        this.m = m;
        this.p = p;
        this.v = v;
        this.name = name;
        this.phymodel = phymodel;
    }
    
    /**
     * Checks if the point p is inside the RectangularIsolatorSpace ris.
     * @param p point to be checked
     * @param ris the RectangularIsolatorSpace to which the point is compared
     * @return true if inside, false otherwise
     */
    protected boolean isInsideRIS(Dimension p, RectangularIsolatorSpace ris)
    {
        if ((p.getX() >= ris.getPosition().getX())&&
        (p.getX() <= ris.getPosition().getX()+ris.getDimension().getX())&&
        (p.getY() >= ris.getPosition().getY())&&
        (p.getY() <= ris.getPosition().getY()+ris.getDimension().getY())) return true;
        else return false;
    }
    
    /**
     * Returns the resulting force acting on the body.
     * <p>
     * Scans through all the forces and all the spaces.
     * Determines a set of spaces in which the body is inside.
     * Determines a set of forces that are inside the same set of spaces as the body.
     * Adds those forces together.
     * @return a Dimension object representing the resulting force acting on the body
     */
    protected Dimension calculateActingForces()
    {
        int i,j;
        boolean identicalSets;
        Vector bodyinris = new Vector();
        Vector bodyinforce = new Vector();
        Dimension F = new Dimension();
        
        for (i=0; i<phymodel.getnRIS(); i++) if (isInsideRIS(p, phymodel.getRISAt(i))) bodyinris.add(phymodel.getRISAt(i));
outer:  for (i=0; i<phymodel.getnGF(); i++){
            GravityField gf = phymodel.getGFAt(i);
            identicalSets = true;
            for (j=0; j<phymodel.getnRIS(); j++){
                RectangularIsolatorSpace ris = phymodel.getRISAt(j);
                if ((isInsideRIS(gf.getPosition(), ris))^(isInsideRIS(p, ris))){
                    identicalSets = false;
                    continue outer;
                }
            }
            if (identicalSets) bodyinforce.add(gf);
        }
        for (i=0; i<bodyinforce.size(); i++) F.setXY(F.getX()+((GravityField)bodyinforce.get(i)).act(m).getX(), F.getY()+((GravityField)bodyinforce.get(i)).act(m).getY());
        return F;
    }

    /**
     * Calculates the new position of the body in function of velocity and elapsed time.
     * <p>
     * By the law of physics s = v*t
     * <p>
     * This s is added to the position of the body
     * @param t amount of time elapsed, measured in seconds
     * @return the new position of the body
     */
    private Dimension calculateNewPosition(double t)
    {
        return new Dimension(p.getX()+v.getX()*t, p.getY()+v.getY()*t);
    }

    /**
     * Calculates the new velocity of the body in function of acceleration and elapsed time.
     * <p>
     * By the law of physics a = F/m
     * <p>
     * Also v = a*t
     * <p>
     * This new v is added to the velocity of the body.
     * @param t amount of time elapsed, measured in seconds
     * @param F force acting on the body
     * @return the new velocity of the body
     */
    private Dimension calculateNewVelocity(double t, Dimension F)
    {
        if (!(m == 0)) return new Dimension(v.getX()+(F.getX()/m)*t, v.getY()+(F.getY()/m)*t);
        else return v;
    } 

    /**
     * If the playback is on, this method calculates a new position and a new velocity of the body from time to time.
     * <p>
     * Time is recorded before and after the Thread.sleep(int) command.
     * By subtracting one from the other we get the time elapsed.
     * This is because sleepTime is not always perfectly as long as specified.
     * In function of that elapsed time and body's other attributes, it calculates its new position and velocity.
     */
    public void run()
    {
        Date pastTime = new Date();
        Date presentTime = new Date();
        int sleepTime = 30;
        boolean resuming = true;
        
        while (true){
            if ((phymodel.isPlaying())/*&&(p.getX() > Double.MIN_VALUE)&&(p.getX() < Double.MAX_VALUE)&&(p.getY() > Double.MIN_VALUE)&&(p.getY() < Double.MAX_VALUE)*/){
                if (resuming){
                    pastTime = new Date();
                    resuming = false;
                }
                else pastTime = presentTime;
                presentTime = new Date();
                p = calculateNewPosition((presentTime.getTime()-pastTime.getTime())*0.001*phymodel.getTimeSpeed());
                v = calculateNewVelocity((presentTime.getTime()-pastTime.getTime())*0.001*phymodel.getTimeSpeed(), calculateActingForces());
//                System.out.println("myname: " + name + "esplased time: " + (presentTime.getTime()-pastTime.getTime()) + " x: " + p.getX() + " y: " + p.getY() + " vx: " + v.getX() + " vy: " + v.getY());   
            }
            else resuming = true;
            try{
                this.sleep(sleepTime);
            }catch(InterruptedException e){
                System.out.println(e.getStackTrace().toString());
            }            
        }
    }
    
    /**
     * Returns the position of the body
     * */    
    public Dimension getPosition()
    {
        return p;
    }
    
    /**
     * Returns the speed of the body
     * @return velocity vector
     */
    public Dimension getVelocity()
    {
        return v;
    }
    
    /**
     * Returns the mass of the body
     */
    public double getMass()
    {
        return m;
    }
    
    /**
     * Returns the name of the body
     */
    public String getMyName()
    {
        return name;
    }

    /**
     * Sets the position of the body
     * @param k position to be assigned
     */
    public void setPosition(Dimension k)
    {
        p = k;
    }
    
    /**
     * Sets the speed of the body
     * @param k speed to be assigned
     */
    public void setVelocity(Dimension k)
    {
        v = k;
    }
    
    /**
     * Sets the mass of the body
     * @param k mass to be assigned
     */
    public void setMass(double k)
    {
        m = k;
    }
    
    /**
     * Sets the name of the body
     * @param s name to be assigned
     */
    public void setMyName(String s)
    {
        name = s;
    }
}
