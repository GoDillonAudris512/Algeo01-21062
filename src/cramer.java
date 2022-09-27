package src;

public class cramer {
    public int counter(double[][] matrix) {
        int i,j,count = 0;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix.length; j++) {
                count += 1;
            }
        }

        return count;
    }

    public void changeColXWithHasil (double[][] matrix, int ColX) {
        int i, j;
        double[] hasil;
        double[][] matrixTemp;
        Matrix matObj = new Matrix();

        for (i = 0; i < matObj.getLastIdxRows(matrix); i++) {
            hasil[i] = matrix[i][matObj.getLastIdxRows(matrix)];
        }

        for (i = 0; i < matObj.getLastIdxRows(matrix); i++) {
            for (j = 0; j < matObj.getLastIdxCols((matrix))-1; j++) {
                matrixTemp[i][j] = matrix[i][j];
            }
        }
    }

    public void cramerElimination(double[][] matrix) {
        int i,k,n;
        double hasilx;
        double[][] matrixtemp, matrixUtama;

        Matrix matrixObj = new Matrix();
        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        
        n = matrixObj.getLastIdxRows(matrix);
        matrixtemp = matrix;

        if (n*n == counter(matrix)) {
            for (i = 0; i < matrixObj.getLastIdxCols(matrix); i++) {
                for (k = 0; k < n; k++) {
                    matrixUtama[i][k] = matrix[i][k];
                    matrixtemp[i][k] = hasil[i];
                    // mengganti baris ke-i dengan hasil

                    hasilx = detKofObj.determinanKofaktor(matrixtemp) / detKofObj.determinanKofaktor(matrix);
                    System.out.print(hasilx);
                    // menghitung hasil x ke-k dengan cara
                    // membagi determinant yang sudah diganti dengan determinant matrix awal

                    matrixtemp[i][k] = matrix[i][k];
                }
            }
        } else {
           System.out.println("Bukan Matriks Persegi!"); 
        }

    }    
}
