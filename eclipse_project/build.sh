#!/bin/sh

echo $0
echo $1
echo $2

echo "javac -d bin src/*/*.java"
echo "java -cp bin:lib/ojdbc7.jar controller.DemoGUI"
