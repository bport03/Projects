Basic CLI Calculator in C

A simple command-line calculator built in C that parses and evaluates mathematical expressions containing addition, subtraction, multiplication, and division. It supports multi-digit and floating-point numbers without requiring whitespace between numbers and operators.

Features

Supports +, -, *, and /

Handles floating-point numbers (e.g., 12.5+3.2*2)

Ignores whitespace

Gracefully handles division by zero

Designed using a single-pass evaluation approach

How It Works

The calculator uses a loop to iterate over the input string. It:

Extracts number strings (including decimal points)

Converts them to double using atof

Applies the current operator to an accumulating result

Updates the operator when a new one is found

How to Compile and Run

Make run 

Example Usage

Enter: 10.5+2.3*3
Result: 38.40

Enter: 100/4-5
Result: 20.00

Enter: 5/0
Error: Division by zero
Result: 5.00

Limitations

Does not support parentheses or operator precedence (e.g., it evaluates left-to-right only)

No input validation for invalid characters

Future Improvements

Add support for parentheses and operator precedence using the Shunting Yard algorithm

Support negative numbers

Add more mathematical operations (exponents, modulo, etc.)



