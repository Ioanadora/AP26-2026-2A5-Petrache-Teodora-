## 1. Arhitectura si Structura Proiectului

client: trimite request-uri HTTP catre server folosind RestTemplate  
controller: gestioneaza request-urile HTTP si expune API-ul REST  
service: contine logica aplicatiei  
repository: comunica cu baza de date folosind JPA  
model: defineste structura entitatii Film  

---

## 2. Pachetul client

### AppConfig.java  
Configureaza bean-uri pentru aplicatie  

### Metoda restTemplate()  
creeaza un obiect RestTemplate  
foloseste HttpComponentsClientHttpRequestFactory pentru suport complet HTTP (inclusiv PATCH)  

---

### FilmClient.java  
Simuleaza un client care apeleaza API-ul REST  

Implementeaza CommandLineRunner, deci se executa automat la pornirea aplicatiei  

### Variabila url  
contine adresa API-ului (http://localhost:8086/movies)  

### Metoda run()  
apeleaza metoda runClient()  

### Metoda runClient()  

creeaza un film nou  
trimite POST request folosind restTemplate.postForObject()  
afiseaza filmul creat  

extrage id-ul filmului  

trimite GET request pentru toate filmele  
foloseste restTemplate.getForObject()  
afiseaza numarul de filme  

creeaza un film actualizat  
trimite PUT request folosind restTemplate.put()  

creeaza un PATCH request  
foloseste restTemplate.exchange() cu HttpMethod.PATCH  
actualizeaza scorul filmului  
afiseaza rezultatul  

trimite DELETE request folosind restTemplate.delete()  
sterge filmul dupa id  

---

## 3. Pachetul controller

### FilmController.java  
Expune endpoint-urile REST  

@RequestMapping("/movies") defineste ruta principala  

### Metoda getAll()  
foloseste @GetMapping  
returneaza toate filmele  

### Metoda addFilm()  
foloseste @PostMapping  
adauga un film nou  

### Metoda updateFilm()  
foloseste @PutMapping("/{id}")  
actualizeaza toate campurile filmului  

### Metoda updateScore()  
foloseste @PatchMapping("/{id}/score")  
actualizeaza doar scorul  

### Metoda deleteFilm()  
foloseste @DeleteMapping("/{id}")  
sterge filmul  

---

## 4. Pachetul service

### FilmService.java  
Contine logica aplicatiei  

### Metoda getAll()  
returneaza toate filmele din baza de date  

### Metoda addFilm()  
salveaza un film  

### Metoda updateFilm()  
cauta filmul dupa id  
daca nu exista, arunca RuntimeException  
actualizeaza toate campurile  

### Metoda updateScore()  
actualizeaza doar scorul filmului  

### Metoda deleteFilm()  
sterge filmul dupa id  

---

## 5. Pachetul repository

### FilmRepository.java  
Extinde JpaRepository  

ofera automat operatii CRUD  
nu necesita SQL manual  

---

## 6. Pachetul model

### Film.java  
Reprezinta tabela "movies" din baza de date  

@Entity marcheaza clasa ca entitate  
@Table(name = "movies") defineste tabelul  

### Atribute  
id: cheia primara, generata automat  
title: titlul filmului  
genre: genul filmului  
score: scorul filmului  

### Contine  
constructor fara parametri  
constructor cu id  
constructor fara id  
metode getter si setter  

---

## 7. Clasa principala

### Lab7tApplication.java  

Este punctul de start al aplicatiei  

@SpringBootApplication configureaza aplicatia  

### Metoda main()  
porneste aplicatia  
obtine bean-ul FilmClient  
apeleaza manual metoda runClient()  

---

## 8. Fluxul aplicatiei

1. Aplicatia porneste si initializeaza componentele  

2. FilmClient trimite request-uri HTTP catre API  

3. FilmController primeste request-urile  

4. Controller apeleaza FilmService  

5. Service foloseste FilmRepository pentru acces la baza de date  

6. Datele sunt salvate sau returnate  

7. Raspunsul este trimis inapoi catre client  

8. Clientul afiseaza rezultatele in consola  
