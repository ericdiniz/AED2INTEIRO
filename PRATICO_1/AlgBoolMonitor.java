import java.io.IOException;
import java.io.InputStreamReader;

// ERIC RODRIGUES DINIZ - 707760
// TP01Q05 - Algebra booleana em Java
class Posicao {
    public int pos;

    Posicao(int pos) {
        this.pos = pos;
    }
}

public class AlgBoolMonitor {
    public static boolean zero(String s) {
        return (s.length() == 1 && s.charAt(0) == '0');
    }

    public static int LerExpressao(String expressao, int numero[], Posicao pos) {
        int result = 0, aux = 0;
        char character = expressao.charAt(pos.pos++);
        if (character == 'A') {
            result = numero[0];

        } else if (character == 'B') {
            result = numero[1];
        }

        else if (character == 'C') {
            result = numero[2];

        } else if (character == 'n') {
            pos.pos += 3;
            if (LerExpressao(expressao, numero, pos) == 0)
                result = 1;
            else
                result = 0;
            pos.pos++;
        } else if (character == 'a') {
            pos.pos += 3;
            result = LerExpressao(expressao, numero, pos);
            while (expressao.charAt(pos.pos) == ',') {
                pos.pos++;
                aux = LerExpressao(expressao, numero, pos);
                if (result == 1 && aux == 1)
                    result = 1;
                else
                    result = 0;
            }
            pos.pos++;
        } else if (character == 'o') {
            pos.pos += 2;
            result = LerExpressao(expressao, numero, pos);
            while (expressao.charAt(pos.pos) == ',') {
                pos.pos++;
                aux = LerExpressao(expressao, numero, pos);
                if (result == 1 || aux == 1)
                    result = 1;
                else
                    result = 0;
            }
            pos.pos++;
        }
        return result;
    }

    public static void main(String[] args) {
        String entrada = new String();
        entrada = MyIO.readLine();
        while (zero(entrada) != true) {
            char n = entrada.charAt(0);
            char[] numeros = new char[n];
            // n estava com valor do asc2
            int nMudado = ((int) n - 48);
            int j = 2;
            for (int i = 0; i < nMudado; i++) {
                numeros[i] = entrada.charAt(j);
                j += 2;
            }
            String expressao = new String();
            expressao = entrada.substring(j);
            expressao = expressao.replace(" ", "");
            int[] numerosMudado = new int[nMudado];
            for (int i = 0; i < nMudado; i++) {
                numerosMudado[i] = numeros[i] - 48;
            }
            Posicao pos = new Posicao(0);
            int result = LerExpressao(expressao, numerosMudado, pos);
            MyIO.println(result);
            entrada = MyIO.readLine();
        }

    }
}
