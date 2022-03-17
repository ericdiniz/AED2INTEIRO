#include <stdio.h> //  Arquivo de cabeçalho (header)
void main()
{
    int n = 0;
    scanf("%d", n);
    int soma = somatorio(n);
    printf("%d", soma);
}

int somatorioPA(double a, double b, int n)
{
    return ((a + b)*n);
}