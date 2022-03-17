import java.net.*;
import java.io.*;
// ERIC RODRIGUES DINIZ - 707760
// TP01Q08 - Leitura de P�gina HTML em Java

public class HTMLjava {

   // metodo para verificar se palavra � igual a FIM
   public static boolean isFim(String s) {
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static void ContadorGeral(String url, String Titulo) throws IOException {
      // declarando variaveis
      URL site = new URL(url);
      InputStream web = site.openStream();
      BufferedReader leitor = new BufferedReader(new InputStreamReader(web));
      String palavra = "";
      String line = "";
      while ((line = leitor.readLine()) != null) {
         palavra += line + "\n";
      }
      // normais
      int a = 0, e = 0, i = 0, o = 0, u = 0;
      // � (... _a)
      int a_a = 0, e_a = 0, i_a = 0, o_a = 0, u_a = 0;

      // ` (... _o)
      int a_o = 0, e_o = 0, i_o = 0, o_o = 0, u_o = 0;

      // ~ (... _tio)
      int a_tio = 0, o_tio = 0;

      // vov� (... _v)
      int a_v = 0, e_v = 0, i_v = 0, o_v = 0, u_v = 0;

      // consoantes
      int cons = 0;

      // < br >
      int b = 0;

      // < table >
      int t = 0;

      for (int j = 0; j < palavra.length(); j++) {
         // normais
         if (palavra.charAt(j) == 'a')
            a++;
         else if (palavra.charAt(j) == 'e')
            e++;
         else if (palavra.charAt(j) == 'i')
            i++;
         else if (palavra.charAt(j) == 'o')
            o++;
         else if (palavra.charAt(j) == 'u')
            u++;

         // á (... _a)
         if ((int) palavra.charAt(j) == 225)
            a_a++;
         else if ((int) palavra.charAt(j) == 233)
            e_a++;
         else if ((int) palavra.charAt(j) == 237)
            i_a++;
         else if ((int) palavra.charAt(j) == 243)
            o_a++;
         else if ((int) palavra.charAt(j) == 250)
            u_a++;

         // ` (... _o)
         if ((int) palavra.charAt(j) == 224)
            a_o++;
         else if ((int) palavra.charAt(j) == 232)
            e_o++;
         else if ((int) palavra.charAt(j) == 236)
            i_o++;
         else if ((int) palavra.charAt(j) == 242)
            o_o++;
         else if ((int) palavra.charAt(j) == 249)
            u_o++;

         // ~ (... _tio)
         if ((int) palavra.charAt(j) == 227)
            a_tio++;
         else if ((int) palavra.charAt(j) == 245)
            o_tio++;

         // vov� (... _v)
         if ((int) palavra.charAt(j) == 226) {
            a_v++;
         } else if ((int) palavra.charAt(j) == 234)
            e_v++;
         else if ((int) palavra.charAt(j) == 238)
            i_v++;
         else if ((int) palavra.charAt(j) == 244)
            o_v++;
         else if ((int) palavra.charAt(j) == 251)
            u_v++;
         // consoantes
         if ((int) palavra.charAt(j) >= 97 && (int) palavra.charAt(j) <= 122) {
            if (palavra.charAt(j) != 'a' && palavra.charAt(j) != 'e' && palavra.charAt(j) != 'i'
                  && palavra.charAt(j) != 'o' && palavra.charAt(j) != 'u' && palavra.charAt(j) != 'A'
                  && palavra.charAt(j) != 'E' && palavra.charAt(j) != 'I' && palavra.charAt(j) != 'O'
                  && palavra.charAt(j) != 'U') {
               cons++;
            }
         }
         // table
         if (palavra.charAt(j) == '<' && palavra.charAt(j + 1) == 't' && palavra.charAt(j + 2) == 'a'
               && palavra.charAt(j + 3) == 'b' && palavra.charAt(j + 6) == '>') {
            t++;
            j += 6;
            // br
         } else if (palavra.charAt(j) == '<' && palavra.charAt(j + 1) == 'b' && palavra.charAt(j + 2) == 'r'
               && palavra.charAt(j + 3) == '>') {
            b++;
            j += 3;
         }
      } // fim do for

      // normais
      MyIO.print("a" + "(" + a + ") ");
      MyIO.print("e" + "(" + e + ") ");
      MyIO.print("i" + "(" + i + ") ");
      MyIO.print("o" + "(" + o + ") ");
      MyIO.print("u" + "(" + u + ") ");

      // � (... _a)
      MyIO.print("á" + "(" + a_a + ") ");
      MyIO.print("é" + "(" + e_a + ") ");
      MyIO.print("í" + "(" + i_a + ") ");
      MyIO.print("ó" + "(" + o_a + ") ");
      MyIO.print("ú" + "(" + u_a + ") ");

      // outros (... _o)
      MyIO.print("à" + "(" + a_o + ") ");
      MyIO.print("è" + "(" + e_o + ") ");
      MyIO.print("ì" + "(" + i_o + ") ");
      MyIO.print("ò" + "(" + o_o + ") ");
      MyIO.print("ù" + "(" + u_o + ") ");

      // ~ (... _tio)
      MyIO.print("ã" + "(" + a_tio + ") ");
      MyIO.print("õ" + "(" + o_tio + ") ");

      // vov� (... _v)
      MyIO.print("â" + "(" + a_v + ") ");
      MyIO.print("ê" + "(" + e_v + ") ");
      MyIO.print("î" + "(" + i_v + ") ");
      MyIO.print("ô" + "(" + o_v + ") ");
      MyIO.print("û" + "(" + u_v + ") ");

      // consoantes
      MyIO.print("consoante" + "(" + cons + ") ");

      // < br >
      MyIO.print("<br>" + "(" + b + ") ");

      // < table >
      MyIO.print("<table>" + "(" + t + ") ");

      // printando a palavra lida
      MyIO.println(Titulo);

   }

   public static void main(String[] args) throws Exception {
      // declarando variaveis
      int i = 0;
      String[] titulo = new String[1000];
      String[] site = new String[1000];
      // ler titulo
      titulo[i] = MyIO.readLine();
      while (isFim(titulo[i]) != true) {
         // lendo url do site
         site[i] = MyIO.readLine();
         // levando pro metodo
         ContadorGeral(site[i], titulo[i]);
         // contador para proxima linha
         i++;
         // lendo novamente o titulo
         titulo[i] = MyIO.readLine();
      }
   }
}