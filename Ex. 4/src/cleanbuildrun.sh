#!/bin/bash
rm -r build
mkdir build
cp GraphicsEventSystem.class ./build
javac -d build MyApp.java
cd build 
java MyApp
