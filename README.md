# Like Hero To Zero

Digitale Darstellung und Analyse von Umweltdaten

## Voraussetzungen

Bevor du mit der Installation und dem Testen dieses Projekts beginnst, stelle sicher, dass du die folgenden Voraussetzungen erfüllt hast:

- Java Development Kit (JDK) installiert (Version 8 oder höher empfohlen)
- Ein Servlet-Container oder Application-Server wie Apache Tomcat
- MySQL-Datenbankserver
- Python 3 und `pip` (für die Datenbankinitialisierung)

## Datenbank-Initialisierung

Die Initialisierung der Datenbank ist ein kritischer Schritt, um dieses Projekt erfolgreich auszuführen und zu testen. Folge diesen Schritten, um die Datenbank korrekt einzurichten:

1. Stelle sicher, dass der MySQL-Datenbankserver läuft.
2. Erstelle eine neue Datenbank namens `co2emissiondata`.
3. Ändere im beiliegenden Python-Skript `init_database.py` die Platzhalter für Benutzernamen (`root`) und Passwort (`password`) zu deinen MySQL-Zugangsdaten.

### Ausführen des Datenbank-Skripts

Das Python-Skript `init_databse.py` bereitet deine Datenbank vor, indem es die notwendigen Tabellen erstellt und mit Startdaten füllt. Installiere zuerst die erforderlichen Python-Pakete:

```
pip install mysql-connector-python csv requests
```

Führe dann das Skript aus:

```
python init_database.py
```
## Projekt Setup

Nach der Datenbankinitialisierung kannst du dein JSF-Projekt wie folgt einrichten:

1. Klone das Repository oder lade den Projektcode herunter.
2. Importiere das Projekt in deine bevorzugte IDE (z.B. Eclipse, IntelliJ IDEA) als Maven-Projekt.
3. Stelle sicher, dass alle Maven-Abhängigkeiten korrekt geladen werden.
4. Konfiguriere deinen Servlet-Container oder Application-Server, um das Projekt zu deployen.

## Nutzung

Nachdem du das Projekt erfolgreich deployt hast, kannst du auf die Webanwendung über einen Webbrowser zugreifen. Beschreibe spezifische URLs, Benutzeraktionen oder Features, die verfügbar sind, um die Benutzerinteraktion mit deinem Projekt zu verdeutlichen.

## Lizenz

Dieses Projekt steht unter der [MIT Lizenz](LICENSE). Weitere Details und die vollständige Lizenzvereinbarung findest du in der Datei `LICENSE`.

## Kontakt

Für weitere Fragen, Feedback oder Anregungen kontaktiere bitte [den Projektleiter](mailto:labeeb.malik318@gmail.com) direkt via E-Mail.


