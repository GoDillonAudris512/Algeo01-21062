package src;

import java.text.DecimalFormat;
import java.util.Scanner;

import src.Matrix.RowCol;

public class MainApp {

    public static void subMenuSPL() {
        /* Merupakan submenu untuk penyelesaian SPL dengan 4 metode*/

        /* Kamus Lokal */
        int pilihan;

        /* Algoritma */
        System.out.println("---------------------------------------------------");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.print("Silahkan masukkan metode yang diinginkan (1-4) : ");

        Scanner inputMetodeSPL = new Scanner(System.in);
        pilihan = inputMetodeSPL.nextInt();
        inputMetodeSPL.close();

        if (pilihan == 1) {
            solveSPLWithGauss();
        }
        else if (pilihan == 2) {
            solveSPLWithGaussJordan();
        }
        else if (pilihan == 3) {
            solveSPLWithMetodeInvers();
        }
        else if (pilihan == 4) {
            solveSPLWithCramer();
        }
        else {
            System.out.println("\nTerjadi error pada input. Kembali ke menu utama");
        }
    }

    /* SUBMENU DETERMINAN */
    public static void subMenuDeterminan() {
        /* Merupakan submenu untuk mendapatkan determinan suatu matriks dengan 2 metode */

        /* Kamus Lokal */
        int pilihan;
        boolean determinanMenuRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);

