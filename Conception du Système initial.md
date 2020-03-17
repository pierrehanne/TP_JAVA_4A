# Conception du Système initial

Dans ce document au format .md on va faire la conception du jeu et expliquer les différentes fonctionnalités qui composent notre système.


## Est-ce que les joueurs intéragissent en temps réel, en tour par tour et pourquoi ?

 _Pour notre jeu nous favorisons un mode de jeu dynamique et pour cela, il est cohérent que les joueurs puissent se déplacer et interagir entre eux en temps réel.
 Une des idées pour rendre l’expérience des joueurs plus agréable est d’implémenter un système de cooldown (délai entre les actions). Ce système de cooldown est également efficace pour pouvoir pouvoir alléger la charge de travail du serveur. On s’assure ainsi, une utilisation plus fluide et gérable_

## Comment sera stocké l'état (toutes les données permettant de représenter le jeu ?)

_Pour stocker toutes les données qui permettent de représenter le jeu, il sera utile de sauvegarder les informations dans un format JSON (JavaScript Object Notation). L’avantage de ce format est qu’il est utilisable par de nombreux langage de programmation, facile à lire et à générer c’est une solution qui utilisé dans de nombreuses applications web._


## Comment seront stockées les informations confidentielles des joueurs (emails, etc...) ?

_Les informations sensibles des joueurs comme leurs identifiants, mot de passes, email seront stockées dans une base de donnée hébergée sur **FIREBASE**. L’avantage d’utiliser **FIREBASE** est qu’on délègue et centralise la responsabilité d’authentification, et stockage de nos données._


## Comment gérer plusieurs parties en même temps ? 

Gestionnaire de Json, 1json = 1 parties
Chaque session de jeu génère un fichier Json contenant les informations de la partie, un gestionnaire de Json pourrait permettre de gérer plusieurs parties en même temps. 

## Comment gérer plusieurs parties par joueurs en même temps ? 

Les joueurs n'ont la possibilité que de jouer dans une seule session de jeu.
Il n'est pas nécessaire de pouvoir gérer plusieurs parties par joueurs simultanément.

## Que se passera-t-il si un serveur plante ? (considérer la machine/vm/conteneur éteint(e) et inaccessible)


Pour s'assurer de la disponibilité d'un serveur, un mécanisme de vérification (script de ping) permettra de détecter l'état d'un serveur.
Si aucun retour de ping n'est détecté le serveur pourra être relancé automatiquement.


## Comment les joueurs s'authentifient et sauvegardent leur progression ? 

Authentification par login/mdp, sauvegarde des stats (nombre de parties gagnées...)
Création d'un compte grâce à une adresse mail, couple @mail/mot de passe stocké de manière sécurisée dans une base de données (firebase ou autre).

## Comment gérer une charge imprévue ? ( 100x plus de joueurs que prévu par exemple)

Initialement les parties ne poruront dépasser 50 participants si une partie est remplie alors une deuxième sera crée pour gérer la charge imprévu.

## Comment est géré la fin d'une partie ? 

Le JSON est supprimé et les joueurs sont renvoyés sur l'écran de connexion. 
