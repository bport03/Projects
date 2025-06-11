#include <ctype.h>
#include <stdio.h>
#include <string.h>

// count how many lines in the file
int count_lines(FILE *file_pointer) {
    int counter = 0;
    char c;
    char last = '\0';
    while ((c = fgetc(file_pointer)) != EOF) {
        if (c == '\n') {
            counter++;
        }// end if
        last = c;


    }// end while

    // check if the last character of the file to decided if its a new line or not
    if (last !='\0' && last != '\n') {
        counter++;
    }

   // fclose(file_pointer);
    return counter;
}

int total_words(FILE *file_pointer) {
    int counter = 0;
    char c;
    while ((c = fgetc(file_pointer)) != EOF) {

        if ( c == ' ' || c == '\t' || c == '\n' || c == '\0') {
            counter++;
        }

    }

    return counter;
}

int character_count(FILE *file_pointer) {
    int counter = 0;
    char c;
    while ((c = fgetc(file_pointer)) != EOF) {
        if (isprint(c)) {
            counter++;
        }
    }
    return counter;

}


int main(void) {
    FILE *file_pointer;
    char s[100];

    //placeholder for functions
    int line_counter=0;
    int word_counter=0;
    int character_counter=0;


    printf("Enter file path: ");
    fgets(s, sizeof s, stdin);
    s[strcspn(s, "\n")] = '\0';


    file_pointer = fopen(s, "r");

    if (file_pointer == NULL) {
        printf("File not found\n");
    }else {
         // call functions
        line_counter=  count_lines(file_pointer);
        printf("Number of lines: %d\n", line_counter);
        rewind(file_pointer);
        word_counter = total_words(file_pointer);
        printf("Total words: %d\n", word_counter);
        rewind(file_pointer);
        character_counter = character_count(file_pointer);
        printf("Characters: %d\n", character_counter);
        fclose(file_pointer);

    }




}



// /Users/brandonp/CLionProjects/test.txt