package com.narxoz.rpg.floor;

import com.narxoz.rpg.combatant.*;
import java.util.List;

public class BattleFloor extends TowerFloor {
    protected String getFloorName() {
        return "Battle Floor";
    }
    protected void setup(List<Hero> party) {
        System.out.println("A monster appears!");
    }
    protected FloorResult resolveChallenge(List<Hero> party) {
        Monster monster = new Monster("Goblin", 40, 10);
        int totalDamage = 0;
        while (monster.isAlive() && party.stream().anyMatch(Hero::isAlive)) {

            for (Hero h : party) {
                if (!h.isAlive()) continue;
                h.startTurn();
                h.attack(monster);
                h.endTurn();
                if (!monster.isAlive()) break;
                monster.attack(h);
                totalDamage++;
            }
        }
        boolean cleared = !monster.isAlive();
        return new FloorResult(cleared, totalDamage, "Battle ended");
    }
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Heroes gain rewards!");
    }
}