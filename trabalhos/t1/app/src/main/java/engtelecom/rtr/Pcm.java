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
    private Integer posPAQ;

    private final int BIT_FINAL = 8;

    public Pcm(String formattedString) {
        this.formattedString = formattedString;
    }

    //confirma se o paq lido será achado no próximo quadro
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

    //verifica se a palavra lida é igual ao paq
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

    // a partir da posição de um PAQ verdadeiro, anda 128 bits para frente para achar o PAMQ.
     // Se achar, confirma 4096 bits a frente
    //se não achar em 128 bits, anda de 256 em 256 até achar o PAMQ
     // Se achar, confirma 4096 bits a frente
    // ao achar a posição do PAMQ, retorna ela
    public int findPAMQ(int posInicial){
        posInicial += 128;
        int posFinal = posInicial + 8;
        String palavra = formattedString.substring(posInicial,posFinal);

        if(palavra.startsWith("0000")){
            posInicial += 4096;
            posFinal = posInicial + BIT_FINAL;
            palavra = formattedString.substring(posInicial,posFinal);
            if(palavra.startsWith("0000")){
                return posInicial;
            }
        } else{
            boolean flag = true;
            posInicial += 256;
            posFinal = posInicial + 8;

            while(flag){
                palavra = formattedString.substring(posInicial,posFinal);
                if(palavra.startsWith("0000")){
                    posInicial += 4096;
                    posFinal = posInicial + BIT_FINAL;
                    palavra = formattedString.substring(posInicial,posFinal);
                    if(palavra.startsWith("0000")){
                        flag = false;
                    }
                }else{
                    posInicial += 256;
                    posFinal = posInicial + 8;
                }
            }
        }
        return posInicial;
    }

    //percorre bit a bit do arquivo formatado até achar o paq e o pamq.
    // Ao achar, corta a string a partir dali para ficar com apenas os quadros alinhados
    public void runTillPAQ() {
        //System.out.println("qtde inciial: " + formattedString.length());

        for (int i = 0; i < formattedString.length(); i++) {
            try {
                String palavra = formattedString.substring(i, i + BIT_FINAL);
                //System.out.println(palavra);
                if (findPAQ(palavra, i)) {
                    System.out.println("achou paq verdadeiro " + palavra + " na posição " + i);
                    posPAQ = i;

                    posPAMQ = findPAMQ(i);
                    System.out.println("PAMQ achado na posição " + (posPAMQ-4096));
                    System.out.println("Confirmação do PAMQ na posição " + posPAMQ);
                    //System.out.println(formattedString.length());
                    break;
                }
            } catch (Exception e) {
                //System.out.println("chegou ao fim da sequência de bits");
                break;
            }
        }
        this.formattedString = formattedString.substring(posPAQ);
        //System.out.println("qtde final " + formattedString.length());
    }

    //printa na tela do usuário com a formatação adequada os timeslots
    public void printTimeslot(String palavra, int contTimeslot){
        System.out.println("---Timeslot " + contTimeslot + "---");
        System.out.println("    " + palavra + " ");
        System.out.println("----------------");
    }

    //printa na tela do usuário, com a formatação adequada, apenas o timeslot 16
    public void printTimeslot16(String palavra, int contTimeslot){
        System.out.println("---Timeslot " + contTimeslot + "---");
        System.out.println(" b4 = " + palavra.charAt(4) + " b5 = " + palavra.charAt(5));
        System.out.println("----------------");
    }

    //printa na tela do usuário com a formatação adequada os quadros
    public void printQuadro(int contQuadro){
        System.out.println("***************");
        System.out.println("***QUADRO " + contQuadro + "***");
        System.out.println("***************");
    }

    //percorre o arquivo alinhado, separa em palavras de 8 bits e printa em timeslots e quadros
    public void findPAMQ() {
        //System.out.println("qtde ao chegar findpamq " + formattedString.length());

        int contTimeslot = 0;
        int contQuadro = 0;

        printQuadro(contQuadro);

        for (int i = 0; i < formattedString.length(); i += 8) {
            String palavra = formattedString.substring(i,i+BIT_FINAL);

            if (contTimeslot < 32) {
                if ((contQuadro == 0) && (contTimeslot == 16)) {
                    System.out.println("PAMQ " + palavra);
                    printTimeslot16(palavra, contTimeslot);
                } else if (contTimeslot == 0) {
                    printTimeslot(palavra,contTimeslot);
                    //canal de sinalização
                } else if (contTimeslot == 16) {
                    printTimeslot16(palavra,contTimeslot);
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
            //i += 8;
        }
    }
}