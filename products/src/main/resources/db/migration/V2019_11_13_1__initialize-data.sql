DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
  id binary PRIMARY KEY,
  description VARCHAR(250) ,
  name VARCHAR(250),
  price DECIMAL(19, 2)
);

INSERT INTO Product (id, description, name, price) VALUES
  (random_uuid(), 'Bilgisayarın Hası', 'Macbook Pro',200000.22),
  (random_uuid(), 'Elektroniklerin üzerine koymanız için geliştirildi!', 'Dantel', 19.99),
  (random_uuid(), 'Ayağınızı Yerden Keser', 'Şahin SLX', 5000.00);