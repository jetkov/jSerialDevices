package cf.jetkov.jSerialDevices.examples;

import java.util.Scanner;

import cf.jetkov.jSerialDevices.Arduino;

public class BasicLEDExample {

	public static void main(String[] args) {
		
		Scanner ob = new Scanner(System.in);
		Arduino arduino = new Arduino(); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
		arduino.openPort();
		System.out.println("Enter 1 to switch LED on and 0  to switch LED off");
		char input = ob.nextLine().charAt(0);
		while(input != 'n'){
			arduino.serialWrite(input);
			input = ob.nextLine().charAt(0);
			System.out.print(arduino.serialRead(10));
		}
		ob.close();
		arduino.closePort();
		

	}

}
