package com.hunorkovacs.phy;

import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 * A class holding all the addresses of role playing objects. It represents the Model in the MVC pattern. All the View and Controller class types are communicating through this one.
 * <p>
 * It stores in vectors the user created objects. The initial states of the same objects are also memorized.
 * It holds a Timer and DefaultListModel with the names in it.
 * <p> 
 * The individual Thread objects like PointlikeBody are referring to this PHYModel in their every cycle in their run method.
 * They determine the forces acting on them by searching through the PHYModel's spaces and forces.
 * <p>
 * An example: A PointlikeBody is created with its no-argument constructor, assigning a brand new PHYModel object holding no other bodies.
 * A GravityForce is created with its no-argument minimal constructor. Later we assign a new g property with -9.8 N*kg on its Y axis.
 * When simulation is played back, nothing happens. The body does not know anything about the force, and neither the force knows
 * nothing about the body. They are completely isolated in completely different systems becouse of their PHYModel attribute being
 * different.
 * <p>
 * I did not declare this as static. Maybe for later extensions like multiple systems simulations.
 */
public class PHYModel
{
    /**
     * States whether the simulation is playing right now or not.
     */
    private boolean isplaying;
    /**
     * Used for measuring elapsed time, and necessary information for simulation objects.
     */
    private Timer timer;
    /**
     * A list holding the names of the simulation objects.
     */
    private DefaultListModel listmodel;  
    /**
     * Vector holding all instances of PointlikeBodys.
     */
    private Vector B;
    /**
     * Vector holding all instances of RectangularIsolatorSpaces.
     */    
    private Vector RIS;
    /**
     * Vector holding all instances of GravityFields.
     */    
    private Vector GF;

    private List<GravitationalPoint> gravitationalPoints;

    /**
     * Vector for keeping in mind the initial states of all PointlikeBodys.
     */    
    private Vector initialB;
    /**
     * Vector for keeping in mind the initial states of all RectangularIsolatorSpaces.
     */      
    private Vector initialRIS;
    /**
     * Vector for keeping in mind the initial states of all GravityFields.
     */      
    private Vector initialGF;

    /**
     * Constructor thats sets up the scene. Sets play to false, assigns a new Timer for timer, and a set of new empty Vectors.
     */
    PHYModel()
    {
        isplaying = false;
        timer = new Timer(this);
        listmodel= new DefaultListModel(); 
         
        B = new Vector();
        RIS = new Vector();  
        GF = new Vector(); 
        
        initialB = new Vector();
        initialRIS = new Vector();
        initialGF = new Vector();
    }
     
    /**
     * Creates and inserts a new PointlikeBody in the system.
     * Renames the object if found an already existing object with the same name.
     * Of course its name is added to the namelist too, and the created object's
     * initial state is recorded in a new ShellPointlikeBody object kept in
     * vector initialB.
     * @param m mass of the new body
     * @param p position of the new body
     * @param v velocity vector of the new body
     * @param name of the new body
     */
    public void addB(double m, Dimension p, Dimension v, String name)
    {
        String pbn;
        Integer a;
        int maxa = 0;
        int i;
        
        for (i=0; i<B.size(); i++){
            pbn = ((PointlikeBody)B.get(i)).getMyName();
            if (pbn.startsWith("PB#")){
                a = new Integer(pbn.substring(3));
                if (maxa < a) maxa = a;
            }
            if (pbn.equals(name)) name = new String("PB#" + (maxa+1));
        }
        
        B.add(new PointlikeBody(m,p,v,this,name));
        initialB.add(new ShellPointlikeBody(m,p,v,name));
        listmodel.addElement(name);
    }
    
