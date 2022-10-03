package Library;

import Matrix.*;
import java.util.Scanner;

public class InterpolasiPolinom {
    public int inputJumlahTitik() {
        Scanner keyboard = new Scanner(System.in);  // Create a Scanner object

        System.out.print("\nJumlah titik masukan : ");  // Output user input
        int jlhTitik = keyboard.nextInt();  // Read user input

        return jlhTitik;
    }

    public double inputNilaiTaksiranX() {
        Scanner keyboard = new Scanner(System.in);  // Create a Scanner object

        System.out.print("Nilai x yang ingin ditaksir : ");  // Output user input
        double x = keyboard.nextDouble();  // Read user input

        return x;
    }

    public double[][] makePersamaanMatrix(double[][] matrix) {
        int i,j;
        Matrix matObj = new Matrix();
        double[][] matrixPers = new double[matObj.getnRows(matrix)][matObj.getnRows(matrix)+1];

        for (i = 0; i < matObj.getnRows(matrixPers); i++) {
            for (j = 0; j < matObj.getnCols(matrixPers) - 1; j++) {
                matrixPers[i][j] = Math.pow(matrix[i][0], j);
            }
        }

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            matrixPers[i][matObj.getLastIdxCols(matrixPers)] = matrix[i][1];
        }

        return matrixPers;
    }

    public Object[] interpolasiPolinom (double[][] titik, int jumlahTitik) {
        Gauss matObj = new Gauss();

        Object[] hasilA = new Object[jumlahTitik];
        double[][] matrixPers = new double[jumlahTitik][jumlahTitik+1];

        matrixPers = makePersamaanMatrix(titik);
        
        matObj.gaussElimination(matrixPers, true); // metode gauss
        hasilA = matObj.gaussEliminationSolution(matrixPers);  // mengambil matrix hasil dari operasi Gauss

        return hasilA;
    }
}
