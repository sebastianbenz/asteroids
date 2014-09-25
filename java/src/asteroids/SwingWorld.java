package asteroids;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SwingWorld implements World {

	private static final int SPACE_SHIP = 50;
	private EventListener eventListener;
	private TriangleShape triangleShape;
	protected JFrame frame;
	private SpaceShip spaceShip;

	private WorldPanel panel;

	public SwingWorld(SpaceShip spaceShip, EventListener eventListener) {
		this.spaceShip = spaceShip;
		this.eventListener = eventListener;
		drawShip();
		start();
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				frame = new JFrame("Asteroids");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				panel = new WorldPanel(eventListener);
				frame.add(panel);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public void drawShip() {
		Position position = spaceShip.getPosition();
		triangleShape = new TriangleShape(new Point2D.Double(position.x,
				position.y), new Point2D.Double(position.x + SPACE_SHIP,
				position.y + SPACE_SHIP), new Point2D.Double(position.x,
				position.y + SPACE_SHIP));
	}

	public void render() {
		if (frame != null) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					System.out.println("repaint");
					panel.invalidate();
					panel.repaint();
					frame.invalidate();
					frame.repaint();
				}
			});
		}
	}

	public class WorldPanel extends JPanel {

		public WorldPanel(final EventListener eventListener) {
			this.setFocusable(true);
			this.requestFocusInWindow();
			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyChar() == 'j') {
						eventListener.onDown();
					} else if (e.getKeyChar() == 'k') {
						eventListener.onUp();
					}
				}

			});
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(800, 600);
		}

		@Override
		protected void paintComponent(Graphics g) {
			System.out.println("paintComponent");
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.GREEN);
			g2d.translate(50, 100);
			g2d.fill(triangleShape);
			g2d.dispose();
		}
	}

	public static class TriangleShape extends Path2D.Double {

		public TriangleShape(Point2D... points) {
			moveTo(points[0].getX(), points[0].getY());
			lineTo(points[1].getX(), points[1].getY());
			lineTo(points[2].getX(), points[2].getY());
			closePath();
		}

	}
}
