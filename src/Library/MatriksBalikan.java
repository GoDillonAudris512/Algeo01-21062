package Library;

import Matrix.*;

public class MatriksBalikan {
    public double[][] splitHasil(double[][] matrix) {
        Matrix matObj = new Matrix();
        double[][] hasil;
        int i, j;

        j = matObj.getLastIdxCols(matrix);

        hasil = new double[matObj.getnRows(matrix)][1];

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            hasil[i][0] = matrix[i][j];
        }

        return hasil;
    }

    public double[][] splitMainMatrix(double[][] matrix) {
        Matrix matObj = new Matrix();
        double[][] mainMat;
        int i, j, k;

        k = matObj.getLastIdxCols(matrix) - 1;

        mainMat = new double[matObj.getnRows(matrix)][matObj.getnCols(matrix) - 1];

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            for (j = 0; j <= k; j++) {
                mainMat[i][j] = matrix[i][j];
            }
        }

        return mainMat;
    }

    public double[][] multiplyMatrixbyMatrix(double[][] m1, double[][] m2) {
        int i, j, m;
        double[][] mMultiply;
        Matrix matObj = new Matrix();

        mMultiply = new double[matObj.getnRows(m1)][matObj.getnCols(m2)];

        for (m = 0; m <= matObj.getLastIdxRows(m1); m++) {
            for (i = 0; i <= matObj.getLastIdxCols(m2); i++) {
                mMultiply[m][i] = 0;
                for (j = 0; j <= matObj.getLastIdxCols(m1); j++) {
                    mMultiply[m][i] += m1[m][j] * m2[j][i];
                }
            }
        }

        return mMultiply;
    }

    public Object[] inversElimination(double[][] matrix) {
        Matrix matObj = new Matrix();
        InversReduksiBaris invRedBarObj = new InversReduksiBaris();
        DeterminanReduksiBaris detRedBarObj = new DeterminanReduksiBaris();

        double[][] hasilxtemp, hasil, mainMat;
        Object[] hasilx = new Object[16];
        int i;

        hasil = splitHasil(matrix);
        mainMat = splitMainMatrix(matrix);

        if (detRedBarObj.determinanReduksiBaris(invRedBarObj.copyMatrix(mainMat)) == 0) {
            hasilx = null;
        }
        else {
            hasilxtemp = multiplyMatrixbyMatrix(invRedBarObj.inversByReduksiBaris(mainMat), hasil);

            for (i = 0; i < matObj.getnRows(hasilxtemp); i++) {
                hasilx[i] = hasilxtemp[i][0];
            }
        }

        return hasilx;
    }
}
