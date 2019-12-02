#!/bin/bash
find -name "*.jar" > sources.txt
find -name "*.java" >> sources.txt
javac -d bin/ -cp @sources.txt