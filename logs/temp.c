#include <stdio.h>
int main()
{
	void bsort(int n, int *x)
	{
		void swap(int *x, int *y)
		{
			int t;
			printf("Swapping x=%d and y=%d\n", *x, *y);
		}

		unsigned char changed;
		int i;
		printf("Swapping n=%d and x=%d\n", n, *x);
		swap(&n, x);
	}

	int a = 5;
	bsort(1, &a);
	return 0;
}
