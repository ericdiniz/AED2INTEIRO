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

class CFila {
    private CCelula frente; // Celula cabeca.
    private CCelula tras; // Ultima celula.
    private int qtde;
    private int n;

    /**
     * Fun��o construtora. Cria a c�lula cabe�a e faz as refer�ncias frente e tras
     * apontarem para ela.
     */
    public CFila() {
        frente = new CCelula();
        tras = frente;
        n = 0;
    }

    /**
     * Verifica se a fila est� vazia.
     * 
     * @return Retorna TRUE se a fila estiver vazia e FALSE caso contr�rio.
     */
    public boolean vazia() {
        return frente == tras;
    }

    /**
     * Imprime os elementos da fila
     */
    public void mostra() {
        for (CCelula c = frente.prox; c != null; c = c.prox)
            c.item.imprimir();
    }

    /**
     * Insere um novo Item no fim da fila.
     * 
     * @param valorItem O parametro "valorItem" � um Object contendo o elemento a
     *                  ser inserido no final da fila.
     */
    public void enfileira(Serie valorItem) {
        if (n == 5) {
            desenfileira();
        }
        tras.prox = new CCelula(valorItem);
        tras = tras.prox;
        n++;
    }

    /**
     * Retira e retorna o primeiro elemento da fila.
     * 
     * @return Retorna um Object contendo o primeiro elemento da fila. Caso a fila
     *         esteja vazia retorna null.
     */
    public Serie desenfileira() {
        Serie item = null;
        if (frente != tras) {
            frente = frente.prox;
            item = frente.item;
            n--;
        }
        return item;
    }

    // Id�ntico ao m�todo anterior, mas sem usar a vari�vel tempor�ria item
    public Serie desenfileirav2() {
        if (frente != tras) {
            frente = frente.prox;
            qtde--;
            return frente.item;
        }
        return null;
    }

    /**
     * Retorna o primeiro Item da fila sem remove-lo.
     * 
     * @return Retorna um Object contendo o primeiro Item da fila.
     */
    public Serie peek() {
        if (frente != tras)
            return frente.prox.item;
        else
            return null;
        // return (frente != tras)? frente.prox.item : null;
    }

    /**
     * Verifica se o Item passado como parametro esta contido na fila.
     * 
     * @param valorItem O parametro "valorItem" e um object contendo o Item a ser
     *                  localizado.
     * @return O m�todo retorna TRUE caso o item esteja presente na fila.
     */
    public boolean contem(Serie valorItem) {
        CCelula aux = frente.prox;
        while (aux != null) {
            if (aux.item.equals(valorItem))
                return true;
            aux = aux.prox;
        }
        return false;
    }

    /**
     * Verifica se o Item passado como parametro esta contido na fila. (Obs: usa o
     * comando FOR)
     * 
     * @param (valorItem) Recebe como parametro um object contendo o Item a ser
     *                    localizado.
     * @return Retorna TRUE caso o Item esteja presente na fila.
     */
    public boolean contemFor(Serie valorItem) {
        for (CCelula aux = frente.prox; aux != null; aux = aux.prox)
            if (aux.item.equals(valorItem))
                return true;
        return false;
    }

    /**
     * Metodo que retorna a quantidade de itens da fila.
     * 
     * @return
     */
    public int quantidade() {
        return qtde;
    }

    public int Media() {
        double soma = 0;
        int resultado = 0;
        double contaMedia = 0;
        for (CCelula c = frente.prox; c != null; c = c.prox) {
            soma = soma + c.item.getTemp();
            contaMedia++;
        }
        resultado = (int) Math.round(soma / contaMedia);

        return resultado;
    }

}

public class FilaFlex {

    // parte do log
    public static int Comp = 0;
    public static long tempo = 0;

    // metodo para verificar se palavra � igual a FIM
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
        CFila fila = new CFila();
        Serie s = new Serie();
        String arquivo = new String();
        arquivo = MyIO.readLine();
        while (isFim(arquivo) != true) {
            // levando pro metodo
            s = new Serie();
            s.ler(arquivo.trim());
            fila.enfileira(s);
            MyIO.println(fila.Media());
            // fila.mostrar();
            arquivo = MyIO.readLine();
        }
        String nome = new String();
        int quantObj = 0;
        quantObj = MyIO.readInt();
        for (int i = 0; i < quantObj; i++) {
            nome = MyIO.readLine();
            if (nome.charAt(0) == 'I') {
                s = new Serie();
                s.ler(LimpaNome(nome));
                fila.enfileira(s);
                // fila.mostrar();
                MyIO.println(fila.Media());
            } else if (nome.charAt(0) == 'R') {
                s = new Serie();
                s = fila.desenfileira();
            }
        }
    }
}
