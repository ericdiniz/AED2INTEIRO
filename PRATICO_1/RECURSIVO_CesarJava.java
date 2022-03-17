import java.io.IOException;
import java.io.InputStreamReader;


// ERIC RODRIGUES DINIZ - 707760
// TP01Q13 - RECURSIVO - Ciframento em Java

public class RECURSIVO_CesarJava
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   public static String cesar(String p, int i)
   {
      String aux = "";
      if(i < p.length())
      {
         // realizando a troca da letra ( k = 3)
         char c;
         c = (char)(p.charAt(i) + 3);                    
         aux = "" + c + cesar(p, i+1);                
      }
      //imprimindo o resultado encontrado
      return aux;
   }
   
   public static void main (String[]args)
   {
      // declarando variaveis
      String[] palavra = new String[1000];
      int i =0;
      //ler entrada pela primeira vez
      palavra[i] = MyIO.readLine();
      
      //enquanto palavra diferente de fim, realizar o ciframento
      while (isFim (palavra[i]) != true)   
      {   
         int j = 0;
         MyIO.println(cesar(palavra[i], j));
         i++;
         palavra[i] = MyIO.readLine();                
      }
   }
}
