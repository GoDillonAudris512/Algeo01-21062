/* Gauss.java */
/* Source Code berisi penyelesaian SPL dengan metode eliminasi Gauss */

package src;

import java.util.Arrays;

public class Gauss {

    public void swapRow (double[][] m, int baris1, int baris2) {
        /* I.S Matriks m terdefinisi, baris1 dan baris 2 ada pada m */
        /* F.S Baris berindeks baris1 pada matriks m akan bertukar dengan baris berindeks baris2 */

        /* Kamus Lokal */
        double[] temp;

        /* Algoritma */
        temp = m[baris1];
        m[baris1] = m[baris2];
        m[baris2] = temp;
    }

    public void addRowWithMultipleOfRow (double[][] m, int baris1, int baris2,  double constant) {
        /* I.S Matriks m terdefinisi, baris1 dan baris2 ada pada m, constant terdefinisi */
        /* F.S Elemen m pada baris berindeks baris1 akan ditambah dengan constant*elemen pada baris berindeks baris2 */

        /* Kamus Lokal */
        int i;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        for (i = 0; i < matrixObj.getnCols(m); i++) {
            m[baris1][i] += m[baris2][i]*constant;
        }
    }

    public void divideRowWithConstant (double[][] m, int barisX, double constant) {
        /* I.S Matriks m terdefinisi, barisX ada pada m, constant terdefinisi */
        /* F.S Elemen m pada baris berindeks barisX akan dibagi dengan constant */

        /* Kamus Lokal */
        int i;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        for (i = 0; i < matrixObj.getnCols(m); i++) {
            m[barisX][i] = m[barisX][i]/constant;
        }
    }

    public boolean isRowAllZero (double[][] m, int barisX) {
        /* Menghasilkan true jika barisX pada m semua elemennya 0, false jika tidak */

        /* Kamus Lokal */
        int i = 0;
        boolean allZero = true;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        while ((i < matrixObj.getnCols(m)) && allZero) {
            if (m[barisX][i] != 0) {
                allZero = false;
            }
            else {
                i++;
            }
        } /* allZero = false or i = matrixObj.getnCols(m) */

        return allZero;
    }

    public boolean isRowAnException (double[][]m, int barisX) {
        /* Menghasilkan true jika barisX adalah suatu baris dengan semua elemen 0 kecuali pada */
        /* kolom terakhir, false jika tidak. Baris dikatakan suatu exception karena baris ini */
        /* menyebabkan SPL tidak memiliki solusi dan harus ditangani */

        /* Kamus Lokal */
        int i = 0;
        boolean isException = true;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        while ((i < matrixObj.getnCols(m)) && isException) {
            if ((i != matrixObj.getLastIdxCols(m)) && (m[barisX][i] != 0)) {
                isException = false;
            }
            else if ((i == matrixObj.getLastIdxCols(m)) && (m[barisX][i] == 0)) {
                isException = false;
            }
            else {
                i++;
            }
        } /* isException = false or i = matrixObj.getnCols(m) */

        return isException;
    }

    public int indexOfPivotRow (double[][]m, int barisX, int kolomX) {
        /* Prekondisi: matriks m sudah tertata, dimana baris yang semuanya 0 dan suatu exception diletakkan di bawah */
        /* Menghasilkan indeks suatu pivot row, dimana pivot row ditentukan dengan membandingkan */
        /* elemen pada kolomX untuk baris mulai dari barisX hingga baris terakhir m */

        /* Kamus Lokal */
        int indexOfPivot = barisX, i;

        /* Algoritma */
        Matrix matrixObj = new Matrix();
        for (i = barisX+1; i < matrixObj.getnRows(m); i++) {
            if ((m[i][kolomX] < m[indexOfPivot][kolomX]) && (m[i][kolomX] != 0) && (!isRowAllZero(m, i)) && (!isRowAnException(m, i)) || ((m[indexOfPivot][kolomX] == 0) && (m[i][kolomX] != 0))) {
                indexOfPivot = i;
            }
        }

        return indexOfPivot;
    }

    public int arrangeMatrix (double[][] m) {
        /* Seluruh baris Zero dan Exception berada di bagian bawah matriks */
        /* Juga mengembalikan banyak baris yang bukan Zero*/
        /* sebagai dasar pengerjaan eliminasi gauss */

        /* Kamus Lokal */
        int i, rowAlreadyArranged = 0, countRowZero;

        /* Algoritma */
        Matrix matrixObj = new Matrix();

        // Tukar seluruh baris Zero ke bawah
        for (i = 0; i < matrixObj.getnRows(m); i++) {
            if (!isRowAllZero(m, i)) {
                swapRow(m, i, rowAlreadyArranged);
                rowAlreadyArranged++;
            }
        }

        countRowZero = matrixObj.getnRows(m) - rowAlreadyArranged;

        // Tukar seluruh baris Exception ke atas baris Zero teratas
        for (i = 0; i < rowAlreadyArranged; i++) {
            if (!isRowAnException(m, i)) {
                swapRow(m, i, matrixObj.getnRows(m)-countRowZero-1);
                countRowZero++;
            }
        }

        return rowAlreadyArranged;
    }