        while (determinanMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Determinan");
            System.out.println("1. Metode Reduksi Baris");
            System.out.println("2. Metode Ekspansi Kofaktor");
            System.out.print("Silahkan masukkan metode yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.nextInt();

            while (((pilihan != 1) && (pilihan != 2) && (pilihan != 0))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                solveDeterminanWithReduksiBaris();
            }
            else if (pilihan == 2) {
                solveDeterminanWithEkspansiKofaktor();
            }
            else {
                determinanMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
        }
    }

    /* Still in work: output to file */
    public static void solveDeterminanWithReduksiBaris() {
        /* Merupakan kode untuk menentukan determinan matriks dengan metode reduksi baris */
        
        /* Kamus Lokal */
        int nRows, nCols, pilihan;
        boolean detRedBarMenuRunning = true;
        double determinan;
        String pathname;
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        DeterminanReduksiBaris detRedBarObj = new DeterminanReduksiBaris();
        Scanner keyboard = new Scanner(System.in);

        /* Menampilkan subsubmenu yang berisi cara input matriks beserta permintaan input kepada pengguna */
        while (detRedBarMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Determinan Metode Reduksi Baris. Mohon masukkan suatu matriks persegi");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Determinan : ");
            pilihan = keyboard.nextInt();

            while ((pilihan != 1) && (pilihan != 2) && (pilihan != 0)) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan == 2) {
                RowCol rc = new RowCol();
                System.out.println("\nMasukkan nama dari file :");
                pathname = keyboard.next();
                pathname = ".\\test\\" + pathname ;

                matrixObj.colRowNumbersFromFile(rc, pathname);
                m = new double[rc.row][rc.col];
                matrixObj.readMatrixFromFile(m, pathname);
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan == 0 ) {
                detRedBarMenuRunning = false;
                System.out.println("Kembali ke submenu Determinan");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Determinan Metode Reduksi Baris");
                }
                else {
                    determinan = detRedBarObj.determinanReduksiBaris(m);

                    System.out.println("\nDeterminan dari matriks tersebut adalah " + determinan);

                    System.out.println("\nApakah output ingin disimpan ke file?");
                }
            }
        }
    }

    /* Still in work : output to file */
    public static void solveDeterminanWithEkspansiKofaktor() {
        /* Merupakan kode untuk menentukan determinan matriks dengan metode ekspansi kofaktor */
        
        /* Kamus Lokal */
        int nRows, nCols, pilihan;
        boolean detKofMenuRunning = true;
        double determinan;
        String pathname;
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        Scanner keyboard = new Scanner(System.in);

        /* Menampilkan subsubmenu yang berisi cara input matriks beserta permintaan input kepada pengguna */
        while (detKofMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Determinan Metode Ekspansi Kofaktor. Mohon masukkan suatu matriks persegi");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Determinan : ");
            pilihan = keyboard.nextInt();

            while ((pilihan != 1) && (pilihan != 2) && (pilihan != 0)) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan == 2) {
                RowCol rc = new RowCol();
                System.out.println("\nMasukkan nama dari file :");
                pathname = keyboard.next();
                pathname = ".\\test\\" + pathname ;

                matrixObj.colRowNumbersFromFile(rc, pathname);
                m = new double[rc.row][rc.col];
                matrixObj.readMatrixFromFile(m, pathname);
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan == 0 ) {
                detKofMenuRunning = false;
                System.out.println("Kembali ke submenu Determinan");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Determinan Metode Ekspansi Kofaktor");
                }
                else {
                    determinan = detKofObj.determinanKofaktor(m);

                    System.out.println("\nDeterminan dari matriks tersebut adalah " + determinan);

                    System.out.println("\nApakah output ingin disimpan ke file?");
                }
            }
        }
    }
    
    /* SUBMENU INVERS */
    public static void subMenuInvers() {
        /* Merupakan submenu untuk mendapatkan invers suatu matriks dengan 2 metode */

        /* Kamus Lokal */
        int pilihan;
        boolean inversMenuRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);

        while (inversMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Invers");
            System.out.println("1. Metode Reduksi Baris");
            System.out.println("2. Metode Adjoint");
            System.out.print("Silahkan masukkan metode yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.nextInt();

            while (((pilihan != 1) && (pilihan != 2) && (pilihan != 0))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                solveInversWithReduksiBaris();
            }
            else if (pilihan == 2) {
                solveInversWithAdjoint();;
            }
            else{
                inversMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
        }
    }

    /* Still in work : output to file */
    public static void solveInversWithReduksiBaris() {
        /* Merupakan kode untuk menyelesaikan invers dengan metode reduksi baris */

        /* Kamus Lokal */
        int nRows, nCols, pilihan;
        boolean invRedBarMenuRunning = true;
        String pathname;
        double[][] m  = new double[0][0], mZero, mInvers;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        InversReduksiBaris invRedBarObj = new InversReduksiBaris();
        Scanner keyboard = new Scanner(System.in);

        /* Menampilkan subsubmenu yang berisi cara input matriks beserta permintaan input kepada pengguna */
        while (invRedBarMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Invers Metode Reduksi Baris. Mohon masukkan suatu matriks persegi");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Invers : ");
            pilihan = keyboard.nextInt();

            while ((pilihan != 1) && (pilihan != 2) && (pilihan != 0)) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan == 2) {
                RowCol rc = new RowCol();
                System.out.println("\nMasukkan nama dari file :");
                pathname = keyboard.next();
                pathname = ".\\test\\" + pathname ;

                matrixObj.colRowNumbersFromFile(rc, pathname);
                m = new double[rc.row][rc.col];
                matrixObj.readMatrixFromFile(m, pathname);
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan == 0 ) {
                invRedBarMenuRunning = false;
                System.out.println("Kembali ke submenu Invers");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Invers Metode Reduksi Baris");
                }
                else {
                    mZero = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];
                    mInvers = invRedBarObj.inversByReduksiBaris(m);

                    if (matrixObj.isMatrixEqual(mZero, mInvers) == false) {
                        System.out.println("\nInvers dari matriks berupa: ");
                        matrixObj.printMatrixToScreen(mInvers);

                        System.out.println("\nApakah output ingin disimpan ke file?");
                    }

                }
            }
        }
    }
    
    /* Still in work: output to file */
    public static void solveInversWithAdjoint() {
        /* Merupakan kode untuk menyelesaikan matriks dengan metode adjoint matriks */

        /*/ kamus Lokal */
        int nRows, nCols, pilihan;
        boolean invAdjMenuRunning = true;
        String pathname;
        double[][] m = new double[0][0], mZero, mInvers;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        InversAdjoint invAdjObj = new InversAdjoint();
        Scanner keyboard = new Scanner(System.in);

        /* Menampilkan subsub menu yang berisi cara input matriks beserta permintaan input kepada pengguna */
        while (invAdjMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Invers Metode Adjoint. Mohon masukkan suatu matriks persegi");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Invers : ");
            pilihan = keyboard.nextInt();

            while ((pilihan != 1) && (pilihan != 2) && (pilihan != 0)) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan == 2) {
                RowCol rc = new RowCol();
                System.out.println("\nMasukkan nama dari file :");
                pathname = keyboard.next();
                pathname = ".\\test\\" + pathname ;

                matrixObj.colRowNumbersFromFile(rc, pathname);
                m = new double[rc.row][rc.col];
                matrixObj.readMatrixFromFile(m, pathname);
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan == 0) {
                invAdjMenuRunning = false;
                System.out.println("Kembali ke submenu Invers");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Invers Metode Adjoint");
                }
                else {
                    mZero = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];
                    mInvers = invAdjObj.inversByAdjoint(m);

                    if (matrixObj.isMatrixEqual(mZero, mInvers) == false) {
                        System.out.println("\nInvers dari matriks berupa: ");
                        matrixObj.printMatrixToScreen(mInvers);

                        System.out.println("\nApakah output ingin disimpan ke file?");
                    }

                }
            }
        }
    }
    
    /* Still in work : until Gauss is fixed */
    public static void solveSPLWithGauss () {
        /* Merupakan kode untuk menyelesaikan SPL dengan eliminasi Gauss */

        /* Kamus Lokal */
        int nRows, nCols;
        double [][] m;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();
    }

    /* Still in work : until GaussJordan is fixed */
    public static void solveSPLWithGaussJordan() {
        /* Merupakan kode untuk menyelesaikan SPL dengan eliminasi Gauss-Jordan */

        /* Kamus Lokal */
        int nRows, nCols;
        double[][] m;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        GaussJordan gaussJordanObj = new GaussJordan();
    }
    
    /* Still in work : output to file, jika SPL ga punya penyelesaian */
    public static void solveSPLWithMetodeInvers() {
        /* Merupakan kode untuk menyelesaikan SPL dengan metode invers */

        /* Kamus Lokal */
        int nRows, nCols, pilihan, i;
        String pathname, currentString, allString = "";
        double[][] m = new double[0][0]; 
        double[] mSolusi;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        MatriksBalikan matBalObj = new MatriksBalikan();

        /* Menampilkan subsubmenu yang berisi cara input matriks beserta permintaan input oleh pengguna */
        System.out.println("\n---------------------------------------------------");
        System.out.println("1. Input melalui keyboard");
        System.out.println("2. Input melalui file");
        System.out.print("Pilih cara input yang diinginkan (1-2) : ");

        Scanner keyboard = new Scanner(System.in);
        pilihan = keyboard.nextInt();

        /* Meminta input matriks dari pengguna sesuai dengan cara input yang telah dipilih*/
        if (pilihan == 1) {
            System.out.print("\nMasukkan jumlah baris matriks : ");
            nRows = keyboard.nextInt();
            System.out.print("Masukkan jumlah kolom matriks : ");
            nCols = keyboard.nextInt();

            m = new double[nRows][nCols];
            matrixObj.readMatrixFromKeyboard(m);
        }
        else if (pilihan == 2) {
            RowCol rc = new RowCol();
            System.out.println("\nMasukkan nama dari file :");
            pathname = keyboard.next();
            pathname = ".\\test\\" + pathname ;

            matrixObj.colRowNumbersFromFile(rc, pathname);
            m = new double[rc.row][rc.col];
            matrixObj.readMatrixFromFile(m, pathname);
        }

        /* Melakukan aksi berdasarkan input pilihan dari pengguna */
        if (pilihan != 1 && pilihan != 2) {
            System.out.println("\nTerjadi error pada input. Kembali ke menu utama");
        }
        else {
            /* Menyelesaikan SPL dengan metode invers dan menampilkan hasil */
            mSolusi = matBalObj.inversElimination(m);

            System.out.println("\nSolusi dari SPL adalah sebagai berikut: ");
            for (i = 0; i <mSolusi.length; i++) {
                currentString = "Nilai x" + (i+1) + " adalah " + mSolusi[i];
                System.out.println(currentString);
                allString += currentString + "\n";
            }
                
            System.out.println("\nApakah output ingin disimpan ke file? ");
            
        }

        keyboard.close(); 
    }
    
    /* Still in work : output to file */
    public static void solveSPLWithCramer() {
        
    }

    /* SUBMENU INTERPOLASI POLINOM */

    /* SUBMENU INTERPOLASI BIKUBIK (still to work, input from file, output to file) */
    public static void subMenuInterpolasiBikubik() {
        /* Merupakan kode untuk menyelesaikan permasalahan terkait interpolasi bikubik */

        /* Kamus Lokal */
        double x = 0, y = 0, nilaiInterpolasi = 0;
        int pilihan, i, p, q;
        String persamaan = "f(x,y) = ", pathname;
        boolean intBikMenuRunning = true;
        double[] mValueOfA = new double[16], listXY = new double[2];
        double[][] m = new double[4][4];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        InterpolasiBikubik intBikObj = new InterpolasiBikubik();
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat dfObj = new DecimalFormat("###.###");

        while (intBikMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Interpolasi Bikubik. Mohon masukkan suatu matriks 4 x 4 dan nilai (x,y) diantara 0 sampai 1");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.nextInt();

            while (((pilihan != 1) && (pilihan != 2) && (pilihan != 0))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                System.out.print("\nMasukkan koordinat absis (x) yang ingin diinterpolasi : ");
                listXY[0] = keyboard.nextDouble();
                while ((listXY[0] > 1) || (listXY[0] < 0)) {
                    System.out.print("Input x salah. Mohon masukkan x yang valid (antara 0 dan 1) : ");
                    listXY[0] = keyboard.nextDouble();
                }

                System.out.print("Masukkan koordinat ordinat (y) yang ingin diinterpolasi : ");
                listXY[1] = keyboard.nextDouble();
                while ((listXY[1] > 1) || (listXY[1] < 0)) {
                    System.out.println("Input y salah. Mohon masukkan y yang valid (antara 0 dan 1) : ");
                    listXY[1] = keyboard.nextDouble();
                }

                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan == 2) {
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = ".\\test\\" + pathname;

                listXY = matrixObj.readFileForInterpolasiBikubik(m, pathname);
            }

            if (pilihan == 0) {
                intBikMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
            else {
                nilaiInterpolasi = 0;
                persamaan = "f(x,y) = ";
                x = listXY[0];
                y = listXY[1];
                mValueOfA = intBikObj.interpolationByBicubic(m, x, y);

                for (i = 0; i < mValueOfA.length; i++) {
                    p = i%4;
                    q = i/4;
                    nilaiInterpolasi += (Math.pow(x, p) * Math.pow(y, q) * mValueOfA[i]);
        
                    if (i == 15) {
                        persamaan += dfObj.format(mValueOfA[i]) + "x^(" + p + ")y^(" + q + ")";
                    }
                    else {
                        persamaan += dfObj.format(mValueOfA[i]) + "x^(" + p + ")y^(" + q + ") + ";
                    }
                }

                System.out.println("\nPersamaan Interpolasi Bikubik berupa : ");
                System.out.println(persamaan);
                System.out.println("\nNilai interpolasi pada x = " + x + ", y = " + y + " adalah " + dfObj.format(nilaiInterpolasi));

                System.out.println("\nApakah output ingin disimpan ke dalam file?");
            }
        }

    }
    public static void main(String[] args)  {
        /* Merupakan program utama untuk mengatasi berbagai persoalan terkait matriks */

        /* KAMUS */
        int pilihan;
        boolean mainMenuRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);
        while (mainMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("MENU UTAMA");
            System.out.println("1. Penyelesaian Sistem Persamaan Linear");
            System.out.println("2. Determinan Matriks");
            System.out.println("3. Balikan Matriks");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bikubik");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");
            System.out.print("Pilih submenu yang diinginkan (1-6) atau ketik 7 untuk mengakhiri program : ");
            pilihan = keyboard.nextInt();

            while ((pilihan != 1) && (pilihan != 2) && (pilihan != 3)  && (pilihan != 4)  && (pilihan != 5)  && (pilihan != 6)  && (pilihan != 7)) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.nextInt();
            }

            if (pilihan == 1) {
                subMenuSPL();
            }
            else if (pilihan == 2) {
                subMenuDeterminan();
            }
            else if (pilihan == 3) {
                subMenuInvers();
            }
            else if (pilihan == 4) {
            }
            else if (pilihan == 5) {
                subMenuInterpolasiBikubik();
            }
            else if (pilihan == 6) {

            }
            else {
                mainMenuRunning = false;
                System.out.println("\nMengakhiri program...");
                System.out.println("Program berakhir");
            }
        }

        keyboard.close();
     }
}
