// Mapping the physical arduino ports to int variables
int xPort = A0; // Analog pin 0
int yPort = A1;

// Coordinate point for the joystick position
int x, y;

// Setting up serial comms
void setup() {
  Serial.begin(9600);
  Serial.setTimeout(50);
}

// Iterates forever
void loop() {
  // Read in the analog values
  x = analogRead(xPort);
  y = analogRead(yPort);

  // Scale to 0-1000 instead of 0-1024
  x = (x/1024.0) * 1000;
  y = (y/1024.0) * 1000;

  // Set the origin to middle instead of top left
  x -= 500;
  y -= 500;

  // Fixing y-axis inversion
  y = -y;

  // Serially sending the results in a framed String format with "/" delimiter
  char buf[12];
  sprintf(buf, "<%d,%d>/", x, y);
  Serial.print(buf);
}
