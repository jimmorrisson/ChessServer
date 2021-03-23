#!/bin/sh
cd src
javac -cp ".:/Users/admin/git/ChessCommon/ChessCommon.jar:/Users/admin/java_proj/json-20201115.jar" *.java
java -cp ".:/Users/admin/git/ChessCommon/ChessCommon.jar:/Users/admin/java_proj/json-20201115.jar" App