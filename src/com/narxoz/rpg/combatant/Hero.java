package com.narxoz.rpg.combatant;
import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NeutralState;
public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;
    private HeroState state;
    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = new NeutralState();
    }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
    public boolean isAlive() { return hp > 0; }

    public void setState(HeroState state) {
        System.out.println(name + " is now " + state.getName());
        this.state = state;
    }

    public HeroState getState() {
        return state;
    }

    public void startTurn() {
        state.onTurnStart(this);
    }
    public void endTurn() {
        state.onTurnEnd(this);
    }
    public boolean canAct() {
        return state.canAct();
    }

    public void attack(Monster monster) {
        if (!canAct()) {
            System.out.println(name + " cannot act!");
            return;
        }
        int damage = state.modifyOutgoingDamage(attackPower);
        monster.takeDamage(damage);
        System.out.println(name + " deals " + damage + " damage");
    }
    public void takeDamage(int amount) {
        int modified = state.modifyIncomingDamage(amount);
        int finalDamage = Math.max(0, modified - defense);
        hp = Math.max(0, hp - finalDamage);
        System.out.println(name + " takes " + finalDamage + " damage");
    }
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals " + amount);
    }
}