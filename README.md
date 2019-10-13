# Lab_4.Strings_processing
This is a branch of the [Java-BSU](https://github.com/asenyarb/Java-BSU/) repository.

## Lab 4 - **String, StringBuffer, StringTokenizer classes**.
Variant 4.
# Problem
The program asks for two lines and an integer number P to search for. 

The first line contains tokens consisting of any characters that can be entered from the keyboard, e.g. a number (see individual variants, 2nd or 8th or 10th or 16th number).

The second line contains separators (1 separator is 1 character, but there can be >1 separator and different separators between tokens).

**Tasks**:
- Divide the first line into tokens (using the separators from the second line)
- Find all the decimals in it. Write these decimals into a new separate array. 
- Among the tokens that are not decimals, find tokens consisting only of the same symbols. 
- Find the number P (if any, it should coincide with the token), display the position in the original line. 
- Duplicate one of the numbers in the string, add it after the number. 
- Remove a substring from the string of punctuation characters, if any.  
- Print all the results.

*Use methods*:

- For String: format, getChars, Split, Remove, Substring, Replace, IndexOf, LastIndexOf

- For StringBuffer: delete, insert, reverse
