import java.net.*;
import java.io.*;
// ERIC RODRIGUES DINIZ - 707760
// TP01Q08 - Leitura de Pgina HTML em Java



// refazer o br e table no estilo do isFIM.


public class HTMLJAVAiso
{
   
   // metodo para verificar se palavra  igual a FIM
   public static boolean isFim(String s)
   {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }
   
   public static void ContadorGeral (String url, String Titulo) throws Exception
   {
      // declarando variaveis
         URL site = new URL(url);
         URLConnection web = site.openConnection();
         BufferedReader leitor = new BufferedReader(new InputStreamReader(web.getInputStream()));
         String palavra = "";
         while(leitor.readLine() != null)
         {
            palavra += leitor.readLine();
         }
         MyIO.println(palavra); 
      // normais
      int a = 0, e = 0, i = 0, o = 0, u = 0;
      //   (... _a) 
      int a_a = 0, e_a = 0, i_a = 0, o_a = 0, u_a = 0;
      
      // `   (... _o)
      int a_o = 0, e_o = 0, i_o = 0, o_o = 0, u_o = 0;
      
      // ~   (... _tio)
      int a_tio = 0, o_tio = 0;
      
      // vov   (... _v)
      int a_v = 0, e_v = 0, i_v = 0, o_v = 0, u_v = 0;
      
      // consoantes
      int cons = 0;
      
      // < br >
      int br = 0;
      
      // < table >
      int table = 0;
            
      for(int j = 0; j < palavra.length(); j++)
      {
         // normais
         if     (palavra.charAt(j) == 'a')
            a++;
         else if(palavra.charAt(j) == 'e')
            e++;
         else if(palavra.charAt(j) == 'i')
            i++;
         else if(palavra.charAt(j) == 'o')
            o++;
         else if(palavra.charAt(j) == 'u')
            u++;
            
         // �  (... _a) 
         else if(palavra.charAt(j) == '�')
            a_a++;
         else if(palavra.charAt(j) == '�')
            e_a++;
         else if(palavra.charAt(j) == '�')
            i_a++;
         else if(palavra.charAt(j) == '�')
            o_a++;
         else if(palavra.charAt(j) == '�')
            u_a++;
                        
         // `  (... _o) 
         else if(palavra.charAt(j) == '�')
            a_o++;
         else if(palavra.charAt(j) == '�')
            e_o++;
         else if(palavra.charAt(j) == '�')
            i_o++;
         else if(palavra.charAt(j) == '�')
            o_o++;
         else if(palavra.charAt(j) == '�')
            u_o++;
            
         // ~   (... _tio) 
         else if(palavra.charAt(j) == '�')
            a_tio++;
         else if(palavra.charAt(j) == '�')
            o_tio++;
            
         // vov�   (... _v) 
         else if(palavra.charAt(j) == '�')
            a_o++;
         else if(palavra.charAt(j) == '�')
            e_o++;
         else if(palavra.charAt(j) == '�')
            i_o++;
         else if(palavra.charAt(j) == '�')
            o_o++;
         else if(palavra.charAt(j) == '�')
            u_o++;
          
         // consoantes 
         else if(palavra.charAt(j) >= 97 && 122 <= palavra.charAt(j)) 
            if(palavra.charAt(j) != 'a' && palavra.charAt(j) != 'e' && palavra.charAt(j) != 'i' && palavra.charAt(j) != 'o' && palavra.charAt(j) != 'u')
               cons++;    
      }// fim do for
      
      
      
      // normais      
      MyIO.print("a"+ "("+a+ ") " );
      MyIO.print("e"+ "("+e+ ") " );
      MyIO.print("i"+ "("+i+ ") " );
      MyIO.print("o"+ "("+o+ ") " );
      MyIO.print("u"+ "("+u+ ") " );
      
      // �  (... _a)    
      MyIO.print("�"+ "("+a_a+ ") " );
      MyIO.print("�"+ "("+e_a+ ") " );
      MyIO.print("�"+ "("+i_a+ ") " );
      MyIO.print("�"+ "("+o_a+ ") " );
      MyIO.print("�"+ "("+u_a+ ") " );
      
      // outros   (... _o)   
      MyIO.print("�"+ "("+a_o+ ") " );
      MyIO.print("�"+ "("+e_o+ ") " );
      MyIO.print("�"+ "("+i_o+ ") " );
      MyIO.print("�"+ "("+o_o+ ") " );
      MyIO.print("�"+ "("+u_o+ ") " );
      
      // ~   (... _tio)
      MyIO.print("�"+ "("+a_tio+ ") " );
      MyIO.print("�"+ "("+a_tio+ ") " );
      
      // vov�   (... _v)   
      MyIO.print("�"+ "("+a_v+ ") " );
      MyIO.print("�"+ "("+e_v+ ") " );
      MyIO.print("�"+ "("+i_v+ ") " );
      MyIO.print("�"+ "("+o_v+ ") " );
      MyIO.print("�"+ "("+u_v+ ") " );
      
      // consoantes
      MyIO.print("consoante"+ "("+cons+ ") " );
      
      // < br >
      MyIO.print("<br>"+ "("+br+ ") " );
      
      // < table >
      MyIO.print("<table>"+ "("+table+ ") " );
      
      // printando a palavra lida    
      MyIO.println(Titulo);

           
   }
   
   public static void main (String[]args) throws Exception
   {
      // declarando variaveis
      int i = 0;
      String[] titulo = new String[1000];
      String[] site = new String[1000];
      // ler titulo
      titulo[i] = MyIO.readLine();
      while(isFim(titulo[i]) != true)
      {
         // lendo dentro do site
         site[i] = MyIO.readLine();
         ContadorGeral(site[i], titulo[i]); 
      }
      // somar no contador + ler proxima palavra
      i++;
      site[i] = MyIO.readLine();
   }
}