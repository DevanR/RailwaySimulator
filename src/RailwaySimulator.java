import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RailwaySimulator{
    
    //Home directory
    static String home_dir = "/home/dr/RailwaySimulator";

    //Read this configuration file to setup simulation settings
    static Data simulation_data = new Data(home_dir + "/src/config.txt");    

    //Data structure for storing passenger movement on trains/stations 
    static Train[] operational_trains = new Train[simulation_data.trains_per_hour]; 
    static Station[] circleline = new Station[simulation_data.stations];
    
    //Main

    public static void main(String [] args){
    	
	//Create folder store simulation results
    LogWriter logger = new LogWriter();
	logger.make_folder(home_dir + "/results");
    logger.make_folder(home_dir + "/results/passenger_results");
    logger.make_folder(home_dir + "/results/train_results");
     

	for(int week=0; week<simulation_data.weeks; week++){
	
         logger.make_folder(home_dir + "/results/passenger_results" + "/Week" + week);
         logger.make_folder(home_dir + "/results/train_results" + "/Week" + week);

	   for(int day=0; day<simulation_data.days; day++){ 
        
            logger.make_folder(home_dir + "/results/passenger_results" + "/Week" + week + "/Day" + day);
            logger.make_folder(home_dir + "/results/train_results" + "/Week" + week + "/Day" + day);

       	    logger.write_to_log( (home_dir + "/log.txt"), "Starting Simulation --> Time:" + get_time() + "\n");
       	    logger.write_to_log( (home_dir + "/log.txt"), "Simulation still in Progress --> Week:" + week + " Day:" + day + "\n");
    
	        //Initialise Stations and Trains
            initialise_stations(day);
            initialise_trains();
	
            for(int hour=0; hour<simulation_data.hours; hour++){
		
		    //Add new passengers to station every hour (depending on passenger distribution)
		    station_update(day, hour);
            logger.make_folder(home_dir + "/results/train_results" + "/Week" + week + "/Day" + day + "/Hour" + hour);

		    //Debug method
            //display_passenger_count_for_stations(hour);
                 
                for(int station=0; station<simulation_data.stations; station++){
                      
                    for(int train=0; train<simulation_data.trains_per_hour; train++){

                        //Method for alighting and boarding Passenger on a train
                        train_arrival(train, station, hour, day, week);
                         
			            //Debug Method
			            //System.out.println("Week=" + week + " Day=" + day + " Hour=" + hour + " Station=" + station + " Train=" + train);
                    }
                }
                 
                //At the end of hour, automatically remove passengers.
                //We assume no passenger will ride for longer than an hour and
                //any journey longer than 14 stations is because of human error. 
                
		        //Remove passengers from stations
                // for(int c_station=0; c_station<simulation_data.stations; c_station++){
                //      circleline[c_station].offboard.clear();
                //   }
       
                //Remove passengers from trains
                for(int c_train=0; c_train<simulation_data.trains_per_hour; c_train++){

			        operational_trains[c_train].onboard.clear();
		        }


		        /*
		        //Remove passengers from trains
                for(int c_train=0; c_train<simulation_data.trains_per_hour; c_train++){

                    //Do not remove passenger from train if train is empty
                    while( operational_trains[c_train].onboard.size() > 0 ){
            	
            	        //Randomly pick a passenger
                        Passenger remove_passenger = new Passenger();

            	        //Remove one passenger (index first) from train
                        remove_passenger = operational_trains[c_train].onboard.remove(0);
                
            	        //Update passengers records. Set end station as 14 hops later
            	        remove_passenger.end_station = (remove_passenger.start_station + (simulation_data.stations/2) )%(simulation_data.stations+1);
            	        remove_passenger.hop = remove_passenger.end_station - remove_passenger.start_station;
            	        if(remove_passenger.hop<0)
            		        remove_passenger.hop = -(remove_passenger.hop);
            	
            	        //Log Passenger data
                        logger.write_to_log(home_dir + "/results/passenger_results/Week" + week + "/Day" + day + "/Hour" + hour + ".txt", remove_passenger.id + "\t" + remove_passenger.start_station + "\t" + remove_passenger.end_station + "\t" + remove_passenger.hop + "\n");

                        //TODO: How to update train records?
                        //Log Train data
                        logger.write_to_log(home_dir + "/results/train_results/Week" + week + "/Day" + day + "/Hour" + hour + "/Station" + station_id + ".txt", train_id + "\t" + original_train_size + "\t" + passengers_boarding + "\t" + passengers_alighting + "\t" + operational_trains[train_id].onboard.size() + "\t" + (original_train_size-passengers_alighting) +  "\n");

                        
                  }
               }
	           */

             }
         }
     }  
             
    //Log completion of simulation
    logger.write_to_log( (home_dir + "/log.txt"), "Ending Simulation --> Time:" + get_time() + "\n");
    logger.write_to_log( (home_dir + "/log.txt"), "Experiment Complete! \n");
}
    
//Methods
    
static void initialise_stations(int day){
    
    // Create Stations
    for(int a=0; a<simulation_data.stations; a++){       	
        circleline[a] = new Station(a);
    }
}

static void station_update(int day, int hour){
	
    int passenger_int = 0;

    //Get correct number based on statistics (dependent on day)
	//Returns total number of passenger on all stations per hour
	passenger_int =  get_hourly_passenger_count(day);            

    //Create passengers and populate stations
    for(int c=0; c<passenger_int; c++){
                
        // Create and initialise passengers
        Passenger born = new Passenger(c, day, hour, (c%simulation_data.stations));
	    //Add passenger to station
        circleline[(c%simulation_data.stations)].offboard.add(born);

    }             
}

static void initialise_trains(){
    
    //Create Trains
    for(int a=0; a<simulation_data.trains_per_hour; a++){       	
        operational_trains[a] = new Train(a);
    }	
}
    
static void train_arrival(int train_id, int station_id, int hour, int day, int week){
    	
    LogWriter logger = new LogWriter();
    Random rand = new Random( System.currentTimeMillis() );
	
	int alighting_passengers;

    //logger.write_to_log( (home_dir + "/log.txt"), "Random Seed = " + System.currentTimeMillis() );
        
	//Not all passengers in train will alight (integer)
    if(simulation_data.passenger_movement_ratio == 0){ 
		
	    alighting_passengers = rand.nextInt(100) * operational_trains[train_id].onboard.size() / 100;

    }
	else{
		
		alighting_passengers = simulation_data.passenger_movement_ratio * operational_trains[train_id].onboard.size() / 100;
	
    }
	
    //Even distribution of passengers at station will board -> 7 trains means 1/7th of the passengers at each station
	//int boarding_passengers = circleline[station_id].offboard.size()/simulation_data.trains_per_hour;
	int boarding_passengers = get_hourly_passenger_count(day)/simulation_data.stations/simulation_data.trains_per_hour;

	//Debug Method
	//System.out.println("Boarding:" + boarding_passengers + " Alighting:" + alighting_passengers);

	int original_train_size = operational_trains[train_id].onboard.size();
        	
    //Randomly pick passengers
    //int rand_alighting_passengers = rand.nextInt( alighting_passengers+1 );
    //int rand_boarding_passengers = rand.nextInt( boarding_passengers+1 );

    //Debug Method
    //System.out.println("At station " + circleline[station_id].offboard.size());
    //System.out.println("No. to board " + boarding_passengers);
    //System.out.println("On Train " + operational_trains[train_id].onboard.size());
    //System.out.println("No. to alight " + alighting_passengers);

    int passengers_boarding = 0;
    int passengers_alighting = 0;

    //Remove passenger from train if train_size > 0 and there are passengers to be removed
    while( operational_trains[train_id].onboard.size() > 0 && passengers_alighting < alighting_passengers ){
        	
        //Randomly pick a passenger from train to be removed
        int random_train_passenger_position = rand.nextInt(operational_trains[train_id].onboard.size());

	    ////Remove one passenger from train
        //Passenger remove_passenger = new Passenger();
	    //remove_passenger = operational_trains[train_id].onboard.remove(1);

        //Devan
	    //Copy details & Remove randomly chosen passenger from train
        Passenger remove_passenger = new Passenger();
	    remove_passenger = operational_trains[train_id].onboard.get(random_train_passenger_position);
        operational_trains[train_id].onboard.remove(random_train_passenger_position);
            
        //Update passengers records
        remove_passenger.end_station = station_id;
        remove_passenger.hop = remove_passenger.end_station - remove_passenger.start_station;
        if(remove_passenger.hop<0)
            remove_passenger.hop = -(remove_passenger.hop);
        	
        	passengers_alighting++;
	     		
        	//Log Passenger data
            logger.write_to_log(home_dir + "/results/passenger_results/Week" + week + "/Day" + day + "/Hour" + hour + ".txt", remove_passenger.id + "\t" + remove_passenger.start_station + "\t" + remove_passenger.end_station + "\t" + remove_passenger.hop + "\n");

    }
    
    //Remove passenger from station and put onto train if there are passengers to be added
    while(passengers_boarding < boarding_passengers){
    	
        //Randomly pick a passenger from station to be removed and placed on train
        int random_station_passenger_position = rand.nextInt(circleline[station_id].offboard.size() );

        //Debug Method
        //System.out.print(random_station_passenger_position + "\t");
        //System.out.print(circleline[station_id].offboard.size() + "\t");
        
        //Devan
        //Copy details & Move passenger off station
        Passenger add_passenger = new Passenger();
        add_passenger = circleline[station_id].offboard.get(random_station_passenger_position);
        circleline[station_id].offboard.remove(random_station_passenger_position);
            
        passengers_boarding++;
            
        //Update passenger records
        add_passenger.start_station = station_id;
        	
        //Move passenger onto train
        operational_trains[train_id].onboard.add(add_passenger);
        	
    }
        
    //Log Train data
    // TODO: Need to figure out how to put files in neat folders
    logger.write_to_log(home_dir + "/results/train_results/Week" + week + "/Day" + day + "/Hour" + hour + "/Station" + station_id + ".txt", train_id + "\t" + original_train_size + "\t" + passengers_boarding + "\t" + passengers_alighting + "\t" + operational_trains[train_id].onboard.size() + "\t" + (original_train_size-passengers_alighting) +  "\n");

        
    //Debug Method
    //System.out.println(operational_trains[train_id].onboard.size() + "\t" + boarding_passengers + "\t" + alighting_passengers + "\t" + (operational_trains[train_id].onboard.size()-alighting_passengers) );
    //System.out.println();
}
    
static int get_random(int input_mean, int input_std){
	
    // Add some Randomness
    //Random rand = new Random( System.currentTimeMillis() );
    //double random_double = 0;
    //	
    //do{
    //random_double = rand.nextGaussian()*input_std + input_mean;
    //random_int = (int)random_double;
    //}while (random_int<=0);
    
    int random_int = 0;
    random_int = input_mean;
    
    return random_int;
}

static int get_hourly_passenger_count(int day){
    
    int total_passengers_for_day = 0;
    int total_passengers_per_hour_for_day = 0;     
            
    //Get number of passenger depending on day        
    if(day==5){ //Saturday
        total_passengers_for_day = simulation_data.passengers_per_saturday;
    }
    else if(day==6){ //Sunday
        total_passengers_for_day = simulation_data.passengers_per_sunday;
    }
    else{
        total_passengers_for_day = simulation_data.passengers_per_weekday;
    }
    
    //Get Passenger distribution
    //if(simulation_data.passenger_distribution==1) {
    //    
    total_passengers_per_hour_for_day = total_passengers_for_day/simulation_data.hours;
    //}

    return total_passengers_per_hour_for_day;
}

//Debug method
static void display_passenger_count_for_stations(int input_hour){

    for(int c=0; c<simulation_data.stations; c++){
        System.out.println("Passenger Count at station " + c + " is " + circleline[c].offboard.size() + " Hour is " + input_hour);
    }
}

static String get_time(){
	
    DateFormat dateFormat = new SimpleDateFormat("dd HH:mm:ss");
    Date date = new Date();
    return dateFormat.format(date);
}
}
