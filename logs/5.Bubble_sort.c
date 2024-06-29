#include "stdlib_alan.h"
int main() {
    void _main(){
        
        
        int seed;
        int x[16];
        int i;
        void bsort(int n, int *x){
            
            unsigned char changed;
            int i;
            void swap(int *x, int *y){
                int t;
                t = x;
                x = y;
                y = t;
            }
            changed = 'y';
            while (changed == 'y') {
                changed = 'n';
                i = 0;
                while (i < n - 1) {
                    if (x[i] > x[i + 1]) {
                        swap(x[i],x[i + 1]);
                        changed = 'y';
                    }
                    i = i + 1;
                }
            }
        }
        void writeArray(unsigned char *msg, int n, int *x){
            int i;
            writeString(msg);
            i = 0;
            while (i < n) {
                if (i > 0)  writeString(",");
                writeInteger(x[i]);
                i = i + 1;
            }
            writeString("\n");
        }
        seed = 65;
        i = 0;
        while (i < 16) {
            seed = seed * 137 + 220 + i % 101;
            x[i] = seed;
            i = i + 1;
        }
        writeArray("Initial array:",16,x);
        bsort(16,x);
        writeArray("Sorted array:",16,x);
    }
    _main();
    return 0;
}
