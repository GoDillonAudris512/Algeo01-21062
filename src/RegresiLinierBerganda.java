package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import src.Matrix.RowCol; 

public class RegresiLinierBerganda {

    void regresiLinierBerganda() {
        // Method/prosedur ini menghitung hasil taksiran regresi berganda dan menulis output ke layar atau file
        /* I.S tidak ada
         * F.S hasil taksiran regresi berganda ditulis ke layar atau file 
         */

        // KAMUS LOKAL
        double hasilTaksiran;
        int i, j, m, n;
        int pilihanIn, pilihanOut;    
        boolean benar;
        Matrix mObj = new Matrix();
        double[][] XY, X, Xt, XtX, Y, XtY, XtXXtY, Xk;
        double[] b; 
        InversAdjoint invAdj = new InversAdjoint();
        MatriksBalikan matBal = new MatriksBalikan();
        InterpolasiBikubik ipBik = new InterpolasiBikubik();
        Gauss gauss = new Gauss();
        String pathname = new String();
        String output;

        // ALGORITMA
        XY = new double[0][0];
        Xk = new double[0][0];
        n = 0;
        // Asumsi input x1i, x2i, ..., xni, dan nilai yi disatukan dalam satu baris
        System.out.println("Pilihan input: (Masukkan kode angka)");
        System.out.println("1. Keyboard");
        System.out.println("2. File");

        Scanner sc= new Scanner(System.in);
        pilihanIn = sc.nextInt();    
        benar = false;
        while (!benar) {
            if (pilihanIn == 1) {
                System.out.println("Masukkan m: ");
                m = sc.nextInt();    

                System.out.println("Masukkan n: ");
                n = sc.nextInt();    
                
                XY = new double[m][n+1]; 
                X = new double[m][n];
                Y = new double[m][1];
                Xt = new double[n][m];
                XtX = new double[n][n];
                XtY = new double[n][1];
                XtXXtY = new double[n][n+1];
                Xk = new double[1][n];
                bObj = new Object[n];
                b = new double[n];
        
                System.out.println("Masukkan matriks XY: ");
                mObj.readMatrixFromKeyboard(XY); //Membaca input nilai-nilai x1i, x2i, ..., xni, dan nilai yi sekaligus

                System.out.println("Masukkan Xk yang ingin ditaksir: ");
                mObj.readMatrixFromKeyboard(Xk); // Membaca nilai Xk yang akan ditaksir yang akan ditaksir fungsinya

                benar = true;
            } else if (pilihanIn == 2) {
                RowCol rc = new RowCol();
                
                System.out.println("Pastikan file sudah berada di folder input.");
                System.out.println("Masukkan nama file input XY: ");

                pathname = sc.nextLine();
                pathname = ".\\test\\" + pathname;

                mObj.colRowNumbersFromFile (rc, pathname);
                m = rc.row;
                n = rc.col-1;
                
                XY = new double[m][n+1]; 
                X = new double[m][n];
                Y = new double[m][1];
                Xt = new double[n][m];
                XtX = new double[n][n];
                XtY = new double[n][1];
                XtXXtY = new double[n][n+1];

                mObj.readMatrixFromFile(XY, pathname);
                
                System.out.println("Masukkan nama file input Xk yang ingin ditaksir: ");

                pathname = sc.nextLine();
                pathname = ".\\test\\" + pathname;
                
                mObj.colRowNumbersFromFile (rc, pathname);
                m = 1;

                Xk = new double[1][n];
                bObj = new Object[n];
                b = new double[n];

                mObj.readMatrixFromFile(Xk, pathname);

                benar = true;
            } else {
                System.out.println("Pilihan salah, mohon masukkan input yang benar.");
            }
    
        }

        // Memisahkan matrikx XY menjadi matriks X dan Y
        X = matBal.splitMainMatrix(XY);

        Y = matBal.splitHasil(XY);
        Xt = invAdj.transposeMatrix(X);
        
        // Menghitung b
        XtX = matBal.multiplyMatrixbyMatrix(Xt, X);
        XtY = matBal.multiplyMatrixbyMatrix(Xt, Y);

        XtXXtY = ipBik.integrateMatrixAandB(XtX, XtY);        
        bObj = gauss.gaussEliminationSolution(XtXXtY);

        for (i=0; i<n; i++) {
            b[i] = (double) bObj[i];
        }

        // Menghitung taksiran
        hasilTaksiran = 0;
        for (i=0; i<n; i++) {
            hasilTaksiran += Xk[0][i] * b[i];
        }
        
        // Menulis keluaran
        System.out.println("Pilihan keluaran: (Masukkan angkanya)");
        System.out.println("1. Layar");
        System.out.println("2. File");

        pilihanOut = sc.nextInt();
        output = "f(Xk) = " + hasilTaksiran;
        
        if (pilihanOut == 1) { // Ke layar
            System.out.println(output);

        } else {    // Ke file
            try {
                File myObj = new File("filename.txt");
                
                if (myObj.createNewFile()) {
                    System.out.println("File berhasil dibuat: " + myObj.getName());
                } else {
                    System.out.println("File sudah ada, akan menulis ke file yang sudah ada.");
                }
                
                FileWriter myWriter = new FileWriter(pathname);
                myWriter.write(output);
                myWriter.close();
                System.out.println("Berhasil menulis ke file.");    
    
            } catch (IOException e) {
                System.out.println("Terjadi error.");
                e.printStackTrace();
            }
    
        }

        sc.close();
    }
}