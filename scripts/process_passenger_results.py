#!/usr/bin/env python
"""
Saleem Bhatti, 03 Sep 2009

Standard Deviation Formula without Mean

  ./random_numbers.py file

  file just contains one number per line

This program validates the code for the method.

<http://mathforum.org/library/drmath/view/61381.html>
---------------------------------
Date: 10/10/2002 at 09:01:06
From: Doctor Pepper
Subject: Re: Standard Deviation without Mean

Hi Ryan - To compute the standard deviation without computing the mean,
you can use this formula:

  std dev = sqrt [(B - A^2/N)/N]

where A is the sum of the data values;
      B is the sum of the squared data values;
      N is the number of data values.

Best wishes! - Doctor Pepper, The Math Forum <http://mathforum.org/dr.math/

Notes from saleem:

  A = sigma (x)
  B = sigma (x^2)
  M = A/N

See also:

  "Practical Statistics", by S. S. Cohen, 1998
   equation 2.3, p20

a = 0.0 # sum of values
b = 0.0 # sum of squares of values
c = []  # all values
v = 0.0 # value read from stdin
n = 0   # number of values
m = 0.0 # mean
s = 0.0 # std dev



t = time.clock()
for w in range(0,52):
	for d in range(0,7):
        	for h in range(0,18):

			current_file = "/home/dr/RailwaySimulator/dist/passenger_results" + "/Week" + str(w) + "/Day" + str(d) + "/Hour" + str(h) + ".txt"
	     
	    		f1 = open(current_file)
			# first, do it using the standard python functions

            		for line in f1.xreadlines():

                		if (len(line) != 0) and (line[0] != '#'):
					col1,col2,col3,col4 = line.split('\t')
                			v = float(col4)
                			c.append(v)
        				n += 1

m1 = numpy.average(c)
s1 = numpy.std(c)
t = time.clock() - t
print "math: m=%.8f s=%.8f t=%.6f n=%f" % (m1, s1, t, n)
"""
import sys
import math
import numpy
import time
import string
import sys

a = 0.0 # sum of values
b = 0.0 # sum of squares of values
c = []  # all values
v = 0.0 # value read from stdin
n = 0   # number of values
m = 0.0 # mean
s = 0.0 # std dev

folder = sys.argv[1]

mean = open(folder + '/mean_hop.txt', 'w')
std = open(folder + '/std_hop.txt', 'w')

t = time.clock()
for w in range(0,52):
	for d in range(0,7):
		for h in range(0,18):

			current_file = folder + "/passenger_results" + "/Week" + str(w) + "/Day" + str(d) + "/Hour" + str(h) + ".txt"
            
			f2 = open(current_file)
	    		for line in f2.xreadlines():

    				if (len(line) != 0) and (line[0] != '#'):
					col1,col2,col3,col4 = line.split('\t')
        				v = float(col4)
        				a += v
        				b += v * v
        				n += 1

m2 = a/n
s2 = math.sqrt((b - a*a/n)/n)
t = time.clock() - t
print "mine: m=%.8f s=%.8f t=%.6s n=%f" % (m2, s2, t, n)
mean.write(str(m2))
std.write(str(s2))
