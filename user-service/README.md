# user-service Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/user-service-${revision}${changelist}-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST resources for Hibernate Reactive with Panache ([guide](https://quarkus.io/guides/rest-data-panache)): Generate JAX-RS resources for your Hibernate Panache entities and repositories
- Reactive PostgreSQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the PostgreSQL database using the reactive pattern
---
> **Inquisitio Imperialis – Mandatum Hereticus**

---

**Datum:** 814.M41  
**Dekretnummer:** 6493-Θ-Epsilon  
**Geheime Ablage:** Ordo Hereticus / Segmentum Obscurus / Rubycon-System  
**Urheber:** Inquisitor Rexan Halbrecht, Mandatum Operantis, Ordo Hereticus

---

**Subjekt der Untersuchung:**  
**Aleksandr von Nachtvalen**  
Registrierter Freihändler, Träger des Freihandelsbriefs FHN-VN817-R, Befehlshaber der *Velum Tenebris*

---

**Anklagepunkte:**

1. **Widerstand gegen imperiale Autorität** – Missachtung direkter Befehle eines autorisierten Inquisitors der Ordo Hereticus.  
2. **Verweigerung der Artefaktübergabe** – Zurückhaltung und Abschottung mehrerer gefährlicher Relikte mit vermuteter Warp-Signatur.  
3. **Verdacht auf Handelsbeziehungen mit Chaos-affinen Entitäten** – Unerlaubte Interaktionen mit Individuen oder Organisationen außerhalb imperialer Legitimation, inkl. möglicher Transaktionen mit ketzerischem Ursprung.

---

**Urteil:**  
Gemäß den Bestimmungen des Codex *Haereticae Pravitatis* und der Konventionen des Calixian Conclave wird das Subjekt **Aleksandr von Nachtvalen** mit dem Status:

> **Hereticus Decretum – Classificatio: Hereticus Extremis**  
> **Excommunicate Traitoris**

belegt.

Der Freihandelsbrief FHN-VN817-R wird hiermit für **ungültig erklärt**. Die *Velum Tenebris* ist ab sofort **imperiale Zielmarkierung**. Alle Ressourcen und Verbündeten unterliegen inquisitorialer Konfiskation.

---

**Direktive an Kill-Team „Nachtvalen“:**  
Im Einklang mit der Vollstreckungsgewalt des Ordo Hereticus wird euch hiermit der Befehl erteilt, das Subjekt **Aleksandr von Nachtvalen** **umgehend zu inhaftieren**. Die Wahrung imperialer Sicherheit erfordert entschlossenes Handeln. Die geistliche Reinigung obliegt den Konventionen des Examinatoriums.

> Missachtung dieses Befehls wird als Heresy Secunda gewertet.

---

**Unterschrift:**  
**Inquisitor Rexan Halbrecht**  
Träger der Flamme des Imperators  
Gezeichnet in Blut und Glauben

**Siegel:** ☩