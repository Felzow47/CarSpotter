# 🚗 CarSpotter - Android Car Spotting Application

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![License](https://img.shields.io/badge/License-Private-red.svg)]()

Une application Android moderne pour les passionnés d'automobiles permettant de photographier, cataloguer et gérer une collection de voitures avec reconnaissance automatique des plaques d'immatriculation.

## 🎯 Fonctionnalités

### 📸 Capture et Reconnaissance
- **Capture photo** haute qualité avec CameraX
- **Reconnaissance OCR** des plaques d'immatriculation via ML Kit
- **Géolocalisation** automatique des observations
- **Métadonnées** complètes (date, heure, coordonnées GPS)

### 💾 Gestion des Données
- **Base de données locale** Room avec architecture MVVM
- **Synchronisation cloud** Firebase Firestore
- **Stockage photos** Firebase Storage
- **Authentification** Firebase Auth

### 📤 Export et Partage
- **Export PDF** avec mise en page professionnelle
- **Export CSV** pour analyse de données
- **Partage** sur réseaux sociaux
- **Sauvegarde cloud** automatique

### 🎨 Interface Utilisateur
- **Material Design 3** moderne et intuitive
- **Navigation drawer** pour accès rapide
- **Mode sombre** support complet
- **Animations** fluides et responsive

## 🛠️ Technologies Utilisées

### 🏗️ Architecture
```
MVVM (Model-View-ViewModel)
├── UI Layer (Activities, Fragments, ViewModels)
├── Repository Layer (Data abstraction)
└── Data Layer (Room Database, Firebase, APIs)
```

### 📚 Stack Technique
- **Language:** Kotlin 2.0.20
- **Build System:** Gradle 8.6.1 + KSP 2.0.20-1.0.25
- **Dependency Injection:** Hilt 2.51.1
- **Database:** Room 2.6.1 avec KSP
- **UI:** Material Design 3, ViewBinding, DataBinding
- **Camera:** CameraX 1.3.4
- **ML/AI:** ML Kit Text Recognition
- **Backend:** Firebase (Firestore, Storage, Auth)
- **Maps:** Google Maps API
- **Network:** Retrofit 2.9.0 + OkHttp
- **Image Loading:** Glide 4.16.0
- **Export:** iText PDF, OpenCSV

## 🚀 Installation et Configuration

### Prérequis
- Android Studio Hedgehog | 2023.1.1+
- Android SDK 24+ (Android 7.0+)
- Compte Google/Firebase configuré
- Google Maps API Key

### 📦 Installation
```bash
# Cloner le repository
git clone https://github.com/Felzow47/CarSpotter.git
cd CarSpotter

# Synchroniser les dépendances
./gradlew --refresh-dependencies

# Build du projet
./gradlew assembleDebug
```

### ⚙️ Configuration Firebase
1. Créer un projet Firebase sur [console.firebase.google.com](https://console.firebase.google.com)
2. Ajouter votre app Android avec le package `com.felzow47.carspotter`
3. Télécharger `google-services.json` et le placer dans `app/`
4. Activer Firestore, Storage et Authentication dans la console

### 🗺️ Configuration Google Maps
1. Obtenir une API Key sur [Google Cloud Console](https://console.cloud.google.com)
2. Activer Google Maps SDK for Android
3. Ajouter la clé dans `local.properties`:
```properties
MAPS_API_KEY=votre_cle_api_ici
```

## 📱 Utilisation

### 🏠 Écran Principal
- **Dashboard** avec statistiques de collection
- **Accès rapide** aux fonctionnalités principales
- **Recherche** et filtres avancés

### 📸 Ajout de Voiture
1. Appuyer sur le bouton **"+"**
2. **Photographier** la voiture
3. **Scanner** automatiquement la plaque
4. **Compléter** les informations (marque, modèle, couleur)
5. **Sauvegarder** avec géolocalisation

### 🔍 Détails et Gestion
- **Fiche détaillée** de chaque véhicule
- **Galerie photos** avec zoom
- **Historique** des observations
- **Modification** et suppression

## 🏗️ Architecture du Projet

```
app/src/main/java/com/felzow47/carspotter/
├── 📁 data/
│   ├── dao/           # Data Access Objects (Room)
│   ├── database/      # Configuration base de données
│   └── entity/        # Entités de données
├── 📁 di/             # Injection de dépendances (Hilt)
├── 📁 repository/     # Couche repository
├── 📁 ui/
│   ├── activity/      # Activités Android
│   ├── adapter/       # Adaptateurs RecyclerView
│   └── viewmodel/     # ViewModels MVVM
└── 📁 utils/          # Utilitaires et extensions
```

## 🔧 État du Développement

### ✅ Fonctionnalités Implémentées
- [x] Configuration build complète (Gradle + KSP + Hilt)
- [x] Architecture MVVM avec Room et ViewModel
- [x] Injection de dépendances avec Hilt
- [x] Structure de base des activités
- [x] Configuration Firebase
- [x] Layouts Material Design

### 🚧 En Cours de Développement
- [ ] Correction des crashes au démarrage
- [ ] Implémentation capture photo CameraX
- [ ] Intégration ML Kit OCR
- [ ] Interface utilisateur complète
- [ ] Fonctionnalités d'export PDF/CSV

### 🔮 Roadmap Future
- [ ] Mode hors-ligne avancé
- [ ] Reconnaissance automatique marque/modèle
- [ ] Partage social intégré
- [ ] Statistiques et analytics
- [ ] Mode multi-utilisateur

## 📊 Versions et Compatibilité

| Composant | Version | Statut |
|-----------|---------|--------|
| Min SDK | 24 (Android 7.0) | ✅ |
| Target SDK | 35 (Android 15) | ✅ |
| Compile SDK | 35 | ✅ |
| Kotlin | 2.0.20 | ✅ |
| AGP | 8.6.1 | ✅ |
| KSP | 2.0.20-1.0.25 | ✅ |

## 🤝 Contribution

Ce projet est actuellement en développement privé. Les contributions externes ne sont pas acceptées pour le moment.

## 📄 Licence

Ce projet est privé et propriétaire. Tous droits réservés.

## 📞 Contact

- **Développeur:** Felzow47
- **GitHub:** [https://github.com/Felzow47](https://github.com/Felzow47)
- **Projet:** [https://github.com/Felzow47/CarSpotter](https://github.com/Felzow47/CarSpotter)

## 🔄 Historique des Versions

### v0.1.0 (Initial) - 2025-01-03
- ✅ Configuration initiale du projet
- ✅ Architecture MVVM complète
- ✅ Intégration Hilt + Room + KSP
- ✅ Configuration Firebase
- ✅ Build fonctionnel
- ⚠️ Debug des crashes en cours

---

**🚗 Développé avec passion pour la communauté automobile 🚗**
