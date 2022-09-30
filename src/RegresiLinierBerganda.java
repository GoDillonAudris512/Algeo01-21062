package src;
import java.util.*;

import src.Matrix.RowCol; 

public class RegresiLinierBerganda {
    
    // Untuk persoalan polinom interpolasi dan regresi, luarannya adalah persamaan
    // polinom/regresi dan taksiran nilai fungsi pada x yang diberikan. Contoh
    // luaran untuk interpolasi adalah 𝑓(𝑥)= -0.0064𝑥 + 0.2266 + 0.6762 , 2
    // 𝑥 𝑓(5) =… dan untuk regresi adalah f(x) = -9.5872 + 1.0732𝑥1, 𝑓(𝑥𝑘)= …

    // Untuk persoalan regresi, masukannya jika dari keyboard adalah n (jumlah
    // peubah x), m (jumlah sampel), semua nilai-nilai x1i, x2i, ..., xni, nilai yi, dan
    // nilai-nilai xk yang akan ditaksir nilai fungsinya. Jika masukannya dari file,
    // maka titik-titik dinyatakan pada setiap baris tanpa koma dan tanda kurung.

    double regresiLinierBerganda () {
        double hasilTaksiran;
        int i, j, m, n;
        int pilihanIn;    
        boolean benar;
        Matrix mObj = new Matrix();
        double[][] XY, X, Xt, XtX, Y, XtY, XtXXtY, Xk, b; 
        InversAdjoint invAdj = new InversAdjoint();
        MatriksBalikan matBal = new MatriksBalikan();
        InterpolasiBikubik ipBik = new InterpolasiBikubik();
        Gauss gauss = new Gauss();
        String pathname = new String();
        
        // XY = new double[m][n+1]; 
        // X = new double[m][n+1];
        // Y = new double[m][1];
        // Xt = new double[n][m];
        // XtX = new double[n][n];
        // XtY = new double[n][1];
        // XtXXtY = new double[n][n+1];
        // Xk = new double[1][n];
        // b = new double[n][1];

        // Asumsi input x1i, x2i, ..., xni, dan nilai yi disatukan dalam satu baris
        System.out.println("Pilihan input: (Masukkan kode angka)");
        System.out.println("1. Keyboard");
        System.out.println("2. File");

        Scanner sc= new Scanner(System.in); //System.in is a standard input stream  
        pilihanIn = sc.nextInt();    
        benar = false;
        while (!benar) {
            if (pilihanIn == 1) {
                System.out.println("Masukkan m: ");
                m = sc.nextInt();    

                System.out.println("Masukkan n: ");
                n = sc.nextInt();    
                
                XY = new double[m][n+1]; 
                X = new double[m][n+1];
                Y = new double[m][1];
                Xt = new double[n][m];
                XtX = new double[n][n];
                XtY = new double[n][1];
                XtXXtY = new double[n][n+1];
                Xk = new double[n];
                b = new double[n];
        
                mObj.readMatrixFromKeyboard(XY); //Membaca input nilai-nilai x1i, x2i, ..., xni, dan nilai yi sekaligus
                mObj.readMatrixFromKeyboard(Xk); // Membaca nilai Xk yang akan ditaksir yang akan ditaksir fungsinya

                benar = true;
            } else if (pilihanIn == 2) {
                RowCol rc = new RowCol();
                
                System.out.println("Pastikan file sudah berada di folder input.");
                System.out.println("Masukkan nama file input X dan Y: ");


                pathname = sc.nextLine();
                pathname = ".\\input\\" + pathname;

                
                mObj.readMatrixFromFile(XY, pathname);
                
                System.out.println("Masukkan nama file input Xk yang ingin ditaksir: ");

                pathname = sc.nextLine();
                pathname = ".\\input\\" + pathname;
                
                mObj.colRowNumbersFromFile (rc, pathname);
                m = rc.row;
                n = rc.col-1;

                XY = new double[m][n+1]; 
                X = new double[m][n+1];
                Y = new double[m][1];
                Xt = new double[n][m];
                XtX = new double[n][n];
                XtY = new double[n][1];
                XtXXtY = new double[n][n+1];
                Xk = new double[n];
                b = new double[n];

                mObj.readMatrixFromFile(Xk, pathname);

                benar = true;
            } else {
                System.out.println("Pilihan salah, mohon masukkan input yang benar.");
            }
    
        }

        X = matBal.splitMainMatrix(XY);
        for (i=0; i<mObj.getnRows(X); i++) {
            for (j=0; j<mObj.getnCols(X); j++) {
                if (j==0) {
                    X[i][j] = 1;
                } else {
                    X[i][j] = X[i][j-1];
                }
            }
        }

        Y = matBal.splitHasil(XY);
        Xt = invAdj.transposeMatrix(X);
        XtX = matBal.multiplyMatrixbyMatrix(Xt, X);
        XtY = matBal.multiplyMatrixbyMatrix(Xt, Y);

        XtXXtY = IpBik.integrateMatrixAandB(double[][] XtX, double[][] XtY);        
        b = gauss.gaussEliminationSolutionKeluaran(XtXXtY);

        hasilTaksiran = 0;
        for (i=0; i<n; i++) {
            hasilTaksiran += Xk[i] * b[i];
        }
        
        return hasilTaksiran;
    }
}