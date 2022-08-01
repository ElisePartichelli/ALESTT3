import java.util.Scanner;



/**
 * Classe que inicializa a execução da aplicacao.
 * @author Isabel H. Manssour
 */
public class Main {
    private static String[] options = {"1- Exibir todo o indice remissivo (em ordem alfabetica)",
                        "2- Exibir o percentual de stopwords do texto (quando % do texto eh formado por stopwords)",
                        "3- Encontrar a palavra mais frequente, isto é, com maior numero de ocorrencias",
                        "4- Pesquisar uma palavra",
                        "5- Encerrar o programa"};

    private static int countword = 0;
    public static void main(String[] args) throws Exception {
    
    Scanner readMenu = new Scanner(System.in);
    int nLinha = 0;
    int nPagina = 1;

    
    ArquivoTexto arquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
    LinhaTexto linha = new LinhaTexto(); // objeto que gerencia uma linha
    String l;

    StopwordList stopwords = stopwordsGenerate("StopWords-EN.txt");
    ListaOrdenadaDePalavras lista = new ListaOrdenadaDePalavras(stopwords); //A lista em si.

    System.out.println("Digite o nome arquivo texto a ser executado: ");
    arquivo.open("textos/"+ readMenu.nextLine());
    //arquivo.open(args[0]); // use esta linha para ler o nome do arquivo pela linha de comando
    do  // laco que passa em cada linha do arquivo
    {
        l = arquivo.getNextLine();
        if (l==null) // acabou o arquivo?
           break;
        nLinha++; // conta mais uma linha lida do arquivo
        if (nLinha == 40) // chegou ao fim da pagina?
        {
            nLinha = 0;
            nPagina++;
        }
        //System.out.println("Linha " + nLinha + ":");

        linha.setLine(l); // define o texto da linha
        do // laco que passa em cada palavra de uma linha
        {
            String palavra = linha.getNextWord(); // obtem a proxima palavra da linha
            if (palavra == null)// acabou a linha
            {
                break;
            }
            countword++;
            lista.add(palavra, nPagina);
            //System.out.println("-" + palavra + "-");
         } while (true);

    } while (true);
    


    menu(readMenu, lista);

    arquivo.close();
    readMenu.close();
    //System.out.println(lista.toString());
    }

    private static void menu(Scanner scanner, ListaOrdenadaDePalavras lista){
        int option = 0;
        while (option!=5){
            printMenu();
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: System.out.println(lista.toString()); break;
                    case 2: System.out.println(lista.getStopwordsNum() + " stopwords no texto, de um total de " +  countword + " palavras. Represtando " + (double)lista.getStopwordsNum()/(double)countword*100 + "% do texto"); break;
                    case 3: System.out.println(lista.getMaiorOcorrencia()); break;
                    case 4: buscaPalavra(scanner, lista);break;
                    case 5: return;
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    private static void buscaPalavra(Scanner scanner, ListaOrdenadaDePalavras lista) {
        System.out.println("Digite a palavra a ser buscada na lista: ");
        System.out.println(lista.getByWord(scanner.next()));
    }

    private static void printMenu(){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Escolha sua opcao: ");
    }

    private static StopwordList stopwordsGenerate(String nomeArquivo) throws Exception{
        ArquivoTexto stopWordsarquivo = new ArquivoTexto(); // objeto que gerencia o arquivo
        System.out.println("textos/"+nomeArquivo);
        stopWordsarquivo.open("textos/"+nomeArquivo); //O programa preve que todos os arquivos de texto estão na pasta texto
        String l = stopWordsarquivo.getNextLine();
        StopwordList stopwordList = new StopwordList();

        while(l!=null){
            stopwordList.add(l); //Em uma lista de stopwords cada linha é uma palavra
            l = stopWordsarquivo.getNextLine();
        }

    return stopwordList;
    }

}

