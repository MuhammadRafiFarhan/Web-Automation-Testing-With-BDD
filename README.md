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
1. **Membuat user dengan mengisi firstName, lastName, dan email**<br>
   Method API: `POST /user/create`<br><br>
   User dapat menambah user baru dengan mengisi field firstName, lastName, dan email yang valid sebagai body request.
2. **Operasi penghapusan satu user menggunakan id user yang terdaftar dalam sistem**<br>
   Method API: `DELETE /user/{id}`<br><br>
   User dapat menghapus user yang telah terdaftar dalam sistem dengan menggunakan id user yang valid.
3. **Operasi get data user menggunakan id user yang valid dan terdaftar dalam sistem**<br>
   Method API: `GET /user/{id}`<br><br>
   User dapat mendapatkan data user yang telah terdaftar dalam sistem dengan menggunakan id user yang valid.
4. **Update title user dengan title mr**<br>
   Method API: `PUT /user/{id}`<br><br>
   User dapat mengubah title user dengan title yang terdaftar dalam enum yang diizinkan, salah satunya adalah "mr".
5. **Update title user dengan title kosong**<br>
   Method API: `PUT /user/{id}`<br><br>
   User dapat mengubah title user menjadi title yang kosong.


#### Test Case Invalid API
1. **Membuat user  dengan dengan mengisi firstName, lastName saja**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field firstName dan lastName saja sebagai body request.
2. **Membuat user dengan first name dan email saja**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field firstName dan email saja sebagai body request.
3. **Membuat user dengan last name dan email saja**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field lastName dan email saja sebagai body request.
4. **Membuat user dengan firstName minimum**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field firstName dengan jumlah karakter minimum (2 karakter) sebagai body request.
5. **Membuat user dengan firstName maksimum**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field firstName dengan jumlah karakter maksimum (50 karakter) sebagai body request.
6. **Membuat user dengan lastName minimum**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field lastName dengan jumlah karakter minimum (2 karakter) sebagai body request.
7. **Membuat user dengan lastName maksimum**<br>
   Method API: `POST /user/create`<br><br>
   User mencoba menambah user baru dengan mengisi field lastName dengan jumlah karakter maksimum (50 karakter) sebagai body request.
8. **Operasi penghapusan satu user menggunakan id user yang tidak sesuai format**<br>
   Method API: `DELETE /user/{id}`<br><br>
   User mencoba menghapus user dengan mencoba menggunakan id user yang tidak sesuai format. Seharusnya mengembalikan status kode 400 dengan error PARAMS_NOT_VALID.
9. **Operasi penghapusan satu user dengan tanpa menggunakan header request app-id**<br>
   Method API: `DELETE /user/{id}`<br><br>
   User mencoba menghapus user tanpa menggunakan header request app-id. Seharusnya mengembalikan status kode 403 dengan error APP_ID_MISSING.
10. **Operasi penghapusan satu user dengan menggunakan header request app-id yang tidak sesuai format**<br>
    Method API: `DELETE /user/{id}`<br><br>
    User mencoba menghapus user dengan menggunakan header request app-id yang tidak sesuai format. Seharusnya mengembalikan status kode 403 dengan error APP_ID_NOT_EXIST.
11. **Operasi penghapusan satu user menggunakan id user yang tidak terdaftar di sistem**<br>
    Method API: `DELETE /user/{id}`<br><br>
    User mencoba menghapus user dengan menggunakan id user yang tidak terdaftar di sistem. Seharusnya mengembalikan status kode 404 dengan error RESOURCE_NOT_FOUND.
12. **Operasi get data user menggunakan id user yang tidak sesuai format**<br>
    Method API: `GET /user/{id}`<br><br>
    User mencoba mendapatkan data user dengan menggunakan id user yang tidak sesuai format. Seharusnya mengembalikan status kode 400 dengan error PARAMS_NOT_VALID.
13. **Operasi get data user dengan tanpa menggunakan header request app-id**<br>
    Method API: `GET /user/{id}`<br><br>
    User mencoba mendapatkan data user tanpa menggunakan header request app-id. Seharusnya mengembalikan status kode 403 dengan error APP_ID_MISSING.
14. **Operasi get data user dengan menggunakan header request app-id yang tidak sesuai format**<br>
    Method API: `GET /user/{id}`<br><br>
    User mencoba mendapatkan data user dengan menggunakan header request app-id yang tidak sesuai format. Seharusnya mengembalikan status kode 403 dengan error APP_ID_NOT_EXIST.
15. **Operasi get data user menggunakan id user yang tidak terdaftar dalam sistem**<br>
    Method API: `GET /user/{id}`<br><br>
    User mencoba mendapatkan data user dengan menggunakan id user yang tidak terdaftar dalam sistem. Seharusnya mengembalikan status kode 404 dengan error RESOURCE_NOT_FOUND.
16. **Operasi update tidak menggunakan id user**<br>
    Method API: `PUT /user/` (menghilangkan akhiran user_id pada endpoint)<br><br>
    User mencoba mengubah data user tanpa menggunakan id user pada bagian endpoint. Seharusnya mengembalikan status kode 404 dengan error PATH_NOT_FOUND.
