import java.io.*;  
import java.io.File; 

public class LogWriter {
	
	public void write_to_log(String logname, String input){

	    try {
	    	BufferedWriter out = new BufferedWriter(new FileWriter(logname, true));
	    	out.write(input);
	    	out.close();
	    	} catch (IOException e) {} 
	}
	
	public void make_folder(String input_name){
		
		File folder = new File(input_name);
		
		try{

			if(folder.mkdir()){
				//System.out.println("Log Folder Created");
				}
			else
				System.out.println("Log Folder is not created");
			}catch(Exception e){}
		 
	}
}  