    public double makeParametrik (double x, String k) {
        String parameter = "";
        
        if (x != 0) {
            if (x != 1) {
                parameter += x;
                parameter += k;
            } else {
                parameter += k;
            }
        } else {
            parameter += 0;
        }
        
        double convert = Double.parseDouble(parameter);
        return convert;
    }

    public boolean isOnlyParametric(String s) {
        boolean OnlyParametric = true;
        if (s.length() == 1 
        && (s != "0")
        && (s != "1")
        && (s != "2")
        && (s != "3")
        && (s != "4")
        && (s != "5")
        && (s != "6")
        && (s != "7")
        && (s != "8")
        && (s != "9")) {
            OnlyParametric = true;
        } else {
            OnlyParametric = false;
        }

        return OnlyParametric;
    }

    public double multiplyParametrik(Object hasilx, double mainMat, boolean xke, String s) {
        String angka = "", parameter = "", hasilAkhir = "";
        int i;
        double hasil, hasilTemp;

        if (xke == true) { // kalau udah di gabung sama parametrik

            // mengubah solusi x menjadi string
            String convert = (String) hasilx;

            if (isOnlyParametric(s)) { // kalau cuma parameter tanpa angka
                hasil = makeParametrik(mainMat, s);

            } else { // kalau udah punya parameter dan angka

                // solusi dipisah-pisah
                String[] arrOfStr = convert.split("",1);

                // memasukkan angka ke dalam "angka" dan huruf ke dalam "parameter"
                for (i = 0; i < convert.length()-1; i++) {
                    angka += arrOfStr[i];
                }
                parameter += arrOfStr[convert.length()-1];

                // mengubah angka menjadi double dan dikali mainMat
                double angkaFinal = Double.parseDouble(angka);
                hasilTemp = angkaFinal * mainMat;

                // mengubah hasil perkalian menjadi double
                String strangkaFinal = Double.toString(hasilTemp);

                // menggabungkan hasil perkalian dengan parameter
                hasilAkhir += strangkaFinal;
                hasilAkhir += parameter;

                // mengubah hasilAkhir (gabungan perkalian dengan parameter)
                // menjadi double
                Double hasilFinal = Double.parseDouble(hasilAkhir);
                hasil = hasilFinal;
            }

        } else { // kalau bukan parametrik
            hasil = (double) hasilx * mainMat;
        }

        return hasil;
    }

