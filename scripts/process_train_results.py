import string, re, sys

weeks = 52
days = 7
hours = 18
stations = 27
trains = 7

results_folder = sys.argv[1];

lnum = 0

o0 = open(results_folder + '/train_0.dat', 'a')
o1 = open(results_folder + '/train_1.dat', 'a')
o2 = open(results_folder + '/train_2.dat', 'a')
o3 = open(results_folder + '/train_3.dat', 'a')
o4 = open(results_folder + '/train_4.dat', 'a')
o5 = open(results_folder + '/train_5.dat', 'a')
o6 = open(results_folder + '/train_6.dat', 'a')

# Go through all StationX.txt files
for w in range(0, weeks):
	for d in range(0, days):
		for h in range(0, hours):
			for s in range(0, stations):

				current_file = results_folder + '/train_results/Week' + str(w) + '/Day' + str(d) + '/Hour' + str(h) + '/Station' + str(s) + '.txt'  
				f = open(current_file, 'r')

				for line in f:
                                        
					#Counter for line number. Each file has 7 lines
					lnum += 1

                                        #Split text file into columns
					columns = line.split("\t")
					
                                        #We are interested only in 2nd=registrations and 5th=handovers
					output_line = str(columns[2]) + '\t' + str(columns[5]) 

                                        #Split handover and registrations into separate train identities
					if(lnum%7 == 1):
						o0.write(output_line)
					if(lnum%7 == 2):
						o1.write(output_line)
					if(lnum%7 == 3):
						o2.write(output_line)
					if(lnum%7 == 4):
						o3.write(output_line)
					if(lnum%7 == 5):
						o4.write(output_line)
					if(lnum%7 == 6):
						o5.write(output_line)
					if(lnum%7 == 0):
						o6.write(output_line)

