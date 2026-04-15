## Structura si Explicatia Codului

### 1. Pachetul model
Acest pachet contine entitatile de date pe care se bazeaza aplicatia. 

Resource.java: Reprezinta o resursa individuala. Clasa implementeaza interfata Serializable pe care o folosim pentru a putea salva datele intr-un fisier. Contine atributele esentiale: un identificator unic (id), un nume (nameResource), locatia fizica sau adresa URL (locationResource) si o colectie de tip dictionar (Map<String, String> properties) pentru a stoca metadate aditionale (cum ar fi anul, autorul sau formatul).

Catalog.java: Actioneaza ca o colectie centrala de resurse. Elementele sunt stocate intern utilizand structura de date HashMap<String, Resource>, permitand astfel o cautare si o accesare foarte rapida  a unei resurse utilizand direct ID-ul ei. Are, de asemenea, metode ajutatoare tipice de adaugare si regasire.

### 2. Pachetul repository
CatalogRepository.java: Acest pachet are ca responsabilitate implementarea persistentei de durata a catalogului.

Metoda save instantieaza un ObjectOutputStream prin care serializeaza obiectul intreg si il stocheaza direct ca o suma de bytes in partitia locala la calea oferita.

Metoda load este reversul. Se bazeaza pe ObjectInputStream pentru a prelua fisierul, a-l deserializa si a returna complet instanta completa a catalogului de date cu resursele setate anterior.

### 3. Pachetul commands
Aceasta sectiune implementeaza o arhitectura bazata pe Command Pattern. Logica operatiilor pe care utilizatorul le poate executa se gaseste aici.

AddCommand.java: Obiectul retine referinta la Catalog si resursa. Odata executata, apeleaza metoda interna a structurii HashMap pentru a salva obiectul in catalog.

ListCommand.java: Itereaza peste toate elementele catalogului si foloseste forEach(System.out::println) pentru a afisa resursele in consola.

ViewCommand.java: Utilizeaza libraria java.awt.Desktop. Verifica daca locatia este URL (incepe cu http) si deschide in browser. Daca este fisier local, il deschide cu aplicatia implicita a sistemului.

ReportCommand.java: Genereaza rapoarte folosind Freemarker. Datele catalogului sunt injectate intr-un sablon la calea /templates/report.ftl si se genereaza fisierul report.html care este deschis automat in browser.

### 4. Pachetul exception
CatalogException.java: O implementare pentru tratarea erorilor. Mosteneste clasa Exception si permite afisarea unor mesaje clare pentru probleme precum lipsa resurselor sau erori la salvare/incarcare.

### 5. Clasa Main.java


1. Creeaza un catalog
2. Creeaza doua obiecte Resource
3. Adauga resursele folosind AddCommand
4. Afiseaza resursele folosind ListCommand
5. Deschide o resursa folosind ViewCommand
6. Genereaza raport HTML folosind ReportCommand

