/* GaussJordan.Java */
/* Source code untuk menyelesaikan metode SPL dengan eliminasi Gauss-Jordan */

package src;

public class GaussJordan {

    public int countRowToWorked(double[][] m) {
        /* Menghasilkan jumlah baris yang bukan baris Zero dalam matriks m */

        /* Kamus Lokal*/
        int count = 0, i;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();

        for (i = 0; i < matrixObj.getnRows(m); i++) {
            if (!gaussObj.isRowAllZero(m, i)) {
                count += 1;
            }
        }

        return count;
    }

    public int indexOfLeadingOne (double[][] m, int barisX) {
        /* Prekondisi : m merupakan matriks eselon baris, barisX ada di dalam m */
        /* Menghasilkan index kolom dimana 1 pertama muncul pada barisX */

        /* Kamus Lokal */
        int j = 0, indexLeadingOne = 0;
        boolean leadingOneFound = false;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        while (j <= matrixObj.getLastIdxCols(m) && !leadingOneFound) {
            if (m[barisX][j] == 1) {
                leadingOneFound = true;
                indexLeadingOne = j;
            }
            else {
                j++;
            }
        }

        return indexLeadingOne;
    }

    public boolean isThereLeadingOne(double[][] matrix, int kolomX) {
        int i,countZero = 0;
        boolean thereIsLeadingOne = true;
        Matrix mat = new Matrix();

        for (i = 0; i < mat.getnRows(matrix); i++) {
            if (matrix[i][kolomX] == 0) {
                countZero += 1;
            }
        }

        if (countZero == mat.getLastIdxRows(matrix)) {
            thereIsLeadingOne = true;
        } else {
            thereIsLeadingOne = false;
        }

        return thereIsLeadingOne;
    }

    public void printSolusiGaussJordan (double[] hasilx) {
        int i;

        for (i = 1; i <= hasilx.length; i++) {
            System.out.println("x" + i + "= " + hasilx[i]);
        }
    }

    public void gaussJordanEliminationSolution (double[][] matrix) {
        Matrix mat = new Matrix();
        MatriksBalikan matObj = new MatriksBalikan();
        GaussJordan matt = new GaussJordan();
        Gauss mattt = new Gauss();

        int i,j,k, countExc = 0;
        String[] parametrik = {"s","t","u","v","x","y","z"};
        double[] hasilx = new double[mat.getnRows(matrix)];
        double[][] mainMat, hasil;

        mainMat = matObj.splitMainMatrix(matrix);
        hasil = matObj.splitHasil(matrix);

        for (i = 0; i < mat.getnRows(matrix); i++){
            if (mattt.isRowAnException(matrix, i)) {
                countExc += 1;
            }
        }

        if (countExc == 0) {
            if (mat.getnCols(matrix) == mat.getnRows(matrix)) {
                for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                    for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                        hasilx[i] = hasil[i][j];
                    }
                }
            } else {
                for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                    for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                        if (isThereLeadingOne(matrix, j)) {
                            hasilx[i] = hasil[i][j];
                        } else {
                            for (k = 0; k < mat.getnRows(matrix); k++) {
                                double convert = Double.parseDouble(parametrik[k]);
                                hasilx[i] = convert;
                            }
                        }
                    }
                }
            }

            printSolusiGaussJordan(hasilx);

        } else {
            System.out.println("No Solution");
        }
    }

    public void gaussJordanElimination(double[][] m) {
        /* I.S matriks m terdefinisi */
        /* F.S m berubah menjadi suatu matriks eselon baris tereduksi yang setara dengan eliminasi Gauss-Jordan */

        /* Kamus Lokal */
        int rowCurrentlyWorked, i, j;
        
        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();
        gaussObj.gaussElimination(m);

        rowCurrentlyWorked = countRowToWorked(m) - 1;
        j = matrixObj.getLastIdxCols(m);

        while (rowCurrentlyWorked >= 0) {
            if (j == indexOfLeadingOne(m, rowCurrentlyWorked)) {
                for (i = rowCurrentlyWorked - 1; i >= 0; i--) {
                    gaussObj.addRowWithMultipleOfRow(m, i, rowCurrentlyWorked, -(m[i][j]/m[rowCurrentlyWorked][j]));
                }
                rowCurrentlyWorked--;
            }
            j--;
        }

        gaussJordanEliminationSolution(m);
    }
}
