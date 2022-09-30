/* InterpolasiBikubik.java */
/* Source code berisi cara menyelesaikan interpolasi bikubik */

package src;

import java.text.DecimalFormat;

public class InterpolasiBikubik {

    public double[][] makeMatrixCoefficient () {
        /* Menghasilkan suatu matriks koefisien yang dibutuhkan untuk menyelesaikan */
        /* interpolasi bikubik dengan input matriks 4 x 4 */
        /* Matriks koefisien akan berukuran 16 x 16 */

        /* Kamus Lokal */
        double[][] mCoefficient = new double[16][16];
        int i, j, x, y;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        // Membuat elemen-elemen matriks koefisien dan handling elemen = -0
        for (i = 0; i < matrixObj.getnRows(mCoefficient); i++) {
            for (j = 0; j < matrixObj.getnCols(mCoefficient); j++) {
                x = i%4 - 1;
                y = i/4 - 1;
                mCoefficient[i][j] = Math.pow(x, j%4) * Math.pow(y, j/4);

                if (mCoefficient[i][j] == -0) {
                    mCoefficient[i][j] = 0;
                }
            }
        }

        return mCoefficient;
    }

    public double[][] changeMatrixDimension (double[][]m) {
        /* Menghasilkan matriks berukuran 16 x 1 dari matriks 4 x 4 */

        /* Kamus Lokal */
        int i, j, rowmHasil = 0;
        double[][] mHasil = new double[16][1];

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                mHasil[rowmHasil][0] = m[i][j];
                rowmHasil++;
            }
        }

        return mHasil;
    }

    public double[][] integrateMatrixAandB (double[][] mA, double[][] mB) {
        /* Menghasilkan matriks augmented 16 x 17 hasil penggabungan matriks koefisien dengan matriks nilai */

        /* Kamus Lokal */
        double [][] mIntegrated = new double[16][17];
        int i, j;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        for (i = 0; i < matrixObj.getnRows(mA); i++) {
            for (j = 0; j < matrixObj.getnCols(mA) +  + matrixObj.getnCols(mB); j++) {
                if (j == 16) {
                    mIntegrated[i][j] = mB[i][0];
                }
                else {
                    mIntegrated[i][j] = mA[i][j];
                }
            }
        }

        return mIntegrated;
    }

    public double interpolationByBicubic (double[][] m, double a, double b) {
        /* I.S m terdefinisi, 0 <= a,b <= 1 */
        /* F.S menghasilkan persamaan interpolasi bikubik berdasarkan m, dan menghasilkan jawaban */
        /*     f(a,b) sesuai persamaan interpolasi */

        /* Kamus Lokal */
        double[][] mCoefficient = makeMatrixCoefficient();
        double[][] mValueOfY;
        double[] mValueOfA = new double [16];
        double nilaiInterpolasi = 0;
        int i, p, q;
        String persamaan = "\nf(x,y) = ";

        /* Algoritma */         
        MatriksBalikan matBalObj = new MatriksBalikan();
        DecimalFormat dfObj = new DecimalFormat("###.###");
        
        mValueOfY = changeMatrixDimension(m);
        mValueOfA = matBalObj.inversElimination(integrateMatrixAandB(mCoefficient, mValueOfY));

        for (i = 0; i < mValueOfA.length; i++) {
            p = i%4;
            q = i/4;
            nilaiInterpolasi += (Math.pow(a, p) * Math.pow(b, q) * mValueOfA[i]);

            if (i == 15) {
                persamaan += dfObj.format(mValueOfA[i]) + "x^(" + p + ")y^(" + q + ")";
            }
            else {
                persamaan += dfObj.format(mValueOfA[i]) + "x^(" + p + ")y^(" + q + ") +";
            }
        }

        System.out.println(persamaan);

        return nilaiInterpolasi;
    }
}
