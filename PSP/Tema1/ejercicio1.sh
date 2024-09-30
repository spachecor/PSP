#!/bin/bash

#USAR sudo chmod +x ./ejercicio1.sh para dar los permisos al script para que 
#le de permisos para ejecutarse.

#CON & pid1=$! estamos almacenando en pid1 el pid del proceso creado
sleep 1 & pid1=$!
echo Ejecutando primer proceso $$
#CON ps -p $$ NOS MUESTRA LA INFORMACIÓN DEL PROCESO ACTUAL, EN ESTE CASO,
#ESTE SCRIPT
ps -p $$
sleep 2 & pid2=$!
echo Ejecutando segundo proceso $$
sleep 3 & pid3=$!
echo Ejecutando tercer proceso $$

echo Esperando al proceso 1 con pid $pid1
wait $pid1 
echo Esperando al proceso 2 con pid $pid2
wait $pid2
echo Esperando al proceso 3 con pid $pid3
wait $pid3

echo Saliendo....
exit
