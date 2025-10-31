public class TabelaDispersaoDup {
    Integer[] tabela = new Integer[10];

    // Função hash principal
    public int hash1(int x) {
        return x % 7;
    }

    // Segunda função hash (não pode dar zero)
    public int hash2(int x) {
        return 1 + (x % 5);
    }

    // Inserção usando dispersão dupla
    public void inserir(int x) {
        int h1 = hash1(x);
        int h2 = hash2(x);
        int pos = h1;
        int i = 0;

        while (tabela[pos] != null) { // posição ocupada?
            if (tabela[pos] == x) {
                System.out.println("O elemento " + x + " já existe!");
                return;
            }
            i++;
            pos = (h1 + i * h2) % tabela.length; // tenta próxima posição
        }

        tabela[pos] = x;
        System.out.println("Inserido " + x + " na posição " + pos);
    }

    // Busca
    public boolean buscar(int x) {
        int h1 = hash1(x);
        int h2 = hash2(x);
        int pos = h1;
        int i = 0;

        while (tabela[pos] != null) {
            if (tabela[pos] == x) {
                System.out.println("Encontrado " + x + " na posição " + pos);
                return true;
            }
            i++;
            pos = (h1 + i * h2) % tabela.length;
        }

        System.out.println("O elemento " + x + " não foi encontrado!");
        return false;
    }

    // Remoção
    public void remover(int x) {
        int h1 = hash1(x);
        int h2 = hash2(x);
        int pos = h1;
        int i = 0;

        while (tabela[pos] != null) {
            if (tabela[pos] == x) {
                tabela[pos] = null;
                System.out.println("Removido " + x + " da posição " + pos);
                return;
            }
            i++;
            pos = (h1 + i * h2) % tabela.length;
        }

        System.out.println("O elemento " + x + " não foi encontrado para remoção!");
    }

    // Mostrar a tabela
    public void mostrar() {
        System.out.println("\nTabela atual:");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null)
                System.out.println(i + ": " + tabela[i]);
            else
                System.out.println(i + ": vazio");
        }
    }

    public static void main(String[] args) {
        TabelaDispersaoDup t = new TabelaDispersaoDup();

        t.inserir(0);
        t.inserir(7);   // colisão com 0 → vai pra outra posição
        t.inserir(14);  // colisão → vai pra outra posição
        t.inserir(8);
        t.inserir(15);  // colisão com 8
        t.mostrar();

        t.buscar(14);
        t.remover(7);
        t.mostrar();
    }
}
