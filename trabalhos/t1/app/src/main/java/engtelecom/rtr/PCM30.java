package engtelecom.rtr;

import java.util.Scanner;

public class PCM30 {
    private int paq[]= {0011011};
    private String arquivo;

    //constructor
    PCM30(String arquivo){
        this.arquivo = arquivo;
        openFile(this.arquivo);
    }

    public void openFile(String arq){
        Scanner entrada = new Scanner(System.in);

        while(entrada.hasNext()){
            String linha = entrada.nextLine();
        }
    }

    //fazer um metodo que separe a linha lida em 8 bits

}
