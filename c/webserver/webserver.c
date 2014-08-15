#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <unistd.h>/*for close command*/
#include <signal.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

#define BUFSIZE 8096

void childProcess(int cd);

struct{
	char *ext;
    char *fType;
} 
extensions [] = {
    {"gif", "image/gif" },
    {"htm", "text/html" },  
    {"html","text/html" },   
    {"jpg", "image/jpeg"}, 
    {"jpeg","image/jpeg"},
    {"png", "image/png" }, 
    {0,0} 
};
 

main() {
  int listensd, pid, port;
  
  /*establish a socket*/
  listensd = socket(AF_INET, SOCK_STREAM,0);
  
  /*bind that socket to a port*/
  struct sockaddr_in serv_addr;
  port = 58848;
  
  /*set the socket to listen*/
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(port);
  bind(listensd, (struct sockaddr *)&serv_addr,sizeof(serv_addr));
  listen(listensd,64);

  /*infinite loop*/
  /*waits for new connection*/
  while(1) {
    int connectionsd;
    socklen_t length;
    struct sockaddr_in client_addr;
    connectionsd = accept(listensd, (struct sockaddr *)&client_addr, &length);
    
    /*the next two allow spawned children to die*/
    /*without the need of a join on the parents part*/
    /*
    (void)signal(SIGCLD,SIG_IGN);
    (void)signal(SIGHUP,SIG_IGN);*/
    
    pid = fork();
    
    if(pid < 0) {
      printf("Error, there was a problem.");
    }
    else if(pid == 0) {
      close(listensd);
      childProcess(connectionsd);
      exit(0);
    }
    else {
      close(connectionsd);
    }
  }
}

void childProcess(int cd) {
  char * fstr;
  char buffer[BUFSIZE+1];
  int i, l, bLen, file_fd;
  /*read in the message*/ 
  int ret = read(cd,buffer,BUFSIZE);
  
  if(ret==0) {
    printf("Browser load failed.");
  }
  
  /*check to see that it is a GET message*/
  if (strncmp(buffer, "GET ", 4) < 0) {
    /*if it is not, report an error and die*/
    printf("Invalid request.");
    exit(EXIT_FAILURE);
  }
  else {
    for(i=4; i<BUFSIZE; i++) {
      if(buffer[i] == ' ') {
        buffer[i] = 0;
        break;
      }
    }
    
    bLen = strlen(buffer);
    fstr = (char *)0;
    
    /*figure out what kind of file it is (html, gif, jpg, etc.)*/
    for(i=0;extensions[i].ext != 0;i++) {
		l = strlen(extensions[i].ext);
		if(!strncmp(&buffer[bLen-l], extensions[i].ext, l)) {
			fstr =extensions[i].fType;
			break;
		}
	}
	
	if((file_fd = open(&buffer[5], O_RDONLY)) == -1) {
	  printf("Failed to open file.");
    }
    
    /*Compose a "send" message that includes the type of file you will be sending*/
    (void)sprintf(buffer, "HTTP/1.0 200 OK\r\nContent-Type: %s\r\n\r\n", fstr);
	
	/*send that message over the port*/
	(void)write(cd, buffer, strlen(buffer));
    
    /*next, send the actual file*/
    while((ret = read(file_fd, buffer, BUFSIZE)) > 0) {
		(void)write(cd,buffer,ret);
	}
	
  }
  
  /*die*/
  close(cd);
  exit(1);
}
