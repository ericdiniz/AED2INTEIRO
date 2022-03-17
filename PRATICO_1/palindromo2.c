#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#define TAMPALAVRA  10000
// ERIC RODRIGUES DINIZ - 707760
// TP01Q02 - Palíndromo em C


//metodo para limpar scanf
void clean_stdin(void)
{
    int c;
    do {
        c = getchar();
    } while (c != '\n' && c != EOF);
}

int main(){
   //declarando variaveis
   char palavra[TAMPALAVRA];
   int numEntrada = 0;
   int tamanho = 0;
   
   // enquanto palavra for diferente de fim, realizar a comparação entre o valor das posicoes
   while( strcmp("FIM", palavra) != 0 )
   {
      // lendo a palavra para comparacao
      scanf("%[^\n]", palavra);
      clean_stdin();
      // verificando se palavra igual a FIM
      if(strcmp("FIM", palavra) != 0 ){
      //declarando variaveis de escopo
        int frente = 0;
        int costas = 0;
        bool aux = 1;
        // pegando o tamanho de palavra
        tamanho = strlen(palavra);
        for(frente = 0, costas = (tamanho - 1); frente < tamanho && costas >= 0; )
        {   
           // se valor da posicao frente diferente de valor da posicao costas, realizar um break.
           if(palavra[frente] != palavra[costas])
              frente = tamanho;
           // se nao, continuar o programa, verificando o valor das posicoes
           else
           {
              frente++;
              costas--;
           }
        }
        // imprimir se a palavra é um palindromo
        printf("%s\n", -1 == costas ? "SIM" : "NAO");
      }
   }
}




