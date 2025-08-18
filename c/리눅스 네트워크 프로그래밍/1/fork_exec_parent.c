#include <sys/types.h>
#include <unistd.h> // getpid
#include <fcntl.h>  // open, fcntl
#include <stdio.h> // perror, dprintf, printf
#include <stdlib.h> // exit
#include <sys/wait.h> // wait

int main()
{
    pid_t pid_child;
    printf("Parent[%d]: Starting\n", getpid()); // write output to stdout

    int fd = open("fork_exec_file.txt", O_WRONLY | O_CREAT | O_APPEND, 0644);
    // returns -1 on failure

    if (fd == -1)
    {
        perror("FAIL: open");
        exit(EXIT_FAILURE); // 프로세스 전체 종료
    }
    dprintf(fd, "Parent[%d]: Open log file(fd=%d)\n", getpid(), fd); // write output to the given file descriptor

#ifdef APPLY_FD_CLOEXEC // clang -DAPPLY_FD_CLOEXEC fork_exec_parent.c -o fork_exec_parent
    int ret_fcntl;
    if ((ret_fcntl = fcntl(fd, F_SETFD, FD_CLOEXEC)) == -1) // file control
    {
        perror("FAIL: fcntl(F_SETFD, FD_CLOEXEC)");
        exit(EXIT_FAILURE);
    }
#endif
    /* fork-exec */
    char *argv_exec[] = {"fork_exec_child", (char *)NULL};
    switch ((pid_child = fork()))
    {
    case 0: /* child process */
        execv(argv_exec[0], argv_exec);
        break;
    case -1: /* error */
        perror("FAIL: FORK");
        break;
    default:              /* parent process */
        (void)wait(NULL); /* wait for child */
        break;
    }
    printf("Parent[%d]: Exit\n", getpid());
    return 0;
}
