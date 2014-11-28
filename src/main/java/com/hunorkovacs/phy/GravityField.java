package com.hunorkovacs.phy;

import java.util.Vector;

/**
* A uniform force field with constant magnitude and direction.
* <p>
* Its base structure is the same as the PointlikeBody.
* In addition it possesses a force vector g.
* Can move and be acted on by another force if its mass is declared to a nonzero value.
* Its method, calcuating the acting forces, had to be modified to function correctly.
* <p>
* @see PointlikeBody
*/
public class GravityField extends PointlikeBody
{
    /**
     * The g property of the force field. Its magnitude is measured in N*kg.
     */
    private com.hunorkovacs.phy.Dimension g;
   
    /**
     * Perfect definition of the object, assigning mass, position, velocity, g, model and name.
     * <p>
     * It gets the main attributes, and also an instance of the PHYModel class.
     * Very important to use this constructor and to specify the phymodel object.
     * Otherwise the body will have no connection to other objects, declared
     * to live inside a different system.
     * @param m mass to be assigned
     * @param p position to be assigned
     * @param v speed to be assigned
     * @param g g constant of the force field to be assigned
     * @param phymodel the model object which holds all the other bodies
     * @param name name to be assigned
     */ 
    GravityField(double m, com.hunorkovacs.phy.Dimension p, com.hunorkovacs.phy.Dimension v, com.hunorkovacs.phy.Dimension g, PHYModel phymodel, String name)
    {
        super(m,p,v,phymodel,name);
        this.g = g;
    }
    
    /**
     * Returns the g property of the object.
     * @return the vector holding x and y components of the force measured in N*kg.
     */
    public com.hunorkovacs.phy.Dimension getG()
    {
        return g;
    }
    
    /**
     * Sets the g property of the object.
     * @param g the vector holding x and y components of the force measured in N*kg.
     */
    public void setG(com.hunorkovacs.phy.Dimension g)
    {
        this.g = g;
    }
    
    /**
     * Returns the applied force to a body with mass m.
     * <p>
     * proceeds by the formula F = m*g
     * @param m the mass of the body to act on
     * @return the force vector applied on the given body by this field
     */
    public com.hunorkovacs.phy.Dimension act(double m)
    {
        return new com.hunorkovacs.phy.Dimension(g.getX()*m, g.getY()*m);
    }
    
    /**
     * The modified version of the calulcateActingForces() from the PointlikeBody class for spaces.
     * <p>
     * I had to override this function because of the way the PointlikeBody caluclates forces.
     * It involves checking forces that apply to this actual object. In case of a force,
     * we should not consider the actual one as an acting force during the calculation.
     * Otherwise each force would make itself accelerate.
     */    
    protected com.hunorkovacs.phy.Dimension calculateActingForces()
    {
        int i,j;
        boolean identicalSets;
        Vector bodyinris = new Vector();
        Vector bodyinforce = new Vector();
        com.hunorkovacs.phy.Dimension F = new com.hunorkovacs.phy.Dimension();
        
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
            if ((identicalSets)&&(!gf.equals(this))) bodyinforce.add(gf);
        }
        for (i=0; i<bodyinforce.size(); i++) F.setXY(F.getX()+((GravityField)bodyinforce.get(i)).act(m).getX(), F.getY()+((GravityField)bodyinforce.get(i)).act(m).getY());
        return F;
    }    
}
