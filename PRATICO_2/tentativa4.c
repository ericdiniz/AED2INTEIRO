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

//funcao que le no meu pub.in
void ler(Serie *serie, char *nome)
{
     FILE *fp = fopen(nome, "r");
	//char filePath[200];
	//sprintf(filePath, "/tmp/series/%s", nome);
    //FILE *fp = fopen(filePath, "r");

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

#define MAXTAM 100

// typedef struct Lista{
//     int n;
//     Serie series[MAXTAM];
// }Lista;    

Serie series[MAXTAM];
int n;
void start(){ 
n = 0;
}

void inserirInicio(Serie x) {
if (n >= MAXTAM)
exit(1);
//levar elementos para o fim do series
for (int i = n; i > 0; i--){
series[i] = series[i-1];
}
series[0] = x;
n++;
}

void inserirFim(Serie x) {
if (n >= MAXTAM)
exit(1);
series[n] = x;
n++;
}

void inserir(Serie x, int pos) {
if (n >= MAXTAM || pos < 0 || pos > n)
exit(1);
//levar elementos para o fim do series
for (int i = n; i > pos; i--){
series[i] = series[i-1];
}
series[pos] = x;
n++;
}

Serie removerInicio() {
if (n == 0)
exit(1);
Serie resp = series[0];
n--;
for (int i = 0; i < n; i++){
series[i] = series[i+1];
}
return resp;
}

Serie removerFim() {
if (n == 0)
exit(1);
return series[--n];
}

Serie remover(int pos) {
if (n == 0 || pos < 0 || pos >= n)
exit(1);
Serie resp = series[pos];
n--;
for (int i = pos; i < n; i++){
series[i] = series[i+1];
}
return resp;
}

void mostrar (){
        
        for (int i = 0; i < n; i++){
            imprimir(series[i]);
        }
        
}

char* pegaString(char* old, int i){
    char *newLine = (char *)malloc(sizeof(char) * strlen(old));
    int j = 0;
    while (i < strlen(old))
    {
        newLine[j] = old[i];

        i++;
        j++;
    }
    newLine[j] = '\0';
    //printf("%s",newLine);
    return newLine;
}

int pegaNum(char* old){
    char newLine[2];
    newLine[0] = old[3];
    newLine[0] = old[4];
    int resp = atoi(newLine);
    return resp;
}

//Parada no FIM
bool isFim(char s[]){

    return (s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main (){
    char entrada[200][200];
    int numEntrada = 0;
    Serie s;
    int n;

        // Leitura da entrada padrao
        do
        {
            scanf(" %[^\n]s", entrada[numEntrada]);
            ler(&s , entrada[numEntrada]);
            inserirFim(s);
        } while (!isFim(entrada[numEntrada++]));
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

        //pega quantas operações ira fazer
        scanf("%d", &n);
        //faz as operaçoes
        
        for(int i = 0; i<n; i++){
            char linha[500]; 
            scanf(" %[^\n]s", linha);
            //ver qual eh a funçãoa ser usada
            if(strstr(linha, "II")!=NULL){
                char segundaParte[500];
                //inserir no inicio
                strcpy(segundaParte, pegaString(linha, 3));
                //printf("%s\n",segundaParte);
                ler(&s , segundaParte);
                inserirInicio(s);
            }
            if((strstr(linha, "I*")!=NULL)){
                //inserir em qualquer posiçao
                int pos = pegaNum(linha);
                char terceiraParte[500];
                strcpy(terceiraParte, pegaString(linha, 6));
                ler(&s , terceiraParte);
                inserir(s, pos);           
            }
            if((strstr(linha, "IF")!=NULL)){
                char segundaParte[500];
                //inserir no fim
                strcpy(segundaParte, pegaString(linha, 3));
                ler(&s , segundaParte);
                inserirFim(s);
            }
            if((strstr(linha, "RI")!=NULL)){
                //remover no inicio
                s = removerInicio();
                printf("(R) ");
                printf("%s\n", getNome(s));
            }
            if((strstr(linha, "R*")!=NULL)){
                //remover em qualquer lugar
                int pos = pegaNum(linha);
                s = remover(pos);
                printf("(R) ");
                printf("%s\n", getNome(s));
            }
            if((strstr(linha, "RF")!=NULL)){
                //remover no fim
                s = removerFim();
                printf("(R) ");
                printf("%s\n", getNome(s));
            }
        }
        mostrar();

    return 0;
}