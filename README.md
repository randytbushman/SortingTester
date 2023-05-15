# SortTester: A Java-Based Sorting Algorithm Performance Evaluator

## Overview

SortTester is a Java program created to assess and compare the efficiency of a variety of sorting algorithms. It does this by gauging the time taken to sort arrays of varying lengths. Users can specify both the minimum and maximum array lengths for the performance tests. SortTester conducts multiple tests within the defined range, each comprising several trials. The average time taken across these trials provides a comprehensive measure of each sorting algorithm's performance. The results are then saved to a user-specified location, formatted as CSV files for ease of analysis.

## User Guide

### Running SortTester

To use SortTester:

1. Execute the 'Driver' class.

The main function in the 'Driver' class hosts several configurable parameters, including:

- Trial Count: The number of trials run for each test.
- Test Increment: The change in length between each tested array.
- Minimum and Maximum Length: The bounds for the lengths of arrays tested.
- Maximum Array Value: The highest possible value for elements in the array (the minimum is set at 0).
- Output File Location: The location where the CSV results file will be saved.

### Incorporating New Sorting Algorithms

To introduce and evaluate a new sorting algorithm, follow these steps:

1. Create a new Java file for the sorting algorithm. Ensure the class contains a static void method that takes an 'int' array as a parameter.
2. In the 'Driver' file, use the 'addSortingAlgorithmToTest' method to include the new sorting algorithm in the test suite.

By following these instructions, users can easily adapt SortTester to evaluate the performance of any sorting algorithm, providing invaluable insights into algorithm efficiency.
