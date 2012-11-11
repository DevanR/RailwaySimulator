#Author:Devan Rehunathan
#Date:August 2009
#Name:makefile
#Function: This is a makefile for RailwaySimulator

#Command
RS = R --slave --no-save --no-restore --no-environ --silent --args 
PY = python
WD = /home/dr/RailwaySimulator
G = gnuplot

#Files
r_10 = "/home/dr/RailwaySimulator/results_10"
r_50  = "/home/dr/RailwaySimulator/results_50"
r_90 = "/home/dr/RailwaySimulator/results_90"
r_r = "/home/dr/RailwaySimulator/results_random"

all: 
	make passenger 
	make train1 
	make train2 
	make train3 
	make train4 
	make graphs

passenger:
	$(PY) process_passenger_results.py $(r_10)
	$(PY) process_passenger_results.py $(r_50)
	$(PY) process_passenger_results.py $(r_90)
	$(PY) process_passenger_results.py $(r_r)
train1:
	$(PY) process_train_results.py $(r_10)
	$(PY) process_trains.py $(r_10)
	$(RS) $(r_10) < train_results.R 
	$(PY) final_graphs.py $(r_10)

train2:	
	$(PY) process_train_results.py $(r_50)
	$(PY) process_trains.py $(r_50)
	$(RS) $(r_50) < train_results.R 
	$(PY) final_graphs.py $(r_50)

train3:	
	$(PY) process_train_results.py $(r_90)
	$(PY) process_trains.py $(r_90)
	$(RS) $(r_90) < train_results.R
	$(PY) final_graphs.py $(r_90)

train4:	
	$(PY) process_train_results.py $(r_r)
	$(PY) process_trains.py $(r_r)
	$(RS) $(r_r) < train_results.R
	$(PY) final_graphs.py $(r_r)

graphs:
	$(G) /home/dr/RailwaySimulator/results_10/plot_train_hand.gp
	$(G) /home/dr/RailwaySimulator/results_10/plot_train_reg.gp
	$(G) /home/dr/RailwaySimulator/results_10/plot_final.gp
	$(G) /home/dr/RailwaySimulator/results_50/plot_train_hand.gp
	$(G) /home/dr/RailwaySimulator/results_50/plot_train_reg.gp
	$(G) /home/dr/RailwaySimulator/results_50/plot_final.gp
	$(G) /home/dr/RailwaySimulator/results_90/plot_train_hand.gp
	$(G) /home/dr/RailwaySimulator/results_90/plot_train_reg.gp
	$(G) /home/dr/RailwaySimulator/results_90/plot_final.gp
	$(G) /home/dr/RailwaySimulator/results_random/plot_train_hand.gp
	$(G) /home/dr/RailwaySimulator/results_random/plot_train_reg.gp
	$(G) /home/dr/RailwaySimulator/results_random/plot_final.gp

clean:
	rm -rf $(r_10)/*~
	rm -rf $(r_10)/*.dat
	rm -rf $(r_10)/*.txt
	rm -rf $(r_10)/*.eps
	rm -rf $(r_50)/*~
	rm -rf $(r_50)/*.dat
	rm -rf $(r_50)/*.txt
	rm -rf $(r_50)/*.eps 
	rm -rf $(r_90)/*~
	rm -rf $(r_90)/*.dat
	rm -rf $(r_90)/*.txt
	rm -rf $(r_90)/*.eps
	rm -rf $(r_r)/*~
	rm -rf $(r_r)/*.dat
	rm -rf $(r_r)/*.txt
	rm -rf $(r_r)/*.eps
