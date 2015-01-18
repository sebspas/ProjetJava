ProjetJava
==========

Projet de java S3

############################
##### Compiler le code #####
############################
Windows
==========
1)Récupérer le path du dossier bin de Java dans Programmes puis se placer dans le dossier src du projet.

2)Créer un nouveau dossier

3)Dans un terminal, taper les lignes suivantes :
set path=%path%;"path_du_dossier_bin"
javac parking/*.java
java parking/Main

Linux
==========
1)Installer Java Jdk si nécessaire via la commande suivante :
sudo apt-get install openjdk-7-jdk

2)Créer un nouveau dossier

3)Extraire le zip dans le nouveau dossier

4)Dans le terminal se rendre dans le dossier et taper la commande suivante :
javac parking/*.java

5)Lancer le projet avec la commande suivante :
java parking/Main

########################################
##### Utilisation de l'application #####
########################################

- Lors de la création d'un parking vous pouvez le nommer "default" pour
obtenir une base de données contenant déja plusieurs clients et véhicules.

- Vous pouvez à tout moment sauvegarder le parking pour le restaurer plus tard.

- Depuis l'interface d'administration du parking vous pouvez cliquer sur une place 
pour la réserver ou la dé-réserver.
