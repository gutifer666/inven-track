INSERT INTO user (id, username, password, roles, full_name, sales, earnings)
VALUES (1, 'user', '$2a$10$TW9wmcAMesXeH1naGswppOMcSA70FkdDs3oH9e7I8BPujPwEXwaE6', 'ROLE_USER', 'Usuario de Prueba', 0, 0.0);

INSERT INTO categories (id, name, description)
VALUES
(1, 'General', 'Productos de uso general'),
(2, 'Electrónica', 'Dispositivos y gadgets electrónicos'),
(3, 'Ropa', 'Prendas de vestir y accesorios'),
(4, 'Alimentos', 'Productos alimenticios y bebidas');

INSERT INTO products (id, code, name, description, category_id, cost_price, retail_price, quantity)
VALUES
(1, 'GEN001', 'Bicicleta', 'Bicicleta BH de montaña', 1, 300.00, 500.00, 20),
(2, 'ELEC001', 'Smartphone', 'Teléfono inteligente de última generación', 2, 200.0, 300.0, 10),
(3, 'ROPA001', 'Camiseta', 'Camiseta de algodón 100%', 3, 30.0, 50.0, 50),
(4, 'ALIM001', 'Galletas', 'Galletas de chocolate', 4, 2.0, 3.0, 100);

