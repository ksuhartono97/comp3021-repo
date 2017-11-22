// TODO: Complete based on UML Diagram.
// TODO: Infantry is Strong against Pikeman, Weak against Cavalry.

package pa1;

public class Infantry extends Unit {
	public Infantry(char ID, int locationX, int locationY) {
		super(ID, locationX, locationY, 10, 5,1,5);
	}

	public void receiveDamage (double rawDamage, Unit attacker) {
		double modifiedDamage = rawDamage;
		if(attacker instanceof Pikeman) {
			modifiedDamage = modifiedDamage / 2.0;
		}
		else if(attacker instanceof Cavalry) {
			modifiedDamage = modifiedDamage * 1.5;
		}

		this.receiveDamage(modifiedDamage);
	}

	@Override
	public String toString()
	{
		return String.format("[%c]  H:%-2d  A:%-2d  D:%-2d  R:%-2d  M:%-2d  x:%-2d  y:%-2d  %-8s  %-5s", ID, health, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE, locationX, locationY, "Infantry", getStatus());
	}
}
