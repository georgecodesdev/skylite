# S K Y L I T E

## When writing a new activity:
- Go to MainActivity
- Write a method to switch to your activity like so:
    
        private void switchTo[New Activity Name]Activity(){
            Intent intent = new Intent(this, [New Activity].class);
            startActivity(intent);
        }

- in onCreate(), comment out whichever switchTo... is being called and add yours