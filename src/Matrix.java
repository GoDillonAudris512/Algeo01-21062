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

    public void printSolusi (double[] hasilx) {
        int i;
        String currentString;

        for (i = 1; i <= hasilx.length; i++) {
            currentString = "Nilai x" + i + " adalah " + hasilx[i-1];
            System.out.println(currentString);
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
    public void colRowNumbersFromFile (RowCol rc, String pathname) {
        try {
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
    
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi error.");
            e.printStackTrace();
        }
    }
    
    // Input dari file
    public void readMatrixFromFile (double[][] m, String pathname) {
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);

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
            e.printStackTrace();
            }
    }

    // Output ke file    
    public void writeMatrixToFile (double[][] m, String pathname) {
        int i,j;
        String out = "";

        for (i=0; i<m.length; i++) {
            for (j=0; j<m[i].length; j++) {
                out += Double.toString(m[i][j]) + " ";
            }
            out += "\n";
        }

        try {
            File myObj = new File("filename.txt");
            
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

                while (j<data_arr.length) {
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
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi error.");
            e.printStackTrace();
            }
        return null;
    }
}