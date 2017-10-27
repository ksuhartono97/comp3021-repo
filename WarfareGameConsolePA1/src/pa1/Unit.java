// TODO: Complete based on UML Diagram.
// TODO: Constructor: Subclasses need to call this constructor in order to initialize the Unit Stats, as they are declared final.
// TODO: getStatus(): Return a String signifying the status of the Unit. DEAD, READY, DONE. READY/DONE status tracked by boolean isReady.
// TODO: beginTurn(): Change status of Unit to READY.
// TODO: endTurn(): Change status of Unit to DONE.
// TODO: isAlive(): Check if Unit is still alive.
// TODO: moveDelta(): Change the location of the Unit. Path-checking done in GameMap.
// TODO: attackUnit(): Damage linearly scaled based on health. Attacker and Defender clash simultaneously (damage each other at same time).
// TODO: abstract receiveDamage(): x1.5 damage dealt, /2.0 damage received, for Advantaged/Disadvantaged TroopType.
// TODO: receiveDamage(): // Round to nearest Integer. Clamp health so it doesn't go negative.
// TODO: heal(): Heal based on constant HEAL_RATE. Remember to not over-heal beyond MAX_HEALTH.
// TODO: equals(): Units with same ID are equal.

package pa1;

public abstract class Unit {
    protected final int MAX_HEALTH = 10;
    protected final int HEAL_RATE = 3;
    protected final char ID;
    protected int health = MAX_HEALTH;
    protected final int ATTACK;
    protected final int DEFENSE;
    protected final int ATTACK_RANGE;
    protected final int MOVEMENT_RANGE;
    protected int locationX;
    protected int locationY;
    protected boolean isReady = false;

    protected Unit(char ID, int locationX, int locationY, int ATTACK, int DEFENSE, int ATTACK_RANGE, int MOVEMENT_RANGE) {
        this.ID = ID;
        this.locationX = locationX;
        this.locationY = locationY;
        this.ATTACK = ATTACK;
        this.DEFENSE = DEFENSE;
        this.ATTACK_RANGE = ATTACK_RANGE;
        this.MOVEMENT_RANGE = MOVEMENT_RANGE;
    }

    protected String getStatus () {
        String status;
        if(isReady) status = "READY";
        else status="DONE";
        if(!isAlive()) status="DEAD";
        return status;
    }

    public char getId() {
        return this.ID;
    }

    public int getAttackRange() {
        return this.ATTACK_RANGE;
    }

    public int getMovementRange() {
        return this.MOVEMENT_RANGE;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public boolean isReady() {
        return isReady;
    }

    public void beginTurn () {
        this.isReady = true;
    }

    public void endTurn() {
        this.isReady = false;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void moveDelta (int x, int y) {
        this.locationX += x;
        this.locationY += y;
    }

    public void attackUnit (Unit defender) {
        double damageToAttacker = (defender.ATTACK - this.DEFENSE) * defender.health * 0.1 ;
        double damageToDefender = (this.ATTACK - defender.DEFENSE) * this.health * 0.1;
        this.receiveDamage(damageToAttacker, defender);
        defender.receiveDamage(damageToDefender, this);
    }

    public abstract void receiveDamage (double rawDamage, Unit attacker);

    protected void receiveDamage(double rawDamage) {
        int newHealth = this.health - (int)Math.round(rawDamage);
        this.health = newHealth <= 0 ? 0 : newHealth;
    }

    public void heal() {
        this.health = (this.health + HEAL_RATE >= MAX_HEALTH) ? MAX_HEALTH : (this.health + HEAL_RATE);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Unit)
            return this.getId() == ((Unit)obj).getId();
        return false;
    }
}