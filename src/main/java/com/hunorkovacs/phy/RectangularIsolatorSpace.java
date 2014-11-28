package com.hunorkovacs.phy;

import java.util.Vector;

/**
* A closed, finite, rectangle shaped space, which isolates forces on the outside from acting in the inside and iverse.
* <p>
* Its base structure is the same as the PointlikeBody.
* In addition it possesses a size attribute.
* Can move and be acted on by a force if its mass is declared as a nonzero value.
* Its method, calcuating the acting forces, had to be modified to function correctly.
* <p>
* @see PointlikeBody
*/
public class RectangularIsolatorSpace extends PointlikeBody
{
	/**
	* Size of the space, measured in meters
	*/
    private Dimension d;
       
    /**
     * Perfect definition of the object, assigning mass, position, velocity, size, model and name.
     * <p>
     * It gets the main attributes, and also an instance of the PHYModel class.
     * Very important to use this constructor and to specify the phymodel object.
     * Otherwise the body will have no connection to other objects, declared
     * to live inside a different system.
     * @param m mass to be assigned
     * @param p position to be assigned
     * @param v speed to be assigned
     * @param d size to be assigned
     * @param phymodel the model object which holds all the other bodies
     * @param name name to be assigned
     */    
    RectangularIsolatorSpace(double m, Dimension p, Dimension v, Dimension d, PHYModel phymodel, String name)
    {
        super(m,p,v,phymodel,name);
        this.d = d;
    }
    
    /**
     * Returns the size of the space.
     * @return vector representing its width and height
     */    
    public Dimension getDimension()
    {
        return d;
    }
    
    /**
     * Sets the size of the space.
     * @param d a Dimension object holding the width and height of the space
     */
    public void setDimension(Dimension d)
    {
        if ((d.getX() >= 0) && (d.getY() >= 0)) this.d = d;
        if (d.getY() < 0) this.d.setY(0);
        if (d.getX() < 0) this.d.setX(0);
    }
    
    /**
     * The modified version of the calulcateActingForces() from the PointlikeBody class for spaces.
     * <p>
     * I had to override this function because of the way the PointlikeBody caluclates forces.
     * It involves checking for spaces in which is present this actual object. In case of a space,
     * we should not consider the actual one as a space when calculating acting forces.
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
                if ((isInsideRIS(gf.getPosition(), ris))^((isInsideRIS(p, ris)&&(!ris.equals(this))))){
                    identicalSets = false;
                    continue outer;
                }
            }
            if (identicalSets) bodyinforce.add(gf);
        }
        for (i=0; i<bodyinforce.size(); i++) F.setXY(F.getX()+((GravityField)bodyinforce.get(i)).act(m).getX(), F.getY()+((GravityField)bodyinforce.get(i)).act(m).getY());
        return F;
    }    
}
