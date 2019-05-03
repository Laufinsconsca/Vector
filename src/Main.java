import java.awt.EventQueue;

public class Main {
	
	final public static int ACCURACY_ROUND = 500;
	final public static int ACCURACY_CALCULATION = ACCURACY_ROUND+1; 
	final public static int SLIDER_MIN_VALUE = 2;
	final public static int SLIDER_MAX_VALUE = 90;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final Interface frame = new Interface();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}