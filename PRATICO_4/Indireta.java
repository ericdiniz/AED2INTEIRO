import java.io.*;

// ERIC RODRIGUES DINIZ - 707760
// CLASSE SERIE
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
class Celula {
    public Serie item;
    public Celula prox;

    public Celula(Serie valorItem, Celula proxCelula) {
        item = valorItem;
        prox = proxCelula;
    }

    public Celula(Serie valorItem) {
        item = valorItem;
        prox = null;
    }

    public Celula() {
        item = null;
        prox = null;
    }
}

class Lista {
    private Celula primeiro; // Primeira celula: SEM elemento valido.
    private Celula ultimo; // Ultima celula: COM elemento valido.

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }
    public void mostrar() {
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            i.item.imprimir();
        }
    }
    public boolean pesquisar(String x) {
        boolean retorno = false;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.item.getNome().compareTo(x) == 0) {
                retorno = true;
                i = ultimo;
            }
        }
        return retorno;
    }
    public void inserirInicio(Serie elemento) {
        Celula tmp = new Celula(elemento);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }
    public void inserirFim(Serie elemento) {
        Celula tmp = new Celula(elemento);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        tmp = null;
    }
    public void inserirMeio(Serie x, int posicao) throws Exception {
        Celula i;
        int cont;

        // Caminhar ate chegar na posicao anterior a insercao
        for (i = primeiro, cont = 0; (i.prox != ultimo && cont < posicao); i = i.prox, cont++)
            ;

        // Se indice for incorreto:
        if (posicao < 0 || posicao > cont + 1) {
            throw new Exception("Erro ao inserir (posicao " + posicao + "(cont = " + cont + ") invalida)!");

        } else if (posicao == cont + 1) {
            inserirFim(x);
        } else {
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }
}

class HashIndiretoLista {
    Lista tabela[];
    int tamanho;
    final int NULO = -1;

    public HashIndiretoLista() {
        this(21);
    }

    public HashIndiretoLista(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }

    public int h(int elemento) {
        return elemento % tamanho;
    }

    public int somaAsc(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            int asciiValue = str.charAt(i);
                sum += asciiValue;   
        }
        return sum;
    }

    boolean pesquisar(String elemento) {
        int pos = h(somaAsc(elemento));
        return tabela[pos].pesquisar(elemento);
    }

    public void inserirInicio(Serie elemento) {
        int pos = h(somaAsc(elemento.getNome()));
        tabela[pos].inserirInicio(elemento);
    }
}

public class Indireta {
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
        HashIndiretoLista arvore = new HashIndiretoLista();
        Serie s = new Serie();
        String arquivo = new String();
        arquivo = MyIO.readLine();
        while (isFim(arquivo) != true) {
            // levando pro metodo
            s = new Serie();
            s.ler(arquivo.trim());
            arvore.inserirInicio(s);
            arquivo = MyIO.readLine();
        }
        String nome = new String();

        // long start = System.currentTimeMillis();

        boolean resp;
        do {
            nome = MyIO.readLine();
            // MyIO.println(nome);
            if (isFim(nome) == false) {
                // chama o pesquisar
                resp = arvore.pesquisar(nome);
                if (resp == true) {
                    MyIO.println(" SIM");
                } else {
                    MyIO.println(" NAO");
                }
            }
        } while (isFim(nome) == false);
    }
}