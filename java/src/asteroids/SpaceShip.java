package asteroids;

public class SpaceShip {

	private static final int STEP = 10;
	private Position position;

	public SpaceShip(Position position) {
		this.position = position;
	}

	public void up() {
		setPosition(new Position(position.x, position.y - STEP));
	}

	private void setPosition(Position newPosition) {
		System.out.println(position);
		this.position = newPosition;
	}

	public void down() {
		setPosition(new Position(position.x, position.y + STEP));
	}

	public Position getPosition() {
		return position;
	}

}
