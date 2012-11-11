import string, re ,sys

trains = 7 

results_folder = sys.argv[1]

for t in range(0,trains):

	current_file = results_folder + '/train_' + str(t) + '.dat'

        f = open(current_file, 'r')

	registration_file = results_folder + '/train_' + str(t) + '_reg.dat'
	handover_file = results_folder + '/train_' + str(t) + '_hand.dat'

	reg = open(registration_file, 'a')
        hand = open(handover_file, 'a')

	reg_row = []
	hand_row = []

	hour = 0
	station = 0
	lnum = 0

	for line in f:
	        
                #Count the line number
		lnum += 1

                #Line number corrersponds to Station number
		station = lnum%27
		
                columns = line.split("\t")

                #Completed a cycle of the Circle Line -> 27 stations
		if station == 0:
			reg_row.append(columns[0])
			columns[1] = columns[1][:-1]
			hand_row.append(columns[1])
			reg.write("\t".join(reg_row))
			reg.write("\n")
			hand.write("\t".join(hand_row))
			hand.write("\n")
			del reg_row[:]
			del hand_row[:]
		else:
			reg_row.append(columns[0])
			columns[1] = columns[1][:-1]
			hand_row.append(columns[1])
