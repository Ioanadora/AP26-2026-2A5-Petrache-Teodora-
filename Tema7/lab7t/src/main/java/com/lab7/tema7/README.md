# Proiect Spring Boot: Gestionarea unui Catalog de Filme

Acest document explica in detaliu un proiect realizat in limbajul Java, folosind framework-ul Spring Boot. Scopul acestui proiect este implementarea unui API REST pentru gestionarea unei baze de date cu filme. Documentatia este scrisa astfel incat sa ajute un student incepator sa inteleaga arhitectura aplicatiei si rolul fiecarei clase in parte.

## Arhitectura Proiectului

Aplicatia respecta o arhitectura pe straturi (layered architecture), care este standard in proiectele Spring Boot. Aceasta separa functionalitatile in pachete distincte pentru a pastra codul organizat, usor de citit si usor de testat. 

Proiectul contine urmatoarele pachete principale:
- model: Contine clasa care defineste structura unui film.
- repository: Contine interfata pentru comunicarea cu baza de date.
- service: Contine logica de business (operatiile care se pot face asupra filmelor).
- controller: Reprezinta punctul de intrare in aplicatie pentru cererile HTTP (web).
- client: Contine un client de test care simuleaza un utilizator ce apeleaza API-ul.
- exception: Gestioneaza erorile la nivel global.

Se folosesc notatiile (adnotarile) specifice Spring care configureaza si conecteaza aceste straturi intre ele in mod automat, prin mecanismul de Dependency Injection (injectare de dependente).

## Detalierea Claselor si a Fisierelor

In continuare, vom lua fiecare fisier din proiect si vom explica, pas cu pas, ce face si de ce exista.

### 1. Lab7tApplication.java
Aceasta este clasa principala a programului. Este punctul de start al aplicatiei.

Explicatie detaliata:
- Contine adnotarea @SpringBootApplication, care ii spune platformei Spring sa configureze totul automat.
- In metoda main, apelam SpringApplication.run, care porneste serverul web intern si initializeaza intregul program.
- Dupa pornirea aplicatiei, clasa preia componenta FilmClient dintr-un context Spring si apeleaza metoda runClient(), pentru a rula testele asupra API-ului.

### 2. model/Film.java
Aceasta clasa este considerata o Entitate de baza de date. Ea reprezinta forma pe care o are un film in memorie si in baza de date.

Explicatie detaliata:
- Adnotarile @Entity si @Table(name = "movies") asigura legatura intre aceasta clasa si o tabela numita "movies" in baza de date.
- Contine campurile specifice: id (care este cheie primara, generata automat gratie lui @Id si @GeneratedValue), title (titlul filmului), genre (genul) si score (nota).
- Contine constructori, adica metode speciale care ne permit sa cream obiecte de tip Film. Avem un constructor gol, necesar pentru Hibernate, si constructori cu parametri.
- Metodele de tip getter si setter sunt folosite pentru a accesa, respectiv a modifica, campurile in mod sigur, respectand principiul incapsularii datelor din programarea orientata pe obiect.

### 3. repository/FilmRepository.java
Acesta este stratul de comunicare directa cu baza de date. Desi nu scriem nicio linie de cod SQL, programul poate efectua operatii asupra bazei de date.

Explicatie detaliata:
- Este o interfata, nu o clasa, si mosteneste (extends) JpaRepository.
- Tipurile transmise sunt Film (tipul de data stocat) si Long (tipul cheii primare id din clasa Film).
- Spring va oferi automat in spate functionalitati esentiale precum: salvarea unui film, stergerea lui, gasirea unui film dupa ID sau intoarcerea listei complete a filmelor din sistem.

### 4. service/FilmService.java
Acesta este stratul de logica. Aici definim ce sa faca aplicatia noastra cand cineva cere o operatie, inainte de a merge efectiv la baza de date.

Explicatie detaliata:
- Este marcat cu @Service pentru a fi detectat de Spring ca o componenta de business.
- Contine actiunile principale pe care vrem sa le facem: getAll (extrage toate filmele), addFilm (salveaza in baza de date un film), updateFilm (actualizeaza un film intreg), updateScore (actualizeaza doar nota unui film) si deleteFilm (sterge un film dupa id).
- Clasa Service preia, prin constructor, componenta FilmRepository. Folosind acest repository, Service-ul comanda bazei de date sa citeasca sau sa modifice inregistrari.
- Tot in Service gestionam cazurile in care un film cautat nu exista: in caz contrar aruncam o exceptie de tip RuntimeException cu mesajul "Film not found".

