CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    birthday DATE,
    login VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS cars (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_year INT,
    license_plate VARCHAR(20) UNIQUE,
    model VARCHAR(50),
    color VARCHAR(20),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
