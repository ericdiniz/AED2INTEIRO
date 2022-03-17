#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#define TAMPALAVRA  10000

// ERIC RODRIGUES DINIZ - 707760
// TP01Q10 - Arquivo em C
    
int main()
{
   FILE *arq = fopen("arquivo.dat", "wb"); // para binario é dat, para texto é txt
   int n = 0;
   float x = 0;
   // char 1 byte // int 4 bytes // double 8 bytes
   scanf("%d", &n); // numero de linhas do arquivo 
   for( int i = 0; i < n; i++ )
   {
      scanf("%f", &x); // le os numeros
      fflush(stdin);
      fwrite( &x, sizeof(float), 1, arq); // binario fprintf
   }
   fclose(arq);
   // começa tudo de novo
   // abre o arq, le as linhas, e mostra ao contrario
   arq = fopen("arquivo.dat", "rb"); // para binario é dat, para texto é txt
   fseek(arq, -4, SEEK_END); // fseek primeira vez pra chegar no fim
   fread( &x, sizeof(float), 1, arq); // le os numeros
   
   if(x - (int)x == 0)
   {
      printf("%.0f\n", x);
   }
   if(x - (int)x != 0)
   {
      x -= 0;
      printf("%g\n", x);   // maxizimar o .000
   }
   for( int i = 0; i < n - 1; i++ )
   {
      fseek(arq, -8, SEEK_CUR); // fseek posiciona o cursor 8 posicoes atras pra ler dnv
      fread( &x, sizeof(float), 1, arq); // fscanf em modo binario
      fflush(stdin);
      if(x - (int)x == 0)
      {
         printf("%.0f\n", x);
      }
      if(x - (int)x != 0)
      {
         
         printf("%g\n", x);   // maxizimar o .000
      }
   }
   fclose(arq); 
   return 0; 
}