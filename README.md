# Algeo01-21062
Repository Tugas Besar Mata Kuliah Aljabar Linier dan Geometri

## General Information
Sebuah program untuk menyelesaikan berbagai masalah yang berkaitan dengan matriks yaitu SPL, determinan matriks, invers matriks, interpolasi polinom,
interpolasi bikubik, dan regresi linear berganda. Repository ini dibuat dan mengandung file yang dibutuhkan untuk menyelesaikan Tugas Besar IF2123 Aljabar Linier dan Geometri.
Contributors: 
- 13521062 Go Dillon Audris
- 13521063 Salomo Reinhart Gregory Manalu
- 13521160 Muchammad Dimas Sakti Widyatmaja

## Languange Used
- Java Languange (100%)

## Features
- Menyelesaikan sistem persamaan linear dengan eliminasi Gauss, eliminasi Gauss-Jordan, metode Invers, dan kaidah Cramer
- Menyelesaikan determinan matriks dengan metode reduksi baris (OBE) dan ekspansi kofaktor
- Menyelesaikan invers matriks dengan metode reduksi baris (OBE) dan metode adjoint
- Menyelesaikan permasalahan interpolasi polinom
- Menyelesaikan permasalahan interpolasi bikubik
- Menyelesaikan permasalahan regresi linear berganda

## Repository Structure
```bash
.
│   README.md
│
├───bin                                   # Folder mengandung bytecode
│   ├───Library
│   │       Cramer.class
│   │       DeterminanKofaktor.class
│   │       DeterminanReduksiBaris.class
│   │       Gauss.class
│   │       GaussJordan.class
|   |       InterpolasiBikubik.class
|   |       InterpolasiPolinom.class
|   |       InversAdjoint.class
|   |       InversReduksiBaris.class
|   |       MatriksBalikan.class
|   |       RegresiLinierBerganda.class
│   │
│   ├───Main
│   │       Main.class
│   │
│   └───Matrix
│           Matrix.class
│           Matrix$RowCol.class
|
├───doc                                   # Dokumentasi
├───src                                   # Source code
│   ├───Library
│   │       Cramer.java
│   │       DeterminanKofaktor.java
│   │       DeterminanReduksiBaris.java
│   │       Gauss.java
│   │       GaussJordan.java
|   |       InterpolasiBikubik.java
|   |       InterpolasiPolinom.java
|   |       InversAdjoint.java
|   |       InversReduksiBaris.java
|   |       MatriksBalikan.java
|   |       RegresiLinierBerganda.java
│   │
│   ├───Main
│   │       Main.java
│   │
│   └───Matrix
│           Matrix.java
│
└───test                            # Studi kasus
    └───resultTestCase              # Hasil studi kasus
        testCase1a.txt
        testCase1b.txt
        testCase1c.txt
        testCase1d6.txt
        testCase1d10.txt
        testCase2a.txt
        testCase2b.txt
        testCase3a.txt
        testCase3b.txt
        testCase4a1.txt
        testCase4a2.txt
        testCase4a3.txt
        testCase4a4.txt
        testCase4b1.txt
        testCase4b2.txt
        testCase4b3.txt
        testCase4c.txt
        testCase5a1.txt
        testCase5a2.txt
        testCase5a3.txt
        testCase5a4.txt
        testCase5d.txt
        testCase6Xk.txt
        testCase6XY.txt
```

## How to Compile and Run
(Jika sudah tercompile (bytecode sudah ada), langsung ke step 3)
1. Buka folder src
2. Compile semua source code dan letakkan di folder bin dengan command : 'javac -d ..\bin Library/*.java Matrix/*.java Main/*.java'
3. Buka folder bin
4. Run program dengan command: 'java Main.Main'


## Acknowledgements
- Terima kasih kepada Tuhan yang Maha Esa
- Terima kasih kepada para dosen pengampu: Pak Judhi, Pak Rinaldi, dan Pak Rila
- Terima kasih kepada Tim Asisten Kuliah IF2123

## Contacts
Diciptakan dan diatur oleh Hiksrot
![](https://user-images.githubusercontent.com/110383663/193578061-5ddcb1d7-ed7a-4441-b873-c4bc65addc9a.jpg)
