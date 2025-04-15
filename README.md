# 🍽️ Recipe App

An elegant and intuitive Android application that allows users to browse and search recipes by categories such as Salad, Main Dish, Drinks, Dessert, and more. Built with Kotlin, Room Database, and modern XML UI components.

## 📱 Features

- 🔍 **Search Recipes**: Real-time text search using `TextWatcher` and dynamic filtering.
- 📂 **Category Browsing**: Filter recipes based on categories like Salad, Drinks, etc.
- 🌐 **Offline Access**: Uses `RoomDB` with a preloaded SQLite asset (`recipe.db`) for offline recipe browsing.
- 🧩 **Clean UI**: Uses `ViewBinding` and XML layouts for a modern, smooth user experience.
- 🔁 **RecyclerView Integration**: Custom Adapters for displaying recipe lists efficiently.
- 📦 **Destructive Migration Support**: Ensures smooth upgrades with fallback strategies.

## 🛠️ Tech Stack

- **Kotlin** - Programming language
- **Room Database** - Local data storage
- **XML** - UI design
- **RecyclerView** - Displaying scrollable lists
- **ViewBinding** - UI interactions
- **Intent Navigation** - Screen transitions with data passing

## 📸 Screenshots

> *Add screenshots here if available*

## 📂 Project Structure

```plaintext
├── HomeActivity.kt
├── SearchActivity.kt
├── CategoryActivity.kt
├── adapters/
│   ├── PopularAdapter.kt
│   ├── CategoryAdapter.kt
│   └── SearchAdapter.kt
├── data/
│   ├── Recipe.kt
│   ├── Dao.kt
│   └── AppDatabase.kt
├── assets/
│   └── recipe.db
├── res/
│   ├── layout/
│   └── drawable/
└── AndroidManifest.xml
