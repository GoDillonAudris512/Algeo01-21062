/* Matrix.java */
/* Source Code yang menyimpan primitif-primitif yang dapat dilakukan pada suatu matriks */
/* untuk membantu operasi pada Source Code lain*/

package src;
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
    void readMatrixFromKeyboard (double[][] m) {
        // Kamus Lokal
        int nRows, nCols, i, j;

        // Algoritma
        // Input baris dan kolom matriks
        Scanner keyboard = new Scanner(System.in); 
        System.out.print("Number of rows: ");
        nRows = keyboard.nextInt();
        System.out.print("Number of columns: ");
        nCols = keyboard.nextInt();
        m = new double[nRows][nCols];

        // Input elemen matriks
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
}