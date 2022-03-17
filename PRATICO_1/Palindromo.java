import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
// ERIC RODRIGUES DINIZ - 707760
// TP01Q01 - Palindromo em java


public class Palindromo
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static void main (String[]args)
   {
      int i = 0;
      String[] palavra = new String[1000];
      palavra[i] = MyIO.readLine();
      while (isFim (palavra[i]) != true) // enquanto palavra diferente de fim, realizar os comandos dentro
      { 
         if(isFim (palavra[i]) != true)
         { // comparando a palavra com FIM
            // declarando variáveis
            int tamanho = 0;
            int frente = 0;
            int costas = 0;
            // pegando o tamanho da palavra
            tamanho = palavra[i].length();
            for (frente = 0, costas = (tamanho - 1); frente < tamanho && costas >= 0;)
            {
               if (palavra[i].charAt (frente) != palavra[i].charAt (costas)) // comparando posicao por posicao
                  frente = tamanho; // se valor das posicoes diferentes, encerrar comando
               else // se nao, continuar comando
               {
                  frente++;
                  costas--;
               }
            }
            // imprimir se palavra é um palindromo
            if (costas == -1)
               MyIO.println("SIM");
                 
            else
               MyIO.println("NAO");
         }
         i++;
         palavra[i] = MyIO.readLine(); // lendo a palavra novamente
      }
   }
}