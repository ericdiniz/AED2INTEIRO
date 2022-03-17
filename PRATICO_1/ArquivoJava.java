import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;

// ERIC RODRIGUES DINIZ - 707760
// TP01Q09 - Arquivo em Java

public class ArquivoJava
{
   public static void main(String[] args) throws Exception
   {  
      //declarando variaveis
      int n = 0;
      float x = 0;
      RandomAccessFile file = new RandomAccessFile("arquivo.dat", "rw");
      //pegando numero de n
      n = MyIO.readInt();
      for(int i = 0; i < n; i++)
      {
         x = MyIO.readFloat();
         file.writeFloat(x); //lendo do teclado e inserindo no arquivo
      }
      file.close();
      // comeca tudo de novo (agora lendo o arquivo)
      file = new RandomAccessFile("arquivo.dat", "r");      
      for( int i = 4; i <= n*4; i=i+4 )
      {      
         // setando a posicao do cursor para ler o numero
         file.seek( ( n * 4 ) - i );
         // lendo do arquivo e printando
         x = file.readFloat();
         if( x % 1 != 0) // 
            System.out.println(x);
         else
            System.out.println((int)x);    
      }
      // fechando o arquivo
      file.close();
   }
}