import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

// ERIC RODRIGUES DINIZ - 707760
// TP01Q04 - Altera��o Aleat�ria em Java

public class AlteracaoAleatoria
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   public static void main (String[]args)
   {
      int i = 0;
      String[] palavra = new String[1000];
      //ler entrada
      palavra[i] = MyIO.readLine();           
      // gerador de letras aleatorias
      Random gerador = new Random( ) ;                                                                       
      gerador.setSeed ( 4 ) ;
      //enquanto palavra nao for igual a FIM, entra no la�o e realiza os comandos
      while (isFim (palavra[i]) != true)
      {
         // caracteres armazenantes de valores aleatorios dentre o alfabeto                                                                    
         char LetraEscolhida = ( ( char ) ( 'a' + (Math.abs ( gerador.nextInt ( ) ) % 26 ) ) ) ;                  
         char LetraSubstitutiva = ( ( char ) ( 'a' + (Math.abs ( gerador.nextInt ( ) ) % 26 ) ) ) ;
         
         // palavra recebendo a troca das letras
         String array = "";
         for(int j = 0; j < palavra[i].length(); j++)
            if(palavra[i].charAt(j) == LetraEscolhida)
               array += LetraSubstitutiva;
               else array += palavra[i].charAt(j);      
         MyIO.println("" + array);
         // somar no contador + ler proxima palavra
         i++;
         palavra[i] = MyIO.readLine();   
      }
   }
}