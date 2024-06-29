#include "stdlib_alan.h"
int main() {
    int prime(int n){
        int i;
        if (n < 0)  return prime( -n);
        else if (n < 2)  return 0;
            else if (n == 2)  return 1;
                else{
                    i = 3;
                    while (i <= n / 2) {
                        if (n % i == 0)  return 0;
                        i = i + 2;
                    }
                    return 1;
                }
    }
    prime();
    return 0;
}