    /**
     * Creates and inserts a new GravityField in the system.
     * Renames the object if found an already existing object with the same name.
     * Of course its name is added to the namelist too, and the created object's
     * initial state is recorded in a new ShellGravityField object kept in
     * vector initialGF.
     * @param m mass of the new force field
     * @param p position of the new force field
     * @param v velocity vector of the new force field
     * @param g gravitational constant of the new force field, representing the force of Newtons that acts on each kilogram of a mass
     * @param name of the new force field
     */    
    public void addGF(double m, Dimension p, Dimension v, Dimension g, String name)
    {
        String gfn;
        Integer a;
        int maxa = 0;
        int i;
        
        for (i=0; i<GF.size(); i++){
            gfn = ((GravityField)GF.get(i)).getMyName();
            if (gfn.startsWith("GF#")){
                a = new Integer(gfn.substring(3));
                if (maxa < a) maxa = a;
            }
            if (gfn.equals(name)) name = new String("GF#" + (maxa+1));
        }
        
        GF.add(new GravityField(m,p,v,g,this,name));
        initialGF.add(new ShellGravityField(m,p,v,g,name));
        listmodel.addElement(name);
    }
    
    /**
     * Creates and inserts a new RectangularIsolatorSpace in the system.
     * Renames the object if found an already existing object with the same name.
     * Of course its name is added to the namelist too, and the created object's
     * initial state is recorded in a new ShellRectangularIsolatorSpace object kept in
     * vector initialRIS.
     * @param m mass of the new space
     * @param p position of the new space
     * @param v velocity vector of the new space
     * @param d dimension of the new space, referring to its width and height
     * @param name of the new space
     */     
    public void addRIS(double m, Dimension p, Dimension v, Dimension d, String name)
    {
        String risn;
        Integer a;
        int maxa = 0;
        int i;
        
        for (i=0; i<RIS.size(); i++){
            risn = ((RectangularIsolatorSpace)RIS.get(i)).getMyName();
            if (risn.startsWith("RIS#")){
                a = new Integer(risn.substring(4));
                if (maxa < a) maxa = a;
            }
            if (risn.equals(name)) name = new String("RIS#" + (maxa+1));
        }
        
        RIS.add(new RectangularIsolatorSpace(m,p,v,d,this,name));
        initialRIS.add(new ShellRectangularIsolatorSpace(m,p,v,d,name));        
        listmodel.addElement(name);
    }  
    
    /**
     * Adds a new PointlikeBody with default attributes: codenamed 1kg mass standing still in the origin.
     */
    public void addB()
    {
        addB(1,new Dimension(), new Dimension(), "PB#1");
    }
    
    /**
     * Adds a new GravityField with default attributes: codenamed force field with zero g, standing still in the origin.
     */    
    public void addGF()
    {
        addGF(0,new Dimension(), new Dimension(), new Dimension(), "GF#1");
    }
    
    /**
     * Adds a new RectangularIsolatorSpace with default attributes: codenamed space with no dimension, standing still in the origin.
     */    
    public void addRIS()
    {
        addRIS(0,new Dimension(), new Dimension(), new Dimension(), "RIS#1");
    }    
    
    /**
     * Modifies the PointlikeBody at the specified location in the B Vector.
     * If duplicate names would appear, adds a different name for the object.
     * Of course modifies the corresponding name in the namelist, and modifies
     * the initial state in initialB.
     * @param m modified mass of the body
     * @param p modified position of the body
     * @param v modified velocity of the body
     * @param k the index of the PointlikeBody in Vector B
     * @param newname modified name of the body
     * @return true on success, false on fail
     */
    public boolean editBAt(double m, Dimension p, Dimension v, int k, String newname)
    {      
        String pbn;
        int maxa = 0;
        int i;
        Integer a;
        
        PointlikeBody pb = (PointlikeBody)B.get(k);
        ShellPointlikeBody spb = (ShellPointlikeBody)initialB.get(k);        
        if (pb != null){
            pb.setMass(m);
            pb.setPosition(p);
            pb.setVelocity(v);
            String oldname = pb.getMyName();

            for (i=0; i<B.size(); i++){
                pbn = ((PointlikeBody)B.get(i)).getMyName();
                if (pbn.startsWith("PB#")){
                    a = new Integer(pbn.substring(3));
                    if (maxa < a) maxa = a;
                }        
                if ((pbn.equals(newname))&&(!((PointlikeBody)B.get(i)).equals(pb))) newname = new String("PB#" + (maxa+1));
            }
            pb.setMyName(newname);
            
            spb.setMass(m);
            spb.setPosition(p);
            spb.setVelocity(v);
            spb.setMyName(newname);
            
            listmodel.removeElement(oldname);
            listmodel.addElement(newname);            
            
            return true;
        }
        return false;
    }   

