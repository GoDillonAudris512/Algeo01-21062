package src;

public class Gauss {
    public static void main(String[] args){
        int i,j,k,n,leadingOne,pembuatNol;
        int[][] matrix = {{1, 2, 3, 4},{5, 6, 7, 8},{1, 6, 8, 3},{5, 2, 7, 9}};
        int[] hasil = {5, 6, 7, 8};

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