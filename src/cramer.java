package src;

public class cramer {
    static int counter(double[][] matrix) {
        int i,j,count = 0;

        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix.length; j++) {
                count += 1;
            }
        }

        return count;
    }
    public void cramerElimination(double[][] matrix, double[] hasil) {
        int i,k,n;
        double hasilx;
        double[][] matrixtemp;

        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        
        n = hasil.length;
        matrixtemp = matrix;

        if (n*n == counter(matrix)) {
            for (k = 0; k < n; k++) {
                for (i = 0; i < n; i++) {
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
