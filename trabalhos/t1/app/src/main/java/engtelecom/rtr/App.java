/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package engtelecom.rtr;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Arquivo arquivo = new Arquivo(entrada);

        Pcm pcm = new Pcm(arquivo.formatString());
        pcm.runAll();
    }
}
