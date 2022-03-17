#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
// SERIE EM C QUESTAO 2
// ERIC RODRIGUES DINIZ
typedef struct Serie
{
    char nome[100];
    char formato[100];
    char duracao[100];
    char paisOrigem[100];
    char idioma[100];
    char emissora[100];
    char transmissao[100];
    int temporada;
    int episodio;
} Serie;

// 2 c
// VAZIO
Serie ConstV()
{
    Serie s;
    strcpy(s.nome, "");
    strcpy(s.formato, "");
    strcpy(s.duracao, "");
    strcpy(s.paisOrigem, "");
    strcpy(s.idioma, "");
    strcpy(s.emissora, "");
    strcpy(s.transmissao, "");
    s.temporada = 0;
    s.episodio = 0;
    return s;
}
// CHEIO
Serie ConstC(char nome[], char formato[], char duracao[],
             char paisOrigem[], char idioma[], char emissora[],
             char transmissao[], int temporada, int episodio)
{
    Serie s;
    strcpy(s.nome, nome);
    strcpy(s.formato, formato);
    strcpy(s.duracao, duracao);
    strcpy(s.paisOrigem, paisOrigem);
    strcpy(s.idioma, idioma);
    strcpy(s.emissora, emissora);
    strcpy(s.transmissao, transmissao);
    s.temporada = temporada;
    s.episodio = episodio;
    return s;
}
// get set
//NOME
char *getNome(Serie s)
{
    char *n;
    strcpy(n, s.nome);
    return n;
}

void setNome(Serie s, char nome[])
{
    strcpy(s.nome, nome);
}

//FORMATO
char *getFormato(Serie s)
{
    char *f;
    strcpy(f, s.formato);
    return f;
}

void setFormato(Serie s, char formato[])
{
    strcpy(s.formato, formato);
}

//DURACAO
char *getDuracao(Serie s)
{
    char *d;
    strcpy(d, s.duracao);
    return d;
}

void setDuracao(Serie s, char duracao[])
{
    strcpy(s.duracao, duracao);
}

//PAIS ORIGEM
char *getPais(Serie s)
{
    char *p;
    strcpy(p, s.paisOrigem);
    return p;
}

void setPais(Serie s, char paisOrigem[])
{
    strcpy(s.paisOrigem, paisOrigem);
}

//IDIOMA
char *getIdioma(Serie s)
{
    char *i;
    strcpy(i, s.idioma);
    return i;
}

void setIdioma(Serie s, char idioma[])
{
    strcpy(s.idioma, idioma);
}

//emissora
char *getEmissora(Serie s)
{
    char *e;
    strcpy(e, s.emissora);
    return e;
}

void setEmissora(Serie s, char emissora[])
{
    strcpy(s.emissora, emissora);
}

//transmissao
char *getTrans(Serie s)
{
    char *t;
    strcpy(t, s.transmissao);
    return t;
}

void setTrans(Serie s, char transmissao[])
{
    strcpy(s.transmissao, transmissao);
}

//temporada
int getTemp(Serie s)
{
    return s.temporada;
}

void setTemp(Serie s, int temporada)
{
    s.temporada = temporada;
}

//episodio
int getEpisodio(Serie s)
{
    return s.episodio;
}

void setEpisodio(Serie s, int episodio)
{
    s.episodio = episodio;
}

// imprimir
void imprimir(Serie s)
{
    printf("%s %s %s %s %s %s %s %d %d\n",
           s.nome, s.formato, s.duracao, s.paisOrigem, s.idioma,
           s.emissora, s.transmissao, s.temporada, s.episodio);
}
char *botandoSoNumero(char *s)
{
    char *resp;
    int j = 0;
    for (int i = 0; i < strlen(s); i++)
    {
        if (s[i] == '0' || s[i] == '1' || s[i] == '2' ||
            s[i] == '3' || s[i] == '4' || s[i] == '5' ||
            s[i] == '6' || s[i] == '7' || s[i] == '8' ||
            s[i] == '9')
        {
            resp[j] = s[i];
            j++;
        }
    }
    resp[j] = '\0';
    return resp;
}
char *limpaTag(char *s)
{
    char *resp;
    resp = (char *)malloc(sizeof(strlen(s)));
    int size = strlen(s);
    int ContResp = 0;
    for (int i = 0; i < size; i++)
    {
        if (s[i] == '>' && i + 1 < size && s[i] != '<')
        {
            int j = i + 1;
            if (s[j] == '&')
            {
                j += 6;
            }
            while (s[j] != '<')
            {
                resp[ContResp] = s[j];
                ContResp++;
                j++;
            }
        }
    }
    resp[ContResp] = '\0';
    return resp;
}
// ler
void ler(char *arquivo, Serie *s)
{
    FILE *fp; // 
    //char *arroz = strcat("/tmp/series/", arquivo);
    char *arroz = arquivo;
    fp = fopen(arroz, "r"); // /tmp/
    char linha[1000];
    //nome
    fgets(linha, 1000, fp);
    while (strstr(linha, "infobox_v2") == NULL)
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    fgets(linha, 1000, fp);
    strcpy(s->nome, limpaTag(linha));
    s->nome[strcspn(s->nome, "\n")] = ' ';
    //formato
    while (!strstr(linha, "Formato"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->formato, limpaTag(linha));
    s->formato[strcspn(s->formato, "\n")] = ' ';
    //duracao
    while (!strstr(linha, "Duração"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->duracao, limpaTag(linha));
    s->duracao[strcspn(s->duracao, "\n")] = ' ';
    //paisOrigem
    while (!strstr(linha, "País de origem"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->paisOrigem, limpaTag(linha));
    s->paisOrigem[strcspn(s->paisOrigem, "\n")] = ' ';
    //idioma
    while (!strstr(linha, "Idioma original"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->idioma, limpaTag(linha));
    s->idioma[strcspn(s->idioma, "\n")] = ' ';
    //emissora
    while (!strstr(linha, "Emissora de televisão original"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->emissora, limpaTag(linha));
    s->emissora[strcspn(s->emissora, "\n")] = ' ';
    //transmissao
    while (!strstr(linha, "Transmissão original"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    strcpy(s->transmissao, limpaTag(linha));
    s->transmissao[strcspn(s->transmissao, "\n")] = ' ';
    // temp
    while (!strstr(linha, "N.º de temporadas"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    int vaiProInt = atoi(botandoSoNumero(limpaTag(linha)));
    s->temporada = vaiProInt;
    //eps
    while (!strstr(linha, "N.º de episódios"))
    {
        fgets(linha, 1000, fp);
    }
    fgets(linha, 1000, fp);
    int vaiProInt2 = atoi(botandoSoNumero(limpaTag(linha)));
    s->episodio = vaiProInt2;
    fclose(fp);
}
// clone
Serie Clone(Serie s)
{
    Serie clone = s;
    return clone;
}
bool isFim(char *s)
{
    return (strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main()
{
    Serie s = ConstV();
    char pubin[150];
    scanf("%s", pubin);
    while (isFim(pubin) == false)
    {
        ler(pubin, &s);
        imprimir(s);
        scanf("%s", pubin);
    }
    return 0;
}