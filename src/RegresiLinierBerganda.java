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
        double[][] XY, X, Xt, XtX, Y, XtY, XtXXtY, Xk, X1;
        Object[] bObj;
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
        X = new double[0][0];
        
        n = 0;
        b = new double[0];
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
                X1 = new double[m][n];
                X = new double[m][n+1];
                Y = new double[m][1];
                Xt = new double[n+1][m];
                XtX = new double[n+1][n+1];
                XtY = new double[n+1][1];
                XtXXtY = new double[n+1][n+2];
                Xk = new double[1][n];
                bObj = new Object[n+1];
                b = new double[n+1];
        
                System.out.println("Matriks XY: ");
                mObj.readMatrixFromKeyboard(XY); //Membaca input nilai-nilai x1i, x2i, ..., xni, dan nilai yi sekaligus

                System.out.println("Matriks Xk yang ingin ditaksir: ");
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
                X1 = new double[m][n];
                X = new double[m][n+1];
                Y = new double[m][1];
                Xt = new double[n+1][m];
                XtX = new double[n+1][n+1];
                XtY = new double[n+1][1];
                XtXXtY = new double[n+1][n+2];

                mObj.readMatrixFromFile(XY, pathname);
                
                System.out.println("Masukkan nama file input Xk yang ingin ditaksir: ");
                pathname = sc.nextLine();

                pathname = ".\\test\\" + pathname;
                
                mObj.colRowNumbersFromFile (rc, pathname);
                m = 1;

                Xk = new double[1][n];
                bObj = new Object[n+1];
                b = new double[n+1];

                mObj.readMatrixFromFile(Xk, pathname);

                benar = true;
            } else {
                System.out.println("Pilihan salah, mohon masukkan input yang benar.");
            }
    
        }

        // Memisahkan matrikx XY menjadi matriks X dan Y
        X1 = matBal.splitMainMatrix(XY);

        System.out.println("X:");
        mObj.printMatrixToScreen(X);
        System.out.println("");        
        
        for (i=mObj.getLastIdxRows(X); i>=0; i--) {
            for (j=mObj.getLastIdxCols(X); j>=0; j--) {
                if (j==0) {
                    X[i][j] = 1;
                } else {
                    X[i][j] = X1[i][j-1];
                }
            }
        }

        System.out.println("X dengan 1:");
        mObj.printMatrixToScreen(X);
        System.out.println("");        

        Y = matBal.splitHasil(XY);
        System.out.println("Y:");
        mObj.printMatrixToScreen(Y);
        System.out.println("");

        Xt = invAdj.transposeMatrix(X);
        System.out.println("Xt:");
        mObj.printMatrixToScreen(Xt);
        System.out.println("");        

        // Menghitung b
        XtX = matBal.multiplyMatrixbyMatrix(Xt, X);
        XtY = matBal.multiplyMatrixbyMatrix(Xt, Y);

        XtXXtY = ipBik.integrateMatrixAandB(XtX, XtY);        

        System.out.println("XtX:");
        mObj.printMatrixToScreen(XtX);
        System.out.println("");

        System.out.println("XtY:");
        mObj.printMatrixToScreen(XtY);
        System.out.println("");

        System.out.println("XtXXtY:");
        mObj.printMatrixToScreen(XtXXtY);
        System.out.println("");
        
        gauss.gaussElimination(XtXXtY);
        bObj = gauss.gaussEliminationSolution(XtXXtY);

        for (i=0; i<n+1; i++) {
            b[i] = (double) bObj[i];
        }

        for (i=0; i<b.length; i++) {
            System.out.print(b[i] + " ");
        }
        // Menghitung taksiran
        hasilTaksiran = 0;

        for (i=0; i<n+1; i++) {
            if (i == 0) {
                hasilTaksiran += b[i];
            } else {
                hasilTaksiran += Xk[0][i-1] * b[i];
            }
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
                System.out.println("Masukkan nama file output: ");
                pathname = sc.nextLine();
                
                File myObj = new File(pathname);
                
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