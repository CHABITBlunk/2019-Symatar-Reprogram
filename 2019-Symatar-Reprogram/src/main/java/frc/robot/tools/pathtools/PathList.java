package frc.robot.tools.pathtools;

import java.io.File;

public class PathList {
  private File testFile1 = new File("/home/lvuser/deploy/test1.pf1.csv");
  private File testFile1Return = new File("/home/lvuser/deploy/test1Return.pf1.csv");
  private File curvedTestFile = new File("/home/lvuser/deploy/curvedTest.pf1.csv");
  private File curvedTestReturnFile = new File("/home/lvuser/deploy/curvedTestReturn.pf1.csv");

  public PathSetup testPath1;
  public PathSetup testPath1Return;
  public PathSetup curvedTest;
  public PathSetup curvedTestReturn;

   //remember that for all paths if the first point is at (0,0,0) for some reason the end y value is revesred in the coordinate plane
  //for example for a path from (x,y,h) to (0,0,0) a path that goes from (0,0,0) to (x,y,h) would look the same but for one you would 
  // be decreasing y units on the coordinate plane, while in the other you would be increasing y units
  public PathList() {
    testPath1 = new PathSetup(testFile1, true);
    testPath1Return = new PathSetup(testFile1Return, false);
    curvedTest = new PathSetup(curvedTestFile, true);
    curvedTestReturn = new PathSetup(curvedTestReturnFile, false);
	}

  public void resetAllPaths(){
  }
}