/* Dada a sequência de bits E1 recebida, fazer um programa para encontrar o alinhamento de quadro (PAQ) 
 * e mostrar na saída os bits alinhados. A saída deve apresentar todos os quadros alinhados a partir do
 * primeiro PAQ verdadeiro encontrado.
 */

package engtelecom.rtr;

import java.util.Arrays;
import java.util.Scanner;

public class Pcm {
    private String paq = "10011011";
    private String arquivo;

    public void openFile(Scanner entrada){
        while(entrada.hasNextLine()) {
            arquivo += entrada.nextLine();
        }
    }

    private String formatString(){
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

    public void findPaq(){
        for (int i = 0; i < formatString().length(); i++) {
            String str = formatString().substring(i, i+8);
            if(str.equals(paq)){
                System.out.println("achou PAQ");
            } else{
                
            }

            /*if((i+8) == formatString().length()){
                break;
            } else{
                if (str.equals(paq)) {
                    System.out.println("Encontrou PAQ na posição " + i);
                    if(str.charAt(1) == '1'){
                        System.out.println("bit b2 setado em 1");
                    }
                } else{

                }
            }*/
        }
    }
}
