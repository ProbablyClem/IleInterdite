*Extrait de la description du projet*

====================
Projet Île Interdite
====================

:**Équipe**: e.k.i.p
:**Membres**: GUITON Clément, DAIKH Nassim, DELHOMME Baptiste, DUPLESSIS Thomas, …
:**Chef de projet**: GUITON Clément


Description du projet
---------------------

L'objectif est de débuter l'informatisation du jeu *l'Île Interdite* en
démontrant vos capacités à concevoir et réaliser une application.

Le `sujet complet`_ du projet et les `règles du jeu`_ sont disponibles sur
Chamilo.


.. _`sujet complet`: https://chamilo.iut2.univ-grenoble-alpes.fr/courses/INFOM2104/document/Projet/m2104_sujet-ile-interdite.pdf
.. _`règles du jeu`: https://chamilo.iut2.univ-grenoble-alpes.fr/courses/INFOM2104/document/Projet/Ile_Interdite-regles.pdf


Organisation du dépôt
---------------------

Le dépôt qui est mis à disposition contient un projet Netbeans
(dossiers ``nbproject/``, ``src/``; fichiers ``build.xml``, ``manifest.mf``)
avec un squelette de code Java pour vous aider à démarrer le projet.


Dans ce dépôt vous trouverez aussi un dossier ``doc/`` qui contiendra la
documentation de votre projet.
Le dossier contiendra en particulier :

- votre dossier de modélisation (fichiers ``.vpp`` et ``.pdf``)
- votre présentation (format ``.pdf``)


Le dépôt contient aussi deux fichiers ``.gitattributes`` et ``.gitignore`` liés
à la configuration de ``git``.


.. code:: console

   .
   ├── doc/
   ├── nbproject/
   ├── src/
   │   ├── m2104/
   │   │   └── ile_interdite/
   │   │       ├── controleur/
   │   │       │   └── Controleur.java
   │   │       ├── modele/
   │   │       │   ├── Aventurier.java
   │   │       │   └── IleInterdite.java
   │   │       ├── util/
   │   │       │   ├── Main.java
   │   │       │   ├── Message.java
   │   │       │   ├── Parameters.java
   │   │       │   └── Utils.java
   │   │       └── vue/
   │   │           ├── IHM.java
   │   │           ├── MessageBox.java
   │   │           ├── VueAventurier.java
   │   │           ├── VueInscriptionJoueurs.java
   │   │           └── VueNiveau.java
   │   └── patterns/
   │       └── observateur/
   │           ├── Observable.java
   │           └── Observateur.java
   ├── .gitattributes
   ├── .gitignore
   ├── build.xml
   ├── manifest.mf
   └── README.rst


Installation
------------

Assurez vous d'avoir installé les logiciels nécessaires pour le projet :

- JDK Java (**≥11**) :

  - paquet Debian/Ubuntu (avec ``apt install``) : ``default-jdk``
  - Windows/macOS : https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

- IDE Netbeans :

  - paquet snap (Ubuntu) : https://snapcraft.io/netbeans
  - autre : https://netbeans.apache.org/download/nb113/nb113.html

- ``git``\  : `https://git-scm.com/book/fr/v2/Démarrage-rapide-Installation-de-Git <https://git-scm.com/book/fr/v2/D%C3%A9marrage-rapide-Installation-de-Git>`__


Les instructions d'`installation de Visual Paradigm`_ sont disponibles sur Chamilo.


.. _`installation de Visual Paradigm`: https://chamilo.iut2.univ-grenoble-alpes.fr/courses/INFOM2104/document/Activation-de-VP-lIUT-et-sur-vos-machines.pdf


Initialisation dans Netbeans
^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Les principales étapes pour démarrer sous Netbeans sont reprises ici.
Référez vous à la |netbeans-git-doc| pour avoir plus de détails.

#. Allez dans le menu ``Team > Git > Clone`` de Netbeans.

#. Renseigner l'URL de votre dépôt, ainsi que vos identifiants de connexion.
   Les informations de connexion à votre dépôt GitLab sont disponibles dans
   l'onglet clone sur la page d'accueil du dépôt.
   **Utilisez l'URL en https://**

#. Vérifiez que Netbeans scannera le dépôt cloné pour trouver les projets
   (case à cocher).

#. Netbeans indique qu'un projet est trouvé : cliquez sur Ouvrir.


Vous trouverez plus d'informations sur l'utilisation de ``git`` avec Netbeans
dans la |netbeans-git-doc|.


.. |netbeans-git-doc| replace:: `documentation git de Netbeans <https://netbeans.org/kb/docs/ide/git.html>`__
