#Author:Devan Rehunathan
#Date:August 2009
#Name:process_passenger_results.R


Args <- commandArgs(TRUE)

directory <- Args[1];

# Constants
number_of_weeks <- 51;
number_of_days <- 6;
number_of_hours <- 17;

mean <- 0;
standard_deviation <- 0;

for(w in 0:number_of_weeks){
    for(d in 0:number_of_days){
        for(h in 0:number_of_hours){

            current_file <- paste("/home/dr/RailwaySimulator/results/passenger_results", "/Week", w, "/Day",  d, "/Hour",  h, ".txt", sep="");
            
            if( (w+d+h)==0){
                hop_table <-read.table(current_file,header=FALSE,sep="\t");
            }
            else{
                input_table <-read.table(current_file,header=FALSE,sep="\t");
                hop_table <- rbind(hop_table, input_table);
            }
            
        }
    }
}

total_passenger <- nrow(hop_table);
mean <- mean(hop_table[1:nrow(hop_table),4]);
standard_deviation <- sd(hop_table[1:nrow(hop_table),4]);
standard_error <- standard_deviation/sqrt(nrow(hop_table));

write( c(total_passenger,  mean,  standard_error), file="/home/dr/RailwaySimulator/results/passenger_summary.txt", append=FALSE, sep="\t");

q()



