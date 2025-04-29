# ⚽ Mondial Tournament Simulation in Java

<p align='center'>
  <a href="https://github.com/yuvalmar16">Yuval Margolin</a> | 
  <a href="https://github.com/RavidGersh59">Ravid Gersh</a> | 
  <a href="https://github.com/danielmaor0808">Daniel Maor</a>
</p>

---

## 📖 Overview

This project is a comprehensive simulation of an international football tournament, fully implemented in Java.  
It leverages **custom data structures** and **efficient algorithms** to manage a hierarchy of players, teams, scores, and matches.  
The system emphasizes **scalability**, **dynamic updates**, and **fast querying** — enabling detailed tracking of player statistics, team performance, and tournament progression.

---

## 🚀 Key Features

- **Custom Data Structures**:  
  - **TwoThreeTree**: A balanced 2-3 tree implementation for efficient insertion and searching.
  - **Node**: A generic tree node adapted for managing players, teams, and scores.

- **Modular Architecture**:  
  Each aspect of the tournament — players, teams, scores, matches — is handled by a dedicated module, allowing easy maintenance and future expansion.

- **Realistic Tournament Management**:  
  Match scheduling, point allocation, player/team scoring, and tournament ranking are fully automated.

---

## 📁 File Structure

| File | Description |
|:---|:---|
| `Faculty.java` | Defines a team (faculty), including its name, ID, and associated players. |
| `FacultyData.java` | Manages additional data for teams, including performance and statistics. |
| `FacultyScore.java` | Tracks and manages score information for each faculty during the tournament. |
| `Player.java` | Defines a player, including name, position, team affiliation, and statistics. |
| `PlayerData.java` | Stores detailed player attributes for deeper tracking and analysis. |
| `PlayersScore.java` | Handles scoring and performance metrics for individual players. |
| `TechnionTournament.java` | Manages the tournament's full lifecycle: matches, results, rankings, and progress. |
| `Tournament.java` | Defines general tournament settings like scoring rules, scheduling, and qualification systems. |
| `TwoThreeTree.java` | A balanced 2-3 tree data structure used for fast storage and retrieval of players and teams. |
| `Node.java` | A basic node class used by the `TwoThreeTree` to store entries. |
| `Main.java` | Main entry point — initializes data structures, loads tournament data, and runs the simulation. |

---

## 🌳 Custom Data Structures

The simulation centers around a powerful **TwoThreeTree** and **Node** system:

- **Efficient Data Access**:  
  The 2-3 tree structure ensures fast searches and balanced height for scalable tournament data handling.

- **Dynamic Updates**:  
  Teams and players can be dynamically added or updated during the simulation as matches progress.

- **Optimized for Tournament Simulation**:  
  Custom nodes allow flexible organization of player, team, and score data with multi-attribute support.

---

## 🛠️ Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher

### Compilation

```bash
javac *.java
```

### Running the Simulation

```bash
java Main
```

The simulation will automatically initialize the tournament, populate teams and players, and simulate match progression.

---

## ⚙️ Usage and Customization

- **Add Teams and Players**:  
  Modify the initialization section in `Main.java` to add your own teams, players, or configurations.

- **Customize Tournament Rules**:  
  Update `Tournament.java` and `TechnionTournament.java` to adjust point systems, qualification rules, or match schedules.

- **Data Structure Experiments**:  
  You can extend or modify `TwoThreeTree.java` to change tree behavior based on tournament-specific needs.

---

## 🏆 Conclusion

This project demonstrates the power of **custom data structures** and **efficient algorithm design** for managing complex tournament simulations.  
With its modular architecture, dynamic tree-based storage, and extensible codebase, the project serves as an excellent platform for experimenting with hierarchical data organization in sports tournaments.

---
