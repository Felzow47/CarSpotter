# 🚗 CarSpotter — Application Android Car Spotting

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Private-red.svg)]()
[![Status](https://img.shields.io/badge/Status-Interface%20Ready-orange.svg)]()

Application Android moderne dédiée aux passionnés d'automobile pour photographier, cataloguer et organiser une collection de voitures qu'on n'as vu avec reconnaissance automatique des plaques d'immatriculation.

---

## 🚦 État du Projet

- **Interface principale** → lance sans crash, navigation basique fonctionnelle
- **Architecture MVVM, Gradle, Hilt, Room** → configurés et opérationnels
- **Layout minimal et Toolbar** → en place

**Tout le backend  (OCR, camera, base de données, export, etc.) reste à implémenter. Certains boutons affichent un message Toast ou sont inactifs.**

---

## 📌 Fonctionnalités Majeures

| Fonctionnalité           | Implémentée ?      | Détail                                      |
|-------------------------|:-----------------:|---------------------------------------------|
| Interface et Navigation  | ✅                | Layout strict minimal avec Toolbar opérationnels, navigation basique fonctionnelle.      |
| CameraX                 | ❌                | Dépendances intégrées, capture photo fonctionnelle mais les photos ne sont pas importées automatiquement dans l’application.     |
| Reconnaissance OCR      | ❌                | ML Kit non implémenté, reconnaissance des plaques d’immatriculation à venir.            |
| Room Database           | ❌                | Architecture configurée, base de données locale créée mais actuellement vide.            |
| Firebase                | ❌                | Configuration Firebase présente (Firestore, Storage, Auth) mais pas encore utilisée.    |
| Export PDF/CSV          | ❌                | Librairies ajoutées (iText, OpenCSV), fonctionnalités d’export à développer.             |
| Géolocalisation/GPS     | ❌                | Fonctionnalité de géolocalisation et intégration Google Maps non encore réalisées.      |
| Material Design 3       | ⚠️                | UI actuelle très basique, refonte complète du design utilizando Material Design 3 envisagée.           |
| Liste voitures          | ❌                | Interface d’affichage des voitures à concevoir, possibilité d’évolution selon priorités.                  |


---

## 🎯 Roadmap de Développement

- **Court terme**
  - Intégration CameraX  
    Mise en place complète de la capture photo avec CameraX.
  - Activation Room Database  
    Finaliser l’implémentation de la base de données locale pour permettre la sauvegarde, la modification et la consultation des voitures observées.
  - Changement dans l'Interface d'ajout manuel  
    Améliorer l’interface utilisateur pour ajouter manuellement des voitures, avec un formulaire ergonomique et validation des données.
  - Finition de la navigation améliorée (drawer)  
    Implémenter une navigation fluide, intuitive avec un drawer permettant d’accéder rapidement aux sections principales.
  - Ajout d'un menu pour écrire manuellement la plaque d'imatriculation.

- **Moyen terme**
  - Reconnaissance OCR des plaques (ML Kit)  
    Intégrer la reconnaissance automatique des plaques d’immatriculation via ML Kit, pour automatiser la saisie des données.
  - Géolocalisation & carte Google Maps  
    Ajouter la géolocalisation automatique des observations et affichage sur une carte intégrée, pour visualiser les emplacements de la ou on a vu les voitures.
  - Refonte UI vers Material Design 3  
    Moderniser l’interface avec Material Design 3 complet, animations, mode sombre, et composants modernes pour une meilleure expérience utilisateur.

- **Long terme**
  - Synchronisation/Firebase cloud  
    Mettre en place la synchronisation des données avec Firebase Firestore pour sauvegarder dans le cloud, accéder aux données sur plusieurs appareils.
  - Export PDF/CSV  
    Développer des fonctionnalités d’export au format PDF et CSV pour permettre l’analyse des données hors application et le partage.

---



---

## 🛠️ Stack Technique

- **Langage** : Kotlin 2.0.20
- **Build** : Gradle 8.6.1 + KSP
- **DI** : Hilt 2.51.1
- **Base locale** : Room 2.6.1
- **UI** : Material Design 3 (partiel), ViewBinding
- **Camera** : CameraX 1.3.4
- **OCR** : ML Kit Text Recognition
- **Backend** : Firebase (Firestore, Storage, Auth)
- **Maps** : Google Maps API
- **Network** : Retrofit 2.9.0
- **Image** : Glide 4.16.0
- **Export** : iText PDF, OpenCSV

---

## ⚡ Installation Rapide

### Prérequis
- Android Studio | 2025.1.3+
- Android SDK 24+ (Android 7.0+)
- Compte Google/Firebase (optionnel)
- Clé Google Maps API (optionnelle)

### Lancement

```bash
# Cloner le repository
git clone https://github.com/Felzow47/CarSpotter.git
cd CarSpotter

# Build du projet (fonctionne !)
./gradlew assembleDebug
```
(c'est bien ça la ligne de code pour le build ?)


### ⚙️ Configuration Firebase (Optionnelle)
Le fichier `google-services.json` est présent mais Firebase n'est pas encore utilisé dans le code.

### 🏠 Écran Principal (FONCTIONNEL)
- **Interface Cards élégante** avec header "🏠 Ma Collection" et compteur dynamique de voitures
- **Menu hamburger** dans la toolbar pour accès aux paramètres et autre options
- **RecyclerView avec cards** - Chaque voiture affichée dans une MaterialCardView avec photo, marque/modèle, icône favori et compteur de photos
- **Menu flottant bouton "+"** - PopupMenu avec choix "📸 Appareil photo" ou "✏️ Entrée manuelle"
- **Observation temps réel** - Interface se met à jour automatiquement quand des voitures sont ajoutées
- **Formulaire d'ajout complet** - AddVoitureActivity avec 19 types de véhicules, validation et sauvegarde Room

## 🔑 Firebase (optionnel)

Le fichier `google-services.json` est présent mais Firebase n'est pas utilisé pour l'instant.

---

## 📑 Licence

Projet privé. Tous droits réservés.

---

**🚗 Développement actif — interface prête, fonctionnalités à venir !**