package asteroids;

public class WorldTest {

	public static void main(String[] args) throws InterruptedException {
		final SpaceShip spaceShip = new SpaceShip(new Position(100, 100));
		World world = new SwingWorld(spaceShip, new EventListener() {

			@Override
			public void onUp() {
				spaceShip.up();
			}

			@Override
			public void onDown() {
				spaceShip.down();
			}
		});


		while (true) {
			world.render();
			Thread.sleep(1000);
		}

	}

}
