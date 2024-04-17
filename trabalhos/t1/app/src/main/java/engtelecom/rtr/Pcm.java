/* Dada a sequência de bits E1 recebida, fazer um programa para encontrar o alinhamento de quadro (PAQ)
 * e mostrar na saída os bits alinhados. A saída deve apresentar todos os quadros alinhados a partir do
 * primeiro PAQ verdadeiro encontrado.
 */

/**
 * PARTE 2
 *  A partir dos quadros alinhados localizar o sincronismo de Multiquadro (PAMQ) e
 *  extrair os bits de sinalização de todos os canais de voz.
 *  b0, b1 e b4, b5 do TS16 de todos os quadros do multiquadro.
 *
 * PAMQ = 000XXXX
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
            //System.out.println(finalIndex);

            if(finalIndex >= total.length()){
                break;
            } else{
                String quadro = total.substring(i, finalIndex);
                System.out.println("lendo sequencia de bits " + quadro);

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
        }
    }

    private void showAfterPaq(int posPaq){
        int timeSlot = 0;
        int quadro = 0;
        boolean signal = false;

        for (int i = posPaq; i < formatString().length(); i++) {
            String total = formatString();
            int finalIndex = i+8;

            if(quadro == 0 && timeSlot == 16){
                String palavra = total.substring(i, finalIndex);
            }

            if(timeSlot > 32){
                System.out.println("------------------------------------");
                System.out.println("QUADRO " + quadro);
                System.out.println("------------------------------------");
                timeSlot = 0;
                quadro += 1;
            } /*else if(quadro == 0 && timeSlot == 16){
                System.out.println("PAMQ");
                String palavra = total.substring(i, finalIndex);
                System.out.println(palavra);
                break;
            }*/ else{
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
