int xPort = A0;
int yPort = A1;

int x, y;

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(50);
}

void loop() {

  // read the analog in values
  x = analogRead(xPort);
  y = analogRead(yPort);

  // scale to 0 - 1000
  x = (x/1024.0) * 1000;
  y = (y/1024.0) * 1000;

  // serially print the results
  char buf[12];
  sprintf(buf, "<%d,%d>", x, y);
  Serial.print(buf);
}
