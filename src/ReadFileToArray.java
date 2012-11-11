import java.io.*;
import java.util.*;
 
public class ReadFileToArray
{
    /**
	 * @uml.property  name="config_array" multiplicity="(0 -1)" dimension="1"
	 */
    int[] config_array = new int[12];      // allocates memory for 12 integers
       
    /**
	 * @uml.property  name="weeks"
	 */
    public int weeks;
    /**
	 * @uml.property  name="days"
	 */
    public int days;
    /**
	 * @uml.property  name="hours"
	 */
    public int hours;
    /**
	 * @uml.property  name="handover"
	 */
    public int handover;
    /**
	 * @uml.property  name="passenger_distribution"
	 */
    public int passenger_distribution;
    /**
	 * @uml.property  name="stations"
	 */
    public int stations;
    /**
	 * @uml.property  name="trains_per_hour"
	 */
    public int trains_per_hour;
    /**
	 * @uml.property  name="passengers_per_weekday"
	 */
    public int passengers_per_weekday;
    /**
	 * @uml.property  name="passengers_per_saturday"
	 */
    public int passengers_per_saturday;
    /**
	 * @uml.property  name="passengers_per_sunday"
	 */
    public int passengers_per_sunday;
    /**
	 * @uml.property  name="passenger_movement_ratio"
	 */
    public int passenger_movement_ratio;
         
    public int[] readfile(String config_file)
    {
        try{
          
          //Open the file that is the first 
          //command line parameter
          FileInputStream fstream = new FileInputStream(config_file);
          
          //Get the object of DataInputStream
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String strLine;
          
          System.out.println("Reading in Configuration data ...");
          
          //Read File Line By Line
          for(int x=0; x<12; x++){
              
              strLine = br.readLine();

              StringTokenizer st = new StringTokenizer(strLine, " = ");
 
            //Print the content on the console
            System.out.print(st.nextToken() + " = ");
            
            config_array[x] = Integer.parseInt(st.nextToken());
            
            //Print the content on the console
            System.out.println(config_array[x]);
            
        }
          
        //Close the input stream
        in.close();
          
      }catch (Exception e){
          //Catch exception if any
          System.err.println("Error: " + e.getMessage());
      }
      
      return config_array;

  }
}

