package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.PoisonState;
import java.util.List;
public class TrapFloor extends TowerFloor {
    protected String getFloorName() {
        return "Trap Floor";
    }
    protected void setup(List<Hero> party) {
        System.out.println("Poison trap triggered!");
    }
    protected FloorResult resolveChallenge(List<Hero> party) {
        for (Hero h : party) {
            h.setState(new PoisonState());
        }
        return new FloorResult(true, 0, "Poison applied");
    }
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("No loot here...");
    }
}