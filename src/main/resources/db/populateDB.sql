DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories) VALUES
  ('21.03.2016', 'Завтрак', '500'),
  ('21.03.2016', 'Обед', '1500'),
  ('21.03.2016', 'Ужин', '500'),
  ('22.03.2016', 'Завтрак', '500'),
  ('22.03.2016', 'Обед', '100'),
  ('22.03.2016', 'Ужин', '500');
