/* Matrix.java */
/* Source Code yang menyimpan primitif-primitif yang dapat dilakukan pada suatu matriks */
/* untuk membantu operasi pada Source Code lain*/

package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

        keyboard.close();
    }

    // Prosedur output dari keyboard
    public void printMatrixToScreen (double[][] m) {
        // Kamus Lokal
        int i, j;
        
        // Algoritma
        for (i = 0; i < getnRows(m) ; i++) {
            for (j = 0; j < getnCols(m); j++) {
                if (j == getLastIdxCols(m)) {
                    System.out.println(m[i][j]);
                }
                else {
                    System.out.print(m[i][j] + " ");
                }
            }
        }
    }
    
    static class RowCol {
        int row;
        int col;
    }

    static void colRowNumbersFromFile (RowCol rc, String pathname) {
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
    
    void readMatrixFromFile (double[][] m, String pathname) {
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
                    m[i][j] = Double.parseDouble(data_arr[i]);
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
    
}
