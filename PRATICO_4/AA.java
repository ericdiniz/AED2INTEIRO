
// CLASSE SERIE
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
        this.nome = ExtrairNome(arquivo).trim();

        // formato
        while (!br.readLine().contains("Formato"))
            ;
        this.formato = removerTremEstranho(limpaTag(br.readLine()));
        // duracao
        while (!br.readLine().contains("Dura????o"))
            ;
        this.duracao = removerTremEstranho(limpaTag(br.readLine()));
        // paisOrigem
        while (!br.readLine().contains("Pa??s de origem"))
            ;
        this.paisOrigem = removerTremEstranho(limpaTag(br.readLine())).trim();
        // idioma
        while (!br.readLine().contains("Idioma original"))
            ;
        this.idioma = removerTremEstranho(limpaTag(br.readLine()));
        // emissora Emissora de televis??o original
        while (!br.readLine().contains("Emissora de televis??o original"))
            ;
        this.emissora = removerTremEstranho(limpaTag(br.readLine()).trim());
        // trans
        while (!br.readLine().contains("Transmiss??o original"))
            ;
        this.trans = removerTremEstranho(limpaTag(br.readLine()));
        // temp
        while (!br.readLine().contains("N.?? de temporadas"))
            ;
        try {
            this.temp = Integer.parseInt(botandoSoNumero(limpaTag(br.readLine())));
        } catch (NumberFormatException e) {
            this.temp = 0;
        }
        // eps
        while (!br.readLine().contains("N.?? de epis??dios"))
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

// CLASSE CELULA
class CCelula {
    public Serie item;
    public CCelula prox;

    public CCelula(Serie valorItem, CCelula proxCelula) {
        item = valorItem;
        prox = proxCelula;
    }

    public CCelula(Serie valorItem) {
        item = valorItem;
        prox = null;
    }

    public CCelula() {
        item = null;
        prox = null;
    }
}

// CLASSE N??
// N?? DAS LETRINHA
class No {
    public char elemento; // Conteudo do no.
    public No esq; // No da esquerda.
    public No dir; // No da direita.
    public No2 outro;

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     */
    No(char elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
        this.outro = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No da esquerda.
     * @param dir      No da direita.
     */
    No(char elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;
    }
}

// N?? SERIE
class No2 {
    public Serie elemento; // Conteudo do no.
    public No2 esq; // No da esquerda.
    public No2 dir; // No da direita.

    /**
     * Construtor da classe.
     * 
     * @param s Conteudo do no.
     */
    No2(Serie s) {
        this.elemento = s;
        this.esq = this.dir = null;
    }

