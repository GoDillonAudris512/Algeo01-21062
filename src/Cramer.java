package src;

public class Cramer {
    public int counter(double[][] matrix) {
        int i,j,count = 0;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix.length; j++) {
                count += 1;
            }
        }

        return count;
    }

    public double[][] changeColXWithHasil (double[][] matrix, double[][] hasil,int ColX) {
        int i;
        double[][] matrixTemp;
        Matrix matObj = new Matrix();
        matrixTemp = matrix;

        for (i = 0; i < matObj.getnRows(matrix); i++) {
            matrixTemp[i][ColX] = hasil[i][0];
        }

        return matrixTemp;
    }

    public Object[] cramerElimination(double[][] matrix) {
        Matrix matrixObj = new Matrix();
        int i,k;
        Object[] hasilx = new Object[matrixObj.getnCols(matrix)-1];
        double[][] matrixTemp, hasil, mainMat;

        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        MatriksBalikan matrixObjek = new MatriksBalikan();

        mainMat = matrixObjek.splitMainMatrix(matrix);
        hasil = matrixObjek.splitHasil(matrix);
        
        matrixTemp = mainMat;

        if (detKofObj.determinanKofaktor(mainMat) != 0) {
            for (i = 0; i < matrixObj.getnCols(mainMat); i++) {
                for (k = 0; k < matrixObj.getnRows(mainMat); k++) {

                    matrixTemp = changeColXWithHasil(mainMat, hasil, k);
                    // menukar kolom di matrixTemp dengan hasil

                    hasilx[i] = (double) (detKofObj.determinanKofaktor(matrixTemp) / detKofObj.determinanKofaktor(mainMat));
                    // menghitung hasil x ke-k dengan cara
                    // membagi determinant yang sudah diganti dengan determinant matrix awal

                    matrixTemp[i][k] = mainMat[i][k];
                    // mengubah nilai dari matrixTemp menjadi mainMat lagi
                }
            }
        }
        else {
           hasilx = null;
        }

        return hasilx;
    } 
}    

