
public class Unit {
	private static final int MAX_HEALTH = 10;
	protected final char id;
	protected int locationX;
	protected int locationY;
	protected int health = MAX_HEALTH;
	protected final int attack;
	protected final int defense;
	protected final int attackRange;
	protected final int movementRange;

	protected Unit(char id, int locationX, int locationY, int attack, int defense, int attackRange, int movementRange) {
		this.id = id;
		this.locationX = locationX;
		this.locationY = locationY;
		this.attack = attack;
		this.defense = defense;
		this.attackRange = attackRange;
		this.movementRange = movementRange;
	}

	public char getId() {
		return id;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void moveDelta(int deltaX, int deltaY) {
		locationX += deltaX;
		locationY += deltaY;
	}

	// TODO 3: Override the equals() method

	@Override
	public boolean equals(Object obj) {
		return this.getId() == ((Unit)obj).getId();
	}

	@Override
	public String toString() {
		return String.format("[%c]  H:%-2d  A:%-2d  D:%-2d  R:%-2d  M:%-2d  x:%-2d  y:%-2d",
            id, health, attack, defense, attackRange, movementRange, locationX, locationY);
	}
}
