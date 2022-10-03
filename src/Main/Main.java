package Main;

import java.text.DecimalFormat;
import java.util.Scanner;
import Library.*;
import Matrix.*;
import Matrix.Matrix.RowCol;

public class Main {

    public static void subMenuSPL() {
        /* Merupakan submenu untuk penyelesaian SPL dengan 4 metode*/

        /* Kamus Lokal */
        String pilihan;
        boolean subMenuSPLRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);

        while (subMenuSPLRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Penyelesaian SPL");
            System.out.println("1. Metode eliminasi Gauss");
            System.out.println("2. Metode eliminasi Gauss-Jordan");
            System.out.println("3. Metode matriks balikan");
            System.out.println("4. Kaidah Cramer");
            System.out.print("Silahkan masukkan metode yang diinginkan (1-4) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "3")) && (!pilihan.equals((String) "4")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                solveSPLWithGauss();
            }
            else if (pilihan.equals((String) "2")) {
                solveSPLWithGaussJordan();
            }
            else if (pilihan.equals((String) "3")) {
                solveSPLWithMetodeInvers();
            }
            else if (pilihan.equals((String) "4")) {
                solveSPLWithCramer();
            }
            else {
                subMenuSPLRunning = false;
                System.out.println("Kembali ke menu utama");
            }
        }
    }
    
    public static void solveSPLWithGauss () {
        /* Merupakan kode untuk menyelesaikan SPL dengan eliminasi Gauss */

        /* Kamus Lokal */
        int nRows, nCols;
        String pathname, pilihan;
        boolean gaussMenuRunning = true;
        Object[] solusiSPL = new Object[0];
        double[] isNull = new double[1];
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();
        Scanner keyboard = new Scanner(System.in);

        while (gaussMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Penyelesaian SPL dengan metode Gauss.");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Penyelesaian SPL : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                gaussMenuRunning = false;
                System.out.println("Kembali ke submenu Penyelesaian SPL");
            }
            else {
                gaussObj.gaussElimination(m, false);
                solusiSPL = gaussObj.gaussEliminationSolution(m);
                matrixObj.printSolusi(solusiSPL);
            }
        }
    }

    public static void solveSPLWithGaussJordan() {
        /* Merupakan kode untuk menyelesaikan SPL dengan eliminasi Gauss-Jordan */

        /* Kamus Lokal */
        int nRows, nCols;
        String pathname, pilihan;
        boolean gaussJordanMenuRunning = true;
        Object[] solusiSPL = new Object[0];
        double[] isNull = new double[1];
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        GaussJordan gaussJordanObj = new GaussJordan();
        Scanner keyboard = new Scanner(System.in);

        while (gaussJordanMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Penyelesaian SPL dengan metode Gauss-Jordan.");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Penyelesaian SPL : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                gaussJordanMenuRunning = false;
                System.out.println("Kembali ke submenu Penyelesaian SPL");
            }
            else {
                gaussJordanObj.gaussJordanElimination(m);
                solusiSPL = gaussJordanObj.gaussJordanEliminationSolution(m);
                matrixObj.printSolusi(solusiSPL);
            }
        }
    }
    
    public static void solveSPLWithMetodeInvers() {
        /* Merupakan kode untuk menyelesaikan SPL dengan metode invers */

        /* Kamus Lokal */
        int nRows, nCols;
        String pathname, pilihan;
        boolean splInversMenuRunning = true;
        Object[] solusiSPL = new Object[0];
        double[] isNull = new double[1];
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        MatriksBalikan matBalObj = new MatriksBalikan();
        Scanner keyboard = new Scanner(System.in);

        /* Menampilkan subsubmenu yang berisi cara input matriks beserta permintaan input oleh pengguna */
        while (splInversMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Penyelesaian SPL dengan metode Invers. Mohon masukkan matriks berukuran n kali (n+1)");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Penyelesaian SPL : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                splInversMenuRunning = false;
                System.out.println("Kembali ke submenu Penyelesaian SPL");
            }
            else {
                if (matrixObj.getnCols(m) != matrixObj.getnRows(m) + 1) {
                    System.out.println("\nMatriks tidak berukuran n kali (n+1). Kembali ke submenu Penyelesaian SPL dengan metode Invers");
                }
                else {
                    solusiSPL = matBalObj.inversElimination(m);
                    matrixObj.printSolusi(solusiSPL);
                }
            }
        }
    }
    
    public static void solveSPLWithCramer() {
        /* Merupakan kode untuk menyelesaikan SPL dengan kaidah cramer */

        /* Kamus Lokal */
        int nRows, nCols;
        String pathname, pilihan;
        boolean cramerMenuRunning = true;
        Object[] solusiSPL = new Object[0];
        double[] isNull = new double[1];
        double[][] m = new double[0][0];

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Cramer cramerObj = new Cramer();
        Scanner keyboard = new Scanner(System.in);

        while (cramerMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Penyelesaian SPL dengan kaidah Cramer. Mohon masukkan matriks berukuran n kali (n+1)");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke submenu Penyelesaian SPL : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                cramerMenuRunning = false;
                System.out.println("Kembali ke submenu Penyelesaian SPL");
            }
            else {
                if (matrixObj.getnCols(m) != matrixObj.getnRows(m) + 1) {
                    System.out.println("\nMatriks tidak berukuran n kali (n+1). Kembali ke submenu Kaidah Cramer");
                }
                else {
                    solusiSPL = cramerObj.cramerElimination(m);
                    matrixObj.printSolusi(solusiSPL);
                }
            }
        }
    }

    /* SUBMENU DETERMINAN */
    public static void subMenuDeterminan() {
        /* Merupakan submenu untuk mendapatkan determinan suatu matriks dengan 2 metode */

        /* Kamus Lokal */
        String pilihan;
        boolean determinanMenuRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);

        while (determinanMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Determinan");
            System.out.println("1. Metode Reduksi Baris");
            System.out.println("2. Metode Ekspansi Kofaktor");
            System.out.print("Silahkan masukkan metode yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                solveDeterminanWithReduksiBaris();
            }
            else if (pilihan.equals((String) "2")) {
                solveDeterminanWithEkspansiKofaktor();
            }
            else {
                determinanMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
        }
    }

    public static void solveDeterminanWithReduksiBaris() {
        /* Merupakan kode untuk menentukan determinan matriks dengan metode reduksi baris */
        
        /* Kamus Lokal */
        int nRows, nCols;
        boolean detRedBarMenuRunning = true;
        double determinan;
        String pathname, pilihan, saveToFile, output;
        double[] isNull = new double[1];
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
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                detRedBarMenuRunning = false;
                System.out.println("Kembali ke submenu Determinan");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Determinan Metode Reduksi Baris");
                }
                else {
                    determinan = detRedBarObj.determinanReduksiBaris(m);

                    output = "Determinan dari matriks tersebut adalah " + determinan;
                    System.out.println("\n" + output);

                    System.out.println("\nApakah output ingin disimpan ke dalam file?");
                    System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke submenu Determinan : ");
                    saveToFile = keyboard.next();

                    if (saveToFile.equals((String) "y")) {
                        pathname = "";
                        System.out.print("\nMasukkan nama file output : ");
                        pathname = keyboard.next();
                        pathname = "..\\test\\resultTestCase\\" + pathname;
                        
                        matrixObj.writeGeneralStringToFile(output, pathname);

                        System.out.println("\nKembali ke submenu Determinan");
                    }
                    else{
                        System.out.println("Kembali ke submenu Determinan");
                    }
                }
            }
        }
    }

    public static void solveDeterminanWithEkspansiKofaktor() {
        /* Merupakan kode untuk menentukan determinan matriks dengan metode ekspansi kofaktor */
        
        /* Kamus Lokal */
        int nRows, nCols;
        boolean detKofMenuRunning = true;
        double determinan;
        String pathname, pilihan, saveToFile, output;
        double[] isNull = new double[1];
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
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
                detKofMenuRunning = false;
                System.out.println("Kembali ke submenu Determinan");
            }
            else {
                if (matrixObj.getnRows(m) != matrixObj.getnCols(m)) {
                    System.out.println("\nMatriks tidak berbentuk persegi. Kembali ke submenu Determinan Metode Ekspansi Kofaktor");
                }
                else {
                    determinan = detKofObj.determinanKofaktor(m);

                    output = "Determinan dari matriks tersebut adalah " + determinan;
                    System.out.println("\n" + output);

                    System.out.println("\nApakah output ingin disimpan ke dalam file?");
                    System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke submenu Determinan : ");
                    saveToFile = keyboard.next();

                    if (saveToFile.equals((String) "y")) {
                        pathname = "";
                        System.out.print("\nMasukkan nama file output : ");
                        pathname = keyboard.next();
                        pathname = "..\\test\\resultTestCase\\" + pathname;
                        
                        matrixObj.writeGeneralStringToFile(output, pathname);

                        System.out.println("\nKembali ke submenu Determinan");
                    }
                    else{
                        System.out.println("Kembali ke submenu Determinan");
                    }
                }
            }
        }
    }
    
    /* SUBMENU INVERS */
    public static void subMenuInvers() {
        /* Merupakan submenu untuk mendapatkan invers suatu matriks dengan 2 metode */

        /* Kamus Lokal */
        String pilihan;
        boolean inversMenuRunning = true;

        /* Algoritma */
        Scanner keyboard = new Scanner(System.in);

        while (inversMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Invers");
            System.out.println("1. Metode Reduksi Baris");
            System.out.println("2. Metode Adjoint");
            System.out.print("Silahkan masukkan metode yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                solveInversWithReduksiBaris();
            }
            else if (pilihan.equals((String) "2")) {
                solveInversWithAdjoint();;
            }
            else{
                inversMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
        }
    }

    public static void solveInversWithReduksiBaris() {
        /* Merupakan kode untuk menyelesaikan invers dengan metode reduksi baris */

        /* Kamus Lokal */
        int nRows, nCols;
        boolean invRedBarMenuRunning = true;
        String pathname, pilihan, saveToFile;
        double[] isNull = new double[0];
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
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
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
                    }

                    System.out.println("\nApakah output ingin disimpan ke file?");
                    System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke submenu Invers : ");
                    saveToFile = keyboard.next();

                    if (saveToFile.equals((String) "y")) {
                        pathname = "";
                        System.out.print("\nMasukkan nama file output : ");
                        pathname = keyboard.next();
                        pathname = "..\\test\\resultTestCase\\" + pathname;
                        
                        if (matrixObj.isMatrixEqual(mZero, mInvers)) {
                            matrixObj.writeGeneralStringToFile("Matriks tidak memiliki invers", pathname);
                        }
                        else{
                            matrixObj.writeMatrixToFile(mInvers, pathname);
                        }

                        System.out.println("\nKembali ke submenu Invers");
                    }
                    else{
                        System.out.println("Kembali ke submenu Invers");
                    }
                }
            }
        }
    }

    public static void solveInversWithAdjoint() {
        /* Merupakan kode untuk menyelesaikan matriks dengan metode adjoint matriks */

        /*/ kamus Lokal */
        int nRows, nCols;
        boolean invAdjMenuRunning = true;
        String pathname, pilihan, saveToFile;
        double[] isNull = new double[1];
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
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                System.out.print("\nMasukkan jumlah baris matriks : ");
                nRows = keyboard.nextInt();
                System.out.print("Masukkan jumlah kolom matriks : ");
                nCols = keyboard.nextInt();

                m = new double[nRows][nCols];
                matrixObj.readMatrixFromKeyboard(m);
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    m = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(m, pathname);
                }
            }

            /* Melakukan aksi berdasarkan input pada pengguna */
            if (pilihan.equals((String) "0") || isNull == null) {
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
                    }
                    System.out.println("\nApakah output ingin disimpan ke file?");
                    System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke submenu Invers : ");
                    saveToFile = keyboard.next();

                    if (saveToFile.equals((String) "y")) {
                        pathname = "";
                        System.out.print("\nMasukkan nama file output : ");
                        pathname = keyboard.next();
                        pathname = "..\\test\\resultTestCase\\" + pathname;
                        
                        if (matrixObj.isMatrixEqual(mZero, mInvers)) {
                            matrixObj.writeGeneralStringToFile("Matriks tidak memiliki invers", pathname);
                        }
                        else{
                            matrixObj.writeMatrixToFile(mInvers, pathname);
                        }

                        System.out.println("\nKembali ke submenu Invers");
                    }
                    else{
                        System.out.println("Kembali ke submenu Invers");
                    }

                }
            }
        }
    }

    /* SUBMENU INTERPOLASI POLINOM*/ 
    public static void subMenuInterpolasiPolinom() {
        /* Merupakan kode untuk menyelesaikan permasalahan terkait interpolasi polinom */

        /* Kamus Lokal */
        Object[] mHasilA;
        double[] isNull = new double[1];
        double[][] titikXY = new double[0][0];
        double x = 0, nilaiInterpolasi;
        int jumlahTitik = 0, i;
        String persamaan, pathname, pilihan, saveToFile;
        boolean intPolMenuRunning = true;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        InterpolasiPolinom intPolObj = new InterpolasiPolinom();
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat dfObj = new DecimalFormat("###.###");

        while (intPolMenuRunning) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("Anda sedang berada di submenu Interpolasi Polinom.");
            System.out.println("1. Input melalui keyboard");
            System.out.println("2. Input melalui file");
            System.out.print("Pilih cara input yang diinginkan (1-2) atau ketik 0 untuk kembali ke menu utama : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                jumlahTitik = intPolObj.inputJumlahTitik();
                titikXY = new double[jumlahTitik][2];
                matrixObj.readMatrixFromKeyboard(titikXY);;
                x = intPolObj.inputNilaiTaksiranX();
            }
            else if (pilihan.equals((String) "2")) {
                RowCol rc = new RowCol();
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                isNull = matrixObj.colRowNumbersFromFile(rc, pathname);
                if (isNull != null) {
                    titikXY = new double[rc.row][rc.col];
                    matrixObj.readMatrixFromFile(titikXY, pathname);
                    x = intPolObj.inputNilaiTaksiranX();
                }
            }

            if (pilihan.equals((String) "0") || isNull == null) {
                intPolMenuRunning = false;
                System.out.println("Kembali ke menu utama");
            }
            else {
                mHasilA = intPolObj.interpolasiPolinom(titikXY, jumlahTitik);
            
                persamaan = "Persamaan Interpolasi Polinom berupa :\np(x) = ";
                nilaiInterpolasi = 0;

                for (i = 0; i < mHasilA.length; i++) {
                    if ((double) mHasilA[i] != 0) {
                        if (i == 0 ) {
                            persamaan += dfObj.format(mHasilA[i]) + " + ";
                        }
                        else if (i == mHasilA.length-1) {
                            if ((double) mHasilA[i] != 1) { 
                                persamaan += dfObj.format(mHasilA[i]) + "x^" + i;
                            }
                            else {
                                persamaan += "x^" + i;
                            }
                        }
                        else {
                            if ((double) mHasilA[i] != 1) { 
                                persamaan += dfObj.format(mHasilA[i]) + "x^" + i + " + ";
                            }
                            else {
                                persamaan += "x^" + i + " + ";
                            }
                        }

                        if (x != 0) {
                            nilaiInterpolasi += (double) mHasilA[i] * Math.pow(x, i);
                        }
                    }
                }

                persamaan += "\n\nNilai interpolasi pada x = " + x + " adalah " + dfObj.format(nilaiInterpolasi);
                System.out.println("\n" + persamaan);

                System.out.println("\nApakah output ingin disimpan ke dalam file?");
                System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke menu utama : ");
                saveToFile = keyboard.next();

                if (saveToFile.equals((String) "y")) {
                    pathname = "";
                    System.out.print("\nMasukkan nama file output : ");
                    pathname = keyboard.next();
                    pathname = "..\\test\\resultTestCase\\" + pathname;
                    
                    matrixObj.writeGeneralStringToFile(persamaan, pathname);

                    System.out.println("\nKembali ke submenu Interpolasi Polinom");
                }
                else{
                    System.out.println("Kembali ke submenu Interpolasi Polinom");
                }
            }
        }
    }

    /* SUBMENU INTERPOLASI BIKUBIK */
    public static void subMenuInterpolasiBikubik() {
        /* Merupakan kode untuk menyelesaikan permasalahan terkait interpolasi bikubik */

        /* Kamus Lokal */
        double x = 0, y = 0, nilaiInterpolasi = 0;
        int i, p, q;
        String persamaan, pathname, pilihan, saveToFile;
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
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
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
            else if (pilihan.equals((String) "2")) {
                System.out.print("\nMasukkan nama dari file : ");
                pathname = keyboard.next();
                pathname = "..\\test\\" + pathname;

                listXY = matrixObj.readFileForInterpolasiBikubik(m, pathname);
            }

            if (pilihan.equals((String) "0") || listXY == null) {
                intBikMenuRunning = false;
                if (listXY == null) {
                    System.out.println("\nAnda tidak memasukkan titik X dan Y ke dalam file");
                }
                System.out.println("Kembali ke menu utama");
            }
            else {
                nilaiInterpolasi = 0;
                persamaan = "Persamaan Interpolasi Bikubik berupa :\nf(x,y) = ";
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

                persamaan += "\n\nNilai interpolasi pada x = " + x + ", y = " + y + " adalah " + dfObj.format(nilaiInterpolasi);
                System.out.println("\n" + persamaan);

                System.out.println("\nApakah output ingin disimpan ke dalam file?");
                System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke menu utama : ");
                saveToFile = keyboard.next();

                if (saveToFile.equals((String) "y")) {
                    pathname = "";
                    System.out.print("\nMasukkan nama file output : ");
                    pathname = keyboard.next();
                    pathname = "..\\test\\resultTestCase\\" + pathname;
                    
                    matrixObj.writeGeneralStringToFile(persamaan, pathname);

                    System.out.println("\nKembali ke submenu Interpolasi Bikubik");
                }
                else{
                    System.out.println("Kembali ke submenu Interpolasi Bikubik");
                }
            }
        }

    }
    
    public static void main(String[] args)  {
        /* Merupakan program utama untuk mengatasi berbagai persoalan terkait matriks */

        /* KAMUS */
        String pilihan;
        boolean mainMenuRunning = true;

        /* Algoritma */
        RegresiLinierBerganda regObj = new RegresiLinierBerganda();
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
            System.out.println("0. Keluar");
            System.out.print("Pilih submenu yang diinginkan (1-6) atau ketik 0 untuk mengakhiri program : ");
            pilihan = keyboard.next();

            while ((!pilihan.equals((String) "1")) && (!pilihan.equals((String) "2")) && (!pilihan.equals((String) "3")) && (!pilihan.equals((String) "4")) && (!pilihan.equals((String) "5")) && (!pilihan.equals((String) "6")) && (!pilihan.equals((String) "0"))) {
                System.out.print("Input salah. Mohon masukkan input yang benar : ");
                pilihan = keyboard.next();
            }

            if (pilihan.equals((String) "1")) {
                subMenuSPL();
            }
            else if (pilihan.equals((String) "2")) {
                subMenuDeterminan();
            }
            else if (pilihan.equals((String) "3")) {
                subMenuInvers();
            }
            else if (pilihan.equals((String) "4")) {
                subMenuInterpolasiPolinom();
            }
            else if (pilihan.equals((String) "5")) {
                subMenuInterpolasiBikubik();
            }
            else if (pilihan.equals((String) "6")) {
                regObj.regresiLinierBerganda();
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