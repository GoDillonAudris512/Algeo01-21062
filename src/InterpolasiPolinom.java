package src;
import java.util.Scanner;

public class InterpolasiPolinom {
    public int inputJumlahTitik() {
        Scanner keyboard = new Scanner(System.in);  // Create a Scanner object

        int jlhTitik = keyboard.nextInt();  // Read user input
        System.out.println("Jumlah Titik Masukan: " + jlhTitik);  // Output user input

        keyboard.close();

        return jlhTitik;
    }

    public double[] inputTitikX(int jumlahTitik) {
        int i;
        double[] titikX = new double[jumlahTitik];
        Scanner keyboard = new Scanner(System.in);

        for (i = 0; i < jumlahTitik; i++) {
            System.out.print("Masukkan Titik X ke-" + (i+1));
            titikX[i] = keyboard.nextDouble();

        }

        keyboard.close();
        return titikX;
    }

    public double[] inputTitikY(int jumlahTitik) {
        int i;
        double[] titikY = new double[jumlahTitik];
        Scanner keyboard = new Scanner(System.in);

        for (i = 0; i < jumlahTitik; i++) {
            System.out.print("Masukkan Titik Y ke-" + (i+1));
            titikY[i] = keyboard.nextDouble(); 
        }

        keyboard.close();

        return titikY;
    }

    public double[][] makePersamaanMatrix(double[] titikX, double[] titikY) {
        int i,j;
        double[][] matrixPers = new double[titikY.length][titikX.length+1];

        for (i = 0; i < titikY.length; i++) {
            for (j = 0; j < titikX.length; j++) {
                matrixPers[i][j] = Math.pow(titikX[j], j);
            }
        }

        for (i = 0; i < titikY.length; i++) {
            matrixPers[i][titikX.length-1] = titikY[i];
        }

        return matrixPers;
    }

    public void interpolasiPolinom (double[][] matrix) {

    }
}
