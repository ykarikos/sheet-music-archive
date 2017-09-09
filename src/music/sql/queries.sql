-- name: music-by-id
-- Fetch single sheet music by unique id.
SELECT *
FROM music
WHERE id = :id

-- name: music-by-composer
-- Fetch sheet music by exact composer name.
SELECT *
FROM music
WHERE composer = :composer

-- name: search-music-by-composer
-- Search sheet music by case-insensitive search of the composer.
SELECT *
FROM music
WHERE composer ilike :composer

-- name: create-music<!
-- Insert a new sheet music by specifying the composer, title, url and genre.
INSERT INTO music (composer, title, url, genre)
VALUES (:composer, :title, :url, :genre)
