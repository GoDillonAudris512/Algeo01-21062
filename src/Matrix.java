/* Matrix.java */
/* Source Code yang menyimpan primitif-primitif yang dapat dilakukan pada suatu matriks */
/* untuk membantu operasi pada Source Code lain*/

package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Matrix {

    // Selektor Ambil Besar Baris dan Kolom
    int getnRows (double[][] m) {
        // Kamus Lokal
        // Tidak ada

        // Algoritma
        return m.length;
    }
    int getnCols (double[][] m) {
        // Kamus Lokal
        // Tidak ada

        // Algoritma
        return m[0].length;
    }

    // Selektor Ambil Indeks Baris dan Kolom
    int getLastIdxRows(double[][] m) {
        // Kamus Lokal
        // Tidak ada

        // Algoritma
        return getnRows(m)-1;
    }
    int getLastIdxCols(double[][] m) {
        // Kamus Lokal
        // Tidak ada

        // Algoritma
        return getnCols(m)-1;
    }

    public void printSolusi (Object[] hasilx) {
        int i;
        String output = "Solusi SPL adalah :\n", saveToFile, pathname;
        Scanner keyboard = new Scanner(System.in);

        if (hasilx == null) {
            output = "Matriks tidak memiliki penyelesaian (Jika menggunakan eliminasi Gauss atau Gauss-Jordan)\nAtau tidak dapat diselesaikan dengan metode yang digunakan (Jika menggunakan metode Invers atau Kaidah Cramer)";
        }
        else{
            for (i = 1; i <= hasilx.length; i++) {
                if (i == hasilx.length) {
                    output += "Nilai x" + i + " adalah " + hasilx[i-1];    
                }
                else {
                    output += "Nilai x" + i + " adalah " +  hasilx[i-1] + "\n";
                }
            }
        }
        
        
        System.out.println("\n" + output);

        System.out.println("\nApakah output ingin disimpan ke dalam file?");
        System.out.print("Input 'y' jika ya, atau input sembarang untuk kembali ke menu utama : ");
        saveToFile = keyboard.next();

        if (saveToFile.equals((String) "y")) {
            pathname = "";
            System.out.print("\nMasukkan nama file output : ");
            pathname = keyboard.next();
            pathname = ".\\test\\resultTestCase\\" + pathname;
                    
            writeGeneralStringToFile(output, pathname);

            System.out.println("\nKembali ke menu utama");
        }
        else{
            System.out.println("Kembali ke menu utama");
            }
    }
    
    // Prosedur input dari keyboard
    public void readMatrixFromKeyboard (double[][] m) {
        // Kamus Lokal
        int i, j;

        // Algoritma
        // Input elemen matriks
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Masukkan elemen matriks: ");
        for (i = 0; i < getnRows(m); i++) {
            for (j = 0; j < getnCols(m); j++) {
                m[i][j] = keyboard.nextDouble();
            }
        }
    }

    // Prosedur output dari keyboard
    public void printMatrixToScreen (double[][] m) {
        // Kamus Lokal
        int i, j;
        
        // Algoritma
        DecimalFormat dfObj = new DecimalFormat("###.###");
        for (i = 0; i < getnRows(m) ; i++) {
            for (j = 0; j < getnCols(m); j++) {
                if (j == getLastIdxCols(m)) {
                    System.out.println(dfObj.format(m[i][j]));
                }
                else {
                    System.out.printf(dfObj.format(m[i][j]) + " ");
                }
            }
        }
    }
    
    static class RowCol {
        int row;
        int col;
    }

    // Cari tahu jumlah row dan column matriks dalam file
    public double[] colRowNumbersFromFile (RowCol rc, String pathname) {
        try {
            double[] isNull = new double[1];
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (myReader.hasNextLine()) {
                    rc.col = data.split(" ").length;                    
                }

                rc.row++;
            }
    
            myReader.close();
            return isNull;
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi error.");
            System.out.println(e.toString());
        }
        return null;
    }
    
    // Input dari file
    public void readMatrixFromFile (double[][] m, String pathname) {
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj) ;

            int i, j;

            i=0;
            while (myReader.hasNextLine()) {
                j = 0;
                String data = myReader.nextLine();
                String data_arr[] = data.split(" ");

                while (j<data_arr.length) {
                    m[i][j] = Double.parseDouble(data_arr[j]);
                    j++;
                }

                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi error.");
            System.out.println(e.toString());
        }
    }

    // Output ke file    
    public void writeMatrixToFile (double[][] m, String pathname) {
        int i,j;
        String out = "";

        for (i=0; i<m.length; i++) {
            for (j=0; j<m[i].length; j++) {
                if (j != m[i].length - 1) {
                    out += Double.toString(m[i][j]) + " ";
                }
                else {
                    out += Double.toString(m[i][j]);
                }
            }
            if (i != m.length - 1) {
                out += "\n";
            }
        }

        try {
            File myObj = new File(pathname);
            
            if (myObj.createNewFile()) {
                System.out.println("File berhasil dibuat: " + myObj.getName());
            } else {
                System.out.println("File sudah ada, akan menulis ke file yang sudah ada.");
            }
            
            FileWriter myWriter = new FileWriter(pathname);
            myWriter.write(out);
            myWriter.close();
            System.out.println("Berhasil menulis ke file.");    

        } catch (IOException e) {
            System.out.println("Terjadi error.");
            e.printStackTrace();
        }
    }

    // Fungsi matrixEqual
    public boolean isMatrixEqual(double[][] m1, double[][] m2) {
        /* Menghasilkan true jika ukuran m1 sama dengan m2 dan seluruh elemen pada indeks yang sama bernilai sama */

        /* Kamus Lokal */
        int i = 0, j = 0;
        boolean stillEqual = true;

        /* Algoritma */
        if ((getnRows(m1) != getnRows(m2)) && (getnCols(m1) != getnCols(m2))) {
            stillEqual = false;
        }
        else {
            while (i < getnRows(m1) && stillEqual) {
                while (j < getnCols(m1) && stillEqual) {
                    if (m1[i][j] != m2[i][j]) {
                        stillEqual = false;
                    }
                    else {
                        j++;
                    }
                }

                if (stillEqual) {
                    i++;
                    j = 0;
                }
            }
        }

        return stillEqual;
    }

    // Input dari file untuk permasalahan interpolasi bikubik
    public double[] readFileForInterpolasiBikubik (double[][] m, String pathname) {
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);

            int i, j;
            double[] listXY = new double[2];

            i=0;
            while (myReader.hasNextLine() && (i != 4)) {
                j = 0;
                String data = myReader.nextLine();
                String data_arr[] = data.split(" ");

                while (j<data_arr.length  && j != 4) {
                    m[i][j] = Double.parseDouble(data_arr[j]);
                    j++;
                }

                i++;
            }

            String data = myReader.nextLine();
            String data_arr[] = data.split(" ");
            listXY[0] = Double.parseDouble(data_arr[0]);
            listXY[1] = Double.parseDouble(data_arr[1]);
            myReader.close();

            return listXY;
        }
        catch (FileNotFoundException e) {
            System.out.println("Terjadi error.");
            System.out.println(e.toString());
        }
        return null;
    }

    // Output ke file untuk suatu string
    public void writeGeneralStringToFile (String out, String pathname) {
        try {
            File myObj = new File(pathname);
            
            if (myObj.createNewFile()) {
                System.out.println("File berhasil dibuat: " + myObj.getName());
            } else {
                System.out.println("File sudah ada, akan menulis ke file yang sudah ada.");
            }
            
            FileWriter myWriter = new FileWriter(pathname);
            myWriter.write(out);
            myWriter.close();
            System.out.println("Berhasil menulis ke file.");    

        } catch (IOException e) {
            System.out.println("Terjadi error.");
            e.printStackTrace();
        }
    }
}