import java.io.IOException;
import java.io.InputStreamReader;


// ERIC RODRIGUES DINIZ - 707760
// TP01Q03 - Ciframento em Java

public class CiframentoCesar
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   public static void main (String[]args)
   {
      //declarando variaveis
      int i = 0;
      String aux;
      String[] palavra = new String[1000];
      
      //ler entrada pela primeira vez
      palavra[i] = MyIO.readLine();
      
      //enquanto palavra diferente de fim, realizar o ciframento
      while (isFim (palavra[i]) != true)   
      {   
         // comparador da palavra com FIM
         if(isFim (palavra[i]) != true)
         {
            //receber tamanho da palavra
            int tamanho = palavra[i].length();
            aux = "";
            
            //for para realizar a troca da letra ( k = 3)
            for( int j = 0; j < tamanho; j++ )   
            {
               char c;
               c = (char)(palavra[i].charAt(j) + 3);                    
               aux += c;               
            }
            
            //imprimindo o resultado encontrado
            MyIO.println("" + aux);
            
            // soma de contador e lendo a proxima linha
            i++;
            palavra[i] = MyIO.readLine();                
         }
      }
   }
}