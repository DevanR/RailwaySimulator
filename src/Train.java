import java.util.*;

public class Train{
    
    /**
	 * @uml.property  name="id"
	 */
    public int id;
    /**
	 * @uml.property  name="next_station"
	 */
    public int next_station;
    /**
	 * @uml.property  name="onboard"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="Passenger"
	 */
    public ArrayList<Passenger> onboard = new ArrayList<Passenger>(500);

    Train(){

        id = -1;
        
        onboard.ensureCapacity(500);
    }
    
    Train(int input_id){

        id = input_id;
        
        onboard.ensureCapacity(500);

    }
}