    /**
     * Modifies the GravityField at the specified location in the GF Vector.
     * If duplicate names would appear, adds a different name for the object.
     * Of course modifies the corresponding name in the namelist, and modifies
     * the initial state in initialGF.
     * @param m modified mass of the force field
     * @param p modified position of force field
     * @param v modified velocity of force field
     * @param g modified g constant of the force field
     * @param k the index of the GravityField in Vector GF
     * @param newname modified name of force field
     * @return true on success, false on fail
     */    
    public boolean editGFAt(double m, Dimension p, Dimension v, Dimension g, int k, String newname)
    {
        String gfn;
        int maxa = 0;
        int i;
        Integer a;
        
        GravityField gf = (GravityField)GF.get(k);
        ShellGravityField sgf = (ShellGravityField)initialGF.get(k);
        if (gf != null){
             gf.setMass(m);
             gf.setPosition(p);
             gf.setVelocity(v);
             gf.setG(g);
             String oldname = gf.getMyName();
             for (i=0; i<GF.size(); i++){
                gfn = ((GravityField)GF.get(i)).getMyName();
                if (gfn.startsWith("GF#")){
                    a = new Integer(gfn.substring(3));
                    if (maxa < a) maxa = a;
                }        
                if ((gfn.equals(newname))&&(!((GravityField)GF.get(i)).equals(gf))) newname = new String("GF#" + (maxa+1));
            }                
            gf.setMyName(newname);  
                        
            sgf.setMass(m);
            sgf.setPosition(p);
            sgf.setVelocity(v);
            sgf.setG(g);
            sgf.setMyName(newname);            
              
            listmodel.removeElement(oldname);
            listmodel.addElement(newname);
            return true;
        }
        return false;
    }
 
    /**
     * Modifies the RectangularIsolatorSpace at the specified location in the RIS Vector.
     * If duplicate names would appear, adds a different name for the object.
     * Of course modifies the corresponding name in the namelist, and modifies
     * the initial state in initialRIS.
     * @param m modified mass of the space
     * @param p modified position of space
     * @param v modified velocity of space
     * @param g modified dimension of the space
     * @param k the index of the RectangularIsolatorSpace in Vector RIS
     * @param newname modified name of the space
     * @return true on success, false on fail
     */    
    public boolean editRISAt(double m, Dimension p, Dimension v, Dimension d, int k, String newname)
    {
        String risn;
        int maxa = 0;
        int i;
        Integer a;
        
        RectangularIsolatorSpace ris = (RectangularIsolatorSpace)RIS.get(k);
        ShellRectangularIsolatorSpace sris = (ShellRectangularIsolatorSpace)initialRIS.get(k);        
        if (ris != null){
            ris.setMass(m);
            ris.setPosition(p);
            ris.setVelocity(v);
            ris.setDimension(d);
            String oldname = ris.getMyName();

            for (i=0; i<RIS.size(); i++){
                risn = ((RectangularIsolatorSpace)RIS.get(i)).getMyName();
                if (risn.startsWith("RIS#")){
                    a = new Integer(risn.substring(4));
                    if (maxa < a) maxa = a;
                }        
                if ((risn.equals(newname))&&(!((RectangularIsolatorSpace)RIS.get(i)).equals(ris))) newname = new String("RIS#" + (maxa+1));
            }                
            ris.setMyName(newname);
            
            sris.setMass(m);
            sris.setPosition(p);
            sris.setVelocity(v);
            sris.setDimension(d);
            sris.setMyName(newname);             
                
            listmodel.removeElement(oldname);
            listmodel.addElement(newname);
            return true;
        }   
        return false;
    }    
    
