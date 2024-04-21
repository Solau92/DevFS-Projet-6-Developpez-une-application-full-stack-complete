
INSERT INTO users (user_id, username, email, password, created_at, updated_at) VALUES 
(1, 'Bill Gates', 'user1@email.com', '$2a$10$TieTTep62PPqhhpHJbhEteM69IP3I9UnBT9ctS8t3m91uc9VOYixe', '2024-03-24', '2024-03-24'),
(2, 'Steve Jobs', 'user2@email.com', '$2a$10$ROrdgfBV3HoXcj6ARgm3COoIO.bfrYijTDrGndRwNiCo1TyNPFyIq', '2024-03-24', '2024-03-24'),
(3, 'Ada Lovelace', 'user3@email.com', '$2a$10$EXM23tvhh4K4kfXp2RcRHu5DmftjIpaHw46poMKqLsfdBlqJn5B5K', '2024-03-24', '2024-03-24'),
(4, 'Toto', 'user4@email.com', '$2a$10$Dn7PVW4p7ly6yOpd9kiB7OWaUkM99tRluOfunyxGEMqAlYBKaUM3q', '2024-03-24', '2024-03-24');

INSERT INTO topics (topic_id, name, description) VALUES 
(1, 'Spring Boot', 'Tout ce que vous avez toujours voulu savoir sur Spring Boot (sans jamais oser le demander)'),
(2, 'Java', 'Tout sur Java !'),
(3, 'Angular', 'Angular pour les nuls'),
(4, 'TypeScript', 'Devenez un crack en TypeScript'),
(5, 'HTML / CSS', "L'art de centrer une <div>");

INSERT INTO subscriptions (subscription_id, user_id, topic_id) VALUES 
(1, 1, 1),
(2, 1, 4),
(3, 1, 5),
(4, 2, 1),
(5, 2, 3),
(6, 3, 2),
(7, 3, 5),
(8, 4, 3),
(9, 4, 4),
(10, 4, 5);

INSERT INTO posts (post_id, user_id, topic_id, title, content, created_at, updated_at) VALUES 
(1, 1, 1, "Le printemps avec Spring", "Dans le monde du developpement logiciel, Spring Boot arrive comme un rayon de soleil, simplifiant la vie des developpeurs Java. Finies les configurations fastidieuses, place aux configurations simples et efficaces. Avec Spring Boot, tout le monde peut briller en codant. Alors, en passe de faire eclore vos projets avec Spring Boot ?", '2024-03-24', '2024-03-24'),
(2, 2, 1, "Spring Boot et Hibernate", "L'association entre Spring Boot et Hibernate constitue un duo redoutable dans le monde du developpement d'applications Java. Tandis que Spring Boot simplifie la configuration des applications, Hibernate offre une gestion efficace de la persistance des datas. Ensemble, ils permettent aux developpeurs de concevoir des applications robustes et evolutives avec une productivitée accrue. Cette alliance renforce la position de Spring Boot en tant que meilleur choix pour le developpement d'applications Java modernes.", '2024-03-24', '2024-03-24'),
(3, 4, 2, "Article sur Java", "Super article sur Java", '2024-03-25', '2024-03-25'),
(4, 1, 4, "Article sur TypeScript", "Super article sur TypeScript", '2024-03-26', '2024-03-26'),
(5, 2, 1, "Article sur Spring", "Bon article sur Spring Security", '2024-03-26', '2024-03-26'),
(6, 3, 5, "Avoir la classe avec le CSS", "Bon article humouristique sur le CSS", '2024-03-26', '2024-03-26'),
(7, 4, 1, "Spring Data", "Bon article sur Spring Data", '2024-03-28', '2024-03-28'),
(8, 2, 4, "Article a propos de TypeScript", "Ceci est un super article sur TypeScript", '2024-03-28', '2024-03-28'),
(9, 1, 1, "Article sur Spring", "Ceci est un super article sur Spring", '2024-04-02', '2024-04-02'),
(10, 1, 5, "Article sur HTML / CSS", "Super article sur Angular Material pour faciliter l'usage du HTML et du CSS", '2024-04-03', '2024-04-03')
;

INSERT INTO comments (comment_id, user_id, post_id, content, created_at, updated_at) VALUES 
(1, 2, 1, "Super article, merci Bill !", '2024-03-24', '2024-03-24'),
(2, 4, 2, "Le printemps, saison des pommes :D", '2024-03-24', '2024-03-24'),
(3, 3, 1, 'Je confirme, article vraiment clair !', '2024-03-24', '2024-03-24'),
(4, 1, 2, "Bon article, j'ouvre my windows pour admirer le printemps !", '2024-03-24', '2024-03-24'),
(5, 3, 4, "Cet article est moins bon que les autres... Il manque de profondeur et semble survoler le sujet sans vraiment l'explorer en profondeur...", '2024-03-26', '2024-03-26'),
(6, 2, 2, 'Merci :-)', '2024-03-24', '2024-03-24'),
(7, 2, 5, 'Bon... voire excellent article !', '2024-03-25', '2024-03-25'),
(8, 4, 1, "Cet article est tout simplement brillant ! J'ai tout compris sur un sujet aussi complexe... Merci Mr GATES !", '2024-04-02', '2024-04-02'),
(9, 3, 5, 'Je confirme', '2024-03-28', '2024-03-28'),
(10, 4, 4, "Je ne suis pas satisfait de cet article. Il contient des erreurs factuelles flagrantes et ne semble pas avoir fait l'objet d'une verification avant publication.", '2024-03-27', '2024-03-27')
;







