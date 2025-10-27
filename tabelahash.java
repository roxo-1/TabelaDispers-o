import java.util.LinkedList;

public class tabelahash{
    LinkedList<Integer>[] hx = new LinkedList[10];
    // construtor: inicializa as listas
    public tabelahash() {
        for (int i = 0; i < hx.length; i++) {
            hx[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args){
                tabelahash tabela = new tabelahash();

    }
}