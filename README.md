# Notes App 📓

A simple and efficient Notes application built using modern Android development techniques. The app allows users to create, edit, and delete notes, all stored locally using Room database with LiveData. The app is built with clean architecture principles, specifically the MVVM architecture, and follows best practices for handling asynchronous tasks using Kotlin Coroutines.

## Features ✨

- Create, update, and delete notes.
- Persistent data storage using Room Database.
- Seamless navigation between screens using Android's Navigation Component.
- LiveData to observe changes in the data in real-time.
- Asynchronous operations with Kotlin Coroutines for efficient performance.
- Clean architecture following MVVM pattern.

## Tech Stack 🛠

1. **MVVM Architecture**: 
   - Separation of concerns with clear layers (UI, ViewModel, Repository).
   - ViewModel interacts with the Repository to fetch and manipulate data.

2. **Kotlin Coroutines**: 
   - Handles background tasks, such as database operations, to avoid blocking the main thread.
   - Easy and efficient asynchronous operations using structured concurrency.

3. **Navigation Component**: 
   - Simplifies the implementation of navigation between different fragments and activities.
   - Ensures safe and consistent navigation throughout the app.

4. **Room Database with LiveData**: 
   - Provides an abstraction layer over SQLite for local data storage.
   - LiveData ensures that the UI stays up to date with changes in the data automatically.

## Screenshots 📸
![Empty Notes HomeScreen](https://github.com/user-attachments/assets/b54d8c3a-db59-4ec7-b267-70136bc7f20e)
![AddNote Screen](https://github.com/user-attachments/assets/745f45b2-e1d3-465d-ad8f-9107d55f75d4)
![HomeScreen](https://github.com/user-attachments/assets/2e1fe793-ee92-47a3-b2ea-13ed4859acc6)
![UpdateNote](https://github.com/user-attachments/assets/18fc4441-4b31-40c5-8c6f-3f9fd521feb3)
![Delete Note Screen](https://github.com/user-attachments/assets/dfb9fc9f-36cd-4556-a346-5370ed252ec8)


This `README.md` covers the essentials for a GitHub repository and explains your use of MVVM, Coroutines, Navigation, and Room DB with LiveData.
