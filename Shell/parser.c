


/* simple program which takes input from the keyboard and parses it into an argv vector */

#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/errno.h>
#include "shell.h"
#include <sys/wait.h>

#include <fcntl.h>
/* function which takes a character buffer and parses the buffer into tokens using th
    strtok subroutine */

/*returns the number of arguments read in  */

int parse(char* buff, char* argv[])
{ int i,j;
    char *point;
    j=-1;
    point=strtok(buff," ");

    // Flag to check if output and input redirection is present
    int in_lessthen = 0;
    int out_greathen = 0;
    // create files
    char* in_file = NULL;
    char* oot_file = NULL;
    // file counter if theres more than one
    int count_files=0;


    while (point != NULL)
    {
        // checks if < is in the command if so flag tester to run
        // if in_lessthen
        if (strcmp(point, "<") == 0)
        {
            // < has been found flag to be tested later
            in_lessthen = 1;
            point = strtok(NULL, " ");
            // moves the token to point for the file folling the < if not flag missing null
            // increment file if found
            if (point != NULL)
            {
                in_file = point;
                point = strtok(NULL, " ");
                count_files++;
                continue;
            }
            else
            {
                perror("Missing input file after '<'\n");
                return -1;
            }
        }
            // checks if > is in the command if so flag tester to run
            // if out_greathen
        else if (strcmp(point, ">") == 0)
        {
            // > has been found flag to be tested later
            out_greathen = 1;
            point = strtok(NULL, " ");
            // moves the token to point for the file folling the > if not flag missing null
            // increment file if found
            if (point != NULL)
            {
                oot_file = point;
                point = strtok(NULL, " ");
                count_files++;
                continue;
            }
            else
            {
                perror(" Missing output file after '>'\n");
                return -1;
            }
        }

        j++;
        // move to to next token
        argv[j] = point;
        point = strtok(NULL, " ");

    }// end of while loop

    // exit program if theres two or more files
    if (count_files > 1)
    {
        perror("More than one file specified\n");
        return -1; // Error
    }

    argv[j + 1] = NULL;

    // checks for <
    if (in_lessthen)
    {
        // at this point file input should have the data store from point
        if (in_file != NULL)
        {
            // check if file input can be open and read
            int data_file = open(in_file, O_RDONLY);
            if (data_file == -1)
            {
                perror("Error opening input file");
                return -1;
            }
            //
            dup2(data_file, STDIN_FILENO);

            close(data_file);

        }
        else
        {
            perror("Missing input file after '<'\n");
            return -1;
        }
    }

    // check for >
    if (out_greathen)
    {
        // at this point file out should have the data store from point
        if (oot_file != NULL)
        {
            // check if file out can be open and write , creat
            int data_file = open(oot_file, O_CREAT | O_WRONLY | O_TRUNC, 0644);
            if (data_file == -1)
            {
                perror("Error opening output file");
                return -1;
            }
            dup2(data_file, STDOUT_FILENO);

            close(data_file);
        }
        else
        {
            perror("Missing output file after '>'\n");
            return -1;
        }
    }


    return(j);
}
