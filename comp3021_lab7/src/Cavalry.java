
public class Cavalry extends Unit {
	private static final int ATTACK = 14;
	private static final int DEFENSE = 3;
	private static final int ATTACK_RANGE = 1;
	private static final int MOVEMENT_RANGE = 10;

	public Cavalry(char id, int x, int y) {
		super(id, x, y, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE);
	}

	@Override
	public String toString() {
		return String.format("%s  %-8s", super.toString(), "Cavalry");
	}
}
