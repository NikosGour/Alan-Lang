#include "stdlib_alan.h"
void test(int *x)
{
    x[0] = 10;
}
int main()
{
    int x[] = {1, 2, 3};
    test(x);
    printf("%d\n", x[0]);
    return 0;
}
