#include <stdlib.h>
#include <stdio.h>

int iterative(char strg[], int lo){
  /* ######## TRy one
   int x= 0;
    char* sentc;
    printf("\n her we go The number x = %d, the address is %d, and c is %c",x,sentc,*sentc);
    printf("Enter a few chars to be printed in reverse order : ");
    while(getchar()!='\n'){
        *sentc = getchar();
        sentc++;
        x++;
    }
    printf("\n her we go The number x = %d, the address is %d, and c is %c",x,sentc,*sentc);
    while(x=0){
        printf("\n The number x = %d, the address is %d, and c is %c",x,sentc,*sentc);
        sentc--;
        x--;
    }
*/  
   {
    while(lo > 0){
        printf("%c",strg[--lo]);    
    }
    printf("\n");
    return 0;
   }
}


int recursive(char str[], int num){
   
    if (num == 0){return 0;}
    printf("%c",str[--num]);
    recursive(str, num);
    return 0;
}


void main(){
    char c[100];
    int x= 0;
    printf("Enter a few chars to be printed in reverse order : ");
    while( (c[x++] = getchar()) != '\n' && x<100);
    c[x]='\0';
    iterative(c,x);
    recursive(c,x);   
}

