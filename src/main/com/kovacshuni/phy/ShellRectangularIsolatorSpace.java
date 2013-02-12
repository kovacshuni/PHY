package com.kovacshuni.phy;

/**
 * A limited version of RectangularIsolatorSpace used only for saving initial state.
 * @see RectangularIsolatorSpace
 */

public class ShellRectangularIsolatorSpace extends ShellPointlikeBody
{
	/**
	* Size of the space, measured in meters
	*/
    private Dimension d;

    /**
     * Minimal definition. Sets all values to zero.
     * @see RectangularIsolatorSpace
     */
    public ShellRectangularIsolatorSpace()
    {
        super();
        d = new Dimension();
    }
    
    /**
     * Perfect definition. Records the mass, position, velcoity, size and name
     * @see RectangularIsolatorSpace
     */
    public ShellRectangularIsolatorSpace(double m, Dimension p, Dimension v, Dimension d, String name)
    {
        super(m,p,v,name);
        this.d = d;
    }

    /**
     * Sets the width and height of the space.
     * @see RectangularIsolatorSpace
     */
    public void setDimension(Dimension d)
    {
        this.d = d;
    }
    
    /** Returns the size of the space.
     * @see RectangularIsolatorSpace
     */
    public Dimension getDimension()
    {
        return d;
    }
}