    /**
     * Modifies the specified named PointlikeBody.
     * Uses the help of methods whereB(name) and editBAt(double, Dimension, Dimension, int, String).
     * @param m modified mass of the body
     * @param p modified position of the body
     * @param v modified velocity of the body
     * @param name the name of the PointlikeBody to be modified
     * @param newname modified name of the body
     * @return true on success, false on fail
     */
    public boolean editB(double m, Dimension p, Dimension v, String name, String newname)
    {
        return editBAt(m, p, v, whereB(name), newname);
    }    

    /**
     * Modifies the specified named GravityField.
     * Uses the help of methods whereGF(name) and editGFAt(double, Dimension, Dimension, Dimension int, String).    
     * @param m modified mass of the force field
     * @param p modified position of force field
     * @param v modified velocity of force field
     * @param g modified g constant of the force field
     * @param name the name of the GravityField to be modified
     * @param newname modified name of force field
     * @return true on success, false on fail
     */
    public boolean editGF(double m, Dimension p, Dimension v, Dimension g, String name, String newname)
    {
        return editGFAt(m, p, v, g, whereGF(name), newname);
    } 
    
    /**
     * Modifies the specified named RectagularIsolatorSpace.
     * Uses the help of methods whereRIS(name) and editRISAt(double, Dimension, Dimension, Dimension, int, String).      
     * @param m modified mass of the space
     * @param p modified position of space
     * @param v modified velocity of space
     * @param g modified dimension of the space
     * @param k the index of the RectangularIsolatorSpace in Vector RIS
     * @param newname modified name of the space
     * @return true on success, false on fail
     */     
    public boolean editRIS(double m, Dimension p, Dimension v, Dimension d, String name, String newname)
    {
        return editRISAt(m, p, v, d, whereRIS(name), newname);
    }
    
    /**
     * Removes a PointlikeBody from the system.
     * @param name the name of the given PointlikeBody
     * @return true on success, false on fail
     */
    public boolean removeB(String name)
    {
        return removeBAt(whereB(name));
    }
    
    /**
     * Removes a GravityField from the system.
     * @param name the name of the given GravityField
     * @return true on success, false on fail
     */    
    public boolean removeGF(String name)
    {
        return removeGFAt(whereGF(name));
    }    
    
    /**
     * Removes a RectangularIsolatorSpace from the system.
     * @param name the name of the given RectangularIsolatorSpace
     * @return true on success, false on fail
     */    
    public boolean removeRIS(String name)
    {
        return removeRISAt(whereRIS(name));
    }    
    
    /**
     * Removes a PointlikeBody from the Vector B at the specified index.
     * Also removes its name from the namelist and the object holding its initial state.
     * @param k the index of the given PointlikeBody
     * @return true on success, false on fail
     */    
    public boolean removeBAt(int k)
    {
        if (getBAt(k)==null) return false;
        listmodel.removeElement(getBAt(k).getMyName());
        B.remove(k);
        initialB.remove(k);
        return true;
    }
   
    /**
     * Removes a GravityField from the Vector GF at the specified index
     * Also removes its name from the namelist and the object holding its initial state.
     * @param k the index of the given GravityField
     * @return true on success, false on fail
     */    
    public boolean removeGFAt(int k)
    {
        if (getGFAt(k)==null) return false;
        listmodel.removeElement(getGFAt(k).getMyName());
        GF.remove(k);
        initialGF.remove(k);
        return true;
    }
    
    /**
     * Removes a RectangularIsolatorSpace from the Vector RIS at the specified index
     * Also removes its name from the namelist and the object holding its initial state.
     * @param k the index of the given RectangularIsolatorSpace
     * @return true on success, false on fail
     */    
    public boolean removeRISAt(int k)
    {
        if (getRISAt(k)==null) return false;
        listmodel.removeElement(getRISAt(k).getMyName());
        RIS.remove(k);
        initialRIS.remove(k);
        return true;
    }    
    
    /**
     * Returns the number of PointlikeBodys in the system.
     * @return size of the B Vector
     */
    public int getnB()
    {
        return B.size();
    }

