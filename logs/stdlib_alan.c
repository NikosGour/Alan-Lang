#include <stdio.h>
#include <unistd.h>
#include <sys/syscall.h>

int readInteger() {
    char buffer[12]; // Buffer to store input string
    int length = syscall(SYS_read, STDIN_FILENO, buffer, sizeof(buffer) - 1);
    buffer[length] = '\0'; // Null-terminate the string
    return atoi(buffer); // Convert string to integer
}

unsigned char readByte() {
    unsigned char b;
    syscall(SYS_read, STDIN_FILENO, &b, 1);
    return b;
}

unsigned char readChar() {
    unsigned char c;
    syscall(SYS_read, STDIN_FILENO, &c, 1);
    return c;
}

void readString(int n, unsigned char *s) {
    int length = syscall(SYS_read, STDIN_FILENO, s, n-1);
    s[length] = '\0'; // Null-terminate the string
}
void writeInteger(int n) {
    char buffer[12];
    int length = snprintf(buffer, sizeof(buffer), "%d", n);
    syscall(SYS_write, STDOUT_FILENO, buffer, length);
}

void writeByte(unsigned char b) {
    syscall(SYS_write, STDOUT_FILENO, &b, 1);
}

void writeChar(unsigned char c) {
    syscall(SYS_write, STDOUT_FILENO, &c, 1);
}

void writeString(unsigned char *s) {
    syscall(SYS_write, STDOUT_FILENO, s, strlen((const char *)s)); // strlen is not a syscall
}
