#!/bin/bash

#Inhibimos SIGTERM, SIGINT y SIGKILL

#SIGTERM hace que el proceso termine de manera ordenada, se permite la
#limpieza antes de la finalización
trap 'echo SIGTERM captured' 15
#SIGINT 2 interrumpe un proceso en ejecucion
trap 'echo SIGINT captured' 2
#SIGKILL 9 mata un proceso sin dejar que podamos evitarlos. Hya PID vetados
trap 'echo SIGKILL captured' 9

#Mostramos el PID
echo $$

#Pausamos 40 segundos

sleep 40
