char data;     // variable to store incoming data from JAVA 
int LED = 10;  // Phyiscal pin LED is on

// Setup port and serial communication
void setup() {
  pinMode(LED, OUTPUT);
  Serial.begin(9600);
  Serial.setTimeout(50);
}

// Iterate
void loop() {
  // If there is incoming data...
  if (Serial.available() > 0) { 
  
    // Reads in a char...
    data = Serial.read();
  
    // And sets the LED accordingly
    if (data == '1') {
      digitalWrite(12, HIGH);
    } else if (data == '0') {
      digitalWrite(12, LOW);
    } else {
      digitalWrite(12, HIGH);
    }
  }
}