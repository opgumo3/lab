#include <stdio.h>
#include <unistd.h> // close

int main()
{
	dprintf(STDOUT_FILENO, "Child[%d]: Start\n", getpid());
	dprintf(3, "Child[%d]: fd(3): Test fd.\n", getpid()); // 3번 fd 는 부모 프로세스가 열었던 파일을 상속한 것.
	close(3); // delete a descriptor
	dprintf(STDOUT_FILENO, "Child[%d]: Exit\n", getpid());
	return 0;
}