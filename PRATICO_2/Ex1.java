import java.net.*;

//import jdk.internal.org.jline.reader.EndOfFileException;
import java.io.*;
import java.io.BufferedReader;

// ERIC RODRIGUES DINIZ - 707760
// 1. Classe Series em Java
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
        String tudojuntoFile = "/tmp/series/" + arquivo;
        // MyIO.println(tudojuntoFile);
        InputStreamReader irs = new InputStreamReader(new FileInputStream(tudojuntoFile));
        BufferedReader br = new BufferedReader(irs);
        // nome
        while (!br.readLine().contains("infobox_v2"))
            ;
        br.readLine();
        this.nome = removerTremEstranho(limpaTag(br.readLine()));
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
        this.paisOrigem = removerTremEstranho(limpaTag(br.readLine()));
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
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3'
                    || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7'
                    || s.charAt(i) == '8' || s.charAt(i) == '9') {
                resp += s.charAt(i);
            }
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
                    j+=6;
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

public class Ex1 {
    // metodo para verificar se palavra � igual a FIM
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        Serie s = new Serie();
        String arquivo = new String();
        arquivo = MyIO.readLine();
        while (isFim(arquivo) != true) {
            // levando pro metodo
            s.ler(arquivo.trim());
            s.imprimir();
            // lendo novamente o site
            arquivo = MyIO.readLine();
        }
    }
}
