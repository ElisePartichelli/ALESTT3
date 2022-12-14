

/**
 * Esta classe guarda os numeros das paginas em que uma palavra ocorre.
 * @author Isabel H. Manssour
 */
public class ListaDeOcorrencias {
        
    // Classe interna Node
    private class Node {
        public int numeroDaPagina;
        public Node next;    
        public Node(int n) {
            numeroDaPagina = n;
            next = null;
        }
    }
    
    // Atributos
    private Node head;
    private Node tail;
    private int count;

    // Metodos 
    public ListaDeOcorrencias() {
        head = null;
        tail = null;
        count = 0;
    }
    
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
     * Adiciona um numero de pagina ao final da lista, caso ele ainda
     * nao tenha sido adicionado.
     * @param element elemento a ser adicionado ao final da lista
     * @return true se adicionou no final da lista o numero de pagina  
     * recebido por parametro, e false caso contrario.
     */
    public boolean add(int numPagina)  {
        if(count == 0){
            Node novaOcorrencia = new Node(numPagina);
            this.tail = novaOcorrencia;
            this.head = novaOcorrencia;
            count++;
        }
        if(contains(numPagina)){
            return false;
        }
        
        Node novaOcorrencia = new Node(numPagina);
        this.tail.next = novaOcorrencia;
        this.tail = novaOcorrencia;
        count++;
        return true;
    }  
    
    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posi????o da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */    
    public Integer get(int index) {
        if (index<0 || index >= size()) {
            throw new IndexOutOfBoundsException(); 
        }
        Node atual = head;
        while (index>0){
            atual = atual.next;
            index--;
        }
        return atual.numeroDaPagina;
    }
 
    /**
     * Retorna true se a lista contem o numero de pagina passado
     * por parametro.
     * @param numPagina o elemento a ser procurado
     * @return true se a lista contem o elemento especificado
     */
    public boolean contains(int numPagina) {
        Node atual = head;
        while(atual != null){
            if (atual.numeroDaPagina == numPagina) {
                return true;
            }
            atual = atual.next;
        }
        return false;
    }    
    
    @Override
    public String toString() {
        String retorno = "[";
        Node atual = head;
        while(atual != null){
            retorno += atual.numeroDaPagina + ", ";
            atual = atual.next;
        }
        return retorno.substring(0, retorno.length()-2) + "]";
    }
}
