#!/bin/sh

cd src/
javadoc -cp ".:../ChessCommon.jar:../json-20201115.jar" -version -author -private -windowtitle Dokumentacja  *.java
