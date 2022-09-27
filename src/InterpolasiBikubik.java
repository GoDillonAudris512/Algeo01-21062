/* InterpolasiBikubik.java */
/* Source code berisi cara menyelesaikan interpolasi bikubik */

package src;

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

    public double interpolationByBicubic (double[][] m, double a, double b) {
        /* I.S m terdefinisi, 0 <= a,b <= 1 */
        /* F.S menghasilkan persamaan interpolasi bikubik berdasarkan m, dan menghasilkan jawaban */
        /*     f(a,b) sesuai persamaan interpolasi */

        /* Kamus Lokal */
        double[][] mCoefficient = makeMatrixCoefficient();
        double[][] mValueOfY = changeMatrixDimension(m);
        double[][] mValueOfA = new double [16][1];
        double nilaiInterpolasi = 0;
        int i, p, q;
        String persamaan = "f(x,y) = ";

        /* Algoritma */
        Matrix matrixObj = new Matrix();             
        SPLInvers splInvObj = new SPLInvers();

        mValueOfA = splInvObj.metodeInvers();

        for (i = 0; i < matrixObj.getnRows(mValueOfA); i++) {
            p = i%4;
            q = i/4;
            nilaiInterpolasi += Math.pow(a, p) * Math.pow(b, q) * mValueOfA[i][0];

            if (i == 15) {
                persamaan += mValueOfA[i][0] + "x^(" + p + ")y^(" + q + ")";
            }
            else {
                persamaan += mValueOfA[i][0] + "x^(" + p + ")y^(" + q + ") +";
            }
        }

        System.out.println(persamaan);

        return nilaiInterpolasi;
    }
}
