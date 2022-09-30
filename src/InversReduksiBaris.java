/* InversReduksiBaris.java */
/* Menghasilkan invers dari suatu matriks dengan metode reduksi baris */
/* Ide: Kita menerapkan langkah Gauss-Jordan yang diterapkan pada matriks m ke matriks identitas dengan */
/*      ukuran sama, sehingga didapat invers dari m */

package src;

public class InversReduksiBaris {
    
    public double[][] makeIdentityMatrix (int n) {
        /* Menghasilkan matriks identitas berukuran n x n*/

        /* Kamus Lokal */
        int i, j;
        double[][] mIdentity = new double[n][n];

        /* Algoritma */
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (i == j) {
                    mIdentity[i][j] = 1;
                }
                else {
                    mIdentity[i][j] = 0;
                }
            } 
        }

        return mIdentity;
    }

    public double[][] copyMatrix(double[][] m) {
        /* Mengeluarkan salinan m dengan dimensi dan elemen yang sama */

        /* Kamus Lokal */
        int i, j;
        double[][] mCopy;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        mCopy = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];
        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                mCopy[i][j] = m[i][j];
            }
        }

        return mCopy;
    }

    public double[][] inversByReduksiBaris (double[][] m) {
        /* Prekondisi: matriks m berbentuk persegi */
        /* Menghasilkan invers dari matriks m dengan cara reduksi baris */
        /* Jika matriks tidak memiliki invers, maka mengembalikan matriks yang elemennya 0 semua */

        /* Kamus Lokal */
        int i, j, indexColumn, rowCurrentlyWorked, indexPivot, rowToWorked;
        double pengali, pembagi;
        double[][] mInvers, mZero, mCopy;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();
        GaussJordan gaussJordanObj = new GaussJordan();
        DeterminanReduksiBaris detRedBarObj = new DeterminanReduksiBaris();

        mInvers = makeIdentityMatrix(matrixObj.getnRows(m));
        mZero = new double[matrixObj.getnRows(m)][matrixObj.getnCols(m)];
        mCopy = copyMatrix(m);

        if (detRedBarObj.determinanReduksiBaris(mCopy) == 0) {
            System.out.println("\nMatriks tidak memiliki invers");
            return mZero;
        }
        else {
            /* Menerapkan langkah Gauss */
            indexColumn = 0;
            rowCurrentlyWorked = 0;
            rowToWorked = gaussObj.arrangeMatrix(m) - 1;

            for (indexColumn = 0; indexColumn < matrixObj.getnCols(m); indexColumn++) {
                if (rowCurrentlyWorked <= rowToWorked) {                 
                    indexPivot = gaussObj.indexOfPivotRow(m, rowCurrentlyWorked, indexColumn);

                    gaussObj.swapRow(m, indexPivot, rowCurrentlyWorked);
                    gaussObj.swapRow(mInvers, indexPivot, rowCurrentlyWorked);

                    if (m[rowCurrentlyWorked][indexColumn] != 0) {
                        for (i = rowCurrentlyWorked+1; i <= rowToWorked; i++) {
                            pengali = -(m[i][indexColumn]/m[rowCurrentlyWorked][indexColumn]);
                            gaussObj.addRowWithMultipleOfRow(m, i, rowCurrentlyWorked, pengali);
                            gaussObj.addRowWithMultipleOfRow(mInvers, i, rowCurrentlyWorked, pengali);
                        }
                        pembagi = m[rowCurrentlyWorked][indexColumn];
                        gaussObj.divideRowWithConstant(m, rowCurrentlyWorked, pembagi);
                        gaussObj.divideRowWithConstant(mInvers, rowCurrentlyWorked, pembagi);
                        rowCurrentlyWorked++;
                    }
                }
            }
    
            for (i = 0; i < matrixObj.getnRows(m); i++) {
                for (j = 0; j < matrixObj.getnCols(m); j++) {
                    if (m[i][j] == -0) {
                        m[i][j] = 0;
                    }
                    if(mInvers[i][j] == -0) {
                        mInvers[i][j] =0;
                    }
                }
            }


            /* Menerapkan langkah Gauss-Jordan */
            rowCurrentlyWorked = gaussJordanObj.countRowToWorked(m) - 1;
            j = matrixObj.getLastIdxCols(m);

            while (rowCurrentlyWorked >= 0) {
                if (j == gaussJordanObj.indexOfLeadingOne(m, rowCurrentlyWorked)) {
                    for (i = rowCurrentlyWorked - 1; i >= 0; i--) {
                        pengali = -(m[i][j]/m[rowCurrentlyWorked][j]);
                        gaussObj.addRowWithMultipleOfRow(m, i, rowCurrentlyWorked, pengali);
                        gaussObj.addRowWithMultipleOfRow(mInvers, i, rowCurrentlyWorked, pengali);
                    }
                    rowCurrentlyWorked--;
                }
                j--;
            }

            return mInvers;
        }
    }
}
