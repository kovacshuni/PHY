package com.hunorkovacs.phy;

import java.util.Date;

/**
 * A class doing the job of a stopwatch for the PHY simulation.
 * <p>
 * Can be started, paused or stopped and it measures the time in miliseconds, in function of the time speed.
 */
public class Timer
{
    /**
     * The model object of the simulation, required for checking whether the simulation is playing or not.
     */
    private PHYModel phymodel;    
    /**
     * This holds the time when the simulation started to play in milliseconds since 1970. january 1st
     */
    private long referenceTime;
    /**
     * Holds the sum of time durations when the simulation was in play state. Does not hold the time durations when simulation stood in pause state.
     */
    private long previousTimes;
    /**
     * The speed multiplier. Holds how faster the simulation flows than real time.
     */
    private double timeSpeed;    
    
    /**
     * Perfect definition constructor. Without referring to the PHYModel object the timer will not be able
     * to connect to the model and check if the simulation is playing or not. Always use this constructor.
     * @param m The model object of the simulation, same where all the simulation objects are stored.
     */
    public Timer(PHYModel m)
    {
        this.phymodel = m;
        timeSpeed = 1;
        referenceTime = 0;
        previousTimes = 0;        
    }
    
    /**
     * Used when time speed is changed. Calculates the elapsed time during the last play interval in function of that interval's constant time speed and adds it to previousTimes.
     */
    private void differentiateTime()
    {
        long d = (new Date()).getTime();
        previousTimes += (long)((d - referenceTime)*timeSpeed);
        referenceTime = d;
    }
    
    /**
     * Sets the time speed.
     * @param x the time multiplier...how faster the simulation speed is than the in real time
     */
    public void setTimeSpeed(double x)
    {
        timeSpeed = x;
        if (phymodel.isPlaying()) differentiateTime();         
    }
    
    /**
     * Returns the time speed.
     * @return time multiplier...how faster the simulation speed is than the in real time
     */
    public double getTimeSpeed()
    {
        return timeSpeed;
    }    
    
    /**
     * Returns the time elapsed since the simulation has started.
     * The main function the view uses from this class. This amount is shown in the toolbar, except that this is in milliseconds.
     */
    public long getElapsedTime()
    {
        if (phymodel.isPlaying()) return (long)(((new Date()).getTime() - referenceTime)*timeSpeed) + previousTimes;
        else return previousTimes;        
    }
    
    /**
     * Stops the timer. Sets previousTimes to zero.
     */
    public void stop()
    {
        previousTimes = 0;
    }
    
    /**
     * Starts the timer. Sets referenceTime to current time. By this the stopwatch will remember when the last play interval was started.
     */
    public void play()
    {
        referenceTime = (new Date()).getTime();        
    }
    
    /**
     * Pauses the timer. Calculates the duration of the last play interval in function of that interval's constant time speed and adds it to previousTimes.
     * This way the stopwatch will remember the overall duration of play intervals until the last complete (paused) play interval.
     */
    public void pause()
    {
        previousTimes += (long)(((new Date()).getTime() - referenceTime)*timeSpeed);        
    }
}
