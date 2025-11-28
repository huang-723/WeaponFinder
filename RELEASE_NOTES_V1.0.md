# WeaponFinder V1.0 Release Notes
## Basic Information
- Version: V1.0 (Basic Functionality Version)
- Release Date: 2025-11-28
- Team Members & Responsibilities:
    - Member A: Developed `Weapon` data model (model package)
    - Member B: Implemented weapon CRUD operations (store package)
    - Member C: Built console menu interface (ui package)
    - Member D: Added basic weapon search (search package)

## Core Features
1. Add new weapons with basic validation rules:
    - Weapon ID cannot be empty or shorter than 3 characters
    - Weapon type is limited to "Sea", "Land" or "Air"
    - Weapon power value ranges from 1 to 100
    - Weapon name cannot be empty
2. Delete weapons by unique ID (prompt if ID not found)
3. View all stored weapons in formatted console output
4. Basic search functions: Find weapons by ID or type
5. User-friendly console menu interaction (numeric options)

## How to Run
1. Open the project in IntelliJ IDEA
2. Locate the `MenuUI.java` file in the `ui` package
3. Right-click the file → Select "Run 'MenuUI.main()'"
4. Follow the console menu prompts (enter numeric options to operate):
    - Option 1: Add a new weapon
    - Option 2: Display all weapons
    - Option 3: Search for a weapon
    - Option 4: Delete a weapon
    - Option 0: Exit the program

## Known Limitations (Optional for V1.0)
- Only console interface is supported (no GUI like JavaFX/JOptionPane yet)
- Advanced search and sorting functions are not implemented (to be added in V2.0)
