# Lab_5.Regex
This is a branch of the [Java-BSU](https://github.com/asenyarb/Java-BSU/) repository.

## Lab 5 - **Regular expressions**.
Variant 4.
# Problem
Read data from the file ( > =6 test lines). 
Write the results to the file. Use the following classes: FileReader, FileWriter, BufferedReader, BufferedWriter.

Check whether the given string is a number, not necessarily an integer, or can be written in an equipotential form in the decimal place (maybe with zeros in the senior digits). 
#### Correct expressions: 
- 123456
- 234567 
- 000333
- 0333.1121
- 2424,423423
- -1.17E-08
- -1.17E-08
- 1.17E8
- 1.17e8

#### Incorrect expressions: 
- 113..444
- 16\.	12345
- 23232312?33
- 1.17E+08
- 1.17EE+08
- 1.17Ee+08
- 1.17Ee++8
- *90875
- !»№;%?*
