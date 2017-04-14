#include <Servo.h>

Servo servo;  // create servo object
String data;

void setup() {
  servo.attach(8);  // attaches the servo on pin 9 to the servo object
  Serial.begin(9600); // Setup port and serial communication
  Serial.setTimeout(50);
}

void loop() {
  while (Serial.available()) {
    if (Serial.available() > 0) {
      char c = Serial.read();  //gets one char from serial buffer
      data += c; //makes the string readString
    } 
  }

  if (data.length() > 0) {
      Serial.println(data);
            
      servo.write(data.toInt());
      data = "";
  }
}

