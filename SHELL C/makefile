all: program2

program2: parser.c main.c  shell.h
	gcc -o program2 parser.c main.c

run: program2
	./program2

clean:
	rm -f program2