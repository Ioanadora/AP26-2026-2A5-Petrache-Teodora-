## 1. Arhitectura si Structura Proiectului

controller: gestioneaza request-urile HTTP si expune API-ul REST  
service: contine logica aplicatiei si proceseaza datele  
repository: comunica direct cu baza de date folosind JPA  
model: defineste structura entitatilor (tabelele din baza de date)  
exception: gestioneaza erorile global  

---

## 2. Pachetul controller

FilmController.java:  
Aceasta clasa expune endpoint-urile REST pentru operatii pe filme  

@RequestMapping("/movies") defineste ruta principala  

Metoda getAll():  
- foloseste @GetMapping  
- returneaza lista tuturor filmelor  
- apeleaza service.getAll()  

Metoda addFilm():  
- foloseste @PostMapping  
- primeste obiect Film din request (@RequestBody)  
- salveaza filmul in baza de date  
- returneaza filmul salvat  

Metoda updateFilm():  
- foloseste @PutMapping("/{id}")  
- primeste id din URL (@PathVariable)  
- actualizeaza toate campurile filmului  
- apeleaza service.updateFilm()  

Metoda updateScore():  
- foloseste @PatchMapping("/{id}/score")  
- actualizeaza doar scorul filmului  
- primeste scorul prin @RequestParam  
- apeleaza service.updateScore()  

Metoda deleteFilm():  
- foloseste @DeleteMapping("/{id}")  
- sterge filmul dupa id  
- apeleaza service.deleteFilm()  

---

## 3. Pachetul service

FilmService.java:  
Contine logica aplicatiei si face legatura intre controller si repository  

Metoda getAll():  
- returneaza toate filmele din baza de date  
- foloseste repository.findAll()  

Metoda addFilm():  
- salveaza un film nou  
- foloseste repository.save()  

Metoda updateFilm():  
- cauta filmul dupa id  
- daca nu exista, arunca RuntimeException  
- actualizeaza title, genre si score  
- salveaza modificarile  

Metoda updateScore():  
- cauta filmul dupa id  
- actualizeaza doar scorul  
- salveaza modificarile  

Metoda deleteFilm():  
- sterge filmul dupa id  
- foloseste repository.deleteById()  

---

## 4. Pachetul repository

FilmRepository.java:  
Extinde JpaRepository  

- ofera automat operatii CRUD  
- nu este nevoie de cod SQL manual  
- metode disponibile: findAll, save, findById, deleteById  

---

## 5. Pachetul model

Film.java:  
Reprezinta tabela "films" din baza de date  

@Entity marcheaza clasa ca entitate JPA  
@Table(name = "movies") specifica numele tabelului  

Atribute:  
- id: cheia primara, generata automat  
- title: titlul filmului  
- genre: genul filmului  
- score: scorul filmului  

@GeneratedValue(strategy = GenerationType.IDENTITY) genereaza automat id-ul  

Contine:  
- constructor fara parametri  
- constructor cu parametri  
- metode getter si setter  

---

## 6. Pachetul exception

GlobalExceptionHandler.java:  

Gestioneaza erorile la nivel global  

@RestControllerAdvice intercepteaza exceptiile din aplicatie  

Metoda handleRuntime():  
- trateaza RuntimeException  
- returneaza mesajul de eroare sub forma de JSON  
- seteaza status HTTP 404 (NOT_FOUND)  

---

## 7. Clasa principala

Lab7Application.java:  

Este punctul de start al aplicatiei Spring Boot  

@SpringBootApplication configureaza automat aplicatia  

Metoda main():  
- porneste aplicatia folosind SpringApplication.run()  
- initializeaza toate componentele (controller, service, repository)  

---

## 8. Fluxul aplicatiei

1. Clientul trimite request HTTP (GET, POST, PUT, PATCH, DELETE)  

2. FilmController preia request-ul  

3. Controller apeleaza metode din FilmService  

4. Service proceseaza logica si apeleaza FilmRepository  

5. Repository interactioneaza cu baza de date  

6. Rezultatul este returnat inapoi catre client in format JSON  

7. Daca apare o eroare, GlobalExceptionHandler returneaza mesajul corespunzator  
