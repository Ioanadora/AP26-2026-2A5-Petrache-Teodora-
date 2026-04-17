


## 1. Structura pe Pachete

### 1.1 Punctul de Intrare: Lab7Application.java

- SpringBootApplication indica framework-ului sa configureze automat infrastructura pe baza bibliotecilor si sa scaneze proiectul pentru a gasi si instantiа componentele programului.
- Apelul SpringApplication.run initializeaza ApplicationContext-ul si activeaza serverul web care va asigura porturile si conexiunea prin retea.

### 1.2 model: Film.java
Acest pachet contine modelarea datelor .

- Entity:Aceasta declara structural clasa Film ca fiind o entitate relationala ce va fi mapata izolat peste un tabel in baza de date.


Identificatorul Unic (id):
- Id: Semnaleaza cu strictete ca atributul id reprezinta cheia primara a entitatii.
- GeneratedValue: Deleaga direct bazei de date logica de auto-incrementare indexata a cheii primare (1, 2, 3...) aplicata la fiecare inserare.

Campurile declarate (title, genre si score) sunt transformate implicit, creandu-se coloane SQL.

---

### 1.3 Accesul la Date (repository): FilmRepository.java

Acest strat elimina necesitatea scrierii SQL manual.

- Extinde JpaRepository<Film, Long>
- Spring Data JPA genereaza automat implementarea in runtime

Metode disponibile:
- findAll()
- findById()
- save()
- deleteById()

---

### 1.4 service: FilmService.java

@Service marcheaza clasa ca bean Spring.

Dependency Injection se face prin constructor.

Metode:
- getAll()
- addFilm(Film film)
- updateFilm(Long id, Film updated)
- updateScore(Long id, double score)
- deleteFilm(Long id)

Daca filmul nu este gasit, se arunca RuntimeException.

---

### 1.5 Controller REST: FilmController.java

@RestController expune REST API.

@RequestMapping("/movies") defineste ruta principala.

Endpoints:
- GetMapping -> returneaza lista filmelor
- PostMapping -> adauga film
- PutMapping("/{id}") -> update complet
- PatchMapping("/{id}/score") -> update partial
- DeleteMapping("/{id}") -> stergere

Raspunsurile sunt in format JSON.

---

### 1.6 Exception Handling: GlobalExceptionHandler.java

@RestControllerAdvice gestioneaza global exceptiile.

@ExceptionHandler prinde erorile.
