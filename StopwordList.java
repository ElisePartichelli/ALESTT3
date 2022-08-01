public class StopwordList {
        
        // Classe interna Node
        private class Node {
            public String stopword;
            public Node next;    
            public Node(String stopword) {
                this.stopword = stopword;
                next = null;
            }
        }
        
        // Atributos
        private Node head;
        private Node tail;
        private int count;
    
        // Metodos 
        public StopwordList() {
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
    
        public boolean add(String stopword)  {
            if(count == 0){
                Node novaOcorrencia = new Node(stopword);
                this.tail = novaOcorrencia;
                this.head = novaOcorrencia;
                count++;
            }
            if(contains(stopword)){
                return false;
            }
            
            Node novaOcorrencia = new Node(stopword);
            this.tail.next = novaOcorrencia;
            this.tail = novaOcorrencia;
            count++;
            return true;
        }  
        
        /**
         * Retorna o elemento de uma determinada posicao da lista.
         * @param index a posição da lista
         * @return o elemento da posicao especificada
         * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
         */    
        public String get(int index) {
            if (index<0 || index >= size()) {
                throw new IndexOutOfBoundsException(); 
            }
            Node atual = head;
            while (index>0){
                atual = atual.next;
                index--;
            }
            return atual.stopword;
        }
     
        /**
         * Retorna true se a lista contem o numero de pagina passado
         * por parametro.
         * @param stopword o elemento a ser procurado
         * @return true se a lista contem o elemento especificado
         */
        public boolean contains(String stopword) {
            Node atual = head;
            while(atual != null){
                if (atual.stopword.equals(stopword)) {
                    return true;
                }
                atual = atual.next;
            }
            return false;
        }    
        
        @Override
        public String toString() {
            return "Todo: Implement toString";
        }
    
}
