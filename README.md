SortTester is a Java program designed to compare the performance of sorting algorithms by measuring array length versus time. The user defines a minimum and maximum array length. SortTester executes several tests between the specified range. Each test executes multiple trials, and the resulting times are averaged. The results are then saved to the specified output location. Outputs are formatted as CSVs.

Instructions:

To run SortTester, the user must execute the Driver. The main function contains several tunable parameters, including trial count, test increment, minimum length, maximum length, maximum array value (minimum is 0), and the output file location.

To create and test a new sorting algorithm, the user must create a new Java file. The class must contain a static void method that takes in an int array. The user then must go into the Driver file and add the sorting algorithm to the test with the 'addSortingAlgorithmToTest' method.