import java.io.IOException;
import java.io.InputStreamReader;


// ERIC RODRIGUES DINIZ - 707760
// TP01Q15 - RECURSIVO - Is em Java

public class RECURSIVO_IsJava
{
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   // metodo para verificar se palavra se enquadra em VOGAL
   public static boolean isVogal ( String palavra, int j, boolean resp )
   {  
      // declarando variaveis
      int tam = palavra.length();
      // for para passar por cada letra da palavra e verificar                                    
      if(j < tam)
      {
         if(palavra.charAt(j) == 'A' || palavra.charAt(j) == 'E' || palavra.charAt(j) == 'I' || palavra.charAt(j) == 'O' || palavra.charAt(j) == 'U' || 
            palavra.charAt(j) == 'a' || palavra.charAt(j) == 'e' || palavra.charAt(j) == 'i' || palavra.charAt(j) == 'o' || palavra.charAt(j) == 'u')
         {
            resp = true; 
            if( j+1 < tam)
            // operacao logica booleana de AND ( && ) , com o AND tudo precisar ser TRUE, ou o resp vira false
               resp = resp && isVogal(palavra, j+1, resp);
         }
         else                    
            resp = false;    
      }
      return resp;
   }
   
   // metodo para verificar se palavra se enquadra em CONSOANTE
   public static boolean isConsoante ( String palavra, int j, boolean resp )
   {
      // declarando variaveis
      int tam = palavra.length();
      // for para passar por cada letra da palavra e verificar                                     
      if(j < tam) 
      {
         if(palavra.charAt(j) != 'A' && palavra.charAt(j) != 'E' && palavra.charAt(j) != 'I' && palavra.charAt(j) != 'O' && palavra.charAt(j) != 'U' && 
            palavra.charAt(j) != 'a' && palavra.charAt(j) != 'e' && palavra.charAt(j) != 'i' && palavra.charAt(j) != 'o' && palavra.charAt(j) != 'u' && 
           (palavra.charAt(j) >= 65 && palavra.charAt(j) <= 90 || palavra.charAt(j) >= 97 && palavra.charAt(j) <= 122 ))
         {
            resp = true; 
            if( j+1 < tam)
            // operacao logica booleana de AND ( && ) , com o AND tudo precisar ser TRUE, ou o resp vira false
               resp = resp && isConsoante(palavra, j+1, resp);
         }
         else                    
            resp = false;    
      }
      return resp;
   }
   
   // metodo para verificar se palavra se enquadra em INTEIRO 
   public static boolean isInteiro ( String palavra, int j, boolean resp )
   {
      // declarando variaveis
      int tam = palavra.length();
      // for para passar por cada letra da palavra e verificar                                                                   
      if(j < tam)
      {
         if(palavra.charAt(j) >= 48 && palavra.charAt(j) <= 57 )
         {
            resp = true; 
            if( j+1 < tam)
            // operacao logica booleana de AND ( && ) , com o AND tudo precisar ser TRUE, ou o resp vira false
               resp = resp && isInteiro(palavra, j+1, resp);
         }
         else                    
            resp = false;    
      }
      return resp;
   }
   
    // metodo para verificar se palavra se enquadra em REAL 
   public static boolean isReal ( String palavra, int j, boolean resp, int cont )
   {
      int tamanho = palavra.length();
      // for para passar por cada letra da palavra e verificar                      
      if(j < tamanho) 
      {
         if( palavra.charAt(j) >= 48 && palavra.charAt(j) <= 57 || palavra.charAt(j) == ',' || palavra.charAt(j) == '.')
         {
            if(palavra.charAt(j) == ',' || palavra.charAt(j) == '.')
               cont++;
            else
            {
               resp = true; 
               if( j+1 < tamanho)
                  // operacao logica booleana de AND ( && ) , com o AND tudo precisar ser TRUE, ou o resp vira false
                  resp = resp && isReal(palavra, j+1, resp, cont);
            }
         
         }         
      }
      // if para saber se numero contem . ou ,
      if ( cont > 1)
         resp = false;
      return resp;                            
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
         int k = 0, cont = 0;
         boolean resp = false;
         MyIO.print(isVogal(palavra[i], k, resp)? "SIM " : "NAO ");
         MyIO.print(isConsoante(palavra[i], k, resp)? "SIM " : "NAO ");
         MyIO.print(isInteiro(palavra[i], k, resp)? "SIM " : "NAO ");
         MyIO.println(isReal(palavra[i], k, resp, cont)? "SIM " : "NAO ");
      
      
         i++;
         palavra[i] = MyIO.readLine();                
      }
   }
}
