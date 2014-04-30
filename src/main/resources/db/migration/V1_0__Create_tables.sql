CREATE TABLE author(
    uuid        BINARY(16)   NOT NULL PRIMARY KEY,
    surname     VARCHAR(255) NOT NULL,
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL
);

CREATE TABLE tag(
    uuid        BINARY(16)   NOT NULL PRIMARY KEY,
    tag         VARCHAR(255) NOT NULL,
    slug        VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE post(
    uuid        BINARY(16)   NOT NULL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    slug        VARCHAR(100) NOT NULL UNIQUE,
    content     MEDIUMTEXT   NOT NULL,
    created_at  DATETIME     NOT NULL,
    author      BINARY(16)   NOT NULL,
    CONSTRAINT fk_author
        FOREIGN KEY (author)
        REFERENCES author (uuid)
);

CREATE TABLE post_tag(
    post_uuid   BINARY(16)   NOT NULL,
    tag_uuid    BINARY(16)   NOT NULL,
    PRIMARY KEY(post_uuid, tag_uuid),
    INDEX tag_idx (tag_uuid, post_uuid),
    CONSTRAINT fk_tag
        FOREIGN KEY (tag_uuid)
        REFERENCES tag (uuid),
    CONSTRAINT fk_post
        FOREIGN KEY (post_uuid)
        REFERENCES post (uuid)
);
