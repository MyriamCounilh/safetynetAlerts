# Créez votre première application web avec Spring Boot

### Application SafetyNet Alerts : Le but de cette application est d'envoyer des informations aux systèmes de services d'urgence.

Architecture MVC => Modèle Vue Contrôleur

- M : implémentation des objets métiers qui seront manipulés par les autres couches.
- V :
- C : gestion des interactions entre l’utilisateur de l’application et l’application.

Service : implémentation des traitements métiers spécifiques à l’application.
Repository : interaction avec les sources de données externes.

- Couverture de code avec JaCoco
- Utilisation de surefire report pour mes tests

### Commande
- mvn site = pour lancer mon rapport de test (Jacoco et surefire report)
- mvn spring-boot:run = afin de lancer l'application

