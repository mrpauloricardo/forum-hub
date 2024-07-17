-- INSERT IGNORE INTO roles (role_id, name) VALUES (1, 'admin');
-- INSERT IGNORE INTO roles (role_id, name) VALUES (2, 'basic');

-- Cria a tabela se não existir
CREATE TABLE IF NOT EXISTS roles (
    role_id INT PRIMARY KEY,
    name VARCHAR(255)
);

-- Insere os dados iniciais apenas se a tabela estiver vazia
INSERT INTO roles (role_id, name)
SELECT 1, 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 1);

INSERT INTO roles (role_id, name)
SELECT 2, 'BASIC'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 2);
