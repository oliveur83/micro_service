# Projet de Développement de Microservices pour la Gestion d'Équipes de Football

Ce projet met en œuvre un système de gestion d'équipes de football en utilisant des microservices basés sur Spring Boot. Les microservices gèrent différentes fonctionnalités liées au football, notamment la gestion des équipes, des joueurs, des matchs et des statistiques. De plus, le projet inclut des fonctionnalités de tolérance de panne, de monitoring, de documentation Swagger, de load balancing.

## Contenu du Projet

Le projet est divisé en plusieurs parties, chacune représentant un aspect clé du développement des microservices.

1. **TeamService**: Microservice de gestion des équipes de football.
2. **PlayerService**: Microservice de gestion des joueurs de football.
3. **MatchService**: Microservice de gestion des matchs de football.
4. **StatsService**: Microservice de gestion des statistiques des équipes et des joueurs.
5. **Documentation Swagger**: Documentation des points de terminaison pour chaque microservice.
6. **Tolérance de Panne**: Intégration de circuit breakers (par exemple, Hystrix) et gestion de la tolérance de panne.
7. **Monitoring**: Intégration d'Actuator pour surveiller la santé, les métriques et d'autres informations pertinentes.
8. **Load Balancing**: Déploiement en mode Actif/Actif/Actif pour le microservice MatchService.

## Installation et Exécution

1. Clônez ce dépôt Git sur votre machine locale.

2. Installez les dépendances nécessaires en utilisant Maven pour chaque microservice.

3. Configurez Eureka pour permettre l'enregistrement des microservices. Assurez-vous que tous les microservices s'enregistrent correctement.

4. Exécutez les microservices en utilisant Spring Boot.

5. Accédez à la documentation Swagger pour explorer les points de terminaison de chaque microservice.

6. Surveillez l'état du circuit breaker et les métriques via les points de terminaison Actuator.

7. Déployez le microservice MatchService sur Kubernetes en utilisant les manifestes Kubernetes fournis.

8. Documentez votre code en expliquant les choix de conception et les étapes clés de l'implémentation.
## eureka adresse
http://localhost:8761/

## adresse de test des micro service 
http://localhost:8080/playerService/players/
http://localhost:8079/team/teams/
http://localhost:8077/stat/statistiques/
http://localhost:8078/match/matches/

## adress de test pour swagger
http://localhost:8078/match/swagger-ui.html
http://localhost:8077/stat/swagger-ui.html
http://localhost:8080/playerService/swagger-ui.html
http://localhost:8079/team/swagger-ui.html

## Auteur

- Nom: Olivier


