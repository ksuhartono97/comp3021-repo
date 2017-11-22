// TODO: Complete based on UML Diagram.
// TODO: Archer has no Type Advantage/Disadvantage to other Units.
// TODO: Archer doesn't clash if out-of-range from other Melee Units. Archers will clash with each other at range.

package pa1;

public class Archer extends Unit{
	public Archer(char ID, int locationX, int locationY) {
		super(ID, locationX, locationY, 9, 1,5,3);
	}

	@Override
	public void attackUnit(Unit defender) {
		int distance = Math.abs(this.locationX - defender.locationX) + Math.abs(this.locationY - defender.locationY);

		double damageToAttacker = (defender.ATTACK - this.DEFENSE) * defender.health * 0.1 ;
		double damageToDefender = (this.ATTACK - defender.DEFENSE) * this.health * 0.1;
		if(distance <= defender.getAttackRange()) {
			this.receiveDamage(damageToAttacker, defender);
			defender.receiveDamage(damageToDefender, this);
		}
		else {
			defender.receiveDamage(damageToDefender, this);
		}
	}

	public void receiveDamage (double rawDamage, Unit attacker) {
		this.receiveDamage(rawDamage);
	}

	@Override
	public String toString()
	{
		return String.format("[%c]  H:%-2d  A:%-2d  D:%-2d  R:%-2d  M:%-2d  x:%-2d  y:%-2d  %-8s  %-5s", ID, health, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE, locationX, locationY, "Archer", getStatus());
	}
}

