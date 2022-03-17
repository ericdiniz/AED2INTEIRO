import java.io.*;

// ERIC RODRIGUES DINIZ - 707760
class Serie {
    private String nome; // 1
    private String formato; // 2
    private String duracao; // 3
    private String paisOrigem; // 4
    private String idioma; // 5
    private String emissora; // 6
    private String trans; // 7
    private int temp; // 8
    private int eps; // 9

    // CONSTRUTORES
    // VAZIO
    Serie() {
    }

    // CHEIO
    Serie(String nome, String formato, String duracao, String paisOrigem, String idioma, String emissora, String trans,
            int temp, int eps) {
        this.nome = nome;
        this.formato = formato;
        this.duracao = duracao; // 3
        this.paisOrigem = paisOrigem;
        this.idioma = idioma;
        this.emissora = emissora; // 6
        this.trans = trans;
        this.temp = temp;
        this.eps = eps; // 9
    }

    // GET AND SET

    // SET NOME
    public void setNome(String nome) {
        this.nome = nome;
    }

    // GET NOME
    public String getNome() {
        return nome;
    }

    // SET FORMATO
    public void setFormato(String formato) {
        this.formato = formato;
    }

    // GET FORMATO
    public String getFormato() {
        return formato;
    }

    // SET DURACAO
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    // GET DURACAO
    public String getDuracao() {
        return duracao;
    }

    // SET PAIS ORIGEM
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    // GET PAIS ORIGEM
    public String getPaisOrigem() {
        return paisOrigem;
    }

    // SET IDIOMA
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // GET IDIOMA
    public String getIdioma() {
        return idioma;
    }

    // SET IDIOMA
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    // GET IDIOMA
    public String getEmissora() {
        return emissora;
    }

    // SET TRANSMISSAO
    public void setTrans(String trans) {
        this.trans = trans;
    }

    // GET TRANSMISSAO
    public String getTrans() {
        return trans;
    }

    // SET TEMPORADA
    public void setTemp(int temp) {
        this.temp = temp;
    }

    // GET TEMPORADA
    public int getTemp() {
        return temp;
    }

    // SET EPISODIO
    public void setEps(int eps) {
        this.eps = eps;
    }

    // GET EPISODIO
    public int getEps() {
        return eps;
    }

    // CLONE
    class Serie2 implements Cloneable {
        String nome; // 1
        String formato; // 2
        String duracao; // 3
        String paisOrigem; // 4
        String idioma; // 5
        String emissora; // 6
        String trans; // 7
        int temp; // 8
        int eps; // 9
        Serie cloneSerie = new Serie();

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    // IMPRIMIR
    public void imprimir() {
        MyIO.println(nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissora + " "
                + trans + " " + temp + " " + eps);
    }

    // LER
    public void ler(String arquivo) throws IOException {
        // MyIO.println(arquivo);
        String tudojuntoFile = "/tmp/series/" + arquivo;
        // MyIO.println(tudojuntoFile);
        InputStreamReader irs = new InputStreamReader(new FileInputStream(tudojuntoFile));
        BufferedReader br = new BufferedReader(irs);
        // nome
        while (!br.readLine().contains("infobox_v2"))
            ;
        br.readLine();
        this.nome = ExtrairNome(arquivo);

        // formato
        while (!br.readLine().contains("Formato"))
            ;
        this.formato = removerTremEstranho(limpaTag(br.readLine()));
        // duracao
        while (!br.readLine().contains("Duração"))
            ;
        this.duracao = removerTremEstranho(limpaTag(br.readLine()));
        // paisOrigem
        while (!br.readLine().contains("País de origem"))
            ;
        this.paisOrigem = removerTremEstranho(limpaTag(br.readLine())).trim();
        // idioma
        while (!br.readLine().contains("Idioma original"))
            ;
        this.idioma = removerTremEstranho(limpaTag(br.readLine()));
        // emissora Emissora de televisão original
        while (!br.readLine().contains("Emissora de televisão original"))
            ;
        this.emissora = removerTremEstranho(limpaTag(br.readLine()).trim());
        // trans
        while (!br.readLine().contains("Transmissão original"))
            ;
        this.trans = removerTremEstranho(limpaTag(br.readLine()));
        // temp
        while (!br.readLine().contains("N.º de temporadas"))
            ;
        try {
            this.temp = Integer.parseInt(botandoSoNumero(limpaTag(br.readLine())));
        } catch (NumberFormatException e) {
            this.temp = 0;
        }
        // eps
        while (!br.readLine().contains("N.º de episódios"))
            ;
        try {
            this.eps = Integer.valueOf(botandoSoNumero(limpaTag(br.readLine())));
        } catch (NumberFormatException e) {
            this.eps = 0;
        }
        br.close();
    }

    public String ExtrairNome(String s) {
        String arroz = new String();
        String arroz2 = new String();
        arroz = s.replaceAll(".html", "");
        arroz2 = arroz.replaceAll("_", " ");
        return arroz2;
    }

    public String removerTremEstranho(String s) {
        String resp = "";
        if (s.contains("&#160;") || s.contains("&nbsp;")) {
            for (int i = 6; i < s.length(); i++) {
                resp += s.charAt(i);
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                resp += s.charAt(i);
            }
        }
        return resp;
    }

