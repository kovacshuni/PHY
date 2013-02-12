package com.kovacshuni.phy;

/**
 * A limited version of GravityField used only for saving initial state.
 * @see GravityField
 */

public class ShellGravityField extends ShellPointlikeBody
{
	/**
	* g property of the field, measured in N*kg
	*/
    private Dimension g;

    /**
     * Minimal definition. Sets all values to zero.
     * @see GravityField
     */    
    public ShellGravityField()
    {
        super();
        g = new Dimension();
    }
    
    /**
     * Perfect definition. Records the mass, position, velcoity, gravitational constant and name
     * @see GravityField
     */    
    public ShellGravityField(double m, Dimension p, Dimension v, Dimension g, String name)
    {
        super(m,p,v,name);
        this.g = g;
    }

     /**
     * Sets g of the field.
     * @see GravityField
     */
    public void setG(Dimension k)
    {
        this.g = k;
    }
    
    /** Returns the gravitational constant of the space.
     * @see GravityField
     */    
    public Dimension getG()
    {
        return g;
    }
}
