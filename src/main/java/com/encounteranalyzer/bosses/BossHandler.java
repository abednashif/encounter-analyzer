package com.encounteranalyzer.bosses;

import net.runelite.api.NPC;

public interface BossHandler
{
    boolean isMatch(NPC npc);
    
    public boolean isMatchByName(String name);

    void onEncounterStart(com.encounteranalyzer.tracker.EncounterSession session);

    void onEncounterEnd(com.encounteranalyzer.tracker.EncounterSession session);
}