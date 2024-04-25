/* Dada a sequência de bits E1 recebida, fazer um programa para encontrar o alinhamento de quadro (PAQ)
 * e mostrar na saída os bits alinhados. A saída deve apresentar todos os quadros alinhados a partir do
 * primeiro PAQ verdadeiro encontrado.
 */

/**
 * PARTE 2
 *  A partir dos quadros alinhados localizar o sincronismo de Multiquadro (PAMQ) e
 *  extrair os bits de sinalização de todos os canais de voz.
 *  b0, b1 e b4, b5 do TS16 de todos os quadros do multiquadro.
 * PAMQ = 0000XXXX
 */

package engtelecom.rtr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Pcm {
    private String paq = "10011011";
    private String formattedString;

    private final int BIT_FINAL = 8;

    public Pcm(String formattedString) {
        this.formattedString = formattedString;
    }

    private boolean confirmPAQ(int posInicial){
        posInicial+=256;
        int posFinal = posInicial + 256 + BIT_FINAL;

        System.out.println("PASSOU PARA VALIDAR O PAQ");

        String palavra = formattedString.substring(posInicial,posFinal);
        if(palavra.charAt(1) == '1'){
            return true;
        } else{
            return false;
        }
    }

    public boolean findPAQ(String palavra, int posInicial){
        if(palavra.equals(paq)){
            if(confirmPAQ(posInicial)){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    public void runAll(){
        for (int i = 0; i < formattedString.length(); i++) {
            try{
                String palavra = formattedString.substring(i,i+BIT_FINAL);
                //System.out.println(palavra);
                if(findPAQ(palavra, i)){
                    System.out.println("achou paq verdadeiro" + palavra + " na posição " + i);
                    alignment(i);
                }
            } catch (Exception e){
                System.out.println("chegou ao fim");
                break;
            }
        }
    }

    public void alignment(int posInicial){
        HashMap<Integer, String> timeslotTable =  new HashMap<Integer, String>();
        int key = 0;

        for (int i = posInicial; i < formattedString.length(); i++) {
            String palavra = formattedString.substring(i,i+BIT_FINAL);

            //a chava é o número dos timeslots
            if(key == 33){
                key= 0;
            } else{
                //TODO
                //tem que verificar se ele não vai sobreescrever a chave e o valor da tabela
                //falta validar isso aqui
                timeslotTable.put(key,palavra);
                key+=1;
            }

            //avança para pegar a próxima palavra
            i+=9;
        }
    }
}
