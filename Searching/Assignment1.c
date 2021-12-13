#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

int filter(char arr[]){
    int i = -1;
    while (arr[++i]!= '\0')
        if(!isalpha(arr[i])!=0)
            arr[i] = ' ';
    return 1;
}

void main(int argc, char *argv[]){

    char *str;
    gets(str);
    filter(str);
    printf("%s",str); 
}

