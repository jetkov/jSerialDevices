void setup() {
  Serial.begin(9600);
  Serial.setTimeout(50);
}

int max = 1000;

void loop() {
  
  int x = random(max);
  int y = random(max);

  char buf[12];

  sprintf(buf, "<%d,%d>/", x, y);
  Serial.print(buf);
}
