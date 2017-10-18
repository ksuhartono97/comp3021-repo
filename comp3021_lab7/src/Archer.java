
public class Archer extends Unit {
	private static final int ATTACK = 9;
	private static final int DEFENSE = 1;
	private static final int ATTACK_RANGE = 5;
	private static final int MOVEMENT_RANGE = 3;

	public Archer(char id, int x, int y) {
		super(id, x, y, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE);
	}

	@Override
	public String toString() {
		return String.format("%s  %-8s", super.toString(), "Archer");
	}
}
