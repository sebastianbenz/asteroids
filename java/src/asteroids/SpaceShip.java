package asteroids;

public class SpaceShip {

	private static final int STEP = 10;
	private Position position;

	public SpaceShip(Position position) {
		this.position = position;
	}

	public void up() {
		this.position = new Position(position.x, position.y + STEP);
	}

	public void down() {
		this.position = new Position(position.x, position.y - STEP);
	}

	public Position getPosition() {
		return position;
	}

}
