package com.encounteranalyzer;

import com.encounteranalyzer.analysis.EncounterAnalyzer;
import com.encounteranalyzer.analysis.EncounterSummary;
import com.encounteranalyzer.tracker.EncounterTracker;
import com.encounteranalyzer.tracker.EncounterSession;
import com.encounteranalyzer.bosses.BossHandler;
//import net.runelite.api.NPC;
//import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Singleton;
import java.util.Optional;

/**
 * EncounterAnalyzerPlugin
 *
 * Listens to RuneLite events to track boss encounters, analyze player performance,
 * and produce post-fight insights. Extensible for new bosses via BossHandler.
 */
@Singleton
public class EncounterAnalyzerPlugin
{
    private final EncounterTracker tracker;
    private final EncounterAnalyzer analyzer;
    private BossHandler activeBoss;

    public EncounterAnalyzerPlugin()
    {
        this.tracker = new EncounterTracker();
        this.analyzer = new EncounterAnalyzer();
    }

    /**
     * Automatically called by RuneLite when an NPC spawns.
     * Detects bosses and starts encounters.
     *
     * @param event The NPC spawn event
     */
    //!!! NOT READY - MUST CHANGE TO BOSS ID
    @Subscribe
    public void onNpcSpawned(String npcName)
    {
        for (BossHandler boss : tracker.getRegisteredBosses())
        {
            if (boss.isMatchByName(npcName))
            {
                tracker.startEncounter(npcName);
                activeBoss = boss;
                System.out.println("[EncounterAnalyzer] Encounter started: " + npcName);
                break;
            }
        }
    }

    /**
     * Called every game tick by RuneLite to update tracking.
     *
     * @param event Game tick event
     */
    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (activeBoss != null)
        {
            // Example: check if player is idle, in combat, etc.
            boolean isIdle = tracker.isPlayerIdle(); // placeholder for actual RuneLite state
            tracker.onTick(isIdle);
        }
    }

    /**
     * Ends the current encounter and produces a post-fight summary.
     *
     * This would be triggered automatically when the boss dies or the player leaves combat.
     */
    public Optional<String> finishEncounter()
    {
        if (activeBoss == null)
        {
            System.out.println("[EncounterAnalyzer] No active encounter to finish.");
            return Optional.empty();
        }

        EncounterSession session = tracker.finishEncounter();
        if (session == null)
        {
            System.out.println("[EncounterAnalyzer] Encounter finished but no session found.");
            return Optional.empty();
        }

        EncounterSummary summary = analyzer.analyze(session);
        System.out.println(summary);

        String report = summary.toString();
        System.out.println("[EncounterAnalyzer] Encounter finished: " + activeBoss.getClass().getSimpleName());
        System.out.println(report);

        activeBoss = null;
        return Optional.of(report);
    }

    /**
     * Dynamically register a new boss handler.
     *
     * @param bossHandler BossHandler implementation to register.
     */
    public void registerBoss(BossHandler bossHandler)
    {
        tracker.registerBoss(bossHandler);
        System.out.println("[EncounterAnalyzer] Registered boss: " + bossHandler.getClass().getSimpleName());
    }
}