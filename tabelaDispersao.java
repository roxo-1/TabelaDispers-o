import java.util.LinkedList;

public class tabelaDispersao{
    LinkedList<Integer>[] hx = new LinkedList[10];
    // construtor: inicializa as listas
    public tabelaDispersao() {
        for (int i = 0; i < hx.length; i++) {
            hx[i] = new LinkedList<>();
        }
    }

    public int calculaHash(int x){//função h(x)
            int resultado = x % 7;
            System.out.println("Endereço calculado: " + resultado);
            return resultado;

    }

    public void inclusao(int x) {
        //Calcula o endereço aplicando a função h(x)
        int endereco = calculaHash(x);
        //Buscar registro na lista associada ao endereço h(x)
        int aux = busca(endereco);
        //Se o registro for encontrado, sinalizar que o elemento já foi cadastrado
        if(aux== 1){
            System.out.println("O elemento já foi cadastrado!\n");
        }
        //Caso contrário, inserir no final da lista
        else{
            hx[endereco].add(x); // guarda o valor na posição calculada
            System.out.println("Inserido " + x + " na posição " + endereco);
        }
    }

    public int busca(int x){
        //Calcular o endereço aplicando função h(x)
        int endereco = calculaHash(x);
        //Percorrer a lista encadeada associada ao endereço;
        if (hx[endereco].contains(x)) {//Comparar a chave do nó da lista encadeada com a chave x, até encontrar o nó desejado.
            //.cointains() percorre a lista encadeada
            return 1;
        } else {
            //Se o final for atingido, emita a mensagem de que não foi encontrado.
            System.out.println("O elemento não foi encontrada!\n");
            return 0;
        }
    }

    public void remocao(int x){
        //Calcular o endereço aplicando função h(x)
        int endereco = calculaHash(x);
        //Buscar elemento na lista associada a h(x)
        boolean removido = hx[endereco].remove((Integer)x);
        //Se o registro for encontrado, excluir o registro.
        if(removido){
            System.out.println("Foi removido!");
        }
        //Caso contrário, emita a mensagem de que não foi encontrado.
        else{
            System.out.println("O elemento não foi encontrado!");
        }
    }

    
    
    public static void main(String[] args){
        tabelaDispersao tabela = new tabelaDispersao();
        //testes
        // Inserir elementos
        System.out.println("=== Inclusão de elementos ===");
        tabela.inclusao(0);
        tabela.inclusao(7);  // Colisão com 0 (0 % 7 == 0, 7 % 7 == 0)
        tabela.inclusao(1);
        tabela.inclusao(8);  // Colisão com 1
        tabela.inclusao(14); // Colisão com 0
        tabela.inclusao(3);
        tabela.inclusao(10);

        // Tentar inserir um elemento já existente
        tabela.inclusao(3);  // Deve avisar que já foi cadastrado

        // Buscar elementos
        System.out.println("\n=== Busca de elementos ===");
        tabela.busca(0);   // Deve encontrar
        tabela.busca(7);   // Deve encontrar
        tabela.busca(5);   // Não existe, deve avisar

        // Remover elementos
        System.out.println("\n=== Remoção de elementos ===");
        tabela.remocao(0); // Remover 0
        tabela.remocao(7); // Remover 7 (mesmo endereço de 0)
        tabela.remocao(5); // Não existe, deve avisar

        // Buscar depois da remoção
        System.out.println("\n=== Busca após remoção ===");
        tabela.busca(0);  // Não deve encontrar
        tabela.busca(7);  // Não deve encontrar
        tabela.busca(3);  // Ainda existe
    }
}