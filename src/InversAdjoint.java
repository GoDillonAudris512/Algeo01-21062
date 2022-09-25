/* InversAdjoint.java */
/* Source Code untuk menentukan invers dari suatu matriks dengan metode Adjoint Matriks */

package src;

public class InversAdjoint {

    // 1. Fungsi Transpose Matriks
    public double[][] transposeMatrix (double[][] m) {
        // Menghasilkan matriks transpose dari m, dimana mTranspose[i][j] = m[j][i]

        // Kamus Lokal
        double [][] mTranspose;
        int i, j;

        // Algoritma
        Matrix matrixObj = new Matrix();
        mTranspose = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];

        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                mTranspose[i][j] = m[j][i]; 
            }
        }

        return mTranspose;
    }

    // 2. Prosedur Kali Matriks dengan Konstanta
    public void multiplyMatrixByConst (double[][] m, double constant) {
        // I.S : Matriks m terdefinisi, double constant terdefinisi
        // F.S : Elemen-elemen matriks m merupakan perkalian elemen di indeks tersebut dengan constant

        // Kamus Lokal
        int i, j;

        // Algoritma
        Matrix matrixObj = new Matrix();

        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                m[i][j] *= constant;
            }
        }
    }

    // 3. Fungsi Membuat Matriks Kofaktor
    public double[][] makeMatrixCofactor (double[][] m) {
        // Membuat suatu matriks kofaktor dari matriks m

        // Kamus Lokal
        double[][] mCofactor, mMinor;
        int i, j, p, q, indexRowMinor, indexColMinor;

        // Algoritma
        Matrix matrixObj = new Matrix();
        mCofactor = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];
        mMinor = new double[matrixObj.getnRows(m) - 1][matrixObj.getnCols(m) - 1];

        for (p = 0; p < matrixObj.getnRows(m); p++) {
            for (q = 0; q < matrixObj.getnCols(m); q++) {
                indexRowMinor = 0;
                for (i = 0; i < matrixObj.getnRows(m); i++) {
                    indexColMinor = 0;
                    for (j = 0; j < matrixObj.getnCols(m); j++) {
                        if ((i != p) && (j != q)) {
                            mMinor[indexRowMinor][indexColMinor] = m[i][j];
                            indexColMinor++;
                        }
                    }
                    indexRowMinor++;
                }

                mCofactor[p][q] = Math.pow(-1, p+q) * determinant(mMinor);
            }
        }

        return mCofactor;
    }

    // Fungsi Invers Adjoint
    public double[][] inversByAdjoint (double[][] m) {
        // Menghasilkan invers dari matriks m dengan metode adjoint

        // Kamus Lokal
        double[][] mInvers;

        // Algoritma
        if (determinant(m) == 0) {
            System.out.println("Matriks tidak memiliki invers");
        }
        else {
            Matrix matrixObj = new Matrix();
            mInvers = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];

            mInvers = makeMatrixCofactor(m);
            mInvers = transposeMatrix(mInvers);
            multiplyMatrixByConst(mInvers, 1/determinant(m));

            return mInvers;
        }
    }
    
}