17. **Operasi update menggunakan id user yang tidak valid**<br>
    Method API: `PUT /user/{id}`<br><br>
    User mencoba mengubah data user dengan menggunakan id user yang tidak valid. Seharusnya mengembalikan status kode 400 dengan error PARAMS_NOT_VALID.
18. **Update title user dengan title di luar enum yang diizinkan, yaitu haryapatih**<br>
    Method API: `PUT /user/{id}`<br><br>
    User mencoba mengubah title user dengan title yang tidak terdaftar dalam enum yang diizinkan. Seharusnya mengembalikan status kode 400 dengan error BODY_NOT_VALID.

### Untuk Pengujian Aplikasi Web Swag Labs
Terdapat total 9 (sembilan) buah test case. Berikut merupakan desain test case yang digunakan dalam pengujian web secara otomatis:

#### Test Case Valid Web

1. **User can login to Swag Labs with valid credential -- Successful Login**<br>
   User dapat login ke website Swag Labs dengan menggunakan username dan password yang valid.
2. **Login Functionality Exists**<br>
   User dapat melihat form login pada halaman utama website Swag Labs.
3. **User can view the catalog of items available for order on the Inventory page**
   User dapat melihat katalog item yang tersedia untuk dipesan pada halaman Inventory.
4. **User adds an item to the cart**<br>
   User dapat menambahkan item ke dalam keranjang belanja.
5. **User adds multiple items to the cart**<br>
   User dapat menambahkan beberapa item ke dalam keranjang belanja.
6. **Product detail's page layout verification**<br>
   User dapat melihat layout halaman detail produk.
7. **Adding a product to the cart from the product detail page**<br>
   User dapat menambahkan produk ke dalam keranjang belanja dari halaman detail produk.
8. **Removing a product from the cart from the product detail page**<br>
   User dapat menghapus produk dari keranjang belanja dari halaman detail produk.
9. **User views the list of items in the cart**<br>
   User dapat melihat daftar item yang ada di keranjang belanja.
10. **User clicks "Continue Shopping" button from the cart page**<br>
    User dapat kembali ke halaman Inventory dari halaman keranjang belanja.
11. **Checkout for product that already added in cart until payment successful**<br>
    User dapat melakukan checkout untuk produk yang telah ditambahkan ke keranjang belanja hingga pembayaran berhasil.
12. **User clicks the sidebar button**<br>
    User dapat menekan tombol sidebar.
13. **User clicks "About" on the sidebar menu**<br>
    User dapat menekan menu "About" pada sidebar.
14. **User clicks the cross button on the sidebar**<br>
    User dapat menekan tombol silang pada sidebar.
15. **Successful Logout**<br>
    User dapat logout dari website Swag Labs dengan sukses.


#### Test Case Invalid Web

1. **Login with Empty Username Field Only**<br>
   User mencoba login ke website Swag Labs dengan mengosongkan field username saja.
2. **Login with Empty Password Field Only**<br>
   User mencoba login ke website Swag Labs dengan mengosongkan field password saja.
3. **Login with Empty Username and Password Fields**<br>
   User mencoba login ke website Swag Labs dengan mengosongkan field username dan password.
4. **Entering Incorrect Username Field with Correct Password**<br>
   User mencoba login ke website Swag Labs dengan memasukkan username yang salah dan password yang benar.
5. **Entering Correct Username Field with Incorrect Password**<br>
   User mencoba login ke website Swag Labs dengan memasukkan username yang benar dan password yang salah.
6. **Entering Incorrect Both Username and Password Fields**<br>
   User mencoba login ke website Swag Labs dengan memasukkan username dan password yang salah.
7. **User clicks "Checkout" button with an empty cart**<br>
   User mencoba menekan tombol "Checkout" ketika keranjang belanja kosong.
8. **Checkout attempt by leaving zip/postal code field empty on Checkout: Your Information page**<br>
   User mencoba melakukan checkout dengan mengosongkan field kode pos pada halaman Checkout: Your Information.
9. **Checkout attempt by leaving all fields empty on Checkout: Your Information page**<br>
   User mencoba melakukan checkout dengan mengosongkan semua field pada halaman Checkout: Your Information.

**Kembali ke [Daftar Isi](#table-of-contents)**


## Author
* Muhammad Rafi Farhan - NIM. 211524054
* Rachmat Purwa Saputra - NIM. 211524054
* Reihan Hadi Fauzan - NIM. 211524058

Mahasiswa Kelas 3B Program Studi DIV-Teknik Informatika<br>
Jurusan Teknik Komputer dan Informatika<br>
Politeknik Negeri Bandung<br>
(C) 2 Juni 2024


## References
Berikut merupakan daftar resource yang dapat dipelajari lebih lanjut terkait pengujian web secara otomatis:
1. [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/)
2. [Cucumber](https://cucumber.io/docs/guides/10-minute-tutorial/)
3. [Page Object Model & Page Factory](https://www.browserstack.com/guide/page-object-model-in-selenium)
4. [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)

**Kembali ke [Daftar Isi](#table-of-contents)**