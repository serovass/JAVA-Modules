
INSERT
 INTO    Chat.User (Login, Password)
VALUES  ('MrFox', '12345'),
        ('OneEyedJoe', '4353'),
        ('Margaret', '54535'),
        ('BigBrother', '4353'),
        ('TrashPanda', '7567'),
        ('Grace', 'EWgQW$'),
        ('BlackSheep', '765767'),
        ('Lost', '675575'),
        ('SleepyCat', '08989'),
        ('LonelyGhost', '1231');

INSERT
 INTO    Chat.Chatroom (RoomName, RoomOwner)
VALUES  ('WhiteRoom', 1),
        ('BlackRoom', 2),
        ('PinkRoom', 3),
        ('BlueRoom', 4),
        ('GreenRoom', 5);

INSERT
 INTO    Chat.Message (Author, Room, Message, timestamp)
VALUES  (1, 1, 'I am hungry!', now()),
        (2, 2, 'I am a pirate!', now()),
        (3, 3, 'You are fired.', now()),
        (4, 4, 'Love all of you', now()),
        (5, 5, 'I am from Toronto', now());
