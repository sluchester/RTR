package engtelecom.rtr;

import java.util.Scanner;

public class Pcm {
    private int paq[]= {0011011};
    private String arquivo;

    public void openFile(Scanner entrada){
        while(entrada.hasNext()){
            String linha = entrada.nextLine();
            //TODO testar entrada.nextByte() - ver o que vai sair com isso
            //lembrando que tem que ler byte por Byte (8 bits)

            System.out.println(linha);
        }
    }

    //fazer um metodo que separe a linha lida em 8 bits

}
