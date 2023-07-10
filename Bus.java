import java.util.Scanner;
import java.util.Arrays;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Bus {
  private int time; // format time: 1200
  private String name;
  private int startPoint;
  private int [] stationsNum;

  // public Bus(int time, int startPoint, int []stations){
  //   this.time = time;
  //   this.startPoint = startPoint;
  //   this.stationsNum = stations;
  // } 

  public Bus(String fileName){
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      name = myReader.nextLine();
      int num = Integer.parseInt(myReader.nextLine());
      stationsNum = new int[num];
      for (int i = 0; i < num; i++) {
        stationsNum[i] = Integer.parseInt(myReader.nextLine());
      }
      myReader.close();

      startPoint = stationsNum[0]; // get the starting point station


    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void printBusRoute(String[] Stations){
      System.out.print("Bus " + name + ": ");
      for (int i = 0; i < stationsNum.length; i++) {
        System.out.print(Stations[stationsNum[i]]);
        if(i < stationsNum.length - 1) System.out.print(", ");
      }
  }

  public boolean isAvailable(int source, int destination){
    // Arrays.binarySearch() that will return the index of search value if not it will return -1
    if (Arrays.binarySearch(stationsNum, source) >= 0 && Arrays.binarySearch(stationsNum, destination) >= 0){ return true; }
    else { return false; }
  }
}

