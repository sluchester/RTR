package engtelecom.rtr;

import java.util.Arrays;
import java.util.Scanner;

public class Pcm {
    private String paq = "0011011";
    private String arquivo;
    private int[] bits;

    public void openFile(Scanner entrada){
        while(entrada.hasNextLine()){
            arquivo = entrada.nextLine();
        }
        System.out.println(arquivo.length());
        convert();
    }

    private void convert(){
        bits = new int[arquivo.length()];

        for (int i = 0; i < arquivo.length(); i++) {
            char caractere = arquivo.charAt(i);
            if(caractere == '0'){
                bits[i] = '0';
            } else if (caractere == '1') {
                bits[i] = '1';
            } else{
                System.out.println("caractere invalido");
            }
        }
    }

    private void separaByte(){
    }
}
