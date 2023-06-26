import csv
import requests
import mysql.connector

# Verbindung zur MySQL-Datenbank herstellen
cnx = mysql.connector.connect(user='root', password='password',
                              host='localhost',
                              database='co2emissiondata')

# Erstellung der Tabelle "emissions", falls sie nicht existiert
create_table_emissions_query = """
CREATE TABLE IF NOT EXISTS emissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    land VARCHAR(255),
    emissionswert VARCHAR(255)
)
"""

create_table_users_query = """
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
)
"""

cursor = cnx.cursor()
cursor.execute(create_table_emissions_query)
cursor.execute(create_table_users_query)
cnx.commit()

# Users initialisieren

query = "INSERT INTO users (username, password) VALUES ('admin', 'password')"

# Daten in die Datenbank einfügen
cursor = cnx.cursor()
cursor.execute(query)
cnx.commit()

# Link zur CSV-Datei
csv_url = 'https://myco2emissionbucket.s3.eu-central-1.amazonaws.com/world-bank-group-data/CO2_emissions/latest/API_EN.ATM.CO2E.KT_DS2_en_csv_v2_5551845.csv'

# CSV-Datei herunterladen
response = requests.get(csv_url)
content = response.content.decode('utf-8')

# CSV-Inhalt als Dateiobjekt behandeln
csv_file = csv.reader(content.splitlines())

# Überspringe die ersten fünf Zeilen
next(csv_file)
next(csv_file)
next(csv_file)
next(csv_file)
next(csv_file)

# Schleife über jede Zeile in der CSV-Datei
for row in csv_file:
    land = row[0]  # Annahme: Das Land befindet sich in der ersten Spalte
    emissionswert = row[63]
    
    print(land, ": ", emissionswert)

    # SQL-Query zum Einfügen der Daten in die Tabelle
    query = "INSERT INTO emissions (land, emissionswert) VALUES (%s, %s)"

    # Daten in die Datenbank einfügen
    cursor = cnx.cursor()
    cursor.execute(query, (land, emissionswert))
    cnx.commit()

# Verbindung zur Datenbank schließen
cnx.close()