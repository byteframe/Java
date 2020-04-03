#!/bin/sh

SRC="/media/SANDISKU3/Tacts"

cd $SRC
javac -d classes/ src/gui/*.java src/xml/*.java src/Tacts.java && cd classes/ && java Tacts
