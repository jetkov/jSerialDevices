package cf.jetkov.jSerialDevices.examples.ArduinoLEDSerialControl;

import java.util.Scanner;

import cf.jetkov.jSerialDevices.Arduino;

/**
 * A simple example of serial communication, using a java console input to control the
 * state of an LED wired to the Arduino. 
 * 
 * @author Alex Petkovic
 *
 */
public class ArduinoLEDSerialControl {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Arduino arduino = new Arduino(); // defaults to 9600 baud
		char input;

		arduino.openPort();

		System.out.println("Please enter 1 or 0 (LED on or off): \n");

		do {
			input = scan.nextLine().charAt(0);
			arduino.serialWrite(input);
		} while (arduino.openPort());

		scan.close();
		arduino.closePort();
	}
}
