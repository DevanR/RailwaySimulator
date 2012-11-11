#Author:Devan Rehunathan
#Date:August 2009
#Name:process_trains.R

number_of_stations <- 27;
number_of_trains <- 7;

hour <- 0;

for(t in 1:number_of_trains){
		
	input_file <- paste("/home/dr/RailwaySimulator/scripts/train_", t, ".txt",  sep="");

	input_table<- read.table(input_file, header=FALSE,sep="\t");

	for(r in 1:nrow(input_table)]{

		station <- r%27;
	
		if(station == 1){
			hour <- hour + 1;
		}	

		train_registration[hour,station] <- input_table_1[r,1]; 
		train_handover_total[hour,station] <- input_table_1[r,2];
	
	}	

	output_file_1 <- paste("home/dr/RailwaySimulator/results/train_", t, "_reg.txt", sep="");
	output_file_2 <- paste("home/dr/RailwaySimulator/results/train_", t, "_hand.txt", sep="");

	write.table(train_registration, filename=output_file_1,  col.names=FALSE,  row.names=FALSE,  sep="\t");
	write.table(train_handover, filename=output_file_2,  col.names=FALSE,  row.names=FALSE,  sep="\t");
}

q()




