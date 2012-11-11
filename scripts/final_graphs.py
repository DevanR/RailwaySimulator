import string, re, math, sys

NEMO_VMN_reg = 284
NEMO_VMN_hand = 836
NEMO_MR_hand = 228

ILNP_VMN_reg = 1362
ILNP_VMN_hand = 144
ILNP_MR_hand = 1362

OPTI_VMN_reg = 284
OPTI_VMN_hand = 604
OPTI_MR_hand = 488

Number_CN = 2
Number_STN = 27
Hand_period = 60

folder = sys.argv[1]
mean_file = open(folder + '/mean_hop.txt', 'r')
std_file = open(folder + '/std_hop.txt', 'r')

Number_Hop = float(mean_file.read())

input_reg = open(folder + '/train_0_reg_plot.txt', 'r')
input_hand = open(folder + '/train_0_hand_plot.txt', 'r')

nemo = open(folder + '/nemo.txt', 'a')
ilnp = open(folder + '/ilnp.txt', 'a')
opti = open(folder + '/opti.txt', 'a')

while 1:
	#Read both reg and hand files simulataneously
	line1 = input_reg.readline()
	columns1 = line1.split(" ")
	line2 = input_hand.readline()
	if not line1 and not line2:
		break
	columns2 = line2.split(" ")

	#Remove end of line
        columns1[2] = columns1[2][:-1]
	columns2[2] = columns2[2][:-1]

	#Calculate for NEMO
        total_sum = ( (float(columns1[1])*float(NEMO_VMN_reg)) + (Number_STN*NEMO_MR_hand)) / (1024*Hand_period)
        #95% upper/lower limit
        total_error = (float(columns1[2])) + (float(columns2[2]))
        output_nemo= str(columns1[0]) + '\t' + str(total_sum) + '\t' + str(total_error) + '\n'
	nemo.write(output_nemo)

	#Calculate for ILNP
        total_sum = ( (float(columns1[1])*float(ILNP_VMN_reg)) + (Number_STN*ILNP_MR_hand) + ((float(columns2[1])*float(ILNP_VMN_hand)*Number_CN*Number_Hop)) ) / (1024*Hand_period)  
        total_error = (float(columns1[2])) + (float(columns2[2]))
        output_ilnp = str(columns1[0]) + '\t' + str(total_sum) + '\t' + str(total_error) + '\n'
	ilnp.write(output_ilnp)

	#Calculate for OPTI
        total_sum = ( (float(columns1[1])*float(OPTI_VMN_reg)) + (Number_STN*OPTI_MR_hand) + ((float(columns2[1])*float(OPTI_VMN_hand)*Number_CN*Number_Hop)) )/ (1024*Hand_period)
        total_error = (float(columns1[2])) + (float(columns2[2]))
        output_opti = str(columns1[0]) + '\t' + str(total_sum) + '\t' + str(total_error) + '\n'
	opti.write(output_opti)
