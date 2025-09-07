# Spring Boot Astrapay Base Project
Berikut adalah Base Project untuk aplikasi Spring Boot, yang sesuai dengan konvensi yang digunakan pada Astrapay.

#### Author Information:  
- Nurmaya Harsa Maulani

## Package Structure
```
com
 +- astrapay
     +- [ServiceName]ApplicationBaseExternal.java
     +- [service name]
         +- configuration
         |   +- WebConfig.java
         |
         +- controller
             +- advice
                 +- GlobalExceptionHandlerAdvice.java
             +- NoteController.java
         |
         +- dto
             +- NoteRequestDto.java
             +- NoteResponseDto.java
             +- PagedResponseDto.java
         |
         +- exception
         |
         +- entity
             +- [Note].java
         |
         +- enums
         |
         +- repository
             +- NoteRepository.java
         |
         +- security
         |
         +- service
             +- NoteService.java
         |
         +- validator
```


## Resources Structure
```
resources
 +- application.properties
 +- application-prd.properties
 +- application-sit.properties
 +- application-uat.properties
 +- sql
    +- data.sql
    +- schema.sql
 +- Resource Bundle 'messages'
    +- messages.properties
    +- messages_id.properties
    +- messages_de.properties
```

## Cara Menjalankan Aplikasi

### Backend
1. Buka project di IDE favorit Anda (misal: IntelliJ, Eclipse).
2. Temukan file `AstrapayBaseExternal.java`.
3. Jalankan file tersebut (Run/Debug).

### Frontend
1. Buka terminal di folder frontend project.
2. Jalankan perintah berikut untuk memulai server Angular:
   ```bash
   ng serve



## Screenshot 

1. Hasil eksekusi Validasi yang berhasil dilewati
 a. Title dan content tidak bisa kosong 

  <img width="1202" height="680" alt="image" src="https://github.com/user-attachments/assets/1944867e-d986-46db-985b-cffecf9c7846" />


 b. Muncul pop up jika delete

  <img width="1111" height="957" alt="image" src="https://github.com/user-attachments/assets/334d912d-866d-4e2d-82b5-f14502ebd465" />


 c. Title tidak boleh lebih dari 100 char

   <img width="1072" height="378" alt="image" src="https://github.com/user-attachments/assets/7fb1aa2d-bd8a-4998-8d8b-3b42b1bd030e" />

   proof:

   <img width="1555" height="577" alt="image" src="https://github.com/user-attachments/assets/bc8ed0a7-dd65-4b98-863a-b32308cd8dc5" />

   
2. Tampilan Postman untuk setiap API
  
   a. GET

   <img width="939" height="883" alt="image" src="https://github.com/user-attachments/assets/635a14d2-0773-470d-a789-1c75d36b7e5f" />


  b. POST 

   <img width="983" height="899" alt="image" src="https://github.com/user-attachments/assets/539151ee-9ec5-4fd5-bcc2-8387e98af539" />


  c. DELETE
 
   <img width="1183" height="619" alt="image" src="https://github.com/user-attachments/assets/c3e84a12-00ad-4866-92fc-c45379a53e1c" />


3. Tampilan halaman list kosong
   
   <img width="1092" height="651" alt="image" src="https://github.com/user-attachments/assets/c6f427a0-f866-467d-9fb2-06eee7b6b9a6" />

5. Tampilan halaman yang berisi 6 notes
   
   <img width="1011" height="1475" alt="image" src="https://github.com/user-attachments/assets/e4413d13-b318-4e0c-9550-3f465231a92c" />



