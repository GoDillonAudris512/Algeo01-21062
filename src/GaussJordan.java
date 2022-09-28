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
    }
}
