package com.narxoz.rpg;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.tower.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Hero> party = List.of(
                new Hero("Knight", 100, 20, 5),
                new Hero("Mage", 70, 25, 2)
        );

        List<TowerFloor> floors = List.of(
                new BattleFloor(),
                new TrapFloor(),
                new RestFloor(),
                new BattleFloor()
        );

        TowerRunner runner = new TowerRunner();
        TowerRunResult result = runner.run(floors, party);

        System.out.println("\n=== RESULT ===");
        System.out.println("Floors cleared: " + result.getFloorsCleared());
        System.out.println("Heroes alive: " + result.getHeroesSurviving());
        System.out.println("Reached top: " + result.isReachedTop());
    }
}