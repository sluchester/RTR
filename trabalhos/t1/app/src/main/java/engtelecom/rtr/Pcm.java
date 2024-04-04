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
            String total = formatString();
            int finalIndex = i+8;
            System.out.println(finalIndex);

            if(finalIndex >= total.length()){
                break;
            } else{
                String quadro = total.substring(i, finalIndex);
                System.out.println("lendo string quadro " + quadro);

                if(quadro.equals("10011011")){
                    System.out.println("PAQ " + quadro + " na posição " + i);
                    String confirmacaoAlinhamento = total.substring(i+263,i+271);
                    if(confirmacaoAlinhamento.charAt(1) == '1'){
                        //achei a confirmação de que o PAQ anterior era verdadeiro
                        //tenho que pegar a posição dele e fazer contar a partir dali
                        System.out.println("PAQ verdadeiro");
                        int posPAQ = quadro.indexOf(quadro);
                        showAfterPaq(posPAQ);break;
                    } else{
                        System.out.println("achou PAQ, mas não confirmou se é verdadeiro");
                    }
                }
            }
            //se dois quadros a frente tiver paq, ele é verdadeiro
        }
    }

    private void showAfterPaq(int posPaq){
        int timeSlot = 0;
        int quadro = 0;

        for (int i = posPaq; i < formatString().length(); i++) {
            String total = formatString();
            int finalIndex = i+8;

            if(timeSlot > 32){
                System.out.println("------------------------------------");
                System.out.println("QUADRO " + quadro);
                System.out.println("------------------------------------");
                timeSlot = 0;
                quadro += 1;
            } else{
                System.out.println("------------------------------------");
                System.out.println("Timeslot " + timeSlot);
                String palavra = total.substring(i, finalIndex);
                System.out.println(palavra);
                System.out.println("------------------------------------");
                timeSlot+=1;
            }

            i = finalIndex + 1;
        }
    }
}
