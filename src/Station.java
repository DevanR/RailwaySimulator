import java.util.*;

public class Station{
    
    /**
	 * @uml.property  name="id"
	 */
    public int id;
    /**
	 * @uml.property  name="offboard"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Passenger"
	 */
    public ArrayList<Passenger> offboard = new ArrayList<Passenger>();
    
    Station(){

        id = -1;
        
        offboard.ensureCapacity(500);
    }
    
    Station(int input_id){

        id = input_id;
        
        offboard.ensureCapacity(500);
    }
    
    public void set_id(int input_id){
        
        id = input_id;
        
    }
    
    public void print_details(){
    	
    	System.out.println("Station is " + id);
    }
}
