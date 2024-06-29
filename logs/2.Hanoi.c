#include <stdio.h>
int main() {
    void solve(){
        void hanoi(int rings, unsigned char *source, unsigned char *target, unsigned char *auxiliary){
            void move(unsigned char *source, unsigned char *target){
                writeString("Moving from ")
                writeString(source)
                writeString(" to ")
                writeString(target)
                writeString(".\n")
            }
            
            if (rings >= 1) {
                hanoi(rings - 1,source,auxiliary,target)
                move(source,target)
                hanoi(rings - 1,auxiliary,target,source)
            }

        }
        
        int NumberOfRings;
        writeString("Rings: ")
        NumberOfRings = readInteger();
        hanoi(NumberOfRings,"left","right","middle")
    }
    return 0;
}
