
## Arhitectura Proiectului

- model: Contine clasa care defineste structura unui film.
- repository: Contine interfata pentru comunicarea cu baza de date.
- service: Contine operatiile care se pot face asupra filmelor.
- controller: Reprezinta punctul de intrare in aplicatie pentru cererile HTTP (web).
- client: Contine un client de test care simuleaza un utilizator ce apeleaza API-ul.
- exception: Gestioneaza erorile la nivel global.
 
## Detalierea Claselor si a Fisierelor


### 1. Lab7tApplication.java
Aceasta este clasa principala a programului

- Contine adnotarea @SpringBootApplication, care ii spune platformei Spring sa configureze totul automat.
- In metoda main, apelam SpringApplication.run, care porneste serverul web intern si initializeaza intregul program.
- Dupa pornirea aplicatiei, clasa preia componenta FilmClient dintr-un context Spring si apeleaza metoda runClient(), pentru a rula testele asupra API-ului.

### 2. model/Film.java
Aceasta clasa reprezinta forma pe care o are un film in memorie si in baza de date.


- Adnotarile @Entity si @Table(name = "movies") asigura legatura intre aceasta clasa si o tabela numita "movies" in baza de date.
- Contine campurile specifice: id (care este cheie primara, generata automat gratie lui @Id si @GeneratedValue), title (titlul filmului), genre (genul) si score (nota).
- Avem un constructor gol, necesar pentru Hibernate, si constructori cu parametri.
- Metodele de tip getter si setter sunt folosite pentru a accesa, respectiv a modifica, campurile in mod sigur.

### 3. repository/FilmRepository.java
Acesta este stratul de comunicare directa cu baza de date. Desi nu scriem nicio linie de cod SQL, programul poate efectua operatii asupra bazei de date.


- Este o interfata si mosteneste JpaRepository.

### 4. service/FilmService.java
 Aici definim ce sa faca aplicatia cand cineva cere o operatie, inainte de a merge efectiv la baza de date.

- Este marcat cu @Service pentru a fi detectat de Spring ca o componenta de business.
- Contine actiunile principale pe care vrem sa le facem: getAll (extrage toate filmele), addFilm (salveaza in baza de date un film), updateFilm (actualizeaza un film intreg), updateScore (actualizeaza doar nota unui film) si deleteFilm (sterge un film dupa id).
- Clasa Service preia, prin constructor, componenta FilmRepository. Folosind acest repository, Service-ul comanda bazei de date sa citeasca sau sa modifice inregistrari.
- Tot in Service gestionam cazurile in care un film cautat nu exista: in caz contrar aruncam o exceptie de tip RuntimeException cu mesajul "Film not found".

### 5. controller/FilmController.java
Acesta este punctul de legatura intre exterior si logica noastra. Prinde cererile de la utilizatori (prin internet) si apeleaza logica din FilmService.

- Adnotarea @RestController transforma clasa intr-un receptor capabil sa lucreze cu pachete JSON. Cu @RequestMapping("/movies") declaram ca orice ruta care incepe cu "/movies" va ajunge aici.
- @GetMapping preia cererea si ruleaza metoda de aducere a tuturor filmelor.
- @PostMapping preia din corpul cererii (@RequestBody) detaliile unui nou film si il construieste.
- @PutMapping si @PatchMapping actualizeaza filme. Put asigura schimbarea unui obiect intreg, pe cand Patch asigura schimbarea unui singur parametru (cum ar fi nota). Parametrul variabil din adresa (cum ar fi un numar de ID) este scos folosind @PathVariable.
- @DeleteMapping permite stergerea din baza de date trimitand ID-ul asociat filmului prin adresa.

### 6. client/AppConfig.java
O clasa de configurare destinata pentru partea de client a aplicatiei.

- Clasa este adnotata cu @Configuration, fapt ce indica platformei Spring Boot ca de aici vor fi expuse componente refolosibile.
- Contine o metoda numita restTemplate care returneaza un obiect RestTemplate. Acesta este folosit pentru a apela alte servicii web.


### 7. client/FilmClient.java
Rolul acestui fisier este de a testa aplicatia si de a arata cum interactioneaza un client cu sistemul proaspat creat.

- Aceasta metoda mosteneste CommandLineRunner si va folosi comanda run pentru a se executa automat dupa lansarea aplicatiei, doar pentru a testa operatiile dezvoltate de noi.
- Preia adresa de functionare, in cazul nostru localhost, pe portul 8086.
- Simuleaza crearea unui nou film trimitand cererile necesare, stocheaza acel film, ii extrage adresa si face niste teste pentru actiunile de tip citire, modificare parametru si stergere film.
- Functiile apeland componentele expuse de RestTemplate sunt: postForObject (adauga un nou film), getForObject (aduce informatia din format JSON), put, exchange (folositor aici in mod special pentru cerinta PATCH) si delete (stergere finala pe id-ul gasit).
- Toate aceste rezultate le expune automat in consola, validand in fata studentului ca toate instrumentele scrise raspund bine in ansamblu.



