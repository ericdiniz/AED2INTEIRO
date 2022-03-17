#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#define TAMPALAVRA  10000

 
// ERIC RODRIGUES DINIZ - 707760
// TP01Q07 - Is em C
// funcao que limpa o buffer
void clean_stdin(void)
{
   int c;
   do {
      c = getchar();
   } while (c != '\n' && c != EOF);
}

 

// funcao para ve se a letra é uma VOGAL
bool vogal (char *palavra)
{
   //declarar variaveis
   bool resp = false;
   int t = strlen(palavra);
   //for para ler cada posicao da palavra
   //printf("t: %d \n palavra: %s\n", t, palavra);
   for ( int i = 0; i < t; i++)
   {
      //printf("entrei:%d\n", i);
      
      //printf("palavra: %c\n", palavra[i]);
      if (palavra[i] == 'a' || palavra[i] == 'e' || palavra[i] == 'i' || palavra[i] == 'o' || palavra[i] == 'u'|| 
          palavra[i] == 'A' || palavra[i] == 'E' || palavra[i] == 'I' || palavra[i] == 'O' || palavra[i] == 'U' )
      {
         resp = true;
         //printf("entrei ( if ) : %d\n", i);  
      }
      else
      {
         resp = false;
         i = t;
      }
   }
   return resp;
}

 

// funcao para ve se a letra é uma CONSOANTE
bool consoante (char *palavra)
{
  // declarando variaveis
   bool resp = false;
   int t = strlen(palavra);
   
   // for para passar por cada letra da palavra e verificar                                     

      for (int i = 0; i < t; i++) 
      {
            if((palavra[i] != 'a' && palavra[i] != 'e' && palavra[i] != 'i' && palavra[i] != 'o' && palavra[i] != 'u' && palavra[i] != 'A' && palavra[i] != 'E' && palavra[i] != 'I' && palavra[i] != 'U') && ((palavra[i] >= 65 && palavra[i] <= 90) || (palavra[i] >= 97 && palavra[i] <= 122)))
            {  
                resp = true;
            }
            else
            {
            resp = false;
            i = t;
            }         
      }      
   return resp;
}

 

// funcao para ve se a letra é um INTEIRO
bool numeroInteiro (char *palavra)
{
   bool resp = true;
   int t = strlen(palavra);
   //printf("t: %d", t);
   // passar por cada posicao da palavra
   for (int i = 0; i < t; i++)
   {
      //printf("palavra[i]: %c\n", palavra[i]);
      if('0' <= palavra[i] && palavra[i] <= '9' )
      {   
          resp = true;
      }
      else
      {
          resp = false;
          i = t;
      }
   }
   return resp;
}

 


// funcao para ve se eh um numero real
bool numeroReal (char *palavra)
{
   // declarar variaveis
   bool resp = false;
   int contador = 0;
   int t = sizeof(palavra);
   // ler cada posicao da palavra
   for (int i = 0; i < t; i++)
   {
      // verificar se numero é real ou nao
      if(palavra[i] >= 48 && palavra[i] <= 57 || palavra[i] == ',' || palavra[i] == '.' ) 
      {
         //verificar se contem , ou .
         if(palavra[i] == ',' || palavra[i] == '.')
            contador++; //contador de virgula
         else
         {
            resp = true;
            i = t;
         } 
      
      }    
   }
   if ( contador > 1)//se tiver + de 1 virgula
      resp = false; //retorna falso para real
   return resp;
}
int main()
{
   char *palavra = (char*)malloc(TAMPALAVRA * sizeof(char));
   fgets(palavra, TAMPALAVRA, stdin);
   // verificar se palavra é igual a FIM
   while( strcmp("FIM\n", palavra) != 0 )
   {
      //limpando o buffer do scanf
      palavra[strlen(palavra) -1] = '\0';
      //printf("palavra: %s, tamanho: %d\n", palavra, strlen(palavra));
      // VOGAL
      if(vogal(palavra))
         printf("SIM ");
      else
         printf("NAO ");
         
         // CONSOANTE
      if(consoante(palavra))
         printf("SIM ");
      else
         printf("NAO ");
         
         // INTEIRO
      if(numeroInteiro(palavra))
         printf("SIM ");
      else
         printf("NAO ");
         
         // REAL
      if(numeroReal(palavra))
         printf("SIM\n");
      else
         printf("NAO\n");
      fgets(palavra, TAMPALAVRA, stdin);
   }
}