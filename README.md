
# SnapTask - A Task managing App

SnapTask is a personal Task Management App built for Android which allows you to manage your tasks.



## Features
-  Create, Read, Update and Delete tasks.
-  Tasks include Title, subtitle and description fields for clear task definitions.
- ROOM Database is used to store and retrieve tasks.
- Clean Architecture is used for for better code organization and maintainability. 
- Toggle between Light & Dark modes on your preference.
- Color of the task is set based on its completion status :-
    - Not Started: Gray
    - In Progress: Yellow
    - Completed: Green
- Sorting is also provided.
    
## Key Design Decisions
- **Clean Architecture**: This pattern of development is used to ensure a structured development approach is followed by organizing code into three layers (presentation, domain, and data), which enhances maintainability and makes it easier to manage and test.

- **Jetpack Compose** for UI: Compose allows the developers to build UIs by describing how the UI should look based on the current state, simplifying the process of updating and managing the UI, and reducing boilerplate code. Moreover, Compose is gaining popularity in the market due to its efficiency and ease of use.

- **ROOM Database**: ROOM is chosen as the data storage solution since it provides a robust abstraction layer over SQLite, allowing for efficient data storage, retrieval, and management while ensuring that data remains available even after the app is closed. So, the data persistence is ensured.









# Setup Instructions
## Requirements
- Certain features of SnapTask requires Android version **Tiramisu (API Level 33)** or higher. This will be fixed in upcoming updates.

## Instructions
1. **Clone the Repository**:
```
git clone https://github.com/singhDevs/SnapTask.git
cd SnapTask
```
2. **Open the Project**: Open the project in Android Studio
3. **Sync your project** with Gradle files by clicking on `Sync Now` if the syncing doesn't start automatically.
4. **Build the Project**: either by clicking on `Rebuild Project` or by clicking on Play button to start the emulator and build & run the app directly.
5. Run the app either on an `emulator` or on a physical device using `USB Debugging` option available in `Developer Options`.

# Screenshots
1.  Main Screen (Light Mode)
- <img src="https://github.com/user-attachments/assets/56f94f85-c094-46bc-918e-155cb495104f" alt="taskScreenLight" width="300"/>

2. Add/Update Task Screen (Light Mode)
- <img src="https://github.com/user-attachments/assets/b42e850e-be81-4aaf-b4b9-ce15affe02c1" alt="taskScreenLight" width="300"/>

3. DatePicker
- <img src="https://github.com/user-attachments/assets/3386bde3-5cf2-4357-83a5-5c239eb45532" alt="taskScreenLight" width="300"/>

4. Main Screen with Settings (Light Mode)
- <img src="https://github.com/user-attachments/assets/13e0b679-23e9-4751-862b-76d0e95693c9" alt="taskScreenLight" width="300"/>

5. Main Screen with Settings (Dark Mode)
- <img src="https://github.com/user-attachments/assets/2bbfb7d8-a401-4f3f-925f-51f6ef62e862" alt="taskScreenLight" width="300"/>

6. New Task (Dark Mode)
- <img src="https://github.com/user-attachments/assets/d72b8af0-3a15-477a-873d-a69a0bca0230" alt="taskScreenLight" width="300"/>

7. Add/Update Task Screen (Dark Mode)
- <img src="https://github.com/user-attachments/assets/2ed1a621-04ce-4f1a-aee7-1543b3b68fb4" alt="taskScreenLight" width="300"/>

8. Sorted by Status (Descending order)
- <img src="https://github.com/user-attachments/assets/f6b9a0cf-86da-43fb-b608-11289fcdfa58" alt="taskScreenLight" width="300"/>

9. Sorted by Title (Ascending order)
- <img src="https://github.com/user-attachments/assets/8d482f5b-6862-431c-916f-1dc5aab9badd" alt="taskScreenLight" width="300"/>
