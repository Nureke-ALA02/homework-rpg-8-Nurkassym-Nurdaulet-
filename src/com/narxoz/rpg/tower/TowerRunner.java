package com.narxoz.rpg.tower;

import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.combatant.Hero;
import java.util.List;

public class TowerRunner {
    public TowerRunResult run(List<TowerFloor> floors, List<Hero> party) {
        int cleared = 0;
        for (TowerFloor floor : floors) {
            FloorResult result = floor.explore(party);
            if (!result.isCleared()) break;
            cleared++;
            if (party.stream().noneMatch(Hero::isAlive)) {
                break;
            }
        }
        long alive = party.stream().filter(Hero::isAlive).count();
        return new TowerRunResult(cleared, (int)alive, cleared == floors.size());
    }
}