#!/bin/sh

dockerize -wait tcp://database:3306 -timeout 120s

# Start server
echo "Start Application"
java -jar app.jar