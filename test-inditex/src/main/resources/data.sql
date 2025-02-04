INSERT INTO brands(id, name, description) VALUES
(1, 'ZARA', 'ZARA'),
(2, 'Massimo Dutti','Massimo Dutti');

INSERT INTO products(id, name, description) VALUES
(1, 'Camisa1', 'Camisa blanca Z&Z'),
(35455, 'Camisa35455','Camisa amarilla xl35');

INSERT INTO prices(id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES
(1, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 4, 35455, 1, 38.95, 'EUR');