package engtelecom.rtr;

import java.util.Arrays;
import java.util.Scanner;

public class Pcm {
    private String paq = "10011011";
    private String arquivo;
    private String[] byyts;

    public void openFile(Scanner entrada){
        while(entrada.hasNextLine()) {
            arquivo += entrada.nextLine();
        }
//        System.out.println(arquivo.length());
//        System.out.println(arquivo);
    }

    public String formatString(){
        String formattedString = "";
        for (int c = 0; c < arquivo.length(); c++){
            if (arquivo.charAt(c) == '0'){
                formattedString += '0';
            } else if (arquivo.charAt(c) == '1'){
                formattedString += '1';
            }
        }
        return formattedString;
    }

    /*public void testEE(){
        System.out.println(formatString(arquivo));
    }*/
    /*private String[] convert(){
        int[] bits = new int[arquivo.length()];
        byyts = new String[arquivo.length()];
        int cont = 0;
        int byyteIndex = 0;

        for (int i = 0; i < arquivo.length(); i++) {
            if(cont < 8) {
                char caractere = arquivo.charAt(i);
                if (caractere == '0') {
                    bits[cont] = '0';
                } else if (caractere == '1') {
                    bits[cont] = '1';
                } else {
                    System.out.println("caractere invalido");
                }
                cont++;
            } else {
                for (int j = 0; j < bits.length; j++) {
                    //String str = bits[j];
                }
                //byyts[byyteIndex] =
                cont = 0;
                byyteIndex++;
            }
        }
        return byyts;
    }*/

    public void findPaq(){
        for (int i = 0; i < formatString().length(); i++) {
            String str = formatString().substring(i, i+8);

            if (str.equals(paq)) System.out.println("Encontrou na posição " + i);

            // System.out.println(str);
        }
    }
}
