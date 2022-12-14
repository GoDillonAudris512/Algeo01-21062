/* GaussJordan.Java */
/* Source code untuk menyelesaikan metode SPL dengan eliminasi Gauss-Jordan */

package Library;

import Matrix.*;

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
        int i;
        boolean thereIsLeadingOne = false;
        Matrix mat = new Matrix();

        for (i = 0; i < mat.getnRows(matrix); i++) {
            if (kolomX == indexOfLeadingOne(matrix, i)) {
                thereIsLeadingOne = true;
            }
        }

        return thereIsLeadingOne;
    }

    public Object[] gaussJordanEliminationSolution (double[][] matrix) {
        Matrix mat = new Matrix();
        MatriksBalikan matObj = new MatriksBalikan();
        GaussJordan matt = new GaussJordan();
        Gauss mattt = new Gauss();

        int i,j,k,countExc = 0, countZero = 0, parameterUsed = 0, currentVariable;
        String[] parametrik = {"a", "b","c","d","e","f","g", "h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Object[] hasilx;
        double[][] mainMat, hasil;
        boolean[] para = new boolean[0];

        mainMat = matObj.splitMainMatrix(matrix);
        hasil = matObj.splitHasil(matrix);

        if (mat.getnCols(mainMat) > mat.getnRows(mainMat)) {
            hasilx = new Object[mat.getnCols(mainMat)];
            para = new boolean[mat.getnCols(mainMat)];
            currentVariable = mat.getLastIdxCols(mainMat);
        }
        else {
            hasilx = new Object[mat.getnRows(mainMat)];
            para = new boolean[mat.getnRows(mainMat)];
            currentVariable = mat.getLastIdxRows(mainMat);
        }

        for (i = 0; i < mat.getnRows(matrix); i++){
            if (mattt.isRowAnException(matrix, i)) {
                countExc += 1;
            }

            if (mattt.isRowAllZero(matrix, i)) {
                countZero += 1;
            }
        }

        if (countExc == 0) { // ada solusi

            if (mat.getnCols(mainMat) == mat.getnRows(mainMat) && countZero == 0) { // matrix persegi, solusi unik
                for (i = mat.getLastIdxRows(mainMat); i >= 0; i--) {
                    for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                        hasilx[i] = hasil[i][0];
                    }
                }

            } else { // kolom > baris, solusi parametrik
                for (i = 0; i < hasilx.length; i++) {
                    hasilx[i] = "";
                }
                
                for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                    if (mattt.isRowAllZero(matrix, i)) {
                        hasilx[currentVariable] += parametrik[parameterUsed];
                        para[currentVariable] = true;
                        parameterUsed++;
                        currentVariable--;
                    }
                    else {
                        for (k = 0; k <= mat.getLastIdxCols(mainMat); k++) {
                            if (!matt.isThereLeadingOne(matrix, k) && hasilx[k] == "") {
                                hasilx[k] = parametrik[parameterUsed];
                                para[k] = true;
                                parameterUsed++;                            
                            }
                        }

                        for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                            if (para[j] == false && hasilx[j] == "") {
                                hasilx[j] = hasil[i][0];
                            }
                        }

                        for (j = mat.getLastIdxCols(mainMat); j > matt.indexOfLeadingOne(mainMat, i); j--) {
                            if ((mainMat[i][j] != 0) && (mainMat[i][j] != 1) && (mainMat[i][j] != -1)) {
                                if (mainMat[i][j] > 0) {
                                    hasilx[matt.indexOfLeadingOne(mainMat, i)] += " - " + mainMat[i][j] + "*(" + hasilx[j] + ")"; 
                                }
                                else {
                                    hasilx[matt.indexOfLeadingOne(mainMat, i)] += " + " + -mainMat[i][j] + "*(" + hasilx[j] + ")";
                                }
                            }
                            else if (mainMat[i][j] == 1) {
                                hasilx[matt.indexOfLeadingOne(mainMat, i)] += " - " + "(" + hasilx[j] + ")"; 
                            }
                            else if (mainMat[i][j] == -1) {
                                hasilx[matt.indexOfLeadingOne(mainMat, i)] += " + " + "(" + hasilx[j] + ")";
                            }
                        } 
                        
                    }
                }
            }    
        }
        else {
            hasilx = null;
        }

        return hasilx;
    }

    public void gaussJordanElimination(double[][] m) {
        /* I.S matriks m terdefinisi */
        /* F.S m berubah menjadi suatu matriks eselon baris tereduksi yang setara dengan eliminasi Gauss-Jordan */

        /* Kamus Lokal */
        int rowCurrentlyWorked, i, j;
        
        /* Algoritma */
        Matrix matrixObj = new Matrix();
        Gauss gaussObj = new Gauss();
        gaussObj.gaussElimination(m, false);

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
