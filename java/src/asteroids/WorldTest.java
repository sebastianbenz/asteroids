package asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldTest {

	public static void main(String[] args) throws InterruptedException {
		final SpaceShip spaceShip = new SpaceShip(new Position(100, 100));
		List<Asteroid> asteroids = new ArrayList<>();
		Random random = new Random(1);
		for (int i = 0; i < 10; i++) {
			Asteroid asteroid = new Asteroid(new Position(random.nextInt(600), 1), random.nextInt(5));
			asteroids.add(asteroid);
		}

		while (true) {
			for (Asteroid asteroid: asteroids) {
				asteroid.update();
			}
			Thread.sleep(1000);
		}

	}

}
