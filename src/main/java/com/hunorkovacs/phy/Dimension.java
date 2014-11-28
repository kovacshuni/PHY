package com.hunorkovacs.phy;

/**
* A vector in 2D.
* <p>
* Holds two coordinates declared as double.
* It makes easier to call, or return from functions, which use geometric
* coordinaes. Shortens the work in the PHY project. One does not have to
* bother with coordinates of x and y, just use a Dimension object like
* this and both x and y will be included.
*/
public class Dimension
{
    /**
	* X component of the vector
	*/
	private double x;
    /**
	* Y component of the vector
	*/ 
	private double y;
    
    /**
	* Creates a Dimension with coordinates in the origin.
	*/
    Dimension()
    {
        x = 0;
        y = 0;
    }
    
	/**
	* Creates a Dimension with coordinates x and y.
	* @param x double representing the position on x axis.
	* @param y double representing the position on y axis.
	*/
    Dimension(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
	/**
	* Creates a Dimension with coordinates x and y.
	* @param x float, later converted to double representing the position on x axis.
	* @param y float, later converted to doulbe representing the position on y axis.
	*/	
    Dimension(float x, float y)
    {
        this.x = (double)x;
        this.y = (double)y;
    }
    
	/**
	* Creates a Dimension with coordinates x and y.
	* @param x integer, later converted to double representing the position on x axis.
	* @param y integer, later converted to doulbe representing the position on y axis.
	*/	
    Dimension(int x, int y)
    {
        this.x = (double)x;
        this.y = (double)y;
    }
    
	/**
	* Creates a new Dimension object by referring to an already declared Dimension.
	* Actually it creates a copy of the parameter.
	* @param d the Dimension object to be copied
	*/
    Dimension(Dimension d)
    {
        this.x = d.getX();
        this.y = d.getY();
    }
	
	/**
	* Returns the x coordinate of the Dimension.
	* @return double representing the x component of the 2D vector.
	*/
    public double getX()
    {
        return x;
    }
    
	/**
	* Returns the y coordinate of the Dimension.
	* @return double representing the y component of the 2D vector.
	*/	
    public double getY()
    {
        return y;
    }
  
	/**
	* Modifies the Dimension object, by assigning it new coordinates passed as arguments.
	* @return double representing the y component of the 2D vector.
	*/	
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
	/**
	* Modifies the Dimension object, assigning it a new x component.
	* @param x double to be assigned as x component of the 2D vector.
	*/	
    public void setX(double x)
    {
        this.x = x;
    }
    
	/**
	* Modifies the Dimension object, assigning it a new x component.
	* @param x double to be assigned as x component of the 2D vector.
	*/		
    public void setY(double y)
    {
        this.y = y;
    }
}