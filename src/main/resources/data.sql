-- Inserir alguns usuários
INSERT INTO users (first_name, last_name, email, birthday, login, password, phone) VALUES
('John', 'Doe', 'john.doe@example.com', '1990-01-01', 'john.doe', '$2a$10$7Vg7jqF75nGQ2E.eJ5tFiO.2Oos0L/N3R8IFu2FDn1o5J.7MV5i4i', '123456789'), -- senha: password
('Jane', 'Smith', 'jane.smith@example.com', '1985-05-15', 'jane.smith', '$2a$10$7Vg7jqF75nGQ2E.eJ5tFiO.2Oos0L/N3R8IFu2FDn1o5J.7MV5i4i', '987654321'); -- senha: password

-- Inserir alguns carros
INSERT INTO cars (car_year, license_plate, model, color, user_id) VALUES
(2018, 'PDV-0625', 'Audi', 'White', 1),
(2020, 'XYZ-1234', 'BMW', 'Black', 1),
(2019, 'ABC-5678', 'Mercedes', 'Red', 2);