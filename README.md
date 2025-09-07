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