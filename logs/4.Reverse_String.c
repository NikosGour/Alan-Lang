#include "stdlib_alan.h"

int main() {
    void _main(){
        unsigned char r[32];
        
        void reverse(unsigned char *s){
            int i;
            int l;
            l = strlen(s);;
            i = 0;
            while (i < l) {
                r[i] = s[l - i - 1];
                i = i + 1;
            }

            r[i] = '\0';
        }
        reverse("\n!dlrow olleH");
        writeString(r);
    }
    _main();
    return 0;
}
