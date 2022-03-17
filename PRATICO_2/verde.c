#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct Serie
{

    char nome[100];
    char idioma[100];
    char formato[100];
    char duracao[100];
    char pais[100];
    char emissora[100];
    char transmicao[100];
    int temporadas;
    int episodios;
} Serie;

//Construtor vazio
Serie inicializaVazio()
{
    Serie serie;
    strcpy(serie.nome, "");
    strcpy(serie.idioma, "");
    strcpy(serie.formato, "");
    strcpy(serie.duracao, "");
    strcpy(serie.pais, "");
    strcpy(serie.emissora, "");
    strcpy(serie.transmicao, "");
    serie.temporadas = 0;
    serie.episodios = 0;

    return serie;
}

//Construtor que recebe parâmetros
Serie inicializa(char nome[], char idioma[], char formato[], char duracao[], char pais[], char emissora[], char transmicao[], int temporadas, int episodios)
{
    Serie serie;
    strcpy(serie.nome, nome);
    strcpy(serie.idioma, idioma);
    strcpy(serie.formato, formato);
    strcpy(serie.duracao, duracao);
    strcpy(serie.pais, pais);
    strcpy(serie.emissora, emissora);
    strcpy(serie.transmicao, transmicao);
    temporadas = temporadas;
    episodios = episodios;

    return serie;
}

//encapsulamento das variáveis
void setNome(Serie *serie, char nome[])
{
    strcpy(serie->nome, nome);
}

char *getNome(Serie serie)
{
    char *a;
    strcpy(a, serie.nome);
    return a;
}

void setIdioma(Serie *serie, char idioma[])
{
    strcpy(serie->idioma, idioma);
}

char *getIdioma(Serie serie)
{
    char *a;
    strcpy(a, serie.idioma);
    return a;
}

void setFormato(Serie *serie, char formato[])
{
    strcpy(serie->formato, formato);
}

char *getFormato(Serie serie)
{
    char *a;
    strcpy(a, serie.formato);
    return a;
}

void setDuracao(Serie *serie, char duracao[])
{
    strcpy(serie->duracao, duracao);
}

char *getDuracao(Serie serie)
{
    char *a;
    strcpy(a, serie.duracao);
    return a;
}

void setPais(Serie *serie, char pais[])
{
    strcpy(serie->pais, pais);
}

char *getPais(Serie serie)
{
    char *a;
    strcpy(a, serie.pais);
    return a;
}

void setEmissora(Serie *serie, char emissora[])
{
    strcpy(serie->emissora, emissora);
}

char *getEmissora(Serie serie)
{
    char *a;
    strcpy(a, serie.emissora);
    return a;
}

void setTransmicao(Serie *serie, char transmicao[])
{
    strcpy(serie->transmicao, transmicao);
}

char *getTransmicao(Serie serie)
{
    char *a;
    strcpy(a, serie.transmicao);
    return a;
}

void setTemporadas(Serie *serie, int temporadas)
{
    serie->temporadas = temporadas;
}

int getTemporadas(Serie serie)
{
    return serie.temporadas;
}

void setEpisodios(Serie *serie, int episodios)
{
    serie->episodios = episodios;
}

int getEpisodios(Serie serie)
{
    return serie.episodios;
}


// bool isLetraorNumber(char a){
//     return (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z' || a >= '0' && a <= '9' || a == ' ' || a == '(' || a == ')' || a == '[' || a == ']');
// }

// bool isLetraorNumber2(char a) { 
//     return (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z' || a >= '0' && a <= '9'); 
// }

// bool onlyNumbers(char a) { 
//     return (a >= '0' && a <= '9'); 
// }

int trataInt(char s[])
{
    int resp = 0;
    char aux[20];
    int j = 0;
    for (int i = 0; i < strlen(s); i++)
    {
        if (s[i] == '0' || s[i] == '1' || s[i] == '2' || s[i] == '3' || s[i] == '4' || s[i] == '5' || s[i] == '6' || s[i] == '7' || s[i] == '8' || s[i] == '9')
        {
            aux[j] = s[i];
            j++;
        }
        if (s[i] == ' ')
        {
            break;
        }
    }
    resp = atoi(aux);

    return resp;
}

