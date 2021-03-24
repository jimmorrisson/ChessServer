#!/bin/sh
cd src
javac -cp ".:../ChessCommon.jar:../json-20201115.jar" *.java
java -cp ".:../ChessCommon.jar:../json-20201115.jar" App