    /**
     * Returns the number of RectangularIsolatorSpace in the system.
     * @return size of the RIS Vector
     */    
    public int getnRIS()
    {
        return RIS.size();
    }   
    
    /**
     * Returns the number of GravityField in the system.
     * @return size of the GF Vector
     */     
    public int getnGF()
    {
        return GF.size();
    }

    public List<GravitationalPoint> getGravitationalPoints() {
        return
    }
    
    /**
     * Returns the PointlikeBody with the given name.
     * @param name name of the object
     * @return a PointlikeBody object with specified name, or null if failed
     */
    public PointlikeBody getB(String name)
    {
        int l;
        if ((l = whereB(name)) != -1) return getBAt(l);
        else return null;
    } 

    /**
     * Returns the RectangularIsolatorSpace with the given name.
     * @param name name of the object
     * @return a RectangularIsolatorSpace object with specified name, or null if failed
     */    
    public RectangularIsolatorSpace getRIS(String name)
    {
        int l;
        if ((l = whereRIS(name)) != -1) return getRISAt(l);
        else return null;
    }
    
    /**
     * Returns the GravityField with the given name.
     * @param name name of the object
     * @return a GravityField object with specified name, or null if failed
     */    
    public GravityField getGF(String name)
    {
        int l;
        if ((l = whereGF(name)) != -1) return getGFAt(l);
        else return null;
    }
        
    /**
     * Returns the PointlikeBody at the index k.
     * @param k index of the object in Vector B
     * @return a PointlikeBody object with specified name, or null if k out of range
     */     
    public PointlikeBody getBAt(int k)
    {
        if ((k < B.size())&&(k >= 0))
            return (PointlikeBody)B.get(k);
        else return null;
    }
    
    /**
     * Returns the RectangularIsolatorSpace at the index k.
     * @param k index of the object in Vector RIS
     * @return a RectangularIsolatorSpace object with specified name, or null if k out of range
     */    
    public RectangularIsolatorSpace getRISAt(int k)
    {
        if ((k < RIS.size())&&(k >= 0))
            return (RectangularIsolatorSpace)RIS.get(k);
        else return null;
    }  
    
    /**
     * Returns the GravityField at the index k.
     * @param k index of the object in Vector GF
     * @return a GravityField object with specified name, or null if k out of range
     */    
    public GravityField getGFAt(int k)
    {
        if ((k < GF.size())&&(k >= 0))
            return (GravityField)GF.get(k);
        else return null;
    }
    
    /**
     * Returns the index of a PointlikeBody from Vector B by knowing its name.
     * @param s name to search for
     * @return location index in Vector B or -1 if not found
     */
    public int whereB(String s)
    {
        int i;
        PointlikeBody pb;
        for (i=0; i<B.size(); i++)
            if ((pb = (PointlikeBody)B.get(i)).getMyName().equals(s)) return i;
        return -1;        
    }
    
    /**
     * Returns the index of a GravityField from Vector GF by knowing its name.
     * @param s name to search for
     * @return location index in Vector GF or -1 if not found
     */    
    public int whereGF(String s)
    {
        int i;
        GravityField gf;
        for (i=0; i<GF.size(); i++)
            if ((gf = (GravityField)GF.get(i)).getMyName().equals(s)) return i;
        return -1;        
    }
    
    /**
     * Returns the index of a RectangularIsolatorSpace from Vector RIS by knowing its name.
     * @param s name to search for
     * @return location index in Vector RIS or -1 if not found
     */    
    public int whereRIS(String s)
    {
        int i;
        RectangularIsolatorSpace ris;
        for (i=0; i<RIS.size(); i++)
            if ((ris = (RectangularIsolatorSpace)RIS.get(i)).getMyName().equals(s)) return i;
        return -1;        
    }
    
    /**
     * Returns the DefaultListmodel used by this PHYModel object. For special purposes only.
     * Created this method only for the need of the PHYView's jList.
     * @return a DefaultListmodel with names of simulation objects in it
     */
    public DefaultListModel getListModel()
    {
        return listmodel;
    }
    
