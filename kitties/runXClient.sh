#!/bin/bash

for i in `cat /proc/cpuinfo | grep processor | cut -f2 -d' '`
do 
	java Client $1 $2 &
done
