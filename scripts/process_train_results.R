#Author:Devan Rehunathan
#Date:August 2009
#Name:process_train_results.R

# Constants
number_of_weeks <- 51;
number_of_days <- 6;
number_of_hours <- 17;
number_of_stations <- 26;
number_of_trains <- 6;

mean <- 0;
standard_deviation <- 0;

    for(w in 0:number_of_weeks){
        for(d in 0:number_of_days){
            for(h in 0:number_of_hours){
                for(s in 0:number_of_stations){

                    current_file <- paste("/home/dr/RailwaySimulator/results/train_results", "/Week", w, "/Day",  d,  "/Hour",  h,  "/Station",  s, ".txt", sep="");

                    input_table <-read.table(current_file,header=FALSE,sep="\t");
                    
                        if( (w+d+h+s)==0){
                            train_1 <- input_table[(1), ];
                            train_2 <- input_table[(2), ];
                            train_3 <- input_table[(3), ];
                            train_4 <- input_table[(4), ];
                            train_5 <- input_table[(5), ];
                            train_6 <- input_table[(6), ];
                            train_7 <- input_table[(7), ];
                        }
                        else{
                            train_1 <-rbind(train_1, input_table[(1), ]);
                            train_2 <-rbind(train_2, input_table[(2), ]);
                            train_3 <-rbind(train_3, input_table[(3), ]);
                            train_4 <-rbind(train_4, input_table[(4), ]);
                            train_5 <-rbind(train_5, input_table[(5), ]);
                            train_6 <-rbind(train_6, input_table[(6), ]);
                            train_7 <-rbind(train_7, input_table[(7), ]);
                        }
                    }
                }         
            }
        }

write.table(train_1, file="/home/dr/RailwaySimulator/results/train_1.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_2, file="/home/dr/RailwaySimulator/results/train_2.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_3, file="/home/dr/RailwaySimulator/results/train_3.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_4, file="/home/dr/RailwaySimulator/results/train_4.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_5, file="/home/dr/RailwaySimulator/results/train_5.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_6, file="/home/dr/RailwaySimulator/results/train_6.txt", row.name=F, col.names=F,  sep="\t");
write.table(train_7, file="/home/dr/RailwaySimulator/results/train_7.txt", row.name=F, col.names=F,  sep="\t");

q()



