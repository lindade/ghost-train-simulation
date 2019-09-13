# Ghost Train Balancing Simulation

Die executable-Dateien liegen im dist-Verzeichnis. (befinden sich nicht auf github)

Zum Aufruf der Ghost Train Balancing Simulation:

java -jar GhostTrain.jar <step in seconds> <pilst>

Beispiel:
java -jar GhostTrain.jar 12 balancing.plist



Die Balancing-plist liegt auf der selben Ebene wie der src-Ordner. (balancing.plist)

Sobald die Simulation beendet ist werden fünf Dateien erstellt.
1. ghosttrain.log.0
2. ghosttrain.log.1
3. level_over_time.csv
4. coins_over_level.csv
5. premiumcurrency_over_level.csv

Die erste und zweite sind Log-Dateien in denen der Levelaufstieg mit Zeitstempel und die Endwerte der Simulation stehen.
Die neusten Werte werden immer in Log0 geschrieben und die Werte der vorherigen Simulation werden aus Log0 in Log1 übernommen.

Dateien 3-5 sind die CSV-Dateien die in Excel importiert werden um Graphen abzubilden. 
