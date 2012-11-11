
trains <- 6
stations <- 27

Args <- commandArgs();      # retrieve args
folder = Args[8];

#Go through all train files
for(t in 0:trains){
	

        #Create output files
	reg_outfile <- paste(folder, "/train_", t, "_reg_plot.txt", sep="");
	hand_outfile <- paste(folder, "/train_", t, "_hand_plot.txt", sep="");

	#Read input files
	reg_file <- paste(folder, "/train_", t, "_reg.dat", sep="");
	hand_file <- paste(folder, "/train_", t, "_hand.dat", sep="");	
	reg_table <-read.table(reg_file,header=FALSE, sep="\t");
	hand_table <-read.table(hand_file,header=FALSE, sep="\t");

	mean_reg <- 0;
	std_reg <- 0;
	std_error_reg <- 0;
	mean_hand <- 0;
	std_hand <- 0;
	std_error_hand <- 0;

	for(s in 1:stations){
		mean_reg[s] <- mean(reg_table[1:nrow(reg_table),s]);
		std_reg[s] <- sd(reg_table[1:nrow(reg_table),s]);
                std_error_reg[s] <- std_reg[s]/sqrt(nrow(reg_table));

		mean_hand[s] <- mean(hand_table[1:nrow(hand_table),s]);
		std_hand[s] <- sd(hand_table[1:nrow(hand_table),s]);
		std_error_hand[s] <- std_hand[s]/sqrt(nrow(hand_table));

	write( c(s, mean_reg[s], (std_error_reg[s]*1.96) ) , file=reg_outfile, append=T, sep=" ");

	write( c(s, mean_hand[s], (std_error_hand[s]*1.96) ) , file=hand_outfile, append=T, sep=" ");

	}

}

q()
