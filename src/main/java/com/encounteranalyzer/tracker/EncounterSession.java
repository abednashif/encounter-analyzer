package com.encounteranalyzer.tracker;

public class EncounterSession
{
    private int totalTicks;
    private int idleTicks;
    private boolean finished;
    private boolean lastTickIdle;

    public void tick(boolean isIdle)
    {
        lastTickIdle = isIdle;
        totalTicks++;
        if (isIdle)
        {
            idleTicks++;
        }
    }

    public void finish()
    {
        this.finished = true;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public int getTotalTicks()
    {
        return totalTicks;
    }

    public int getIdleTicks()
    {
        return idleTicks;
    }
    
    public boolean lastTickWasIdle()
    {
        return lastTickIdle;
    }
}