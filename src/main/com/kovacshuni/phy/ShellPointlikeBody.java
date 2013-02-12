package com.kovacshuni.phy;

/**
 * A limited version of PointlikeBody used only for saving initial state.
 * @see PointlikeBody
 */
public class ShellPointlikeBody
{
	/**
	* Mass of the body, measured in kilograms
	*/
    private double m;
    
    /**
     * Position of the body, measured in meters, relative to the center of a mathematical coordinate system.
     */    
    private Dimension p;
    
    /**
     * Velocity vector of the body, measured in meters/seconds.
     */    
    private Dimension v;
    
    /**
     * Name of the body.
     */    
    private String name;
    
    /**
     * Minimal definition. Sets all values to zero.
     * @see PointlikeBody
     */    
    public ShellPointlikeBody()
    {
        this.m = 1;
        this.p = new Dimension();
        this.v = new Dimension();
        this.name = new String();
    }
    
    /**
     * Perfect definition. Records the mass, position, velcoity and name
     * @see PointlikeBody
     */   
    public ShellPointlikeBody(double m, Dimension p, Dimension v, String name)
    {
        this.m = m;
        this.p = p;
        this.v = v;
        this.name = name;
    }

    /**
     * Sets the position of the body.
     * @see PointlikeBody
     */    
    public void setPosition(Dimension k)
    {
        p = k;
    }
    
    /**
     * Sets the speed of the body.
     * @see PointlikeBody
     */     
    public void setVelocity(Dimension k)
    {
        v = k;
    }
    
    /**
     * Sets the mass of the body.
     * @see PointlikeBody
     */     
    public void setMass(double k)
    {
        m = k;
    }
    
    /**
     * Sets the name of the body.
     * @see PointlikeBody
     */     
    public void setMyName(String s)
    {
        name = s;
    }

     /**
     * Returns the mass of the body.
     * @see PointlikeBody
     */ 
    public double getMass()
    {
        return m;
    }
    
     /**
     * Returns the position of the body.
     * @see PointlikeBody
     */     
    public Dimension getPosition()
    {
        return p;
    }
    
     /**
     * Returns the speed of the body.
     * @see PointlikeBody
     */     
    public Dimension getVelocity()
    {
        return v;
    }
    
     /**
     * Returns the name of the body.
     * @see PointlikeBody
     */     
    public String getMyName()
    {
        return name;
    }
}
