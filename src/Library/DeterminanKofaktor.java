package Library;

import Matrix.*;

public class DeterminanKofaktor {

    public double determinanKofaktor (double[][] m) {
        Matrix mObj = new Matrix();
        int i, j, p, indexRowMinor, indexColMinor;
        double det = 0;

        if (mObj.getnRows(m) == mObj.getnCols(m)) {
            if (mObj.getnRows(m) == 1) {
                return m[0][0];

            } else if (mObj.getnRows(m) == 2) {
                return (m[0][0]*m[1][1] - m[0][1]*m[1][0]);

            } else {
                double[][] minor = new double[mObj.getnRows(m)-1][mObj.getnCols(m)-1];

                for (p = 0; p <= mObj.getLastIdxCols(m); p++) {
                    indexRowMinor = 0;
                
                    for (i = 1; i <= mObj.getLastIdxRows(m); i++) {
                        indexColMinor = 0;
                
                        for (j = 0; j <= mObj.getLastIdxRows(m); j++) {
                
                            if (j != p) {
                                minor[indexRowMinor][indexColMinor] = m[i][j];
                                indexColMinor++;
                            }
                        }
                        indexRowMinor++;
                    }
        
                    if (p % 2 == 0) {
                        det = det + (m[0][p]*determinanKofaktor(minor));
                        
                    }
                    else /* p % 2 == 1 */ {
                        det = det - (m[0][p]*determinanKofaktor(minor));
                    }                
                }

                return det;
            }

        } else {
            System.out.println("Bukan matriks persegi.");

            return 0;
        }
    }

}
