import csv
import requests
import mysql.connector

# Verbindung zur MySQL-Datenbank herstellen
cnx = mysql.connector.connect(user='root', password='password',
                              host='localhost',
                              database='co2emissiondata')

# Erstellung der Tabelle "emissions", "users" und "pendingrequests", falls sie nicht existiert
create_table_emissions_query = """
CREATE TABLE IF NOT EXISTS emissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(255),
    emissiondata VARCHAR(255)
)
"""

create_table_users_query = """
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
)
"""

create_table_pendingrequests_query = """
CREATE TABLE IF NOT EXISTS pendingrequests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    country VARCHAR(255),
    emissiondata VARCHAR(255)
)
"""

cursor = cnx.cursor()
cursor.execute(create_table_emissions_query)
cursor.execute(create_table_users_query)
cursor.execute(create_table_pendingrequests_query)

# Users initialisieren

query_1 = "INSERT INTO users (username, password) SELECT 'admin', 'password'"
query_2 = "INSERT INTO users (username, password) SELECT 'scientist', 'Scientist123'"

# Pending requests initialisieren

query_3 = "INSERT INTO pendingrequests (username, country, emissiondata) SELECT 'scientist2', 'France', '25353.22'"
query_4 = "INSERT INTO pendingrequests (username, country, emissiondata) SELECT 'scientist3', 'Germany', '19231.22'"
query_5 = "INSERT INTO pendingrequests (username, country, emissiondata) SELECT 'scientist4', 'China', '112454.22'"

# Daten in die Datenbank einfügen
cursor = cnx.cursor()
cursor.execute(query_1)
cursor.execute(query_2)
cursor.execute(query_3)
cursor.execute(query_4)
cursor.execute(query_5)

# Link zur CSV-Datei
csv_url = 'https://myco2emissionbucket.s3.eu-central-1.amazonaws.com/world-bank-group-data/CO2_emissions/latest/API_EN.ATM.CO2E.KT_DS2_en_csv_v2_5871652.csv'

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
    land = row[0]
    emissionswert = row[64]

    print(land, ": ", emissionswert)

    # SQL-Query zum Einfügen der Daten in die Tabelle
    query = "INSERT INTO emissions (country, emissiondata) VALUES (%s, %s)"

    # Daten in die Datenbank einfügen
    cursor = cnx.cursor()
    cursor.execute(query, (land, emissionswert))

# Verbindung zur Datenbank schließen
cnx.commit()
cnx.close()