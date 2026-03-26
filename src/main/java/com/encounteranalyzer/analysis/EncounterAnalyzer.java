package com.encounteranalyzer.analysis;

import com.encounteranalyzer.tracker.EncounterSession;

public class EncounterAnalyzer
{
    public EncounterSummary analyze(EncounterSession session)
    {
        double idleRatio = (double) session.getIdleTicks() / session.getTotalTicks();

        String insight;

        if (idleRatio > 0.2)
        {
            insight = "High inactivity detected. Improve action consistency.";
        }
        else if (idleRatio > 0.1)
        {
            insight = "Minor inefficiencies detected.";
        }
        else
        {
            insight = "Consistent performance.";
        }

        return new EncounterSummary(
            session.getTotalTicks(),
            session.getIdleTicks(),
            insight
        );
    }
}