# Mise à jour du système
Dans ce document au format.md on va faire l'étude de la mise à jour de notre système. 

## Comment faire pour éviter la coupure de service ?
Nous devrions proposer une installation asynchrone de notre service, ce qui permettrait de continuer notre service sans le couper.

## Que faire des parties en cours ?
Nous devrions laisser la fin des parties restantes étants données qu'ils fonctionnent sur l'ancienne version d'API. Cependant, lors de la relance de notre service nous devons proposer l'utilisation de notre nouveau système.

## Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétro-compatibilité)
Pour la gestion de l'API face à ces possibles évolutions dans le temps nous devrions utiliser la version sémantique (par exemple, 2.0.1 pour indiquer la version principale 2 et le premier patch).

Avec ceci nous pourrons maintenir progressivement de notre api à la place de forcer tout le monde à passer à la nouvelle API en même temps. Le point de terminaison v1 peut rester actif pour les personnes qui ne veulent pas changer, tandis que le v2, avec ses nouvelles fonctionnalités, peut servir ceux qui sont prêts à mettre à niveau. La négociation de contenu permettra à l'API d'être rétro-compatibile

## Comment avertir les joueurs de la nouveauté une unique fois ?
Nous devrions lors du démarrage (appel) de notre service mis à jours faire appel à un message qui s'active automatiquement pour faire transiter les informations concernant les nouveautés aux utilisateurs. L'emploi d'un objet éphémère serait idéal.