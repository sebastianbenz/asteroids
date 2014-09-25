package asteroids;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

final public class Main
{

    JFrame frame;
    DrawPanel drawPanel;
	private SpaceShip spaceShip;
	private EventListener eventListener;


    public Main(SpaceShip spaceShip, EventListener eventListener) {
		this.spaceShip = spaceShip;
		this.eventListener = eventListener;
	}

	public static void main(String... args)
    {
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
		new Main(ship, eventListener).go();
    }

    private void go()
    {
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

    class DrawPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g)
        {
        	g.setColor(Color.BLACK);
            g.fillRect(spaceShip.getPosition().x, spaceShip.getPosition().y, 20, 20);
        }
    }

    private void moveIt()
    {
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            frame.repaint();
        }
    }
}