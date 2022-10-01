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

    public int inputNilaiTaksiranX() {
        Scanner keyboard = new Scanner(System.in);  // Create a Scanner object

        int x = keyboard.nextInt();  // Read user input
        System.out.println("Nilai x yang ingin ditaksir: " + x);  // Output user input

        keyboard.close();

        return x;
    }

    public double[] inputTitikX(int jumlahTitik) {
        int i;
        double[] titikX = new double[jumlahTitik];
        Scanner keyboard = new Scanner(System.in);

        for (i = 0; i < jumlahTitik; i++) {
            titikX[i] = keyboard.nextDouble();
            System.out.println("Masukkan Titik X ke-" + (i+1) + " " + titikX[i]);
        
        }

        keyboard.close();
        return titikX;
    }

    public double[] inputTitikY(int jumlahTitik) {
        int i;
        double[] titikY = new double[jumlahTitik];
        Scanner keyboard = new Scanner(System.in);

        for (i = 0; i < jumlahTitik; i++) {
            titikY[i] = keyboard.nextDouble();
            System.out.println("Masukkan Titik Y ke-" + (i+1)+ " " + titikY[i]); 
        }

        keyboard.close();

        return titikY;
    }

    public double[][] makePersamaanMatrix(double[][] matrix) {
        int i,j;
        Matrix matObj = new Matrix();
        double[][] matrixPers = new double[matObj.getnRows(matrix)][matObj.getnRows(matrix)+1];

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            for (j = 0; j < matObj.getnRows(matrix); j++) {
                matrixPers[i][j] = Math.pow(matrix[i][0], j);
            }
        }

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            matrixPers[i][matObj.getnRows(matrix)-1] = matrix[i][1];
        }

        return matrixPers;
    }

    public double makePersamaan(Object[] hasilA, double x, int jumlahTitik) {
        int i;
        double hasil = 0;
        
        for (i = 0; i < jumlahTitik; i++) {
            hasil += (double) hasilA[i] * Math.pow(x,i);
        }

        return hasil;
    }

    public void printPersamaan(double[][] matrix) {
        int i,j;
        String persamaan = "p(x)";
        double[][] mainMat, hasil;
        Matrix matObj = new Matrix();
        MatriksBalikan mat = new MatriksBalikan();

        mainMat = mat.splitMainMatrix(matrix);
        hasil = mat.splitHasil(matrix);

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            for (j = 0; j < matObj.getnCols(matrix); j++) {
                persamaan += "a" + i + Math.pow(mainMat[i][j],i);
                persamaan += " = " + hasil[i][j]; 
            }
            System.out.println(persamaan); // ngeprint persamaan
        }
    }

    public Object[] interpolasiPolinom (double[][] titik, int jumlahTitik) {
        Object[] hasiltemp;
        int i;
        
        Gauss matObj = new Gauss();

        Object[] hasilA = new Object[jumlahTitik];
        double[][] matrixPers = new double[jumlahTitik][jumlahTitik+1];

        matrixPers = makePersamaanMatrix(titik);
        
        matObj.gaussElimination(matrixPers); // metode gauss
        hasiltemp = matObj.gaussEliminationSolution(matrixPers);  // mengambil matrix hasil dari operasi Gauss

        for (i = 0; i < jumlahTitik; i++) {
            hasilA[i] = hasiltemp[i]; // matrix hasil di assign ke hasilA
        }

        return hasilA;
    }
}
