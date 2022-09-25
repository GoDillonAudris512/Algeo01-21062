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
        int i,j,k;
        Matrix mObj = new Matrix();
        double det = 1, pembagi,pengali;
        
        for (j=0; j<mObj.getnCols(m)-1; j++) {
            for (i=j+1; i<mObj.getnRows(m); i++) {
                pembagi = m[j][j];
                pengali = m[i][j];

                for (k=j; k<mObj.getnCols(m); k++) {
                    if (isRowZero(m, i)) {
                        return 0;
                    }
        
                    if (m[i][j] == 0) {
                        if (i != mObj.getLastIdxRows(m)) {
                            m = swapRow(m, i, i+1);
                            det *= -1;
                        } else {
                            return 0;
                        }
    
                    }
                    
                    if (i != mObj.getLastIdxRows(m)) {
                        m[i][k] -= pengali*m[j][k]/pembagi;
                    }
                }
                
            }
    
            det *= m[j][j];
        }

        return det;
    }
}
