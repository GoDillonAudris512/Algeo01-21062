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
        mTranspose = new double[matrixObj.getnCols(m)][matrixObj.getnRows(m)];

        
        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                mTranspose[j][i] = m[i][j]; 
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
        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
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
                    if (i != p) {
                        indexRowMinor++;
                    }
                }

                mCofactor[p][q] = Math.pow(-1, p+q) * detKofObj.determinanKofaktor(mMinor);
            }
        }

        return mCofactor;
    }

    // Fungsi Invers Adjoint
    public double[][] inversByAdjoint (double[][] m) {
        // Menghasilkan invers dari matriks m dengan metode adjoint
        // Jika matriks tidak memiliki invers, mengembalikan suatu m yang elemennya 0 semua

        // Kamus Lokal
        double[][] mInvers;

        // Algoritma
        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        Matrix matrixObj = new Matrix();
        mInvers = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];

        if (detKofObj.determinanKofaktor(m) == 0) {
            System.out.println("\nMatriks tidak memiliki invers");
            return mInvers;
        }
        else {
            mInvers = makeMatrixCofactor(m);
            mInvers = transposeMatrix(mInvers);
            multiplyMatrixByConst(mInvers, 1/detKofObj.determinanKofaktor(m));

            return mInvers;
        }
    }
    
}