    public Object[] gaussEliminationSolution(double[][] matrix) {
        MatriksBalikan matObj = new MatriksBalikan();
        Matrix mat = new Matrix();
        GaussJordan matt = new GaussJordan();

        int i,j,k,n,countExc = 0, countZero = 0;
        String[] parametrik = {"s","t","u","v","w"};
        Object[] hasilx = new Object[mat.getnRows(matrix)];
        double[][] mainMat, hasil;
        boolean[] para = new boolean[mat.getnRows(matrix)];
        
        mainMat = matObj.splitMainMatrix(matrix);
        hasil = matObj.splitHasil(matrix);

        for (i = 0; i < mat.getnRows(matrix); i++){
            if (isRowAnException(matrix, i)) {
                countExc += 1; // kalau lebih > 0 berarti no solusi
            }
            if (isRowAllZero(matrix, i)) {
                countZero += 1;
            }
        }

        if (countExc == 0) {
            // bukan baris exception
            if ((mat.getnCols(mainMat) == mat.getnRows(mainMat)) && (countZero == 0)) {
                // matrix persegi
                for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                    for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                        if ((i == mat.getLastIdxRows(matrix)) && (j == mat.getLastIdxCols(mainMat))) {
                            // nilai x-terakhir
                            hasilx[j] = hasil[i][0];
                        } else {
                            // nilai x bukan yang terakhir
                            if (j != matt.indexOfLeadingOne(mainMat, i)) {
                                hasil[i][0] -= ((double) hasilx[j] * mainMat[i][j]);
                                hasilx[i] = hasil[i][0];
                            }
                        }
                    }
                }
            } else { // jumlah kolom > baris (parametrik)
                    // pengecekan dari ujuang kanan bawah ke ujung kiri atas
                    for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                        for (j = mat.getLastIdxCols(mainMat); j >= matt.indexOfLeadingOne(mainMat, i); j--) {
                            for (k = (mat.getLastIdxCols(mainMat)-i); k > 0; k++) { // berapa elemen yang perlu di cek sampai index leading one
                                for (n = mat.getLastIdxCols(mainMat); n >= 0; n--) { // mengecek apakah ada leadingOne di kolom n
                                            
                                    if (n == matt.indexOfLeadingOne(mainMat, i)) { // ada leading one di kolom n
                                        System.out.println(Arrays.toString(hasilx));
                                        hasil[i][0] -= multiplyParametrik(hasilx[j], mainMat[i][j], para[j], parametrik[i]);
                                        hasilx[i] = hasil[i][0];
                                                

                                    } else { // ga ada leading one di kolom n, maka x-xke adalah parameter
                                        String p = parametrik[i];
                                        hasilx[i] = p;
                                        para[i] = true;
                                    }
                                }
                            }
                        }
                    }    
            }

            mat.printSolusi(hasilx);
        } else { // ga ada solusi, countExc > 0
            System.out.println("No Solution");
        }
        return hasilx;
    }

    public double[] gaussEliminationSolutionKeluaran(double[][] matrix) {
        MatriksBalikan matObj = new MatriksBalikan();
        Matrix mat = new Matrix();
        GaussJordan matt = new GaussJordan();

        int i,j,k, countExc = 0;
        double[] hasilx = new double[mat.getnRows(matrix)];
        double[][] mainMat, hasil;
        
        mainMat = matObj.splitMainMatrix(matrix);
        hasil = matObj.splitHasil(matrix);

        for (i = 0; i < mat.getnRows(matrix); i++){
            if (isRowAnException(matrix, i)) {
                countExc += 1;
            }
        }

        if (countExc == 0) {
            // bukan baris exception

            if (mat.getnCols(mainMat) == mat.getnRows(mainMat)) {
                // matrix persegi

                for (i = mat.getLastIdxRows(matrix); i >= 0; i--) {
                    for (j = mat.getLastIdxCols(mainMat); j > matt.indexOfLeadingOne(mainMat, i); j--) {
                        if ((i == mat.getLastIdxRows(matrix)) && (j == mat.getLastIdxCols(mainMat))) {
                            // nilai x-terakhir
                            hasilx[i] = hasil[i][j];
                        } else {
                            // nilai x bukan yang terakhir
                            for (k = (mat.getLastIdxCols(mainMat)-i); k > 0; k++) {
                                hasil[i][j] -= (hasilx[j] * mainMat[i][j]);
                                hasilx[i] = hasil[i][j];
                            }
                        }
                    }
                }
            }

            return hasilx;

        } else {
            System.out.println("No Solution");
            return hasilx;
        }
    }
        
    public void gaussElimination(double[][] m){
        /* I.S m terdefinisi */
        /* F.S m berubah menjadi suatu matriks eselon baris yang setara dengan metode eliminasi Gauss */

        /* Kamus */
        int indexColumn = 0, rowCurrentlyWorked = 0, indexPivot = 0, rowToWorked, i, j;
        /* Algoritma */
        Matrix matrixObj = new Matrix();

        // Menukar seluruh baris Zero dan baris Exception ke bawah dan menentukan banyak row yang akan dilakukan Gauss Elimination
        rowToWorked = arrangeMatrix(m) - 1;

        // Mulai Gauss Elimination
        for (indexColumn = 0; indexColumn < matrixObj.getnCols(m); indexColumn++) {
            if (rowCurrentlyWorked <= rowToWorked) {
                indexPivot = indexOfPivotRow(m, rowCurrentlyWorked, indexColumn);
                swapRow(m, indexPivot, rowCurrentlyWorked);

                if (m[rowCurrentlyWorked][indexColumn] != 0) {
                    for (i = rowCurrentlyWorked+1; i <= rowToWorked; i++) {
                        addRowWithMultipleOfRow(m, i, rowCurrentlyWorked, -(m[i][indexColumn]/m[rowCurrentlyWorked][indexColumn]));
                    }
                    divideRowWithConstant(m, rowCurrentlyWorked, m[rowCurrentlyWorked][indexColumn]);
                    rowCurrentlyWorked++;
                }
            }
            
        }

        // Handling elemen = -0
        for (i = 0; i < matrixObj.getnRows(m); i++) {
            for (j = 0; j < matrixObj.getnCols(m); j++) {
                if (m[i][j] == -0) {
                    m[i][j] = 0;
                }
            }
        }
    }
}
