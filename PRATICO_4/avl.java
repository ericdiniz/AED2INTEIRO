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

class No {
	public Serie elemento; // Conteudo do no.
	public No esq, dir;  // Filhos da esq e dir.
	public int nivel;    //Numero de niveis abaixo do no

	public No(Serie elemento) {
		this(elemento, null, null, 1);
	}

	public No(Serie elemento, No esq, No dir, int nivel) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
      this.nivel = nivel;
	}

   public void setNivel() {
      this.nivel = 1 + Math.max(getNivel(esq),getNivel(dir));
   }

   public static int getNivel(No no) {
      return (no == null) ? 0 : no.nivel;
   }
}

class arvoreAVL {
	private No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public arvoreAVL() {
		raiz = null;
	}

	public boolean pesquisar(String x) {
        MyIO.print("raiz ");
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (x.equals(i.elemento.getNome())) {
            resp = true;

        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            MyIO.print("esq ");
            resp = pesquisar(x, i.esq);

        } else {
            MyIO.print("dir ");
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	public void inserir(Serie x) throws Exception {
		raiz = inserir(x, raiz);
	}
	private No inserir(Serie x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x.getNome().compareTo(i.elemento.getNome()) < 0){
         i.esq = inserir(x, i.esq);

      } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return balancear(i);
	}

   private No balancear(No no) throws Exception {
      if(no != null){
         int fator = No.getNivel(no.dir) - no.getNivel(no.esq);

         //Se balanceada
         if (Math.abs(fator) <= 1){
            no.setNivel();

         //Se desbalanceada para a direita
         }else if (fator == 2){

            int fatorFilhoDir = No.getNivel(no.dir.dir) - No.getNivel(no.dir.esq);

            //Se o filho a direita tambem estiver desbalanceado
            if (fatorFilhoDir == -1) {
               no.dir = rotacionarDir(no.dir);
            }
            no = rotacionarEsq(no);

         //Se desbalanceada para a esquerda
         }else if (fator == -2){

            int fatorFilhoEsq = No.getNivel(no.esq.dir) - No.getNivel(no.esq.esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no.esq = rotacionarEsq(no.esq);
            }
            no = rotacionarDir(no);

         }else{
            throw new Exception("Erro no No(" + no.elemento + ") com fator de balanceamento (" + fator + ") invalido!"); 
         }
      }

      return no;
   }

   private No rotacionarDir(No no) {
      No noEsq = no.esq;
      No noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      no.setNivel();  //Atualizar o nivel do no
      noEsq.setNivel(); //Atualizar o nivel do noEsq

      return noEsq;
   }

   private No rotacionarEsq(No no) {
      No noDir = no.dir;
      No noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;

      no.setNivel(); //Atualizar o nivel do no
      noDir.setNivel(); //Atualizar o nivel do noDir
      return noDir;
   }
}
public class avl {
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
        arvoreAVL arvore = new arvoreAVL();
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

        // long start = System.currentTimeMillis();

        boolean resp;
        do {
            nome = MyIO.readLine();
            //MyIO.println(nome);
            if (isFim(nome) == false) {
                //chama o pesquisar
                resp = arvore.pesquisar(nome);
                if(resp == true){
                    MyIO.println("SIM");
                }
                else{
                    MyIO.println("NAO");
                }
            }    
        } while (isFim(nome) == false);
    }
}
