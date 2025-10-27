import java.util.LinkedList;

public class tabelahash{
    LinkedList<Integer>[] hx = new LinkedList[10];
    // construtor: inicializa as listas
    public tabelahash() {
        for (int i = 0; i < hx.length; i++) {
            hx[i] = new LinkedList<>();
        }
    }

    public int metodoDivisao(int x){//função h(x)
        int resultado = x % 7;
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

    private boolean buscarInterno(int chave) {
        int endereco = metodoDivisao(chave);
        return hx[endereco].contains(chave);
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

    public void encadeamentoInterior(int x){
        //
    }

    public static void main(String[] args){
                tabelahash tabela = new tabelahash();

    }
}