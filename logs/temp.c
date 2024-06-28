#include <stdio.h>
#include <unistd.h>
int main()
{
	char *x = "peos";
	write(STDOUT_FILENO, x, 4);
	return 0;
}
