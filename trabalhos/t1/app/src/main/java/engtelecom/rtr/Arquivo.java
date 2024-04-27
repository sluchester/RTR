package engtelecom.rtr;

import java.util.Scanner;

public class Arquivo {
    private Scanner arquivo;
    private String garbageString = "";
    private String formattedString = "";

    public Arquivo(Scanner arquivo) {
        this.arquivo = arquivo;
    }

    //abre o arquivo e coloca tudo dentro de uma string
    private String openFile(){
        String total = "";
        while(arquivo.hasNextLine()) {
            total += arquivo.nextLine();
        }
        return total;
    }

    //tendo a string lida, separa os bits dos caracteres que não serão necessários
    public String formatString(){
        String totalDeCaracteres = openFile();
        //System.out.println("total de caracteres antes" + totalDeCaracteres.length());

        for (int c = 0; c < totalDeCaracteres.length(); c++){
            if (totalDeCaracteres.charAt(c) == '0'){
                formattedString += '0';
            } else if (totalDeCaracteres.charAt(c) == '1'){
                formattedString += '1';
            } else{
                garbageString += totalDeCaracteres.charAt(c);
            }
        }
        //System.out.println(" Garbage String " + garbageString.length());
        //System.out.println(garbageString);
        //System.out.println("acabaou");
        //System.out.println("total de caracteres após tratamento " + formattedString.length());
        return formattedString;
    }
}
