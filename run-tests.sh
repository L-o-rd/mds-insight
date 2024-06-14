#!/bin/bash

# Compile main code
javac -d out -cp "lib/*" src/com/insight/*.java
javac -d out -cp "lib/*" src/com/insight/graphics/*.java
javac -d out -cp "lib/*" src/com/insight/states/*.java

# Compile test code
javac -d out -cp "lib/*:out" src/tests/*.java

# Run tests
java -cp "lib/*:out" org.junit.runner.JUnitCore tests.Tests
