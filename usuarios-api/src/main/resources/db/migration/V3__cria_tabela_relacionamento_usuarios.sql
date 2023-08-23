CREATE TABLE relacionamento_usuarios (
    usuario_id BIGINT REFERENCES usuarios(id),
    seguindo_id BIGINT REFERENCES usuarios(id),
    PRIMARY KEY (usuario_id, seguindo_id)
);