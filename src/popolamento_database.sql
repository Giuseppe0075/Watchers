-- Inserimento di dati nella tabella Brand
INSERT INTO admin (email, psw) VALUES
('admin1@root.com', 'root');
-- Inserimento di dati nella tabella Brand
INSERT INTO Brand (business_name, name, description) VALUES
('brand1', 'Brand 1', 'Description of Brand 1'),
('brand2', 'Brand 2', 'Description of Brand 2'),
('brand3', 'Brand 3', 'Description of Brand 3');

-- Inserimento di dati nella tabella User
INSERT INTO User (email, psw, name, surname, birthday, road, civic_number, city, CAP) VALUES
('user1@example.com', 'password1', 'John', 'Doe', '1990-01-01', 'Street 1', '123', 'City 1', '12345'),
('user2@example.com', 'password2', 'Jane', 'Smith', '1985-05-10', 'Street 2', '456', 'City 2', '54321');

-- Inserimento di dati nella tabella Watch
INSERT INTO Watch (name, brand, description, reviews_avg, price, material, stock, dimension, IVA, sex, visible) VALUES
('Watch 1', 'brand1', 'Description of Watch 1', 4.5, 100.00, 'Material 1', 10, 40.0, 22, 'MAN', 1),
('Watch 2', 'brand2', 'Description of Watch 2', 3.8, 120.00, 'Material 2', 15, 38.5, 22, 'WOMEN', 1),
('Watch 3', 'brand3', 'Description of Watch 3', 4.2, 150.00, 'Material 3', 20, 42.0, 22, 'UNISEX', 1);

-- Inserimento di dati nella tabella Purchase
INSERT INTO Purchase (id_order, user, watch, quantity, IVA, price) VALUES
(1, 1, 1, 1, 22, 100.00),
(2, 1, 2, 2, 22, 240.00),
(3, 2, 3, 1, 22, 150.00);

-- Inserimento di dati nella tabella Favourite
INSERT INTO Favourite (watch, user) VALUES
(1, 1),
(3, 1),
(2, 2);

-- Inserimento di dati nella tabella Cart
INSERT INTO Cart (user, watch, quantity) VALUES
(1, 1, 1),
(1, 3, 2),
(2, 2, 1);

-- Inserimento di dati nella tabella Operation
INSERT INTO Operation (admin, watch, operation, date) VALUES
(1, 1, 'Operation 1', '2024-04-18'),
(1, 2, 'Operation 2', '2024-04-19'),
(1, 3, 'Operation 3', '2024-04-20');

-- Inserimento di dati nella tabella Review
INSERT INTO Review (watch, user, stars, description, date) VALUES
(1, 1, 5, 'Great watch!', '2024-04-18'),
(2, 1, 4, 'Nice design.', '2024-04-19'),
(3, 2, 4, 'Good quality.', '2024-04-20');

-- Inserimento di dati nella tabella Image (esempio di immagini codificate in base64)
INSERT INTO Image (id, watch, image) VALUES
(1, 1, 'base64_encoded_image_data_1'),
(2, 2, 'base64_encoded_image_data_2'),
(3, 3, 'base64_encoded_image_data_3');
