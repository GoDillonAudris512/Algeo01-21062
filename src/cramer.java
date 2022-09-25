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
        int i,j,k,n;
        double[] hasilx;
        double[][] matrixtemp;

        n = hasil.length;
        matrixtemp = matrix;

        if (n*n == counter(matrix)) {
            for (k = 0; k < n; k++) {
                matrixtemp[k] = hasil;
                // mengganti baris ke-k dengan hasil

                hasilx[k] = determinant.matrixtemp[k][k] / determinant.matrix;
                // menghitung hasil x ke-k dengan cara
                // membagi determinant yang sudah diganti dengan determinant matrix awal
            }
        } else {
           break;
           System.out.println("Bukan Matriks Persegi!"); 
        }

    }    
}
