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
    private Integer posPAMQ;

    private final int BIT_FINAL = 8;

    public Pcm(String formattedString) {
        this.formattedString = formattedString;
    }

    private boolean confirmPAQ(int posInicial) {
        posInicial += 256;
        //int posFinal = posInicial + 256 + BIT_FINAL;
        int posFinal = posInicial + BIT_FINAL;

        //System.out.println("PASSOU PARA VALIDAR O PAQ");

        String palavra = formattedString.substring(posInicial, posFinal);
        if (palavra.charAt(1) == '1') {
            posInicial += 256;
            posFinal = posInicial + BIT_FINAL;
            palavra = formattedString.substring(posInicial, posFinal);
            if(palavra.equals(paq)){
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean findPAQ(String palavra, int posInicial) {
        if (palavra.equals(paq)) {
            if (confirmPAQ(posInicial)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public int findPAMQ(int posInicial){
        int posFinal = 0;
        //logica ta ERRADA
         //somar a posição do paq com + 128 bits. Se não achar, tem que se somar 256 bits para ver se acha
         //e assim ficar indo até achar

        do{
            posInicial += 128;
            posFinal = posInicial + BIT_FINAL;
        }while (!formattedString.substring(posInicial, posFinal).startsWith("0000"));

        return posInicial;
    }

    public void runTillPAQ() {
        //System.out.println(formattedString.length());
        for (int i = 0; i < formattedString.length(); i++) {
            try {
                String palavra = formattedString.substring(i, i + BIT_FINAL);
                //System.out.println(palavra);
                if (findPAQ(palavra, i)) {
                    //a partir do primeiro paq verdadeiro, descarta os bits anteriores e
                    System.out.println("achou paq verdadeiro " + palavra + " na posição " + i);

                    posPAMQ = findPAMQ(i);
                    System.out.println("pamq na posição " + posPAMQ);
                    //System.out.println(formattedString.length());
                }
            } catch (Exception e) {
                System.out.println("chegou ao fim da sequência de bits");
                break;
            }
        }
        //formattedString = formattedString.substring(posPAMQ);
    }

    public void printTimeslot(String palavra, int contTimeslot){
        System.out.println("---Timeslot " + contTimeslot + "---");
        System.out.println("    " + palavra + " ");
        System.out.println("----------------");
    }

    public void printQuadro(int contQuadro){
        System.out.println("***************");
        System.out.println("***QUADRO " + contQuadro + "***");
        System.out.println("***************");
    }

    public void findPAMQ() {
        int contTimeslot = 0;
        int contQuadro = 0;
        System.out.println(formattedString.length());

        printQuadro(contQuadro);

        for (int i = 0; i < formattedString.length(); i++) {
            String palavra = formattedString.substring(i, i + BIT_FINAL);

            if (contTimeslot < 32) {
                if ((contQuadro == 0) && (contTimeslot == 16)) {
                    System.out.println("PAMQ " + palavra);
                    if (palavra.startsWith("0000")) {
                        System.out.println(palavra);
                        System.out.println("PAMQ achado " + " b4 = " + palavra.charAt(4) + " b5 = " + palavra.charAt(5));
                        System.out.println("------------------");
                    }
                    //canal de alinhamento
                } else if (contTimeslot == 0) {
                    printTimeslot(palavra,contTimeslot);
                    //canal de sinalização
                } else if (contTimeslot == 16) {
                    printTimeslot(palavra,contTimeslot);
                } else {
                    System.out.println("---Timeslot " + contTimeslot + "---");
                    System.out.println("   b0= " + palavra.charAt(0) + " b1= " + palavra.charAt(1));
                    System.out.println("----------------");
                }
                contTimeslot += 1;
            } else {
                if(contQuadro == 16){
                    contQuadro = 0;
                    contTimeslot = 0;
                    printQuadro(contQuadro);
                } else{
                    contQuadro += 1;
                    contTimeslot = 0;
                    printQuadro(contQuadro);
                }
            }

            //avança para pegar a próxima palavra
            i += 9;
        }
    }
}
    /*public void pamq(){
        int contTimeslot = 0;
        int contQuadro = 0;

        for (int i = 0; i < formattedString.length(); i++) {
            Timeslot timeslot = new Timeslot(formattedString.substring(i, i + BIT_FINAL));
            if(contTimeslot < 33){
                timeslot.addTimeslot(contTimeslot);
                contTimeslot+=1;
            } else{
                quadro.addQuadro();
                contQuadro+=1;
                contTimeslot=0;
            }
            //avança para pegar a próxima palavra
            i+=9;
        }
    }

    /*public void alignment(int posInicial){
        HashMap<Integer, String> timeslotTable =  new HashMap<Integer, String>();
        int key = 0;

        for (int i = posInicial; i < formattedString.length(); i++) {
            String palavra = formattedString.substring(i,i+BIT_FINAL);

            //a chava é o número dos timeslots
            if(key == 33){
                key= 0;
            } else{
                //tem que verificar se ele não vai sobreescrever a chave e o valor da tabela
                //falta validar isso aqui
                timeslotTable.put(key,palavra);
                key+=1;
            }

            //avança para pegar a próxima palavra
            i+=9;
        }
    }*/