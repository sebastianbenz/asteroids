package asteroids;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

final public class Main {

	JFrame frame;
	DrawPanel drawPanel;
	private SpaceShip spaceShip;
	private EventListener eventListener;
	private List<Asteroid> asteroids;

	public Main(SpaceShip spaceShip, List<Asteroid> asteroids,
			EventListener eventListener) {
		this.spaceShip = spaceShip;
		this.asteroids = asteroids;
		this.eventListener = eventListener;
	}

	public static void main(String... args) {
		final SpaceShip ship = new SpaceShip(new Position(100, 100));
		EventListener eventListener = new EventListener() {

			@Override
			public void onUp() {
				ship.up();
			}

			@Override
			public void onDown() {
				ship.down();
			}
		};
		List<Asteroid> asteroids = new ArrayList<>();
		Random random = new Random(1);
		for (int i = 0; i < 10; i++) {
			Asteroid asteroid = new Asteroid(new Position(800, random.nextInt(600)
					), random.nextInt(5));
			asteroids.add(asteroid);
		}
		new Main(ship, asteroids, eventListener).go();
	}

	private void go() {
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new DrawPanel();
		drawPanel.setFocusable(true);
		drawPanel.requestFocusInWindow();
		drawPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("jasdfasd");
				if (e.getKeyChar() == 'j') {
					eventListener.onDown();
				} else if (e.getKeyChar() == 'k') {
					eventListener.onUp();
				}
			}

		});

		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

		frame.setResizable(false);
		frame.setSize(800, 600);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		moveIt();
	}

	class DrawPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(spaceShip.getPosition().x, spaceShip.getPosition().y,
					20, 20);
			for (Asteroid asteroid : asteroids) {
				g.setColor(Color.RED);
				g.fillOval(asteroid.getPosition().x, asteroid.getPosition().y,
						20, 20);
			}
		}
	}

	private void moveIt() {
		while (true) {
			try {
				Thread.sleep(100);
				for (Asteroid asteroid : asteroids) {
					asteroid.update();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			frame.repaint();
		}
	}
}