package engtelecom.rtr;

import java.util.Scanner;

public class Pcm {
    private String paq = "0011011";
    private String arquivo;
    //private int[] b;

    /*public void openFile(Scanner entrada){
        while(entrada.hasNext()){
            arquivo = entrada.nextLine();
        }
        separaByte();
    }

    private void separaByte(){
        int cont=0;
        char[] var = arquivo.toCharArray();
        char[] byyte;
        for (int i = 0; i < var.length; i++) {
            if (i == 7) {
                byyte == paq;
            } else{
                 byyte = var[i];
            }
        }
    }*/

    public void openFile(Scanner entrada){
        while(entrada.hasNextLine()){
            arquivo = entrada.nextLine();
            separaByte();
        }
    }

    private void separaByte(){
        int cont = 0;
        if(arquivo.contains(paq)){
            cont++;
            if(cont == 3){
                for (int i = (arquivo.indexOf(paq)+7); (i < arquivo.length()) && (i < (arquivo.indexOf(paq)+17)); i++) {
                    System.out.print(arquivo.charAt(i));
                }
                System.out.println();
            }
        } else{
            System.out.println("sequencia não eocntrada");
        }
    }
}
