package com.encounteranalyzer.analysis;

public class EncounterSummary
{
    private final int totalTicks;
    private final int idleTicks;
    private final String insight;

    public EncounterSummary(int totalTicks, int idleTicks, String insight)
    {
        this.totalTicks = totalTicks;
        this.idleTicks = idleTicks;
        this.insight = insight;
    }

    @Override
    public String toString()
    {
        return String.format(
            "Encounter Summary\n-----------------\nTicks: %d\nIdle: %d\nInsight: %s",
            totalTicks,
            idleTicks,
            insight
        );
    }
}