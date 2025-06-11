#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {

    char s[100];
    char op='+';
    double result = 0.0;
    double temp_num = 0.0;
    char convert_double[100];
    int j=0;
    // ask user input
    printf("Enter: ");
    fgets(s,sizeof(s),stdin);
    s[strcspn(s, "\n")] = '\0'; // Remove newline from input

    for (int i = 0; s[i] != '\0'; i++) {
        // look for number strings and convert them to digits
        while (isdigit(s[i])|| s[i]=='.') {
            convert_double[j++] = s[i++];

        }
        // convert string to double
         convert_double[j] = '\0';
         temp_num = atof(convert_double);

        // look for operators and do the math
        if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || s[i]== '\0') {

            switch (op) {

                case '+':
                    result += temp_num;
                    temp_num = 0;
                    break;
                case '-':
                    result -= temp_num;
                    temp_num = 0;
                    break;
                case '*':
                    result *= temp_num;
                    temp_num = 0;
                    break;
                case '/':
                    if (result!=0) {
                        result /= temp_num;
                        temp_num = 0;
                        break;
                    }
                    printf("Error: Division by zero\n");
                    break;
            }
            temp_num = 0;
            j=0;
            op=s[i];
        }// end if


    }// end loop


    // Apply the final operation (since no operator follows the last number)
    switch (op) {
        case '+': result += temp_num; break;
        case '-': result -= temp_num; break;
        case '*': result *= temp_num; break;
        case '/':
            if (temp_num != 0) result /= temp_num;
            else printf("Error: Division by zero\n");
            break;
    }
    printf("Result: %.10g\n", result);

    return 0;
}