### 5. controller/FilmController.java
Acesta este punctul de legatura intre exterior si logica noastra. Prinde cererile de la utilizatori (prin internet) si apeleaza logica din FilmService.

Explicatie detaliata:
- Adnotarea @RestController transforma clasa intr-un receptor de trafic capabil sa lucreze cu pachete JSON. Cu @RequestMapping("/movies") declaram ca orice ruta care incepe cu "/movies" va ajunge aici.
- @GetMapping preia cererea si ruleaza metoda de aducere a tuturor filmelor.
- @PostMapping preia din corpul cererii (@RequestBody) detaliile unui nou film si il construieste.
- @PutMapping si @PatchMapping actualizeaza filme. Put asigura schimbarea unui obiect intreg, pe cand Patch asigura schimbarea unui singur parametru (cum ar fi nota). Parametrul variabil din adresa (cum ar fi un numar de ID) este scos folosind @PathVariable.
- @DeleteMapping permite stergerea din baza de date trimitand ID-ul asociat filmului prin adresa.

### 6. client/AppConfig.java
O clasa de configurare destinata pentru partea de client a aplicatiei.

Explicatie detaliata:
- Clasa este adnotata cu @Configuration, fapt ce indica platformei Spring Boot ca de aici vor fi expuse componente refolosibile.
- Contine o metoda numita restTemplate care returneaza un obiect RestTemplate. Acesta este folosit pentru a apela alte servicii web.
- Noi folosim HttpComponentsClientHttpRequestFactory si il oferim platformei cu @Bean pentru a fi folosit mai tarziu. Acest tip de obiect de comunicare sprijina corect folosirea cererilor HTTP de tip PATCH, foarte utile pentru actualizari partiale.

### 7. client/FilmClient.java
Rolul acestui fisier este de a testa aplicatia si de a arata cum interactioneaza un client cu sistemul proaspat creat.

Explicatie detaliata:
- Aceasta metoda mosteneste CommandLineRunner si va folosi comanda run pentru a se executa automat dupa lansarea aplicatiei, doar pentru a testa operatiile dezvoltate de noi.
- Preia adresa de functionare, in cazul nostru localhost, pe portul 8086.
- Simuleaza crearea unui nou film trimitand cererile necesare, stocheaza acel film, ii extrage adresa si face niste teste pentru actiunile de tip citire, modificare parametru si stergere film.
- Functiile apeland componentele expuse de RestTemplate sunt: postForObject (adauga un nou film), getForObject (aduce informatia din format JSON), put, exchange (folositor aici in mod special pentru cerinta PATCH) si delete (stergere finala pe id-ul gasit).
- Toate aceste rezultate le expune automat in consola, validand in fata studentului ca toate instrumentele scrise raspund bine in ansamblu.

### 8. exception/GlobalExceptionHandler.java
Acesta este ultimul nivel si asigura tratarea corecta a erorilor. Pentru un student este extrem de important ca programul sa prinda erorile elegant, fara a afisa mesaje complicate de sistem.

Explicatie detaliata:
- Cu adnotarea @RestControllerAdvice, ea se leaga global de toate erorile venite din program.
- Foloseste @ExceptionHandler pentru prinde orice mesaj de eroare ridicat in timpul functionarii - de exemplu acel mesaj "Film not found" pe care l-am instalat anterior in componenta Service.
- Transforma acel mesaj intr-un raspuns de eroare de tip 404. HttpStatus.NOT_FOUND semnaleaza utilizatorului lipsa datelor, impreuna cu un model simplu JSON formatat special.
- In acest mod codul ramane curat, formal si aplicatia preia probleme in mod previzibil fara intarzieri aditionale.

## Concluzie Finala
Proiectul se asigura ca parcurge tot ce are nevoie un instrument modern de programare: preluare date prin Model si Controller, aplicare logica prin Service si pastrarea informatiilor sigur in Repository. Sper ca aceasta abordare detaliata face fiecare fisier mai usor de inteles pentru orice nivel de experienta.
