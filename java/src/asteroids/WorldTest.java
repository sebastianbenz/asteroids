package asteroids;

public class WorldTest {
	
	public static void main(String[] args) {
		World world = new SwingWorld(new EventListener() {
			
			@Override
			public void onUp() {
				System.out.println("UP");
				
			}
			
			@Override
			public void onDown() {
				System.out.println("DOWN");
			}
		});
		
	}

}
