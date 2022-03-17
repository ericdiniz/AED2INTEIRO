import java.io.IOException;
import java.io.InputStreamReader;

// ERIC RODRIGUES DINIZ - 707760
// TP01Q06 - Is em Java
public class IsEmJava
{

   // função na qual detecta se palavra é igual a FIM
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   // metodo para verificar se palavra se enquadra em VOGAL
   public static boolean isVogal ( String palavra  )
   {
      // declarando variaveis
      boolean resp = false;
      int tamanho = palavra.length();
      
      // for para passar por cada letra da palavra e verificar                                    
      for( int j = 0; j < tamanho; j++)
      {
         if(palavra.charAt(j) == 'A' || palavra.charAt(j) == 'E' || palavra.charAt(j) == 'I' || palavra.charAt(j) == 'O' || palavra.charAt(j) == 'U' || 
            palavra.charAt(j) == 'a' || palavra.charAt(j) == 'e' || palavra.charAt(j) == 'i' || palavra.charAt(j) == 'o' || palavra.charAt(j) == 'u')
            resp = true;
         else                    
            return false;    
      }
      return resp;
   }
   
   // metodo para verificar se palavra se enquadra em CONSOANTE
   public static boolean isConsoante ( String palavra  )
   {
      // declarando variaveis
      boolean resp = false;
      int tamanho = palavra.length();
      // for para passar por cada letra da palavra e verificar                                     
      for( int j = 0; j < tamanho; j++) 
      {
         if(palavra.charAt(j) != 'A' && palavra.charAt(j) != 'E' && palavra.charAt(j) != 'I' && palavra.charAt(j) != 'O' && palavra.charAt(j) != 'U' && 
            palavra.charAt(j) != 'a' && palavra.charAt(j) != 'e' && palavra.charAt(j) != 'i' && palavra.charAt(j) != 'o' && palavra.charAt(j) != 'u' && 
           (palavra.charAt(j) >= 65 && palavra.charAt(j) <= 90 || palavra.charAt(j) >= 97 && palavra.charAt(j) <= 122 ))
            resp = true;
         else
            return false;         
      }      
      return resp;
   }
   
   // metodo para verificar se palavra se enquadra em INTEIRO 
   public static boolean isInteiro ( String palavra  )
   {
      // declarando variaveis
      boolean resp = false;
      int tamanho = palavra.length();
      // for para passar por cada letra da palavra e verificar                                                                   
      for( int j = 0; j < tamanho; j++)
      {
         if(palavra.charAt(j) >= 48 && palavra.charAt(j) <= 57 )
         {
            resp = true;
         } 
         else
            return false;      
      }                                             
      return resp; 
   }
   
   // metodo para verificar se palavra se enquadra em REAL 
   public static boolean isReal ( String palavra )
   {
      boolean resp = false;
      int contador = 0;
      int tamanho = palavra.length();
      // for para passar por cada letra da palavra e verificar                      
      for( int j = 0; j < tamanho; j++) 
      {
         if( palavra.charAt(j) >= 48 && palavra.charAt(j) <= 57 || palavra.charAt(j) == ',' || palavra.charAt(j) == '.')
         {
            if(palavra.charAt(j) == ',' || palavra.charAt(j) == '.')
               contador++;
            else
               resp = true;
         }         
      }
      // if para saber se numero contem . ou ,
      if ( contador > 1)
         resp = false;
      return resp;                            
   }
   
   public static void main (String[]args)
   {
      // declarando variaveis
      int i = 0;
      String[] palavra = new String[1000];
      palavra[i] = MyIO.readLine();
      // verificar se palavra nao é FIM
      while ( isFim( palavra[i] ) != true )
      {
         // metodo para VOGAL
         if( isVogal ( palavra[i] ))
            MyIO.print("SIM ");
         else
            MyIO.print("NAO ");
         
         // metodo para CONSOANTE
         if(isConsoante (palavra[i]))
            MyIO.print("SIM ");
         else
            MyIO.print("NAO ");
         
         // metodo para INTEIRO
         if(isInteiro (palavra[i]))
            MyIO.print("SIM ");
         else
            MyIO.print("NAO ");
         // metodo para REAL
         if(isReal (palavra[i]))
            MyIO.println("SIM");
         else
            MyIO.println("NAO");
      
         // somando 1 ao contador + lendo a palavra novamente                  
         i++;
         palavra[i] = MyIO.readLine();
      }    
   }
}