import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
// ERIC RODRIGUES DINIZ - 707760
// TP01Q11 - RECURSIVO - Palíndromo em Java


public class RECURSIVO_palindromoJAVA
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   // metodo onde compara cada posição
   public static int inverte (String n, int y, int aux) 
   {
   // condição de parada do metodo recursivo
      if (y <= aux) 
         return 1;
      else {
         if (n.charAt(y - 1) != n.charAt(aux)) 
            return 0;
         return inverte(n, y-1, aux+1);
      } 
   }
// metodo recursivo que verifica se palavra é palindromo
    public static void  palindromo (String n) 
   {
   // declarando variaveis
      int aux1, x = 0;
   // aux recebendo palindromo de inverte
      aux1 = inverte(n, n.length(), x);
   // verificando se aux é 1 ou 0 (SIM OU NAO) 
      if 
      (aux1 == 1)  MyIO.println("SIM");
      else         MyIO.println("NAO");
   }

   public static void main (String[]args)
   {
      // declarando variaveis
      int i = 0;
      String[] palavra = new String[1000];
      
      // lendo a palavra
      palavra[i] = MyIO.readLine();
      while (isFim (palavra[i]) != true) // enquanto palavra diferente de fim, realizar os comandos dentro
      { 
         // chamando metodo recursivo para verificar se é palindromo
         palindromo(palavra[i]);
         
         // somando 1 ao contador para mudar de palavra
         i++;
         // lendo palavra novamente
         palavra[i] = MyIO.readLine();
      }
   }
}