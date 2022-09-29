package src;

public class DeterminanReduksiBaris {
    double[][] copyMatrix (double[][] m) {
        int i,j;
        Matrix mObj = new Matrix();

        double[][] n = new double[mObj.getnRows(m)][mObj.getnCols(m)];

        for (i=0; i<mObj.getnRows(m); i++) {
            for (j=0; j<mObj.getnCols(m); j++) {
                n[i][j] = m[i][j];
            }    
        }

        return n;
    }

    double[][] swapRow (double[][] m, int r1, int r2) {
        int j;
        Matrix mObj = new Matrix();

        double[][] n = new double[mObj.getnRows(m)][mObj.getnCols(m)];

        n = copyMatrix(m);

        for (j=0; j<mObj.getnCols(m); j++) {
            n[r1][j] = m[r2][j];
            n[r2][j] = m[r1][j];
        }    

        return n;
    }

    boolean isRowZero (double[][] m, int r) {
        int j;
        Matrix mObj = new Matrix();

        for (j=0; j<mObj.getnCols(m); j++) {
            if (m[r][j] != 0) {
                return false;
            }
        }    

        return true;
    }

    double determinanReduksiBaris (double[][] m) {
        // int i,j,k;
        // Matrix mObj = new Matrix();
        Gauss gauss = new Gauss();
        double det = 1;
            /* Kamus */
            int indexColumn = 0, rowCurrentlyWorked = 0, indexPivot = 0, rowToWorked, i, j;

            /* Algoritma */
            Matrix matrixObj = new Matrix();

            // Menukar seluruh baris Zero dan baris Exception ke bawah dan menentukan banyak row yang akan dilakukan Gauss Elimination
            rowToWorked = gauss.arrangeMatrix(m) - 1;

            // Mulai Gauss Elimination
            for (indexColumn = 0; indexColumn < matrixObj.getnCols(m); indexColumn++) {
                if (rowCurrentlyWorked <= rowToWorked) {
                    indexPivot = gauss.indexOfPivotRow(m, rowCurrentlyWorked, indexColumn);
                    swapRow(m, indexPivot, rowCurrentlyWorked);

                    if (m[rowCurrentlyWorked][indexColumn] != 0) {
                        for (i = rowCurrentlyWorked+1; i <= rowToWorked; i++) {
                            gauss.addRowWithMultipleOfRow(m, i, rowCurrentlyWorked, -(m[i][indexColumn]/m[rowCurrentlyWorked][indexColumn]));
                        }
                        rowCurrentlyWorked++;
                    }
                }
                
            }

            // Handling elemen = -0
            for (i = 0; i < matrixObj.getnRows(m); i++) {
                for (j = 0; j < matrixObj.getnCols(m); j++) {
                    if (m[i][j] == -0) {
                        m[i][j] = 0;
                    }
                }
            } 

            for (i=0; i<matrixObj.getnRows(m); i++) {
                det *= m[i][i];
            }

            if (det == -0) {
                det = 0;
            }

            return det;
        }
}
