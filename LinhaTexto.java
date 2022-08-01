/**
 *
 * @author Isabel H. Manssour
 */
public class LinhaTexto {
    private String linha;
    private String palavras[];
    private int contPalavras;
    
    /**
     * Recebe a string da linha que sera armazenada.
     * @param string com a linha de texto
     */
    public void setLine(String lin) {
        linha = lin;
        linha = linha.replaceAll("\\t"," "); // substitui tab por espaco em branco
        linha = linha.replaceAll(",",""); // para remover vírgulas
        linha = linha.replaceAll("\\.",""); // para remover ponto final
        linha = linha.replaceAll("\\?",""); // para remover ponto interrogacao
        linha = linha.replaceAll("\\!",""); // para remover ponto exclamacao
        linha = linha.replaceAll(":",""); // para remover :
        linha = linha.replaceAll("'",""); // para remover '
        linha = linha.replaceAll("--",""); // para remover --
        linha = linha.replaceAll(";",""); // para remover ;
        linha = linha.replaceAll("\\(",""); // para remover (
        linha = linha.replaceAll("\\)",""); // para remover )
        linha = linha.replaceAll("\"",""); // para remover aspas
        linha = linha.toLowerCase(); //Para minusculo
        palavras = linha.split(" "); // divide a string pelo espaco em branco 
        contPalavras = 0;
    }
    
    /**
     * Retorna uma palavra da linha.
     * @return a palavra, ou null caso nao tenha mais palavras.
     */
    public String getNextWord() {
      String pal = null;
      if (contPalavras < palavras.length) {
          pal = palavras[contPalavras];
          contPalavras++;
      }
      return pal;
    }
}
