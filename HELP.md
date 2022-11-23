# Fürs Team
Maven
```
mvn clean install               // Projekt durchbauen
mvn clean install -DskipTests   // Projekt ohne Tests durchbauen
```
Docker
```
docker-compose up -d --build                    // Alle Docker Container installieren
docker-compose up                               // Docker Container starten
docker-compose down                             // Docker Container stoppen
docker rm -f $(docker ps -a -q)                 // Docker Container löschen
docker volume rm $(docker volume ls -q)         // Delete all volumes
```
Tests manuell ausführen
```
mvn test                            // Backend
npm test -- --watchAll=false        // Fronend (Jest)
npm run eslint                      // Frontend (ESLint)
npm run cypress                     // Frontend (Cypress)
```

## Team Working Agreements (vorläufig)

- Nur auf grünen Master pushen
- Vor dem Pushen lokal maven durchbauen und alle Tests ausgühren
- TDD (Erst Tests für Feature bauen, dann implementieren)
- Keine unnötigen Tests bauen (z.B. bestehende API testen)

## Clean Code

Clean Code [hier](https://htw-projekt-group-confluence.atlassian.net/wiki/spaces/SD/pages/1507391/Clean+Code)

### Spotless
Wichtig: Damit spottless funktioniert muss man sich mit dem Terminal in dem jeweiligen Modul 
befinden, sonst wird das Plugin nicht gefunden
```
mvn spotless:check      // Formattierung überprüfen
mvn spotless:apply      // Formattierung anpassen
```

### SonarLint
Installieren unter https://plugins.jetbrains.com/plugin/7973-sonarlint

Sonar beim pushen aktivieren (`Commit > Settings > Check: Perform SonarLint Analysis`)

### Prettier
Installationshilfe unter https://www.jetbrains.com/help/idea/prettier.html#prettier_before_you_start

Prettier beim Speichern aktivieren (`Settings > Tools > Actions on Save > Check: Run Prettier`)

### Lombok
Annotationen von Lombok benutzen, anstatt viel redundanten Code zu schreiben. 

Siehe: https://projectlombok.org/features/
