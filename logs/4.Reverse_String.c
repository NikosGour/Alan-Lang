#include <stdio.h>
int main() {
    void _main(){
        void reverse(unsigned char *s){
            int i;
            int l;
            l = strlen(s);
            i = 0;
            while (i < l) {
                r[i] = s[l - i - 1];
                i = i + 1;
            }

            r[i] = '\0';
        }
        unsigned char r[32];
        
        reverse("\n!dlrow olleH")
        writeString(r)
    }
    return 0;
}
