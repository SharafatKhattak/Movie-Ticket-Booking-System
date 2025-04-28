# Movie-Ticket-Booking-System
# Basic Flow
1. Application Start
Display a welcome screen with three options:
  Login as Admin
  Login as User
  Continue as Guest

2. Authentication
  If Admin/User Login selected:
  Prompt for Username and Password.
  Validate credentials against the Admin or Customer database tables.
  If valid ➔ proceed to their respective dashboards.
  If invalid ➔ show error message ("Invalid Username/Password").
  If Guest selected:
          Allow basic exploration (can browse movies and showtimes but cannot book).

3. User Features
  (After successful User login)
  Browse Movies
  View list of available movies, their posters, genres, descriptions.
  View Showtimes
  Select a movie ➔ view all available dates, times, and screens.
  Book Tickets
  Choose movie ➔ select showtime ➔ select number of seats ➔ confirm booking.
  View My Bookings
  See list of previously booked tickets (history).
  Manage Account
  Update personal details (name, email, password).
  Logout
  Exit to main login screen.

4. Guest Features
  (No login required)
  Browse Movies
  View Showtimes
  No Booking Allowed (Button disabled or redirect to login).

5. Admin Features
  (After successful Admin login)
  Dashboard
  Overview (total movies, customers, bookings).
  Add Movie
  Add a new movie (name, poster path, genre, description, etc.).
  Manage Movies
  Update or delete existing movies.
  Manage Showtimes
  Set showtimes for each movie (date, time, screen).
  View Customers
  List of registered customers.
  View Bookings
  See all bookings made by users.
  Manage Admin Account
  Change admin password, update account info.
  Logout
Exit to main login screen.
#Database
MySql is used to store data  with Schemas Given Below:

CREATE TABLE Admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE Movie (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_name VARCHAR(255) NOT NULL,
    poster_path VARCHAR(255),
    release_date DATE,
    duration INT, -- duration in minutes
    genre VARCHAR(100),
    description TEXT
);
CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15),
    password VARCHAR(100) NOT NULL
);

CREATE TABLE Showtime (
    showtime_id INT PRIMARY KEY AUTO_INCREMENT,
    movie_id INT,
    show_date DATE NOT NULL,
    show_time TIME NOT NULL,
    screen_number INT,
    available_seats INT,
    FOREIGN KEY (movie_id) REFERENCES Movie(movie_id)
);
CREATE TABLE Booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    showtime_id INT,
    number_of_tickets INT,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (showtime_id) REFERENCES Showtime(showtime_id)
);
