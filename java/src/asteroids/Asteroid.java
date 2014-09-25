package asteroids;

import java.util.Random;

public class Asteroid {

	private Position position;
	private int speed;

	public Position getPosition() {
		return position;
	}

	public Asteroid(Position position, int speed) {
		this.position = position;
		this.speed = speed;
	}

	public void update() {
		position = new Position(position.x - speed, position.y);
	}

}
