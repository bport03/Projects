#ifndef SHELL_H
#define SHELL_H

// functions to parse input
int parse(char* buff, char* argv[]);
int caller_parse();

// functions to run child
void child_caller(char** tokens, int numTokens);

// functions to run dup
void dup_caller();

#endif
