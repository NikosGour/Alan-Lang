#include <stdio.h>
#include <unistd.h>
#include <sys/syscall.h>

int strlen(const unsigned char *s)
{
	const unsigned char *ptr = s;
	while (*ptr != '\0')
	{
		ptr++;
	}
	return ptr - s;
}
int strcmp(const unsigned char *s1, const unsigned char *s2)
{
	while (*s1 && (*s1 == *s2))
	{
		s1++;
		s2++;
	}
	return *(unsigned char *)s1 - *(unsigned char *)s2;
}
void strcpy(unsigned char *trg, const unsigned char *src)
{
	while ((*trg++ = *src++) != '\0')
		;
}

void strcat(unsigned char *trg, const unsigned char *src)
{
	while (*trg)
	{
		trg++;
	}
	while ((*trg++ = *src++) != '\0')
		;
}
void writeInteger(int n)
{
	char buffer[12];
	int length = snprintf(buffer, sizeof(buffer), "%d", n);
	syscall(SYS_write, STDOUT_FILENO, buffer, length);
}

void writeByte(unsigned char b)
{
	syscall(SYS_write, STDOUT_FILENO, &b, 1);
}

void writeChar(unsigned char c)
{
	syscall(SYS_write, STDOUT_FILENO, &c, 1);
}

void writeString(unsigned char *s)
{
	syscall(SYS_write, STDOUT_FILENO, s, strlen((const char *)s)); // strlen is not a syscall
}

int main()
{

	char *str = "Hello, Nikos!\n";
	int x = 3;
	syscall(SYS_write, STDOUT_FILENO, &x, sizeof(x));
	return 0;
}