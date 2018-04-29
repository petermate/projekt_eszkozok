# projekt_eszkozok
# EU4 csataszimulátor

A méltán népszerű és sikeres Europa Universalis 4 egy egyszerűbb csataszimulátora gyors és érdekes harcok kiszámolására és szimulálására, nem csak rajongóknak!

Kövesd a megadott utasításokat és add meg a kért paramétereket a csaták levezetéséhez.

Fejlesztés alatt. More features to come!

elte projekt eszközök gitrepo, EU4

## Felhasznált eszközök:
* Build tool: Maven
* CI: Travis, ami minden master branch-re történő változtatáskor végez egy `mvn clean verify` parancsot. Ebben a következő eszközök segítségével futnak különböző dolgok egymás után:
  * JUnit: JUnit tesztek, minden megírt függvényre.
  * FindBugs: Maven check fázisában futó bugellenőrzés. -- A doksi írásának idejében a bugokat még csak megtalálja, azok kijavítva nincsenek --
  * CheckStyle: Statikus java ellenőrzés. -- Szintén csak beépítve van még, kijavítva nincs, a buildet nem töri meg hiba találásakor --
