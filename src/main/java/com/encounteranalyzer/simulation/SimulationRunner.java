package com.encounteranalyzer.simulation;

import com.encounteranalyzer.analysis.EncounterAnalyzer;
import com.encounteranalyzer.tracker.EncounterTracker;
import com.encounteranalyzer.tracker.EncounterSession;

public class SimulationRunner
{
    public static void main(String[] args)
    {
        EncounterTracker tracker = new EncounterTracker();
        EncounterAnalyzer analyzer = new EncounterAnalyzer();

        //Example: Zulrah
        tracker.startEncounter("Zulrah");

        boolean[] ticks = {
            false, false, true, false, true,
            false, false, false, true, true,
            false, false
        };

        for (boolean isIdle : ticks)
        {
            tracker.onTick(isIdle);
        }

        EncounterSession session = tracker.finishEncounter();
        System.out.println(analyzer.analyze(session));
        System.out.println("Simulation complete for Zulrah!");
    }
}