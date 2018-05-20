# projekt_eszkozok
# EU4 csataszimulátor

A méltán népszerű és sikeres Europa Universalis 4 egy egyszerűbb csataszimulátora gyors és érdekes harcok kiszámolására és szimulálására, nem csak rajongóknak!

Kövesd a megadott utasításokat és add meg a kért paramétereket a csaták levezetéséhez.

Fejlesztés alatt. More features to come!

## Felhasznált eszközök:
* Javadocs: Automatikusan generált javadocs, minden osztályról és a fontosabb metódusaikról. Elérhető a `doc/index.html` alatt.
* Build tool: Maven
* CI: Travis, ami minden master branch-re történő változtatáskor végez egy `mvn clean verify` parancsot. Ebben a következő eszközök segítségével futnak különböző dolgok egymás után:
  * JUnit: JUnit tesztek, minden megírt függvényre.
  * FindBugs: Maven check fázisában futó bugellenőrzés. -- A doksi írásának idejében a bugokat még csak megtalálja, azok mind kijavítva nincsenek --
* Travis link : https://travis-ci.org/petermate/projekt_eszkozok

## Branchek:
* A `master` branchen a játék egy leegyszerűsített változata található. Ez a konzolos felülettel lefut és leszimulál egy egyszerű csatát két sereg között.
* Az `army` brachen található a játék kiegészített és javított seregszintű logikája, amihez azonban nem készült el a GUI. Jelenlegi állapotában leginkább a tesztek felparaméterezésével lehet futtatni..

## Felhasználói dokumentáció:
1. A repository klónozása
2. A projekt betöltése egy fejlesztői környezetbe.
3. EU4 fő osztály futtatása
4. Konzolos utasítások követése.
