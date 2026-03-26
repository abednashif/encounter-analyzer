package com.encounteranalyzer.tracker;

import com.encounteranalyzer.bosses.BossHandler;
//import net.runelite.api.NPC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncounterTracker
{
    private EncounterSession current;
    private BossHandler currentBoss;
    private final List<BossHandler> bosses = new ArrayList<>();

    public EncounterTracker()
    {
        //? bosses will be added manually for now
        bosses.add(new com.encounteranalyzer.bosses.Zulrah());
        //TODO!!: scan the bosses package via reflection
    }

    //TODO!!!: Change to boss id  
    public void startEncounter(String npcName)
    {
        for (BossHandler boss : bosses)
        {
            if (boss.isMatchByName(npcName))
            {
                currentBoss = boss;
                current = new EncounterSession();
                currentBoss.onEncounterStart(current);
                break;
            }
        }
    }

    public void onTick(boolean isIdle)
    {
        if (current != null)
        {
            current.tick(isIdle);
        }
    }

    public EncounterSession finishEncounter()
    {
        if (current != null && currentBoss != null)
        {
            current.finish();
            currentBoss.onEncounterEnd(current);
            EncounterSession finished = current;
            current = null;
            currentBoss = null;
            return finished;
        }
        return null;
    }

    public void registerBoss(BossHandler bossHandler) {
        bosses.add(bossHandler);
    }

    public BossHandler getActiveBoss() {
        return currentBoss;
    }

    public List<BossHandler> getRegisteredBosses()
    {
        return Collections.unmodifiableList(bosses);
    }

    public boolean isPlayerIdle() {
        return current == null || current.lastTickWasIdle();
    } 
}