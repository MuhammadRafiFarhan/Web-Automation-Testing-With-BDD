# SwagLabs Web Apps and Dummy API Automation Testing

Repository ini merupakan gabungan dari dua project berbeda, yakni:

  1. pengujian otomatis pada web e-commerce [Swag Labs](https://saucedemo.com/) yang menggunakan konsep Behavior-Driven Development (BDD) dengan menggunakan bahasa pemrograman Java dan framework Cucumber + Selenium.
  2. pengujian API yang disediakan oleh [Dummy API](https://dummyapi.io/). Hanya API untuk data User yang diuji


 Proyek ini dibuat untuk memenuhi tugas mata kuliah Pengujian Perangkat Lunak (Software Testing) praktikum pada pertemuan ke-11, 12, 13, 14, dan Minggu Tenang sebelum pelaksanaan EAS Genap 2024.

## Table of Contents
  - [Software Under Test (SUT)](#software-under-test-sut)
  - [Tools yang Digunakan](#tools-yang-digunakan)
  - [Getting Started](#getting-started)
    - [Pre-requisites](#pre-requisites)
    - [Installation](#installation)
    - [Folder structure (Maven, simplified)](#folder-structure-maven-simplified)
  - [Alur Kerja Pembuatan Test Script](#alur-kerja-pembuatan-test-script)
    - [Untuk Pengujian API (Dummy API)](#untuk-pengujian-api-dummy-api)
    - [Untuk Pengujian Web Otomatis (Swag Labs)](#untuk-pengujian-web-otomatis-swag-labs)
  - [Running Test Script](#running-test-script)
  - [Test Case Design](#test-case-design)
    - [Untuk Pengujian API Dummy API](#untuk-pengujian-api-dummy-api)
      - [Test Case Valid API](#test-case-valid-api)
      - [Test Case Invalid API](#test-case-invalid-api)
    - [Untuk Pengujian Aplikasi Web Swag Labs](#untuk-pengujian-aplikasi-web-swag-labs)
      - [Test Case Valid Web](#test-case-valid-web)
      - [Test Case Invalid Web](#test-case-invalid-web)
  - [Author](#author)
  - [References](#references)

## Software Under Test (SUT)
Software yang diuji adalah aplikasi web e-commerce bernama [Swag Labs](https://www.saucedemo.com/). Aplikasi ini menyediakan enam buah produk yang dapat dibeli oleh pengguna. Pengguna dapat melakukan login terlebih dahulu untuk mengakses fitur-fitur yang ada pada aplikasi ini. Aplikasi ini memiliki beberapa fitur, dengan fitur yang dijadikan fokus pada pengujian ini adalah:
1. Fitur login
2. Fitur logout<br>

Adapun software kedua yang diuji adalah API yang disediakan oleh [Dummy API](https://dummyapi.io/). API ini menyediakan data-data dummy yang dapat digunakan untuk keperluan pengujian. Data yang diuji pada API ini adalah data pada bagian yang dimarkahi User Controller. Method API yang dapat diuji adalah:

1. `GET` /user/{id}
   > Mendapatkan data user berdasarkan ID
2. `POST` /user/create
   > Membuat data user baru, kembalikan data user yang telah dibuat
   > Body request (required field):
   ```json
   {
     "firstName": "string",
      "lastName": "string",
     "email": "string",
   }
   ```
3. `PUT` /user/{id}
   > Mengubah data user berdasarkan ID, kembalikan data user yang telah diubah
   > Body request: field data user yang akan diubah, kecuali ID dan email
4. `DELETE` /user/{id}
   > Menghapus data user berdasarkan ID, kembalikan ID user yang telah dihapus

**Kembali ke [Daftar Isi](#table-of-contents)**


## Tools yang Digunakan

Proyek pengujian secara otomatis ini menggunakan beberapa tools yang esensial, diantaranya adalah:

* Visual Studio Code IDE
* Java Development Kit (JDK) 8 atau lebih tinggi
* Apache Maven v3.9.2
* Rest Assured v5.4.0
* Hamcrest v2.2
* JSON Schema Validator v5.4.0
* Maven Surefire Plugin 3.0.0-M8
* Dotenv v3.0.0 (io.github.cdimascio)
* Cucumber v7.11.1
* Cucumber (Gherkin) Full Support VS Extension *(wajib)*
* Selenium 4.21.0
* WebDriverManager 5.8.0 (io.github.bonigarcia)
* JUnit Jupiter 5.9.2
* Google Chrome Browser dan Chrome Driver
* Git dan Github
* CLI (cmd atau Powershell)

**Kembali ke [Daftar Isi](#table-of-contents)**


## Getting Started
### Pre-requisites
Agar dapat menjalankan project ini, pastikan bahwa perangkat Anda telah memenuhi atau memiliki spesifikasi berikut:
- [x] Memiliki koneksi internet yang stabil
- [x] Java Development Kit (JDK) versi 8 atau lebih tinggi
- [x] Apache Maven versi 3.8.3 atau lebih tinggi
- [x] Web browser Google Chrome yang telah terinstall di dalam perangkat
- [x] Menginstall ekstensi Cucumber (Gherkin) Full Support pada Visual Studio Code
- [x] Memiliki akun pada https://dummyapi.io/ untuk mendapatkan API key app-id pribadi

**Kembali ke [Daftar Isi](#table-of-contents)**

### Installation

> <big><b>Peringatan untuk pengguna Powershell</b></big><br>
> Ketika menambahkan flag ke perintah maven, berikan tanda kutip tunggal. Contoh:<br>
> `mvn test '-Dcucumber.filter.name="^Successful\s.*$"'`<br><br>
> Untuk pengguna CMD, tambahkan flag tanpa tanda kutip. Contoh yang sama:<br>
> `mvn test -Dcucumber.filter.name="^Successful\s.*$"`<br><br>

1. Clone repository ini ke dalam perangkat lokal Anda dengan menjalankan perintah berikut pada terminal atau command prompt:
    ```bash
    git clone https://github.com/MuhammadRafiFarhan/Web-Automation-Testing-With-BDD.git
    ```

2. Setelah proses cloning selesai, buka folder proyek ini pada Visual Studio Code.

3. Jalankan perintah berikut pada terminal atau command prompt untuk menginstall dependencies yang diperlukan:
    ```bash
    mvn clean install -Dmaven.test.skip=true
    ```
    <br/>
    Flag `-Dmaven.test.skip=true` digunakan untuk melewati proses kompilasi dan eksekusi test, yang dapat lebih cepat jika Anda tidak perlu mengkompilasi kode test ketika akan menginstall dependencies.

4. Tunggu hingga proses instalasi dependencies selesai dan sukses. Setelah itu, proyek siap untuk dijalankan.

5. Buat file `.env` baru pada root folder proyek ini dengan cara mengcopy file `.env.example`. Isi file baru tersebut dengan API key yang didapatkan dari [Dummy API](https://dummyapi.io/). Contoh isi file `.env`:
    ```env
    API_KEY=your-api-key-here
    ```

**Kembali ke [Daftar Isi](#table-of-contents)**


### Folder structure (Maven, simplified)
Berikut adalah struktur folder dari proyek ini:
```
  root
    ├───.vscode
    ├───src
    │   ├───main/java/com
    │   │   ├───apitest
    │   │   │   └─── InitiationUserId.java
    │   │   └───swaglab
    │   │               
    │   └───test
    │       ├───java/com
    │       │       ├───apitest
    │       │       │    ├───runners
    │       │       │    └───utils
    │       │       └───swaglab
    │       │           ├───locators
    │       │           ├───runner
    │       │           ├───stepDefinitions
    │       │           └─── utils
    │       └───resources
    │           ├───com
    │           │    ├───apitest
    │           │    │   └───user-schema.json 
    │           │    └───swaglab
    │           │        └───features
    │           └─── junit-platform.properties (properties file for JUnit5 usage)
    ├───target
    │   ├───cucumber-reports
    │   │   └───Cucumber.html (cucumber report in HTML format)
    │   └───site
    │       └───surefire-report.html (surefire report in HTML format)
    ├───.env
    └───pom.xml

```

**Kembali ke [Daftar Isi](#table-of-contents)**


## Alur Kerja Pembuatan Test Script

### Untuk Pengujian API (Dummy API)
1. Membuat class test baru di dalam folder `test/java/com/apitest/runners` dengan format nama file class `{MethodAPI}Testing.java`.
   ```
   Contoh: 
   - GetTesting.java
   - PostTesting.java
   - PutTesting.java
   - DeleteTesting.java
   ```

2. Import library yang diperlukan untuk menjalankan test script, yang berasal dari dependencies Maven pada file `pom.xml`. Misalkan:
   ```java
   package com.apitest;
   import io.github.cdimascio.dotenv.Dotenv;
   import io.restassured.RestAssured;
   import static io.restassured.RestAssured.given;
   import org.junit.jupiter.api.DisplayName;
   import org.junit.jupiter.api.Test;
   import org.junit.jupiter.api.BeforeEach;
   import org.hamcrest.Matchers;
   import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath; 
   ```

2. Melakukan load dotenv dari file `.env` untuk mendapatkan API key app-id.
   ```java
   Dotenv dotenv = Dotenv.load();
   ```

3. Membuat method `beforeEach()` untuk melakukan setup sebelum menjalankan test script.
   ```java
   @BeforeEach
    public void beforeEach() {
        // reset all request specifications
        RestAssured.reset();

        // set url
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
   }
   ```

4. Membuat method test sesuai nama test case pada dokumen Test Case Design. Pisahkan antar kata pada nama method dengan tanda underscore `_` dengan semua kata ditulis dalam huruf kecil. Berikan juga anotasi `@Test` dan `@DisplayName` pada method tersebut.
   ```java
   @Test  // <-- anotasi
   @DisplayName("Operasi GET menggunakan id user yang valid")
   public void get_valid_user_data() {
        // test script
    }
   ```

5. Isilah body method test tersebut dengan script testing yang sesuai dengan test case yang diinginkan. Struktur pipeline method test adalah sebagai berikut:
   ```java
         // test script
         given()
            .header("app-id", dotenv.get("APP_ID"))
         .when()
            .get("/user/{id}")
         .then()
            .assertThat()
            .statusCode({kode Status Kode yang ingin divalidasi})
            .body(matchesJsonSchemaInClasspath("user-collection.json"));
   ```

   Pada kasus error handling, ganti bagian `.then()` menjadi seperti berikut:
   ```java
         // ... kode sebelumnya
         .then()
            .assertThat()
            .statusCode({kode Status Kode error yang ingin divalidasi})
            .body("error", Matchers.equalTo({string pesan error yang diharapkan}));
   ```

6. Lebih bagus bila diberikan komentar yang memadai di setiap kode. Utamanya, dengan memberikan kode terkait nomor Test Case di setiap header method test untuk memudahkan identifikasi test case yang dijalankan. Misal:
   ```java
   // TC_01  <<--- inilah komentar yang dimaksud
   @Test
   @DisplayName("Operasi GET menggunakan id user yang valid")
   public void get_valid_user_data() {
        // test script
    }
   ```


### Untuk Pengujian Web Otomatis (Swag Labs)
1. Membuat file `.feature` pada folder `src/test/resources/com/swaglab/features` yang berisi skenario pengujian yang akan dijalankan dengan sintaks Gherkin. Pastikan nama file bersesuaian dengan nama fitur yang akan diuji.<br>
   a. Format dasar dalam file feature adalah sebagai berikut:
   ```gherkin
    Feature: <nama fitur yang diuji>
      Rule: <nama rules>
        Scenario: <nama skenario yang diuji>
          Given <langkah awal>
          When <langkah aksi>
          Then <langkah hasil>
    ```
2. Membuat file `Step Definitions` pada folder `src/test/java/com/swaglab/stepDefinitions` yang berisi implementasi dari skenario pengujian yang ada pada file `.feature`. Jumlah file `Step Definitions` harus sama dengan jumlah file `.feature`.<br>
   a. Format dasar dalam file `Step Definitions` adalah sebagai berikut:
   ```java
    package com.swaglab.stepDefinitions;

    public class <nama file> {

      // misal untuk keperluan inisialisasi nama lokator dan driver
      private static <nama kelas yang diimpor> nama-variabel;

      @Before
      public void setUp() {
        if (driver == null) {
          // dapatkan nama driver dari WebDriverSetup

          // instansiasi lokator ke-1
          // initElements dari PageFactory lokator ke-1

          // instansiasi lokator ke-n
          // initElements dari PageFactory lokator ke-n
        }
      }

      @Given("<langkah awal>")
      public void langkah_awal() {
        // implementasi langkah awal
      }

      @When("<langkah aksi>")
      public void langkah_aksi() {
        // implementasi langkah aksi
      }

      @Then("<langkah hasil>")
      public void langkah_hasil() {
        // implementasi langkah hasil
      }

      @AfterAll
      public void tearDown() {
        // tutup driver
      }
    }
    ```
    <br>
    b. Tambahkan komentar yang berarti secara ringkas. Bisa secara inline ataupun block comment (khusus di atas nama kelas). Contoh:<br>
        
        /**
         * Contoh block comment:
         * Komentar ini berisi penjelasan singkat dari kelas ini
         */

        public class LoginSteps {
          // Komentar inline
          private static WebDriver driver;
        }
        
    <br>
3. Membuat file `WebDriverSetup` pada folder `src/test/java/com/swaglab/utils` yang berisi setup WebDriver yang akan digunakan dalam pengujian. Contoh:<br>
      ```java
      package com.swaglab.utils;
  
      public class WebDriverSetup {

          private static WebDriver driver;

          private WebDriverSetup() {
              WebDriverManager.chromedriver().setup();
          }
          
          public static WebDriver getDriver() {
              if (driver == null) {
                  WebDriverSetup.driver = new ChromeDriver();
              }
              
              return driver;
          }
      }
      ```
4. Membuat file `TestRunner` pada folder `src/test/java/com/swaglab/runner` yang berisi konfigurasi untuk menjalankan test case. Contoh paling minimum:
    ```java
      @Suite
      @IncludeEngines("cucumber")
      @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:com/swaglab/features")
      public class TestRunner {
          
      }
    ```
5. Membuat file properties `junit-platform.properties` pada folder `src/test/resources` yang berisi konfigurasi properti Cucumber saat kelas `TestRunner` dijalankan. Format:
    ```properties
    cucumber.<nama-properties-1>=<value-1>
    .
    .
    cucumber.<nama-properties-n>=<value-n>
    ```
6. Membuat file-file locators pada folder `src/test/java/com/swaglab/locators` yang berisi locator dari elemen-elemen yang ada pada aplikasi web yang akan diuji. Di dalamnya, terdapat repositori elemen-elemen web yang akan diuji beserta metode-metode yang digunakan untuk mengakses elemen-elemen tersebut.<br>
    a. Simpan URL statis disini dengan scope public<br>
    b. Simpan nilai-nilai statis dari elemen-elemen web yang akan diuji disini dengan scope public<br>
    c. Simpan web element dengan anotasi `@FindBy` disini, dalam scope protected.<br>
    d. Buat constructor public untuk menginisialisasi web element<br>
    e. Tambahkan metode getter public untuk mengakses web element<br>
    f. Tambahkan metode public lain untuk mengakses elemen-elemen web yang ada pada aplikasi web yang akan diuji. Misal:<br>
    ```java
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void clickBurgerMenu() {
        burgerMenu.click();
    }
    ```

**Kembali ke [Daftar Isi](#table-of-contents)**


## Running Test Script
Terdapat empat pilihan dalam menjalankan test script pada project ini, yaitu:
1. Menjalankan seluruh test case yang ada pada project ini dengan hasil akhir hanya menghasilkan report dari Cucumber tanpa menghasilkan report untuk pengujian API. Untuk proses ini, jalankan perintah berikut:
   ```bash
   mvn test
   ```
2. Menjalankan seluruh test case yang ada pada project ini dengan hasil akhir berupa report dari Surefire yang pelaporannya mencakup hasil pengujian terotomasi Web Swag Labs maupun pengujian API. Untuk proses ini, jalankan perintah berikut:
   ```bash
   mvn surefire-report:report
   ```
3. Menjalankan test case dengan filter nama skenario tertentu (CASE SENSITIVE) dengan hasil akhir berupa report dari Cucumber. Untuk proses ini, jalankan perintah berikut:
   ```bash
   mvn test '-Dcucumber.filter.name="^<nama skenario tertentu>$"'
   ```
4. Menjalankan test case dengan filter nama skenario tertentu (CASE SENSITIVE) dengan hasil akhir berupa report dari Surefire. Untuk proses ini, jalankan perintah berikut:
   ```bash
   mvn surefire-report:report '-Dcucumber.filter.name="^<nama skenario tertentu>$"'
   ```

Rata-rata waktu penyelesaian eksekusi seluruh test case adalah sekitar 1 hingga 3 menit. Waktu eksekusi dapat berbeda-beda tergantung dari spesifikasi perangkat yang digunakan untuk melakukan pengujian.

**Kembali ke [Daftar Isi](#table-of-contents)**


## Test Case Design

### Untuk Pengujian API Dummy API
Test Case dihasilkan mengggunakan metode ECP dan BVA. Berikut adalah daftar test case yang diuji pada proyek ini:

#### Test Case Valid API
<!-- TO DO -->
#### Test Case Invalid API
<!-- TO DO -->

### Untuk Pengujian Aplikasi Web Swag Labs
Terdapat total 9 (sembilan) buah test case. Berikut merupakan desain test case yang digunakan dalam pengujian web secara otomatis:

#### Test Case Valid Web
<!-- TO DO -->
1. **Login Page Validation**<br>
   User can access login page from the website and see the login form.
2. **Successful Login**<br>
   User successfully login to the website using valid credentials (valid username and password).
3. **Successful Logout**<br>
   User successfully logout from the website after login and access logout menu from burger menu.

#### Test Case Invalid Web
<!-- TO DO -->
4. **Failed Login (Invalid Username)**<br>
   User failed to login to the website using invalid username.
5. **Failed Login (Invalid Password)**<br>
   User failed to login to the website using invalid password.
6. **Failed Login (Invalid Username and Password)**<br>
   User failed to login to the website using invalid username and password.
7. **Failed Login (Empty Username)**<br>
   User failed to login to the website using empty username.
8. **Failed Login (Empty Password)**<br>
   User failed to login to the website using empty password.
9. **Failed Login (Empty Username and Password)**<br>
   User failed to login to the website using empty username and password.

**Kembali ke [Daftar Isi](#table-of-contents)**


## Author
* Muhammad Rafi Farhan - NIM. 211524054
* Rachmat Purwa Saputra - NIM. 211524054
* Reihan Hadi Fauzan - NIM. 211524058

Mahasiswa Kelas 3B Program Studi DIV-Teknik Informatika<br>
Jurusan Teknik Komputer dan Informatika<br>
Politeknik Negeri Bandung<br>
(C) Mei 2024


## References
Berikut merupakan daftar resource yang dapat dipelajari lebih lanjut terkait pengujian web secara otomatis:
1. [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/)
2. [Cucumber](https://cucumber.io/docs/guides/10-minute-tutorial/)
3. [Page Object Model & Page Factory](https://www.browserstack.com/guide/page-object-model-in-selenium)
4. [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)

**Kembali ke [Daftar Isi](#table-of-contents)**