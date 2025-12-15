# WeaponFinder V2.0 Release Notes
## Overview
WeaponFinder V2.0 is an upgraded version of the weapon management system, focusing on **data persistence** while retaining all stable features of V1.1. The core enhancement is TXT file-based data storage, ensuring weapon data is not lost after program exit. All code follows Java basic syntax standards, with clear module division and key English comments for easy understanding and maintenance.

## Key Features
1. **TXT File Persistence** 
    - Auto-save: Weapon data is saved to `weapons.txt` when exiting the program
    - Auto-load: Weapon data is loaded from `weapons.txt` automatically on program startup
    - Data format: Each weapon’s attributes are stored as a comma-separated string for easy parsing
2. **Retained V1.1 Features**
    - Array-based storage with validation for duplicate IDs and attribute ranges
    - JOptionPane popup UI with input type validation
    - Flexible weapon search: Partial ID match, exact type filter 
    - Power sorting: Bubble sort supporting both ascending and descending order

## Technical Highlights
- Uses Java basic IO streams (`BufferedReader`/`BufferedWriter`) for file operations—no complex frameworks, easy to explain
- Implements try-with-resources syntax to auto-close IO streams, avoiding resource leaks
- Reuses `addWeapon()` method during data loading to validate imported data automatically, ensuring data integrity
- Creates a copy of the weapon array during sorting to avoid modifying original storage data
- Input validation for all user operations
## How to Run
1. Prerequisites: IntelliJ IDEA, JDK 8+
2. Import the project: Download the source code and open it in IntelliJ IDEA
3. Run the program: Execute the `main()` method in `ui.MenuUI.java`
4. Data Operation Rules:
    - Add/delete/search/sort weapons via popup menu
    - Exit the program via option 0 or closing the popup—data is saved automatically
    - Re-run the program to load previously saved weapons from `weapons.txt`
    - If `weapons.txt` does not exist, the program starts with an empty storage

## Compatibility
- Fully compatible with V1.1: V1.1 code can be directly upgraded to V2.0 without modification
- Data compatibility: Weapons added in V1.1 can be loaded and saved in V2.0 
- Environment compatibility: Works on Windows/macOS/Linux