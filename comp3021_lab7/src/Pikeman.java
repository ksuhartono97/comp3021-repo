
public class Pikeman extends Unit {
	private static final int ATTACK = 8;
	private static final int DEFENSE = 7;
	private static final int ATTACK_RANGE = 1;
	private static final int MOVEMENT_RANGE = 3;

	public Pikeman(char id, int x, int y) {
		super(id, x, y, ATTACK, DEFENSE, ATTACK_RANGE, MOVEMENT_RANGE);
	}

	@Override
	public String toString() {
		return String.format("%s  %-8s", super.toString(), "Pikeman");
	}
}
