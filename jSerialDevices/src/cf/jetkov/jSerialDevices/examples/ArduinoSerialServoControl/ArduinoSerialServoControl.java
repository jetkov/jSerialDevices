package cf.jetkov.jSerialDevices.examples.ArduinoSerialServoControl;

import java.util.Scanner;

import cf.jetkov.jSerialDevices.Arduino;

/**
 * A simple example of serial communication, using a java console input to control the
 * state of an LED wired to the Arduino. 
 * 
 * @author Alex Petkovic
 *
 */
public class ArduinoSerialServoControl {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Arduino arduino = new Arduino(); // defaults to 9600 baud
		
		String input;

		arduino.openPort();

		System.out.println("Please enter an angle to set the servo to: \n");

		do {
			input = scan.nextLine();
			arduino.serialWrite(input);
		} while (arduino.openPort());

		scan.close();
		arduino.closePort();
	}
}
