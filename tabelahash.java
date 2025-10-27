import java.util.LinkedList;

class NoInterior {
    public static final int EOL = -1; // End of List/Compartimento Vazio
    
    int valor;    // O dado armazenado
    int proximo;  // Índice do próximo elemento na lista encadeada (ou EOL)
    boolean ocupado; // Flag para saber se o compartimento está em uso

    public NoInterior() {
        this.valor = EOL;
        this.proximo = EOL;
        this.ocupado = false;
    }
}

public class tabelahash{
    private NoInterior[] tabela;
    private int p; // Tamanho da Região de Endereço Base (0 a p-1)
    private int s; // Tamanho da Zona de Colisão (p a p+s-1)
    private int m; // Tamanho Total da Tabela (p + s)
    private int proximaLivreColisao; // Mantém o índice do primeiro slot livre na Zona de Colisão
    // construtor: inicializa as listas
    public tabelahash() {
        this.p = tamanhoBase;
        this.s = tamanhoColisao;
        this.m = p + s;
        this.tabela = new NoInterior[m];
        
        // Inicializa todos os compartimentos
        for (int i = 0; i < m; i++) {
            tabela[i] = new NoInterior();
        }
        
        // A primeira posição livre para colisão é o início da zona de colisão
        this.proximaLivreColisao = p;
        // Inicializa todos os compartimentos
        for (int i = 0; i < m; i++) {
            tabela[i] = new NoInterior();
        }
        
        // A primeira posição livre para colisão é o início da zona de colisão
        this.proximaLivreColisao = p;
    }

    public int metodoDivisao(int x){//função de dispersão
        int resultado = x % 7;
        return resultado;

    }
    public void inclusao(int x) {
        if (busca(x) == 1) {
            System.out.println("O elemento " + x + " já foi cadastrado!\n");
            return;
        }
        int enderecoBase = calculaHash(x);
        if (!tabela[enderecoBase].ocupado) {
            tabela[enderecoBase].valor = x;
            tabela[enderecoBase].ocupado = true;
            tabela[enderecoBase].proximo = NoInterior.EOL;
            System.out.println("Inserido " + x + " na posição base " + enderecoBase);
            return;
        }
        int indiceColisao = encontrarLivreColisao();
        if (indiceColisao == NoInterior.EOL) {
            System.out.println("ERRO: Overflow! A zona de colisão está cheia. Não foi possível inserir " + x + "\n");
            return;
        }
        tabela[indiceColisao].valor = x;
        tabela[indiceColisao].ocupado = true;
        tabela[indiceColisao].proximo = NoInterior.EOL; // É o novo fim da lista
        System.out.println("Colisão para " + x + "! Inserido em " + indiceColisao + " (Zona de Colisão)");
        int atual = enderecoBase;
        while (tabela[atual].proximo != NoInterior.EOL) {
            atual = tabela[atual].proximo;
        }
        tabela[atual].proximo = indiceColisao;
    }

    public int busca(int x){
        //Calcular o endereço aplicando função h(x)
        int endereco = metodoDivisao(x);
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

    private int encontrarLivreColisao() {
        // proximaLivreColisao vai de p até m-1
        if (proximaLivreColisao < m) {
            return proximaLivreColisao++;
        }
        return NoInterior.EOL; // Indica Overflow/Tabela cheia
    }

    public static void main(String[] args){
        tabelahash tabela = new tabelahash();
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
    }
}