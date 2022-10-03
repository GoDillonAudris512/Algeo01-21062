package Library;

import Matrix.*;
import java.util.*;
import Matrix.Matrix.RowCol; 

public class RegresiLinierBerganda {

    public void regresiLinierBerganda() {
        // Method/prosedur ini menghitung hasil taksiran regresi berganda dan menulis output ke layar atau file
        /* I.S tidak ada
         * F.S hasil taksiran regresi berganda ditulis ke layar atau file 
         */

        // KAMUS LOKAL
        double hasilTaksiran;
        int i, j, m, n;
        String pilihanIn, pilihanOut;    
        boolean regMenuRunning = true;
        Matrix mObj = new Matrix();
        double[][] XY, X, Xt, XtX, Y, XtY, XtXXtY, Xk, X1;
        Object[] bObj;
        double[] b, isNull1 = new double[0], isNull2 = new double[0]; 
        InversAdjoint invAdj = new InversAdjoint();
        MatriksBalikan matBal = new MatriksBalikan();
        InterpolasiBikubik ipBik = new InterpolasiBikubik();
        Scanner keyboard = new Scanner(System.in);
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
        while (regMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Regresi Linear Berganda");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihanIn = keyboard.next();

            while ((!pilihanIn.equals((String) "1")) && (!pilihanIn.equals((String) "2")) && (!pilihanIn.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihanIn = keyboard.next();
            }

            if (pilihanIn.equals((String) "1")) {
                System.out.print("\nMasukkan m: ");
                m = keyboard.nextInt();    

                System.out.print("Masukkan n: ");
                n = keyboard.nextInt();    
                    
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
            } 
            else if (pilihanIn.equals((String) "2")) {
                RowCol rc = new RowCol();
                    
                System.out.print("\nMasukkan nama file input XY: ");

                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull1 = mObj.colRowNumbersFromFile (rc, pathname);

                if (isNull1 != null) {
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
                        
                    System.out.print("Masukkan nama file input Xk yang ingin ditaksir: ");
                    pathname = keyboard.next();

                    pathname = "..\\test\\" + pathname;
                        
                    isNull2 = mObj.colRowNumbersFromFile (rc, pathname);
                    if (isNull2 != null) {
                        m = 1;

                        Xk = new double[1][n];
                        bObj = new Object[n+1];
                        b = new double[n+1];

                        mObj.readMatrixFromFile(Xk, pathname);
                    }
                }
            } 

            if (pilihanIn.equals((String) "0") || isNull1 == null || isNull2 == null) {
                regMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
            else {
                // Memisahkan matrikx XY menjadi matriks X dan Y
                X1 = matBal.splitMainMatrix(XY);
                
                // Menambahkan kolom berisi angka 1 pada kolom ke-1
                for (i=mObj.getLastIdxRows(X); i>=0; i--) {
                    for (j=mObj.getLastIdxCols(X); j>=0; j--) {
                        if (j==0) {
                            X[i][j] = 1;
                        } else {
                            X[i][j] = X1[i][j-1];
                        }
                    }
                }       

                Y = matBal.splitHasil(XY);
                Xt = invAdj.transposeMatrix(X);     

                // Menghitung b
                XtX = matBal.multiplyMatrixbyMatrix(Xt, X);
                XtY = matBal.multiplyMatrixbyMatrix(Xt, Y);

                XtXXtY = ipBik.integrateMatrixAandB(XtX, XtY);        

                gauss.gaussElimination(XtXXtY, false);
                bObj = gauss.gaussEliminationSolution(XtXXtY);

                for (i=0; i<n+1; i++) {
                    b[i] = (double) bObj[i];
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

                output = "f(Xk) = " + hasilTaksiran;
                System.out.println("\n" + output);
                
                // Menulis keluaran
                System.out.println("\nApakah output ingin disimpan ke dalam file?");
                System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke menu utama : ");
                pilihanOut = keyboard.next();

                if (pilihanOut.equals((String) "y")) {
                    pathname = "";
                    System.out.print("\nMasukkan nama file output : ");
                    pathname = keyboard.next();
                    pathname = "..\\test\\resultTestCase\\" + pathname;
                        
                    mObj.writeGeneralStringToFile(output, pathname);

                    System.out.println("\nKembali ke menu utama");
                }
                else{
                    System.out.println("Kembali ke menu utama");
                }
            }
        }
    }
}