# Zan CRUD
Esegue operazioni CRUD su una base dati di utenti.

L'applicazione si divide in due progetti fondamentali:

* zan-crud-rest: progetto contenente i servizi REST;
* zan-crud-web: progetto che espone una semplice pagina web che consuma i servizi REST del progetto di cui sopra.

### Prerequisiti

Versione jdk: 1.8+
Database: MySQL Server

### Installazione

Creare una propria istanza MySQL ed eseguire lo script create.sql presente in zan-crud-rest/src/main/resources.

Sul proprio application server creare un datasource avente nome jndi "jdbc/zands".
Nel caso si utilizzi Tomcat è possibile sostituire il file context_tomcat.xml presente in zan-crud-rest/src/main/resources
rinominandolo in "context.xml" e avendo cura di sostituire i parametri di connessione.

Compilare i pom relativi ai due progetti con un semplice

```
mvn clean install
```

per ogni progetto.

## Deploy

L'applicativo è installabile su qualsiasi application server. Il codice è stato testato su Apache Tomcat v.9.0.13.
