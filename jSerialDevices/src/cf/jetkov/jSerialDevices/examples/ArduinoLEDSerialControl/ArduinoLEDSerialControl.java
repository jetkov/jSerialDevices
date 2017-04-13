package cf.jetkov.jSerialDevices.examples.ArduinoLEDSerialControl;

import java.util.Scanner;

import cf.jetkov.jSerialDevices.Arduino;

public class ArduinoLEDSerialControl {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Arduino arduino = new Arduino(); // defaults to 9600 baud

		arduino.openPort();

		System.out.println("Please enter 1 or 0 (LED on or off): \n");

		char input = scan.nextLine().charAt(0);

		while (arduino.openPort()) {
			arduino.serialWrite(input);
			input = scan.nextLine().charAt(0);
		}

		scan.close();
		arduino.closePort();
	}
}
