// Brandon Portillo
// Sep-25,2023
// Simple shell
//performs rudimentary shell actions

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include "shell.h"
#include <sys/wait.h>

int main() {
    char user_input[100];
    char *parse_data[100];
    char* ls_args[] = {"/bin/ls", NULL};


    // infinite loop to have shell runing
    while(1) {

        printf("portillo_shell%%: ");
        fgets(user_input, sizeof(user_input), stdin);
        // remove \n to avoid error
        user_input[strlen(user_input) - 1] = '\0';
        // break down user input, and count each token
        int  args_count = 0;
        args_count= parse(user_input,parse_data);
        //input ls"cd", "pwd", "echo", "mkdir", and "exit"
        if (strcmp(parse_data[0], "cd") == 0) {
            if (args_count !=1) {
                perror(  "cd: missing argument\n");
            } else {
                if (chdir(parse_data[1]) != 0) {
                    perror("cd");
                }
            }
        }//end cd
        else if(strcmp(parse_data[0],"pwd")==0){
            char path[2000];
            if (getcwd(path, sizeof(path)) != NULL) {
                printf("%s\n", path);
            } else {
                perror("getcwd");
            }
        }// end pwd
        else if(strcmp(parse_data[0],"echo")==0){
            // iterate throught echo arguments
            for (int i = 1; i < args_count; i++) {
                printf("%s ", parse_data[i]);
            }
            printf("\n");
        }// end echo
        else if(strcmp(parse_data[0],"mkdir")==0){
            if(args_count > 1){
                if(mkdir(parse_data[1],0777)!=0){
                    perror("mkdir");
                }
            }
        }// end mkdir

        else if(strcmp(parse_data[0],"ls")==0) {
            // List directory  ls command
            pid_t pid = fork();
            if (pid == -1) {
                perror("fork");
            } else if (pid == 0) {
                // Child process

                execvp(parse_data[0], parse_data);
                perror("execvp");
                exit(EXIT_FAILURE);
            } else {
                // Parent process
                int status;
                waitpid(pid, &status, 0);
            }
        }// end ls

        else if (strcmp(parse_data[0], "exit") == 0) {
            printf("Exiting Shell....");
            exit(0);
        }// end exit

        else{
            //child process
            pid_t pid = fork();
            if (pid == -1) {
                perror("execv");
                exit(EXIT_FAILURE);
            } else if (pid == 0) {
                // Child process
                execvp(parse_data[0], parse_data);

            } else {
                // Parent process
                int status;
                waitpid(pid, &status, 0);
            }
        }

    }// end while



    return 0;
}

















































