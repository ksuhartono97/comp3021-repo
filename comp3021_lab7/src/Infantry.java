
public class Infantry extends Unit {
	private static final int ATTACK = 10;
	private static final int DEFENSE = 5;
	private static final int ATTACK_RANGE = 1;
	private static final int MOVEMENT_RANGE = 5;

	public Infantry(char id, int x, int y) {
		super(id, x, y, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE);
	}

	@Override
	public String toString() {
		return String.format("%s  %-8s", super.toString(), "Infantry");
	}
}
