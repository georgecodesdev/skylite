# S K Y L I T E

## When writing a new activity:
- Go to MainActivity
- Write a method to switch to your activity like so:
    
        private void switchTo[New Activity Name]Activity(){
            Intent intent = new Intent(this, [New Activity].class);
            startActivity(intent);
        }

- in onCreate(), comment out whichever switchTo... is being called and add yours

## Running S K Y L I T E
Due to key VR functionality, the application does not fully work on the Android emulator. OpenGL ES2 is not supported, and navigating to certain screens will cause the emulated app to crash. For this reason, it is important to test and run the app on a physical Android phone with a minimum SDK of 27.