char *limpaPais(char *old)
{
    char *newLine = (char *)malloc(sizeof(char) * strlen(old));
    int i = 0, j = 0;
    while (i < strlen(old))
    {
        if (old[i] == '<')
        {
            i++;
            while (old[i] != '>')
                i++;
        }
        else
        {
            if (old[i] == '&')
            {
                i += 6;
            }
            newLine[j] = old[i];
            j++;
        }
        i++;
    }
    newLine[j] = '\0';
    //printf("%s",newLine);
    return newLine;
}

char *limpaTag(char *old)
{
    char *newLine = (char *)malloc(sizeof(char) * strlen(old));
    int i = 0, j = 0;
    while (i < strlen(old))
    {
        if (old[i] == '<'){
            i++;
            while (old[i] != '>'){
                i++;
            }
                
        }
        else
        {
            newLine[j] = old[i];
            j++;
        }
        i++;
    }
    newLine[j] = '\0';
    //printf("%s",newLine);
    return newLine;
}

// char *limpaTag2(char *old)
// {
//     char *newLine = (char *)malloc(sizeof(char) * strlen(old));
//     int i = 0, j = 0;
//     while (i < strlen(old))
//     {
        
//         while (old[i++] != '>');
//         if(isLetraorNumber2(old[i+1])){
//             i++;
//             while (old[i] != '<'){ 
//                 newLine[j] = old[i];
//                 i++;
//                 j++;
//             }    
//         }
//     }        
//     newLine[j] = '\0';
//     //printf("%s",newLine);
//     return newLine;
// }





//funcao que le no meu pub.in
void ler(Serie *serie, char *nome)
{
		char filePath[200];
		sprintf(filePath, "/tmp/series/%s", nome);
    FILE *fp = fopen(filePath, "r"); //verde

    char buf[3000];
    fgets(buf, 3000, fp);
    while(strstr(buf, "infobox_v2") == NULL){
    fgets(buf, 3000, fp);
    }
    fgets(buf,3000,fp);
    fgets(buf,3000,fp);
    strcpy(serie->nome,limpaTag(buf));
    serie->nome[strcspn(serie->nome, "\n")] = ' ';

    while (strstr(buf, ">Formato<") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->formato, limpaTag(buf));
    serie->formato[strcspn(serie->formato, "\n")] = ' ';

    while (strstr(buf, ">Dura") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->duracao, limpaTag(buf));
    serie->duracao[strcspn(serie->duracao, "\n")] = ' ';

    while (strstr(buf, "s de origem<") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->pais, limpaPais(limpaTag(buf)));
    serie->pais[strcspn(serie->pais, "\n")] = ' ';

    while (strstr(buf, ">Idioma original<") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->idioma, limpaTag(buf));
    serie->idioma[strcspn(serie->idioma, "\n")] = ' ';

    while (strstr(buf, ">Emissora de televis") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->emissora, limpaTag(buf));
    serie->emissora[strcspn(serie->emissora, "\n")] = ' ';

    while (strstr(buf, ">Transmiss") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    strcpy(serie->transmicao, limpaPais(limpaTag(buf)));
    serie->transmicao[strcspn(serie->transmicao, "\n")] = ' ';

    while (strstr(buf, " de temporadas<") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    serie->temporadas = trataInt(limpaTag(buf));
    //serie->temporadas[strcspn(serie->temporadas, "\n")] = ' ';

    while (strstr(buf, " de epis") == NULL)
        fgets(buf, 3000, fp);
    fgets(buf, 3000, fp);
    serie->episodios = trataInt(limpaTag(buf));

    fclose(fp);
}

//funcao que imprimi de acordo com o meu pub.out
void imprimir(Serie serie)
{
    printf("%s%s%s%s%s%s%s%d %d\n", serie.nome, serie.formato, serie.duracao, serie.pais, serie.idioma, serie.emissora, serie.transmicao, serie.temporadas, serie.episodios);
}

//Parada no FIM
bool isFim(char s[])
{

    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main()
{
    char entrada[200][200];
    int numEntrada = 0;
    Serie series[61];
    //String endereco, nome, url= "/tmp/series/"; //quando for passar pro verde

    // Leitura da entrada padrao
    do
    {
        scanf(" %[^\n]s", entrada[numEntrada]);
        // fgets(entrada[numEntrada],1000,stdin);
        // entrada[numEntrada][strlen(entrada[numEntrada])-1] = '\0';
    } while (!isFim(entrada[numEntrada++]));
    numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

    //Leitura da entrada padrao
    for (int i = 0; i < numEntrada; i++)
    {
        series[i] = inicializaVazio();
        ler(&series[i], entrada[i]); //!
        imprimir(series[i]);
    }

    return 0;
}
