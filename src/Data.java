public class Data{
    
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
	 * @uml.property  name="passenger_std"
	 */
    public int passenger_std;
    /**
	 * @uml.property  name="stations"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Passenger"
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
    
    /**
	 * @uml.property  name="reader"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    ReadFileToArray Reader = new ReadFileToArray();
    
    /**
	 * @uml.property  name="config_data" multiplicity="(0 -1)" dimension="1"
	 */
    int[] config_data;
    
    Data(String filename){
        
        config_data = Reader.readfile(filename);
        
        weeks = config_data[0];
        days = config_data[1];
        hours = config_data[2];
        handover = config_data[3];
        passenger_distribution = config_data[4];
        passenger_std = config_data[5];
        stations = config_data[6];
        trains_per_hour = config_data[7];
        passengers_per_weekday = config_data[8];
        passengers_per_saturday = config_data[9];
        passengers_per_sunday = config_data[10];
        passenger_movement_ratio = config_data[11];
    }
    
    public void output_config_data(){
        
        System.out.println("The total time of simulation is " + weeks);
        System.out.println("Number of days a week is " + days);
        System.out.println("Number of hours a day is " + hours);
        System.out.println("Period taken for a handover is " + handover + "s");
        System.out.println("Passenger distribution per day is  " + passenger_distribution);
        System.out.println("Passenger standard deviation per day is  " + passenger_std);
        System.out.println("Number of stations " + stations);
        System.out.println("Number of train/hour " + trains_per_hour);
        System.out.println("Average number of passengers/weekday " + passengers_per_weekday);
        System.out.println("Average number of passengers/saturday " + passengers_per_saturday);
        System.out.println("Average number of passengers/sunday " + passengers_per_sunday);
        System.out.println("Average percentage of passengers moving at each stop " + passenger_movement_ratio);
        System.out.println();

    }
}
