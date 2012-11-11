public class Passenger{
    
    /**
	 * @uml.property  name="id"
	 */
    public int id;
    /**
	 * @uml.property  name="hour_travelled"
	 */
    public int hour_travelled;
    /**
	 * @uml.property  name="day_travelled"
	 */
    public int day_travelled;
    /**
	 * @uml.property  name="start_station"
	 */
    public int start_station;
    /**
	 * @uml.property  name="end_station"
	 */
    public int end_station;
    /**
	 * @uml.property  name="hop"
	 */
    public int hop;

    Passenger(){

        id = -1;
        day_travelled  = -1;
        hour_travelled = -1;
        start_station = -1;

    }

    Passenger(int input_id, int input_day, int input_hour, int input_station_id){

        id = input_id;
        day_travelled  = input_day;
        hour_travelled = input_hour;
        start_station = input_id;

    }
    
    public void print_details(){

    	System.out.println("Passenger Id = " + id);   	

    }
 
    public void set_end_station(int input_id){

    	end_station = input_id;

    }
    
}
