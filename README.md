# 🍔 Tasty – E-Commerce App (Jetpack Compose)

**Tasty** is a Yemeksepeti clone I wrote for the final project in Kasim Adalan's Android bootcamp. It relies on Django backend and uses a wide range of mobile technologies. 

## Screenshots

| Login Screen | Sign In Screen | Sign Up Screen |
|--------------|----------------|----------------|
| ![Login Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_1.png) | ![Sign In Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_2.png) | ![Sign Up Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_3.png) |

| Home Screen | Food Detail Screen | Cart Screen | Cart Success Screen |
|-----------------|---------------|-------------------|---------------|
| ![Homepage Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_4.png) | ![Filter Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_5.png) | ![Food Detail Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_10.png) | ![Search Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_11.png) |

| Empty Screen | Favourite Screen | Profile Screen | Order Screen |
|-------------|------------------|----------------|--------------|
| ![Cart Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_9.png) | ![Favourite Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_7.png) | ![Profile Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_12.png) | ![Order Screen](https://github.com/bugrahankaramollaoglu/tasty/blob/main/tasty/readme_files/photo_13.png) |


## 📱 Features

- **User Authentication**: Sign up, sign in, and password recovery functionalities with Django Restframework.
- **Homepage**: Displays featured products and categories.
- **Search**: Allows users to search for products.
- **Filters**: Enables filtering products based on various criteria.
- **Product Details**: Provides detailed information about each product.
- **Cart**: Users can add products to their shopping cart.
- **Favorites**: Option to mark products as favorites.
- **Profile**: Users can view and edit their profile information.
- **Order Management**: Users can place and track their orders.

## 🛠️ Technologies Used

- **Frontend**: Jetpack Compose (Android)
- **Backend**: Django REST Framework / RestAPI
- **Database**: SQLite
- **Image Handling**: Coil
- **Architecture**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit to a PHP server with **Moshi** as Json parser.
- **Authentication**: JWT (JSON Web Tokens)
- **Data**: DataStore, SharedPreferences
- **API**: Google Maps API

## 📂 Project Structure

The repository is organized into the following directories:

- `tasty/`: Contains the Android application code.
- `backend_tasty/`: Includes the backend API developed with Django REST Framework.
- `tasty_db/`: Database schema and related files.

## 🧪 Setup & Installation

### Prerequisites

- Android Studio
- Python 3.x
- Django
- SQLite

### Backend Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/bugrahankaramollaoglu/tasty.git
   cd tasty/backend_tasty
   python3 -m venv venv 
   source ./venv/bin/active
   pip install django djangorestframework djangorestframework-simplejwt
   python manage.py runserver


### Project Structure
```bash
lib/

 ├─ api/         # Services (Auth, Basket, Food)

 ├─ data/        # data classes and repositories

 ├─ model/       # models for each data class

 ├─ navigation/   # routing class

 ├─ util/   # custom classes

 ├─ view/   # UI-related screens

 ├─ viewmodel/    # viewmodels (Auth, Food, Favourite, Basket)

 └─ MainActivity

assets/

 ├─ images/

 └─ icons/

 ```
