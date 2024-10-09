
# SnapTask - A Task managing App

SnapTask is a personal Task Management App built for Android which allows you to manage your tasks.



## Features
-  Create, Read, Update and Delete tasks.
-  Tasks include Title, subtitle and description fields for clear task definitions.
- ROOM Database is used to store and retrieve tasks.
- Clean Architecture is used for for better code organization and maintainability. 
- Toggle between Light & Dark modes on your preference.
## Key Design Decisions
- **Clean Architecture**: This pattern of development is used to ensure a structured development approach is followed by organizing code into three layers (presentation, domain, and data), which enhances maintainability and makes it easier to manage and test.

- **Jetpack Compose** for UI: Compose allows the developers to build UIs by describing how the UI should look based on the current state, simplifying the process of updating and managing the UI, and reducing boilerplate code. Moreover, Compose is gaining popularity in the market due to its efficiency and ease of use.

- **ROOM Database**: ROOM is chosen as the data storage solution since it provides a robust abstraction layer over SQLite, allowing for efficient data storage, retrieval, and management while ensuring that data remains available even after the app is closed. So, the data persistence is ensured.
