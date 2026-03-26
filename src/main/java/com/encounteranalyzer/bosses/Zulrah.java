package com.encounteranalyzer.bosses;

import net.runelite.api.NPC;
import com.encounteranalyzer.tracker.EncounterSession;

public class Zulrah implements BossHandler
{
	@Override
	public boolean isMatch(NPC npc)
	{
	    return npc != null && npc.getName() != null && npc.getName().equals("Zulrah");
	}

	@Override
	public boolean isMatchByName(String name)
	{
	    return "Zulrah".equalsIgnoreCase(name);
	}
	
    @Override
    public void onEncounterStart(EncounterSession session)
    {
        // future boss-specific initialization
    }

    @Override
    public void onEncounterEnd(EncounterSession session)
    {
        // future boss-specific cleanup or analysis tweaks
    }
}