package src;

public class gaussjordan {
    public void gaussJordanElimination(double[][] matrix, double[] hasil){
        int i,j,k,n;
        double leadingOne,pembuatNol;

        n = hasil.length;
        // jumlah baris

        for (k = 0; k < n; k++) {
            leadingOne = matrix[k][k];
            // leading one nya itu adalah elemen yg ada di diagonal


            for (j = 0; j < n; j++) {
                matrix[k][j] = matrix[k][j] / leadingOne; 
            }
            hasil[j] = hasil[j] / leadingOne;
            // membagi baris sm nilai leading one di baris itu


            for (i = 0; i < n; i++) {
                if (k == i || matrix[i][k] == 0) {
                    // karna kalau k == i, leadingOne nya
                    // matrix[i][k] == 0, ga perlu di apa2in
                    continue;
                }

                pembuatNol = matrix[i][k];
                
                for (j = k; k < n; j++) {
                    matrix[i][j] -= pembuatNol * matrix[k][j];
                    // membuat nilai dibawah dan diatas leading one jadi 0
                }
                hasil[i] -= pembuatNol * hasil[k];
            }
        }
    }
}