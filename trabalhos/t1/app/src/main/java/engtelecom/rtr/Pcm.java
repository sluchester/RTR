package engtelecom.rtr;

import java.util.Scanner;

public class Pcm {
    private int paq[]= {0011011};
    //private String arquivo;
    private int[] b;

    public void openFile(Scanner entrada){
        while(entrada.hasNext()){
            //arquivo = entrada.nextLine();
            //arquivo = entrada.nextInt();
            System.out.println(entrada.nextInt());
            System.out.println("quebra");
            //System.out.println(entrada.length());
        }
    }


//    private void separaByte(){
//        //int aux = 1;
//        for (int i = 0; i < arquivo.length(); i++) {
//            b = arquivo.substring()
//        }
//    }
    //fazer um metodo que separe a linha lida em 8 bits

}
