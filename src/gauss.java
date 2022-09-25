package src;

public class gauss {
    public void gaussElimination(double[][] matrix, double[] hasil){
        int i,j,k,n;
        double leadingOne,pembuatNol;

        n = hasil.length;
        for (k = 0; k < n; k++) {
            leadingOne = matrix[k][k];
            for (j = 0; j < n; j++) {
                matrix[k][j] = matrix[k][j] / leadingOne; 
            }
            hasil[j] = hasil[j] / leadingOne;

            for (i = k+1; i < n; i++) {
                if (k == i || matrix[i][k] == 0) {
                    // karna kalau k == i, leadingOne nya
                    // matrix[i][k] == 0, ga perlu di apa2in
                    continue;
                }

                pembuatNol = matrix[i][k];
                
                for (j = k; k < n; j++) {
                    matrix[i][j] -= pembuatNol * matrix[k][j];
                }
                hasil[i] -= pembuatNol * hasil[k];
            }
        }
    }
}