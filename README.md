<div align="center">

# Snake and Ladders - Heuristic Player (Part B)

**Data Structures course project implementation in Java**

Aristotle University of Thessaloniki (AUTH) - ECE, 2022-23

---

</div>

## Overview

This repository contains an implementation of a board game environment (snakes, ladders, and presents) and two competing players:
- a standard player that moves based on dice rolls
- a heuristic player that evaluates candidate moves and keeps per-round statistics

The project focuses on object-oriented modeling, randomized board generation with constraints, game-loop orchestration, and decision heuristics.

| Component | Purpose | Key Ideas |
|:---------:|---------|-----------|
| **Board generation** | Create a valid game board and place entities | Snake-pattern numbering, constrained random placement, uniqueness checks |
| **Player mechanics** | Simulate moves and interactions | Snake bites, one-use ladders, collectible presents and score updates |
| **Heuristic agent** | Choose the strongest move each round | 1-6 dice evaluation, weighted step/score utility, round-by-round telemetry |

---

## Project Structure

```text
SnakeGame/
|-- src/
|   |-- Game.java
|   |-- Board.java
|   |-- Player.java
|   |-- HeuristicPlayer.java
|   |-- Snake.java
|   |-- Ladder.java
|   `-- Present.java
|
|-- out/
|   `-- production/
|       `-- 10874_PartB/
|           `-- *.class
|
|-- 10874_PartB.iml
`-- README.md
```

---

## Work Implemented

### 1. Board and Entities

- `Board.java`: creates a 10x20 board (configurable), numbers tiles in snake-like traversal, and places snakes/ladders/presents with validity constraints.
- `Snake.java`, `Ladder.java`, `Present.java`: encapsulate entity state and accessors.
- Board visualization support prints three element maps in console output:
	- snake map (`SH` / `ST`)
	- ladder map (`LU` / `LD`)
	- present map (`PR`)

### 2. Core Gameplay Rules

- `Player.java` implements movement logic after a dice roll:
	- snake head -> move to tail
	- ladder bottom -> move to top (ladder becomes broken after first use)
	- present tile -> add points and consume present
- `Game.java` contains the full game flow:
	- player setup
	- turn-order selection
	- round loop with termination rules
	- winner selection based on board progress and score

### 3. Heuristic Opponent

- `HeuristicPlayer.java` extends `Player` and evaluates all dice outcomes from 1 to 6.
- Utility function combines:
	- movement gain (steps)
	- immediate score gain from presents
- Weighting is adaptive:
	- earlier/mid board: prioritize progress
	- late board region: prioritize score more heavily
- The class stores per-round path records and prints summary statistics (snake bites, ladder climbs, present pickups).

---

## How to Run

From the project root:

```bash
javac src/*.java
java -cp src Game
```

Notes:
- The program asks for Player 1 name from stdin.
- Player 2 is the heuristic player.
- Console output includes ANSI color codes; rendering depends on terminal support.

---

## Tech Stack

- Java (object-oriented implementation)
- Standard Java utilities (`java.util`, `java.lang.Math`)

---

<div align="center">
<sub>Legacy AUTH ECE project - preserved and documented for coherent portfolio presentation</sub>
</div>
