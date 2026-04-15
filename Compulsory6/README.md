

## 1. Arhitectura si Structura Proiectului

databases: Contine logica pentru conexiunea la baza de date 
DAO: Incapsuleaza operatiunile CRUD (Create, Read, Update, Delete)   

## 2. Pachetul databases

DataBase.java:  
Aceasta clasa gestioneaza conexiunea la baza de date.

URL este setat la "jdbc:sqlite:movies.db", baza de date se numeste movies.db  

getConnection(): verifica daca exista conexiune. Daca nu, o creeaza folosind DriverManager.getConnection()  

closeConnection(): inchide conexiunea si elibereaza resursele  

CreateTables.java:  
Contine metoda create() care genereaza schema bazei de date  

Se foloseste CREATE TABLE IF NOT EXISTS pentru a evita erori la rulare repetata  

Se creeaza tabelele:  
actors  
movies  

Ambele au id INTEGER PRIMARY KEY AUTOINCREMENT  


## 3. Pachetul DAO

Acest pachet separa logica SQL de restul aplicatiei  

ActorDAO.java:  

Contine clasa Actor folosind Lombok pentru getter, setter si constructori  

Metoda create(String name):  
Foloseste PreparedStatement pentru a preveni SQL Injection  
Semnul ? este parametru sigur in query  

Metodele findByName si findById:  
Executa SELECT si returneaza rezultate folosind ResultSet  
Daca nu exista rezultat, returneaza null  

MovieDAO.java:  

Metoda create(String title):  
Foloseste RETURN_GENERATED_KEYS pentru a obtine ID-ul generat automat  

getGeneratedKeys() returneaza ID-ul nou creat  

Metoda addActor(int movieId, int actorId):  
Creeaza legatura intre film si actor in tabelul movie_actors  

## 4. Fluxul de executie in Main.java

1. Se initializeaza conexiunea folosind DataBase.getConnection()  

2. Se sterg datele existente din tabele si se reseteaza indexurile AUTO_INCREMENT  

3. Se adauga date noi:  
Actor: Chuck Norris  
Film: Walker, Texas Ranger  

4. Se obtine ID-ul actorului folosind findByName  

5. Se creeaza legatura intre actor si film folosind addActor  

6. Se afiseaza datele din tabele folosind SELECT si while(rs.next())  

7. Se inchide conexiunea folosind DataBase.closeConnection()  