    /**
     * Construtor da classe.
     * 
     * @param elemento Conteudo do no.
     * @param esq      No2 da esquerda.
     * @param dir      No2 da direita.
     */
    No2(Serie elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

// CLASSE DA ARVORE
public class AA {
    private No raiz; // Raiz da arvore.

    public AA() throws Exception {
        raiz = null;
        inserir('D');
        inserir('R');
        inserir('Z');
        inserir('X');

        inserir('V');
        inserir('B');
        inserir('F');
        inserir('P');

        inserir('U');
        inserir('I');
        inserir('G');
        inserir('E');

        inserir('J');
        inserir('L');
        inserir('H');
        inserir('T');

        inserir('A');
        inserir('W');
        inserir('S');
        inserir('O');

        inserir('M');
        inserir('N');
        inserir('K');
        inserir('C');

        inserir('Y');
        inserir('Q');
    }

    public void inserir(char x) throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(char x, No i) throws Exception {
        if (i == null) {
            i = new No(x);

        } else if (x < (i.elemento)) {
            i.esq = inserir(x, i.esq);

        } else if (x > (i.elemento)) {
            i.dir = inserir(x, i.dir);

        } else {
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }

    public void inserir(Serie s) throws Exception {
        inserir(s, raiz);
    }

    public void inserir(Serie s, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao inserir: caractere invalido!");

        } else if (s.getNome().charAt(0) < i.elemento) {
            inserir(s, i.esq);

        } else if (s.getNome().charAt(0) > i.elemento) {
            inserir(s, i.dir);

        } else {
            i.outro = inserir(s, i.outro);
        }
    }

    private No2 inserir(Serie s, No2 i) throws Exception {
        if (i == null) {
            i = new No2(s);

        } else if (s.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(s, i.esq);

        } else if (s.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(s, i.dir);

        } else {
            throw new Exception("Erro ao inserir: elemento existente!");
        }

        return i;
    }

    public void remover(String x) throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(String x, No i) throws Exception {

        if (i == null) {

        } else if (x.charAt(0) < i.elemento) {
            i.esq = remover(x, i.esq);

        } else if (x.charAt(0) > i.elemento) {
            i.dir = remover(x, i.dir);
        } else {
            i.outro = remover(x, i.outro);
        }
        return i;
    }

    private No2 remover(String x, No2 i) throws Exception {

        if (i == null) {
            // throw new Exception("Erro ao remover!");

        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            i.esq = remover(x, i.esq);

        } else if (x.compareTo(i.elemento.getNome()) > 0) {
            i.dir = remover(x, i.dir);
            // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    private No2 maiorEsq(No2 i, No2 j) {

        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.

            // Existe no a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }

    public boolean pesquisar(String elemento) {
        MyIO.print("raiz ");
        return pesquisar(raiz, elemento);
    }

    private boolean pesquisar(No no, String x) {
        boolean resp;
        if (no == null) {
            resp = false;

        } else if (pesquisarSegundaArvore(no.outro, x)){
            resp = true;

        } else {
            MyIO.print("esq ");
            resp = pesquisar(no.esq, x);
            if(resp == false)
            {
                MyIO.print("dir ");
                resp = pesquisar(no.dir, x);
            }
        }
        return resp;
    }

    private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;
        if (no == null) {
            resp = false;

        } else if (x.compareTo(no.elemento.getNome()) == 0){
            resp = true;

        } else {
            MyIO.print("ESQ ");
            resp = pesquisarSegundaArvore(no.esq, x);
            if(resp == false)
            {
                MyIO.print("DIR ");
                resp = pesquisarSegundaArvore(no.dir, x);
            }
        }
        return resp;
    }

    // parte do log
    public static int Comp = 0;
    public static long tempo = 0;

    // metodo para verificar se palavra ??? igual a FIM
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String LimpaNome(String s) {
        String n = new String();
        if (s.charAt(0) == 'I')
            n = s.replaceAll("I ", "");
        else if (s.charAt(0) == 'R')
            n = s.replaceAll("R ", "");
        return n;
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        AA arvore = new AA();
        Serie s = new Serie();
        String arquivo = new String();
        arquivo = MyIO.readLine();
        while (isFim(arquivo) != true) {
            // levando pro metodo
            s = new Serie();
            s.ler(arquivo.trim());
            arvore.inserir(s);
            arquivo = MyIO.readLine();
        }
        String nome = new String();
        // lista.mostrar();
        int quantObj = 0;
        quantObj = MyIO.readInt();
        // long start = System.currentTimeMillis();
        // arvore.caminharCentral();
        for (int i = 0; i < quantObj; i++) {
            nome = MyIO.readLine();
            if (nome.charAt(0) == 'I') {
                s = new Serie();
                s.ler(LimpaNome(nome));
                arvore.inserir(s);
            } else if (nome.charAt(0) == 'R') {
                // nome = MyIO.readLine();
                arvore.remover(LimpaNome(nome));
            }
        }
        boolean resp;
        // arvore.caminharCentral();
        do {
            nome = MyIO.readLine();
            // MyIO.println(nome);
            if (isFim(nome) == false) {
                // chama o pesquisar
                resp = arvore.pesquisar(nome);
                if (resp == true) {
                    MyIO.println("SIM");
                } else {
                    MyIO.println("NAO");
                }
            }
        } while (isFim(nome) == false);
    }
}
