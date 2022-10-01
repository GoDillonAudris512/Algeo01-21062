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

    public boolean isPersegi(double[][] matrix) {
        boolean isPersegi = true;
        int i,j,count = 0;
        Matrix matObj = new Matrix();

        for (i = 0; i < matObj.getnCols(matrix); i++) {
            for (j = 0; j < matObj.getnRows(matrix); j++) {
                count += 1;
            }
        }

        if (count == (matObj.getnCols(matrix) * matObj.getnRows(matrix))) {
            isPersegi = true;
        } else {
            isPersegi = false;
        }

        return isPersegi;
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

    public void cramerElimination(double[][] matrix) {
        Matrix matrixObj = new Matrix();
        int i,k;
        Object[] hasilx = new Object[matrixObj.getnCols(matrix)-1];
        double[][] matrixTemp, hasil, mainMat;

        DeterminanKofaktor detKofObj = new DeterminanKofaktor();
        MatriksBalikan matrixObjek = new MatriksBalikan();

        mainMat = matrixObjek.splitMainMatrix(matrix);
        hasil = matrixObjek.splitHasil(matrix);
        
        matrixTemp = mainMat;

        if (isPersegi(mainMat)) {
            if (detKofObj.determinanKofaktor(mainMat) != 0) {
                for (i = 0; i < matrixObj.getnCols(mainMat); i++) {
                    for (k = 0; k < matrixObj.getnRows(mainMat); k++) {

                        matrixTemp = changeColXWithHasil(mainMat, hasil, k);
                        // menukar kolom di matrixTemp dengan hasil

                        hasilx[i] = detKofObj.determinanKofaktor(matrixTemp) / detKofObj.determinanKofaktor(mainMat);
                        // menghitung hasil x ke-k dengan cara
                        // membagi determinant yang sudah diganti dengan determinant matrix awal

                        matrixTemp[i][k] = mainMat[i][k];
                        // mengubah nilai dari matrixTemp menjadi mainMat lagi
                    }
                }
                matrixObj.printSolusi(hasilx);
            }
            else {
                System.out.println("Matriks tidak mempunyai solusi unik");
            }
        } else {
           System.out.println("Bukan Matriks Persegi!"); 
        }

    }    
}
