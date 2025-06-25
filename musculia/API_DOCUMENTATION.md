# API Documentation - Programmes d'entraînement

## Nouveaux endpoints pour les programmes par défaut

### 1. Récupérer tous les programmes par défaut
```
GET /api/programs/default
```
Retourne la liste de tous les programmes par défaut disponibles pour tous les utilisateurs.

### 2. Récupérer les programmes personnalisés d'un utilisateur
```
GET /api/programs/user/{userProfileId}/custom
```
Retourne la liste des programmes créés par l'utilisateur (excluant les programmes par défaut).

### 3. Appliquer un programme par défaut à un utilisateur
```
POST /api/programs/default/{defaultProgramId}/apply/{userProfileId}
```
Copie un programme par défaut et l'associe à l'utilisateur spécifié.

## Endpoints existants modifiés

### 4. Récupérer tous les programmes d'un utilisateur
```
GET /api/programs/user/{userProfileId}
```
Retourne tous les programmes de l'utilisateur (programmes par défaut + programmes personnalisés).

### 5. Créer un programme personnalisé
```
POST /api/programs
```
Crée un nouveau programme personnalisé pour l'utilisateur.

### 6. Modifier un programme
```
PUT /api/programs/{id}
```
Modifie un programme existant (impossible pour les programmes par défaut).

### 7. Supprimer un programme
```
DELETE /api/programs/{id}
```
Supprime un programme (impossible pour les programmes par défaut).

## Logique métier

- **Programmes par défaut** : Accessibles à tous, ne peuvent pas être modifiés ou supprimés
- **Programmes utilisateur** : Créés par l'utilisateur ou copiés depuis un programme par défaut
- Un utilisateur peut avoir plusieurs programmes (par défaut + personnalisés)
- L'application d'un programme par défaut crée une copie associée à l'utilisateur

## Exemples de programmes par défaut

1. **Prise de masse - Débutant** (12 semaines, 3 jours/semaine)
2. **Perte de poids - Débutant** (8 semaines, 4 jours/semaine)
3. **Développement de la force - Intermédiaire** (16 semaines, 4 jours/semaine)
4. **Hypertrophie - Intermédiaire** (12 semaines, 5 jours/semaine)
5. **Powerlifting - Avancé** (20 semaines, 4 jours/semaine)
6. **Bodybuilding - Avancé** (16 semaines, 6 jours/semaine) 