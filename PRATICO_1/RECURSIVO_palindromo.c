#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#define TAMPALAVRA  10000

// ERIC RODRIGUES DINIZ - 707760
// TP01Q12 - RECURSIVO - Palíndromo em C

//metodo para limpar scanf
void clean_stdin(void)
{
   int c;
   do c = getchar();
   while (c != '\n' && c != EOF);
}

// metodo onde compara cada posição
int inverte (char *n, int y, int aux) 
{
   // condição de parada do metodo recursivo
   if (y <= aux) 
      return 1;
   else {
      if (n[y - 1] != n[aux]) 
         return 0;
      return inverte(n, y-1, aux+1);
   } 
}
// metodo recursivo que verifica se palavra é palindromo
void palindromo (char *n) 
{
   // declarando variaveis
   int aux1, x = 0;
   // aux recebendo palindromo de inverte
   aux1 = inverte(n, strlen(n), x);
   // verificando se aux é 1 ou 0 (SIM OU NAO) 
   if 
   (aux1 == 1) printf("SIM\n");
   else 
      printf("NAO\n");
}

int main()
{
   //declarando variaveis
   char palavra[TAMPALAVRA];
   int numEntrada = 0;
   
   // enquanto palavra for diferente de fim, realizar a comparação entre o valor das posicoes
   while( strcmp("FIM", palavra) != 0 )
   {
      // lendo a palavra para comparacao
      scanf("%[^\n]", palavra);
      clean_stdin();
      
      // chamando metodo recursivo para verificar se é palindromo
      palindromo(palavra);
   }
}