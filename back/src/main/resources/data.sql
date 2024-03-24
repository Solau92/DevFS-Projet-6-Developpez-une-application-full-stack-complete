
INSERT INTO users (user_id, username, email, password, created_at, updated_at) VALUES 
(1, 'user1', 'user1@email.com', 'password', '2024-03-24', '2024-03-24'),
(2, 'user2', 'user2@email.com', 'password', '2024-03-24', '2024-03-24'),
(3, 'user3', 'user3@email.com', 'password', '2024-03-24', '2024-03-24'),
(4, 'user4', 'user4@email.com', 'password', '2024-03-24', '2024-03-24');

INSERT INTO topics VALUES 
(1, 'topic 1'),
(2, 'topic 2'),
(3, 'topic 3'),
(4, 'topic 4'),
(5, 'topic 5');

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

INSERT INTO posts (post_id, user_id, topic_id, content, created_at, updated_at) VALUES 
(1, 1, 1, 'this is the post number 1', '2024-03-24', '2024-03-24'),
(2, 2, 2, 'this is the post number 2', '2024-03-24', '2024-03-24'),
(3, 4, 1, 'this is the post number 3', '2024-03-24', '2024-03-24'),
(4, 1, 4, 'this is the post number 4', '2024-03-24', '2024-03-24'),
(5, 2, 1, 'this is the post number 5', '2024-03-24', '2024-03-24'),
(6, 3, 5, 'this is the post number 6', '2024-03-24', '2024-03-24'),
(7, 4, 1, 'this is the post number 7', '2024-03-24', '2024-03-24'),
(8, 2, 4, 'this is the post number 8', '2024-03-24', '2024-03-24'),
(9, 1, 1, 'this is the post number 9', '2024-03-24', '2024-03-24'),
(10, 1, 5, 'this is the post number 10', '2024-03-24', '2024-03-24')
;

INSERT INTO comments (comment_id, user_id, post_id, content, created_at, updated_at) VALUES 
(1, 1, 1, 'comment 1 about post 1 from user 1', '2024-03-24', '2024-03-24'),
(2, 4, 2, 'comment 1 about post 2 from user 4', '2024-03-24', '2024-03-24'),
(3, 3, 1, 'comment 2 about post 1 from user 3', '2024-03-24', '2024-03-24'),
(4, 1, 2, 'comment 2 about post 2 from user 1', '2024-03-24', '2024-03-24'),
(5, 3, 4, 'comment 1 about post 4 from user 3', '2024-03-24', '2024-03-24'),
(6, 2, 2, 'comment 3 about post 2 from user 2', '2024-03-24', '2024-03-24'),
(7, 2, 5, 'comment 1 about post 5 from user 2', '2024-03-24', '2024-03-24'),
(8, 1, 1, 'comment 3 about post 1 from user 1', '2024-03-24', '2024-03-24'),
(9, 3, 5, 'comment 2 about post 5 from user 3', '2024-03-24', '2024-03-24'),
(10, 4, 4, 'comment 2 about post 4 from user 4', '2024-03-24', '2024-03-24')
;







