

## 1. Arhitectura Sistemului 

1. `config`: Gestioneaza infrastructura tehnica.
2. `model`: Defineste structurile de date fundamentale ale aplicatiei.
3. `dao`  Incapsuleaza logica si comunicare cu baze de date.
4. `service`: Contine logica si integreaza datele obtinute pentru a indeplini sarcinile.

---

## 2. Analiza Detaliata a Claselor

### 2.1. Pachetul `config` 

Acest modul este responsabil cu medierea comunicarii dintre aplicatia Java si sistemul de gestiune a bazelor de date, in SQLite.

#### Clasa `DataSourceManager`

Responsabilitate: Initializarea si stocarea unui Connection Pool.

Design Pattern implementat: Singleton. Constructorul clasei este declarat private (private DataSourceManager() {}). Acest lucru blocheaza instantierea clasei din exteriorul ei, garantand astfel existenta unei singure instante partajate, accesibila prin metoda statica getDataSource(). Setand in acest fel clasa, impiedicam supraaglomerarea blocarea bazei de date cu cereri multiple de conectare.

Biblioteca HikariCP: Metoda getDataSource() configureaza un obiect de tip HikariConfig, apartinand bibliotecii HikariCP, un sistem performant de tip Connection Pool din Java.

Setarea "jdbc:sqlite:movies.db" indica driverului protocolul si locatia fizica a bazei de date.

Setarea setMaximumPoolSize(10) limiteaza numarul maxim de conexiuni concurente la 10.

### 2.2. Pachetul `model` (Entitati si Transfer de Date)

#### Clasele `Actor`, `Genre` si `Movie`

Aceste trei clase partajeaza aceeasi arhitectura, diferentiindu-se prin starea pe care o modeleaza.

Principiul Incapsularii: Toate atributele clasei (cum ar fi id, name, title, duration, score) sunt declarate cu modificatorul de acces private.

Metode Getters / Setters:

(ex. public String getName()) citesc si returneaza starea privata.

(ex. public void setName(String name)) modifica starea interna in mod controlat.

Constructori Suprascrisi (Overloaded Constructors): Clasele au atat constructori fara parametri, cat si constructori cu parametri.

### 2.3. Pachetul `dao` (Data Access Object)

DAO Pattern izoleaza restul aplicatiei de limbajul SQL.

#### Clasele `ActorDAO` si `GenreDAO`

Metoda create(Entitate): insereaza date folosind PreparedStatement.

PreparedStatement previne SQL Injection folosind parametri ?.

Try-With-Resources: asigura inchiderea automata a conexiunilor si a resurselor.

Metodele findById si findByName: interogheaza baza de date folosind ResultSet si returneaza obiecte.

#### Clasa `MovieDAO`

Metoda findAllFromView(): interogheaza view-ul SQL movie_view si returneaza lista de filme.

### 2.4. Pachetul `service` 

#### Clasa `ReportService`

Metoda generateReport() genereaza un raport HTML.


### 2.5. Clasa `Main`

Reprezinta EntryPoint-ul aplicatiei.

Metoda main:

1. Instantiere obiecte DAO
2. Adaugare date in baza
3. Generare raport