    public String botandoSoNumero(String s) {
        String resp = "";
        int i = 0;
        while (i < s.length() && (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
                || s.charAt(i) == '8' || s.charAt(i) == '9')) {
            resp += s.charAt(i);
            i++;
        }
        return resp;
    }

    public String limpaTag(String s) {
        // <th colspan="2" class="topo televisao2" style="font-size:1.5em;
        // background-color:#D4F2CE;
        // font-style:italic; padding:0px;"><span class="">13 Reasons Why</span></th>
        String resp = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '>' && i + 1 < s.length() && s.charAt(i + 1) != '<') {
                int j = i + 1;
                if (s.charAt(j) == '&') {
                    j += 6;
                }
                while (s.charAt(j) != '<') {
                    resp += s.charAt(j);
                    j++;
                }
            }
        }
        return resp;
    }
}

class List {
    private Serie[] array;
    private int n;
    public int Comp;
    public int Mov;

    // construtor vazio
    public List() {
        this(1);
    }

    // construtor com tamanho
    public List(int i) {
        array = new Serie[i];
        n = 0;
        Comp = 0;
        Mov = 0;
    }

    // Insere um elemento na primeira posicao da lista
    // e desloca os demais elementos para o fim da lista.
    public boolean II(Serie item) {
        if (n < array.length) {
            // Desloca elementos para o fim do array
            for (int i = n; i > 0; i--)
                array[i] = array[i - 1];

            array[0] = item;
            n++;
            return true;
        }
        return false;
    }

    // Insere um elemento na ultima posicao da lista.
    public boolean IF(Serie item) {
        // validar insercao
        if (n < array.length) {
            array[n] = item;
            n++;
            return true;
        }
        return false;
    }

    // Insere um elemento em uma posicao especifica
    // e move os demais elementos para o fim da lista.
    public boolean IQ(Serie item, int pos) {

        // validar insercao
        if (n < array.length && pos >= 0 && pos <= n) {
            // Desloca elementos para o fim do array
            for (int i = n; i > pos; i--)
                array[i] = array[i - 1];

            array[pos] = item;
            n++;
            return true;
        }
        return false;
    }

    // Remove um elemento da primeira posicao da lista
    // e movimenta os demais elementos para o inicio da mesma.
    public Serie RI() {
        if (n > 0) {
            Serie item = array[0];
            n--;

            for (int i = 0; i < n; i++)
                array[i] = array[i + 1];

            return item;
        }
        return null;
    }

    // Remove um elemento da ultima posicao da lista.
    public Serie RF() {
        if (n > 0)
            return array[--n];
        return null;
    }

    // Remove um elemento de uma posicao especifica da lista
    // e movimenta os demais elementos para o inicio da mesma.
    public Serie RQ(int pos) {
        Serie item = new Serie();
        if (n > 0 && pos >= 0 && pos < n) {
            item = array[pos];
            n--;
            for (int i = pos; i < n; i++)
                array[i] = array[i + 1];
        }
        return item;
    }

    // Mostra os elementos da lista separados por espacos.
    public void mostrar() {
        for (int i = n-1; i >= 0; i--) {
            array[i].imprimir();
        }
    }
    // mostrar nome e formato
    public void mostrarDiferente() {
        for (int i = n-1; i >= 0; i--) {
            MyIO.println(array[i].getNome() + " & " + array[i].getTemp());
        }
    }

    // Procura um elemento e retorna se ele existe.
    public boolean pesquisar(Serie item) {
        for (int i = 0; i < n; i++)
            if (array[i].equals(item))
                return true;
        return false;
    }

    public void bubble() {
        for (int i = 0; i < n; i++) { // O(N)
            for (int j = i + 1; j < n; j++) { // O(N)
                if (array[i].getTemp() < array[j].getTemp()) {
                    Serie aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                } else if ((array[i].getTemp() == array[j].getTemp())
                        && (array[i].getNome().compareTo(array[j].getNome()) < 0)) {
                    Serie aux = array[j];
                    array[j] = array[i];
                    array[i] = aux;
                }
            }
        }
    }

}

public class Bolha {
    // parte do log
    public static long tempo = 0;

    // metodo para verificar se palavra � igual a FIM
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        List lista = new List(70);
        Serie s = new Serie();
        String arquivo = new String();
        arquivo = MyIO.readLine();
        while (isFim(arquivo) != true) {
            // levando pro metodo
            s = new Serie();
            s.ler(arquivo.trim());
            lista.IF(s);
            arquivo = MyIO.readLine();
        }
        long start = System.currentTimeMillis();
        lista.bubble();
        lista.mostrar();

        long tGasto = System.currentTimeMillis() - start;
        tempo += tGasto;

        // coisa do log
        RandomAccessFile raf = new RandomAccessFile("707760_bolha.txt", "rw");
        String tudo = "";
        tudo = "707760\t" + String.valueOf(lista.Comp) + "\t" + String.valueOf(lista.Mov) + "\t"
                + String.valueOf(tempo);
        raf.writeUTF(tudo);
        raf.close();
    }
}
