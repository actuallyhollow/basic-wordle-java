## Basic "Wordle" Java ##
A simple Java-based implementation of the popular word-guessing game, Wordle. The project offers a simple CLI with no
actual GUI, while focusing heavily on OOP principles to handle game logic and state, and custom Data Structures to
optimize performance.

### 🚧 Status ###
**Active [v1.0.0]** - Launch release delivers decoupled MVC layers, extensive unit tests, and finalized documentation.
<details>
  <summary><i>View Execution Demonstration (click to expand)</i></summary><br>

  ![Refresh page to load asset.][terminal-demo-light]![Refresh page to load asset.][terminal-demo-dark]

</details>

### 🎯 Goals ###
1. **Apply OOP Concepts** - Strict encapsulation, class relationships, and abstraction.
2. **Implement Basic Data Structures** - Custom and specialized Hash Tables to improve performance.
3. **Use MVC Architecture** - Professional structure for large projects.
4. **Write Clean and Documented Code** - Provide detailed and easy-to-read documentation.

### 🎮 Rules ###
**How to Play**
* **Make a Guess** - You have exactly 6 attempts to guess the hidden 5-letter word.
* **Submit Valid Words** - Each guess must be a valid word from the internal dictionary.
* **Receive Visual Clues** - Colored feedback will indicate how close your letters are to the hidden word.

**Feedback Indicators**
* **🟩 Exact Match** - The letter is in the hidden word, and in the correct position.
* **🟨 Partial Match** - The letter is in the hidden word, but in the wrong position.
* **⬛ Incorrect** - The letter does not exist in the hidden word in any position.

**Game End States**
* **Victory** - Correctly guessing the 5-letter word before or on the 6th attempt.
* **Defeat** - Exhausting all 6 attempts without matching the hidden word.

### 🚀 Compiling ###
<details>
  <summary><i>View Compilation Setup (click to expand)</i></summary><br>

  **Setup** - Requires JDK 17 or higher, an IDE (VS Code, IntelliJ, Eclipse).

  ```bash
  # Note for Windows: Swap colons ':' to semicolons ';', and forward slashes '/' to backslashes '\' in any local file path.

  # 1. Clone repository and navigate.
  git clone https://github.com/actuallyhollow/basic-wordle-java.git
  cd basic-wordle-java

  # 2. Compile and run.
  javac -d bin -sourcepath src/main/java src/main/java/com/omaridris/wordle/Main.java
  java -cp bin com.omaridris.wordle.Main

  # 3. (Optional) Generate HTML docs defining the custom test tags.
  javadoc -d docs/src -sourcepath src/main/java:src/test/java -cp "lib/*" -tag scenario:m:"Scenario:" -tag expected:m:"Expected:" -subpackages com.omaridris.wordle
  ```

</details>

### 🌱 Outcomes ###
<details>
  <summary><i>View Project Takeaways (click to expand)</i></summary><br>

  **System Architecture & Design**
  * Implement MVC architecture to isolate business logic, utilizing SSMs to manage state mutability.
  * Apply SWE principles including SOLID, SoC, DRY, DI, YAGNI, and DDD to ensure maintainability.
  * Adopt Effective Java to enforce Immutability, design for Inheritance, and block Reflection.
  * Write self-documenting code adhering to SRP, elevating all literals into class constants.
  * Design custom, highly specialized Data Structures and Algorithms to optimize performance.

  **Core Java & Testing**
  * Handle file I/O operations and external text parsing for datasets via Scanner and Path.
  * Generate Cryptographically Secure Pseudo-Random Numbers, CSPRNGs, through SecureRandom.
  * Control application execution flow and rendering pacing by utilizing Thread delays.
  * Format standard console output into colored UI elements using ANSI escape sequences.
  * Develop extensive JUnit test suites, intercepting and injecting streams with System Lambda.

  **Documentation & Version Control**
  * Author comprehensive Javadocs across all public APIs, integrating custom tags and inline HTML.
  * Model standard-compliant UML diagrams, featuring custom stereotypes and instance multiplicity.
  * Produce visual assets by recording console execution with VHS and writing custom SVG XML.
  * Write a detailed README by formatting Markdown, embedding HTML, and optimizing asset delivery.
  * Maintain standard Git workflows, enforcing strictly formatted, conventional, atomic Commits.

</details>

### 🏛️ Architecture ###
<details>
  <summary><i>View Supplementary Diagrams (click to expand)</i></summary><br>

  **Unified UML Class Diagram** - Visualizes core MVC architecture and class relationships.<br><br>
  ![Refresh page to load asset.][class-uml-light]![Refresh page to load asset.][class-uml-dark]

  **Execution Activity Diagram** - Visualizes application launch sequence and MVC routing.<br><br>
  ![Refresh page to load asset.][app-activity-light]![Refresh page to load asset.][app-activity-dark]

  **Project File Tree Hierarchy** - Visualizes package structure and file organization.<br><br>
  ![Refresh page to load asset.][file-tree-light]![Refresh page to load asset.][file-tree-dark]

</details>

### ✏️ Feedback ###
* **Report an Issue** - To report a bug or request a feature, please open a ticket on the [Issues][issues-url] tracker.
* **Join the Discussion** - To share code or design critiques, please visit the [Discussions][discussions-url] board.
* **Show Your Support** - If you enjoyed exploring the repository or documentation, consider leaving a ⭐️!

### 📖 Credits ###
* **Author** - Omar Idris
* **GitHub** - [@actuallyhollow][author-url]
* **License** - Check the [LICENSE][license-url] file for details.

<!-- URL References -->
[issues-url]: https://github.com/actuallyhollow/basic-wordle-java/issues
[discussions-url]: https://github.com/actuallyhollow/basic-wordle-java/discussions
[author-url]: https://github.com/actuallyhollow
[license-url]: ./LICENSE
<!-- Asset References -->
[terminal-demo-light]: assets/terminal-demo-light.gif#gh-light-mode-only
[terminal-demo-dark]: assets/terminal-demo-dark.gif#gh-dark-mode-only
[class-uml-light]: assets/class-uml-light.drawio.svg#gh-light-mode-only
[class-uml-dark]: assets/class-uml-dark.drawio.svg#gh-dark-mode-only
[app-activity-light]: assets/app-activity-light.drawio.svg#gh-light-mode-only
[app-activity-dark]: assets/app-activity-dark.drawio.svg#gh-dark-mode-only
[file-tree-light]: assets/file-tree-light.drawio.svg#gh-light-mode-only
[file-tree-dark]: assets/file-tree-dark.drawio.svg#gh-dark-mode-only