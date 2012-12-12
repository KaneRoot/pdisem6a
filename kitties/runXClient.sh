#!/bin/bash
for i in `cat /proc/cpuinfo | grep processor`;
do 
java Client $1 $2 &
done


