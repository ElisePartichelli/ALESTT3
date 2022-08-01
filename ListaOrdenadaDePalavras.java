/**
 * Esta classe guarda as palavra do indice remissivo em ordem alfabetica.
 * @author Isabel H. Manssour
 */

public class ListaOrdenadaDePalavras {
    private Palavra head;
    private Palavra tail;
    private int count;
    private StopwordList listaStopwords;
    private int ignored;
    private Palavra maiorOcorrencia;

    // Classe interna 
    private class Palavra {
        public String s;
        public ListaDeOcorrencias listaOcorrencias;
        public Palavra next;
        
        // public Palavra(String str) {
        //     s = str;
        //     next = null;
        //     listaOcorrencias = new ListaDeOcorrencias();
        // }

        //Faz mais sentido o construtor ter já um numero de pagina
        public Palavra(String str, int numPag){
            s = str;
            next = null;
            listaOcorrencias = new ListaDeOcorrencias();
            listaOcorrencias.add(numPag);
        }

        @Override
        public String toString() {
            return "Palavra: "+ this.s + "\nOcorrencias: " + this.listaOcorrencias.toString() + "\n\n";
    }}
        
    public ListaOrdenadaDePalavras(){
        head = null;
        tail = null;
        count = 0;
        listaStopwords = new StopwordList();
        ignored = 0;
        maiorOcorrencia = null;
    }// Construtores

    public ListaOrdenadaDePalavras(StopwordList stopwordList){
        head = null;
        tail = null;
        count = 0;
        listaStopwords = stopwordList;
        ignored = 0;
        maiorOcorrencia = null;
    }

    public int getStopwordsNum(){
        return ignored;
    }

    public String getMaiorOcorrencia(){
        return maiorOcorrencia.toString();
    }


    /**
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * @param element elemento a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina  
     * recebido por parametro, e false caso contrario.
     * @throws Exception
     */
    protected void add(String a, int numPagina) throws Exception{
        if(this.listaStopwords.contains(a)){
            ignored++;
            return;
        }
        if(count == 0){
            Palavra palavraNova = new Palavra(a,numPagina);
            this.head = palavraNova;
            this.tail = palavraNova;
            count++;
            maiorOcorrencia = palavraNova;
        }

        Palavra palavraNova = new Palavra(a,numPagina);
        int position = getPosition(a);
        if(position == count){
            this.tail.next = palavraNova;
            this.tail = palavraNova;
            count++;
            return;
        }
        if(position == 0){
            palavraNova.next = this.head;
            this.head = palavraNova;
            count++;
            return;
        }
        if(position>0){
            Palavra existente = get(position);
            existente.listaOcorrencias.add(numPagina);
            if(existente.listaOcorrencias.size()>maiorOcorrencia.listaOcorrencias.size()) maiorOcorrencia = existente;
            return;
        }
        else{
            position = Math.abs(position);
            palavraNova.next = get(position).next;
            get(position).next = palavraNova;
            count++;
        }
    }// metodo add para adicionar uma palavra na lista
        
    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() {
        return (head == null);
    }   
    
    /**
     * Retorna o numero de elementos da lista.
     * @return o numero de elementos da lista
     */
    public int size() {
        return count;
    }  
    
    /**
     * Esvazia a lista
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posição da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Palavra get(int index) throws IndexOutOfBoundsException {
        if (index<0 || index >= size()) {
            throw new IndexOutOfBoundsException(); 
        }
        Palavra atual = head;
        while (index>0){
            atual = atual.next;
            index--;
        }
        return atual;
    }
 
    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * @param palavra o elemento a ser procurado
     * @return index caso a palavra esteja na lista ou -1 caso não esteja
     */
    public int contains(String palavra) {
        Palavra atual = head;
        int index = 0;
        while(atual != null){
            if ((atual.s).equals(palavra)) {
                return index;
            }
            atual = atual.next;
            index++;
        }
        return -1;
    }   

    /**
     * Retorna a posição na lista se a lista contem o palavra passada
     * por parametro
     * Ou (0 - pos) sendo pos a posição do ultimo elemento cuja palavra é menor lexicograficamente
     * Ou 0 caso a palavra seja menor lexicograficamente que a raiz da Lista.
     * @param palavra o elemento a ser procurado
     * @return index caso a palavra esteja na lista ou -(lastIndex) caso não esteja 
     * (sendo lastIndex a posicão onde a palavra estaria, caso estivesse na lista ordenada)
     */
    protected int getPosition(String palavra) throws Exception{
        Palavra atual = head;
        Integer lastval = null;
        int index = 0;
        while(atual != null){
            int val = (atual.s).compareTo(palavra);
            if (val<0){
                lastval = index;
                atual = atual.next;
                index++;
                continue;
            }
            else if(val == 0){
                return index;
            }
            else{
                if(lastval == null) return 0;
                return Math.negateExact(lastval);
            }
            
        }
        return count;
    }
    
    @Override
    public String toString() {
        String retorno = "";
        Palavra atual = head;
        while(atual != null){
            retorno += atual.toString();
            atual = atual.next;
        }
        return retorno;
    }


    public String getByWord(String str) {
        Palavra atual = head;
        while(atual != null){
            if ((atual.s).equals(str)) {
                return atual.toString();
            }
            atual = atual.next;
        }
        return "Palavra não está na lista.";
    }
    
}
