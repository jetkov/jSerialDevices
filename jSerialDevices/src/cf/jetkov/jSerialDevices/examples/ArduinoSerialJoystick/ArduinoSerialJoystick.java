package cf.jetkov.jSerialDevices.examples.ArduinoSerialJoystick;

import cf.jetkov.jSerialDevices.Arduino;

/**
 * Gets the values of a joystick wired to an Arduino over serial. (0, 0) is the center of
 * the joystick and both axis range from -500 (left or down max) to +500 (up or right
 * max). This class is threaded so once it is created, its calues are constantly being
 * updated.
 * 
 * @author Alex Petkovic
 *
 */
public class ArduinoSerialJoystick implements Runnable {
	private Arduino arduino;
	private Thread thread;

	private int baudRate;
	private int x, y;

	/**
	 * Creates a new joystick
	 */
	public ArduinoSerialJoystick() {
		this.baudRate = 9600;
		init();
	}

	/**
	 * Creates a new joystick
	 * 
	 * @param baudRate
	 *            - Desired communication baud rate. Must update in Arduino code as well.
	 */
	public ArduinoSerialJoystick(int baudRate) {
		this.baudRate = baudRate;
		init();
	}

	/**
	 * Gets the X value of the joystick
	 * 
	 * @return X value of the joystick between -500 (left) and +500 (right)
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y value of the joystick
	 * 
	 * @return Y value of the joystick between -500 (down) and +500 (up)
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets up Arduino communications and starts the thread
	 */
	private void init() {
		arduino = new Arduino(baudRate);
		arduino.openPort();

		thread = new Thread(this);
		thread.start();
	}

	/**
	 * The update thread. While the Arduino is available it continuously reads in and
	 * parses its serial output. It uses serial printing and regex of strings to get the
	 * data across. Though this is not as efficient as it could be, it is easier to
	 * understand.
	 */
	@Override
	public void run() {

		// A new input buffer
		String buf = "";

		while (arduino.openPort()) {

			// Reads 100 tokens into the buffer
			buf = arduino.serialRead(100);

			// Splits the buffer up into points <xxx,yyy> based on the "/" delimeter
			String[] coords = buf.split("/");

			// Iterates through this new array
			for (String coord : coords) {
				// Checks if the point is in the expected format of <xxx,yyy>
				if (coord.matches("\\<(.*?),(.*?)\\>")) {
					try {
						// Gets the data between the angle brackets and comma into
						// respective x and y values
						x = Integer.valueOf((coord.substring(coord.indexOf("<") + 1, coord.indexOf(","))));
						y = Integer.valueOf((coord.substring(coord.indexOf(",") + 1, coord.indexOf(">"))));
					} catch (NumberFormatException e) {
						// If something went wrong (serial stream lost packets) and an
						// integer couldn't be parsed
						System.err.println("WARNING: Packt lost!");
					}
//					System.out.printf("[%d,%d]\n", x, y);
				}

			}

		}

		arduino.closePort();

	}

}
