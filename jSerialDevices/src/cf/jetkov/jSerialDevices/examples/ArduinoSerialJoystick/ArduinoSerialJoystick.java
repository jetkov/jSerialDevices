package cf.jetkov.jSerialDevices.examples.ArduinoSerialJoystick;

import cf.jetkov.jSerialDevices.Arduino;

public class ArduinoSerialJoystick implements Runnable {
	private Arduino arduino;
	private Thread thread;

	private int baudRate;
	private int x, y;

	public ArduinoSerialJoystick() {
		this.baudRate = 9600;
		init();
	}

	public ArduinoSerialJoystick(int baudRate) {
		this.baudRate = baudRate;
		init();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private void init() {
		arduino = new Arduino(baudRate);
		arduino.openPort();
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		String buf = "";

		while (arduino.openPort()) {

			buf = arduino.serialRead(100);
			String[] coords = buf.split("/");

			for (String coord : coords) {
				if (coord.matches("\\<(.*?),(.*?)\\>")) {
					try {
						x = Integer.valueOf((coord.substring(coord.indexOf("<") + 1, coord.indexOf(","))));
						y = Integer.valueOf((coord.substring(coord.indexOf(",") + 1, coord.indexOf(">"))));
					} catch (NumberFormatException e) {
						System.err.println("WARNING: Packt lost!");
					}
//					System.out.printf("[%d,%d]\n", x, y);
				}
				
			}

		}

		arduino.closePort();

	}

}