    /**
     * Returns the size of the namelist.
     * @return the number of names on the namelist
     */
    public int getListSize()
    {
        return listmodel.getSize();
    }
    

    /**
     * Returns the object at index k from the namelist.
     * @param k index to return
     * @return name on index k in listmodel
     */
    public Object getListElementAt(int k)
    {
        return listmodel.getElementAt(k);
    }      
    
    /**
     * Stops the simulation.
     * Sets isplaying attribute to false. Calls for stop() in timer, to reset the time.
     * Puts back every simulation object to its initial state.
     */
    public void stop()
    {
        int i;
        isplaying = false;
        timer.stop();
        for (i=0; i<B.size(); i++){
            PointlikeBody pb = getBAt(i);
            ShellPointlikeBody spb = (ShellPointlikeBody)initialB.get(i);
            pb.setPosition(new Dimension(spb.getPosition()));
            pb.setVelocity(new Dimension(spb.getVelocity()));
            pb.setMass(spb.getMass());
            pb.setMyName(new String(spb.getMyName()));
        }
        for (i=0; i<GF.size(); i++){
            GravityField gf = getGFAt(i);
            ShellGravityField sgf = (ShellGravityField)initialGF.get(i);
            gf.setPosition(new Dimension(sgf.getPosition()));
            gf.setVelocity(new Dimension(sgf.getVelocity()));
            gf.setG(new Dimension(sgf.getG()));
            gf.setMass(sgf.getMass());
            gf.setMyName(new String(sgf.getMyName()));
        }
        for (i=0; i<RIS.size(); i++){
            RectangularIsolatorSpace ris = getRISAt(i);
            ShellRectangularIsolatorSpace sris = (ShellRectangularIsolatorSpace)initialRIS.get(i);
            ris.setPosition(new Dimension(sris.getPosition()));
            ris.setVelocity(new Dimension(sris.getVelocity()));
            ris.setDimension(new Dimension(sris.getDimension()));
            ris.setMass(sris.getMass());
            ris.setMyName(new String(sris.getMyName()));
        }
    }
    
    /**
     * Starts the simulation.
     * Sets isplaying attribute to true, and calls for start() functions in threads which are not yet alive.
     * Also calls for play() in the timer, to memorize the time, when play was set.
     */
    public void play()
    {
        int i;       
        isplaying = true;
        timer.play();

        for (i=0; i<B.size(); i++) if (!((PointlikeBody)B.get(i)).isAlive()) ((PointlikeBody)B.get(i)).start();
        for (i=0; i<GF.size(); i++) if (!((GravityField)GF.get(i)).isAlive()) ((GravityField)GF.get(i)).start();
        for (i=0; i<RIS.size(); i++) if (!((RectangularIsolatorSpace)RIS.get(i)).isAlive()) ((RectangularIsolatorSpace)RIS.get(i)).start();
    }
    
    /**
     * Pauses the simulation.
     * Sets isplaying attribute to false, and calls for pause() in the timer, to take into consideration the time passing when paused
     */
    public void pause()
    {        
        isplaying = false;
        timer.pause();        
    }
    
    /**
     * Returns whether the simulation is on or off.
     * @return true when playing, false when paused or stopped
     */
    public boolean isPlaying()
    {
        return isplaying;
    }   
    
    /**
     * Forwards the getTimeSpeed() function call to the timer. Returns the value returned by the timer.
     * @return value representing how faster the time flows in the simulation than in real
     */
    public double getTimeSpeed()
    {
        return timer.getTimeSpeed();
    }
    
    /**
     * Forwards the setTimeSpeed() function call to the timer. Sets the timer's time speed.
     * @param x value representing how faster the time should flow in the simulation than in real
     */    
    public void setTimeSpeed(double x)
    {
        timer.setTimeSpeed(x);       
    }
    
    /**
     * Forwards the getElapsedTime() function call to the timer. Returns the elapsed time since the simulation has started.
     * @return number of miliseconds elapsed since simulation has started
     */
    public long getElapsedTime()
    {
        return timer.getElapsedTime();
    }
}
