-- ============================================================
-- SmartEng Database - Seed Data (10 records per table)
-- ============================================================

USE SmartEngDB;
GO

SET IDENTITY_INSERT tblLanguage ON;
INSERT INTO tblLanguage (ID, code, name, iconURL) VALUES
(1,  'en', 'English',    'https://cdn.smarteng.io/flags/en.png'),
(2,  'ja', 'Japanese',   'https://cdn.smarteng.io/flags/ja.png'),
(3,  'ko', 'Korean',     'https://cdn.smarteng.io/flags/ko.png'),
(4,  'fr', 'French',     'https://cdn.smarteng.io/flags/fr.png'),
(5,  'de', 'German',     'https://cdn.smarteng.io/flags/de.png'),
(6,  'zh', 'Chinese',    'https://cdn.smarteng.io/flags/zh.png'),
(7,  'es', 'Spanish',    'https://cdn.smarteng.io/flags/es.png'),
(8,  'it', 'Italian',    'https://cdn.smarteng.io/flags/it.png'),
(9,  'pt', 'Portuguese', 'https://cdn.smarteng.io/flags/pt.png'),
(10, 'ru', 'Russian',    'https://cdn.smarteng.io/flags/ru.png');
SET IDENTITY_INSERT tblLanguage OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblUser ON;
INSERT INTO tblUser (ID, username, password, position) VALUES
(1,  'learner01',  'Pass@1234',  'learner'),
(2,  'learner02',  'Pass@5678',  'learner'),
(3,  'learner03',  'Pass@abcd',  'learner'),
(4,  'learner04',  'Pass@efgh',  'learner'),
(5,  'learner05',  'Pass@ijkl',  'learner'),
(6,  'learner06',  'Pass@mnop',  'learner'),
(7,  'learner07',  'Pass@qrst',  'learner'),
(8,  'learner08',  'Pass@uvwx',  'learner'),
(9,  'admin01',    'Admin@2024', 'admin'),
(10, 'admin02',    'Admin@2025', 'admin');
SET IDENTITY_INSERT tblUser OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLeague ON;
INSERT INTO tblLeague (ID, name, iconURL) VALUES
(1,  'Bronze',   'https://cdn.smarteng.io/leagues/bronze.png'),
(2,  'Silver',   'https://cdn.smarteng.io/leagues/silver.png'),
(3,  'Gold',     'https://cdn.smarteng.io/leagues/gold.png'),
(4,  'Sapphire', 'https://cdn.smarteng.io/leagues/sapphire.png'),
(5,  'Ruby',     'https://cdn.smarteng.io/leagues/ruby.png'),
(6,  'Emerald',  'https://cdn.smarteng.io/leagues/emerald.png'),
(7,  'Amethyst', 'https://cdn.smarteng.io/leagues/amethyst.png'),
(8,  'Pearl',    'https://cdn.smarteng.io/leagues/pearl.png'),
(9,  'Diamond',  'https://cdn.smarteng.io/leagues/diamond.png'),
(10, 'Obsidian', 'https://cdn.smarteng.io/leagues/obsidian.png');
SET IDENTITY_INSERT tblLeague OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblProfile ON;
INSERT INTO tblProfile (ID, displayName, avatarURL, level, totalEXP, currentStreak, heartCount, prizes, userID, tblLeagueID, tblLanguageID) VALUES
(1,  'StarLearner',   'https://cdn.smarteng.io/avatars/star.png',    5,  12500, 30, 5, 'streak_shield,double_exp',       1, 3, 1),
(2,  'NightOwl',      'https://cdn.smarteng.io/avatars/owl.png',     3,   7800, 12, 3, 'streak_shield',                  2, 2, 2),
(3,  'SakuraChan',    'https://cdn.smarteng.io/avatars/sakura.png',  7,  22000, 60, 5, 'golden_trophy,double_exp',       3, 5, 2),
(4,  'DragonKing',    'https://cdn.smarteng.io/avatars/dragon.png',  9,  45000, 90, 5, 'legendary_badge,golden_trophy',  4, 7, 6),
(5,  'QuickFox',      'https://cdn.smarteng.io/avatars/fox.png',     2,   3200, 5,  4, '',                               5, 1, 4),
(6,  'MoonWalker',    'https://cdn.smarteng.io/avatars/moon.png',    6,  18500, 45, 5, 'double_exp,streak_shield',       6, 4, 3),
(7,  'PandaMaster',   'https://cdn.smarteng.io/avatars/panda.png',   4,   9900, 20, 2, 'streak_shield',                  7, 3, 6),
(8,  'FlameRunner',   'https://cdn.smarteng.io/avatars/flame.png',   8,  33000, 75, 5, 'golden_trophy,legendary_badge',  8, 6, 1),
(9,  'AdminAlpha',    'https://cdn.smarteng.io/avatars/admin1.png',  1,       0, 0,  5, '',                               9, 1, 1),
(10, 'AdminBeta',     'https://cdn.smarteng.io/avatars/admin2.png',  1,       0, 0,  5, '',                              10, 1, 1);
SET IDENTITY_INSERT tblProfile OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblPersonalSetting ON;
INSERT INTO tblPersonalSetting (ID, dailyTimeGoal, isNotificationEnabled, isDarkMode, isSoundEnabled, tblUserID) VALUES
(1,  '15 minutes', 1, 0, 1, 1),
(2,  '10 minutes', 1, 1, 0, 2),
(3,  '20 minutes', 0, 1, 1, 3),
(4,  '30 minutes', 1, 0, 1, 4),
(5,  '5 minutes',  0, 0, 0, 5),
(6,  '25 minutes', 1, 1, 1, 6),
(7,  '15 minutes', 1, 0, 0, 7),
(8,  '45 minutes', 1, 1, 1, 8),
(9,  '60 minutes', 0, 1, 1, 9),
(10, '30 minutes', 1, 0, 1, 10);
SET IDENTITY_INSERT tblPersonalSetting OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblAccessHistory ON;
INSERT INTO tblAccessHistory (ID, accessTime, IpAddress, deviceInfo, actionType, timeStamp, duration, tblUserID) VALUES
(1,  830,  '192.168.1.101', 'Chrome/Windows 11',      'LOGIN',    '2026-05-28 08:30:00', 1800, 1),
(2,  1400, '192.168.1.102', 'Safari/macOS',            'STUDY',    '2026-05-28 14:00:00', 3600, 2),
(3,  2100, '10.0.0.15',     'Firefox/Ubuntu',          'PRACTICE', '2026-05-29 21:00:00', 900,  3),
(4,  730,  '172.16.5.200',  'Edge/Windows 10',         'LOGIN',    '2026-05-29 07:30:00', 2700, 4),
(5,  1230, '192.168.2.50',  'Chrome/Android',          'LOGOUT',   '2026-05-30 12:30:00', 120,  5),
(6,  1800, '10.10.1.88',    'Samsung Browser/Android', 'STUDY',    '2026-05-30 18:00:00', 4500, 6),
(7,  900,  '192.168.0.99',  'Chrome/iOS',              'PRACTICE', '2026-05-31 09:00:00', 600,  7),
(8,  2000, '172.20.10.3',   'Firefox/Windows 11',      'LOGIN',    '2026-05-31 20:00:00', 5400, 8),
(9,  1100, '192.168.1.1',   'Chrome/Windows 11',       'MANAGE',   '2026-05-27 11:00:00', 7200, 9),
(10, 1500, '10.0.0.1',      'Edge/Windows Server',     'STATS',    '2026-05-27 15:00:00', 3000, 10);
SET IDENTITY_INSERT tblAccessHistory OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblDailyMission ON;
INSERT INTO tblDailyMission (ID, title, description, targetValue, reward) VALUES
(1,  'First Step',         N'Hoàn thành 1 bài học trong ngày',               1,   20),
(2,  'Word Collector',     N'Học 10 từ vựng mới trong ngày',                 10,  30),
(3,  'Practice Master',    N'Làm 3 bài luyện tập trong ngày',                3,   50),
(4,  'Streak Keeper',      N'Duy trì chuỗi ngày học ít nhất 7 ngày',         7,  100),
(5,  'Perfect Score',      N'Đạt điểm tuyệt đối trong 1 bài kiểm tra',       1,   80),
(6,  'Speed Learner',      N'Hoàn thành 1 bài học trong vòng 5 phút',        1,   40),
(7,  'Question Champion',  N'Trả lời đúng 20 câu hỏi liên tiếp',            20,   60),
(8,  'Daily Goal',         N'Đạt mục tiêu thời gian học trong ngày',         1,   25),
(9,  'Alphabet Hero',      N'Ôn tập toàn bộ bảng chữ cái phát âm',          26,   35),
(10, 'Social Learner',     N'Hoàn thành bài học trong top 10 bảng xếp hạng', 1,   70);
SET IDENTITY_INSERT tblDailyMission OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblPersonsMission ON;
INSERT INTO tblPersonsMission (ID, assignedDate, currentProgress, isCompleted, isRewardClaimed, tblDailyMissionID, tblProfileID) VALUES
(1,  '2026-05-31', '1',  1, 1, 1, 1),
(2,  '2026-05-31', '7',  0, 0, 2, 2),
(3,  '2026-05-31', '3',  1, 1, 3, 3),
(4,  '2026-05-31', '7',  1, 0, 4, 4),
(5,  '2026-05-31', '0',  0, 0, 5, 5),
(6,  '2026-05-31', '1',  1, 1, 6, 6),
(7,  '2026-05-31', '15', 0, 0, 7, 7),
(8,  '2026-05-31', '1',  1, 1, 8, 8),
(9,  '2026-05-30', '26', 1, 1, 9, 3),
(10, '2026-05-30', '1',  1, 1, 10, 1);
SET IDENTITY_INSERT tblPersonsMission OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblTopic ON;
INSERT INTO tblTopic (ID, name, description, orderIndex, tblLanguageID) VALUES
(1,  'Greetings',       N'Học cách chào hỏi và giới thiệu bản thân',       1,  1),
(2,  'Numbers',         N'Học các con số và đếm số trong tiếng Anh',        2,  1),
(3,  'Food & Drink',    N'Từ vựng về ẩm thực và thức uống',                3,  1),
(4,  'Travel',          N'Từ vựng và hội thoại liên quan đến du lịch',      4,  1),
(5,  'Family',          N'Từ vựng về gia đình và các mối quan hệ',         5,  1),
(6,  'Animals',         N'Tên các loài động vật quen thuộc',               6,  1),
(7,  'Colors',          N'Học các màu sắc trong tiếng Anh',                7,  1),
(8,  'Jobs',            N'Từ vựng về nghề nghiệp',                         8,  1),
(9,  'Weather',         N'Cách miêu tả thời tiết',                         9,  1),
(10, 'Hobbies',         N'Sở thích và hoạt động giải trí',                 10, 1);
SET IDENTITY_INSERT tblTopic OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLesson ON;
INSERT INTO tblLesson (ID, name, code, description, des, tblTopicID) VALUES
(1,  'Hello & Goodbye',          'EN-GR-01', N'Chào hỏi cơ bản',             N'Học cách nói Hello, Hi, Good morning, Goodbye...', 1),
(2,  'Introducing Yourself',     'EN-GR-02', N'Tự giới thiệu bản thân',      'My name is..., I am from..., Nice to meet you', 1),
(3,  N'Numbers 1–10',             'EN-NB-01', N'Số từ 1 đến 10',              N'One, Two, Three... Ten với phát âm chuẩn',    2),
(4,  N'Numbers 11–100',           'EN-NB-02', N'Số từ 11 đến 100',            'Eleven, Twelve... Twenty, Thirty... Hundred', 2),
(5,  'Ordering Food',            'EN-FD-01', N'Gọi món ăn',                  'I would like..., Can I have..., The bill please', 3),
(6,  'Fruits & Vegetables',      'EN-FD-02', N'Rau củ quả',                  'Apple, Banana, Carrot, Broccoli...',          3),
(7,  'At the Airport',           'EN-TV-01', N'Tại sân bay',                 'Check-in, Boarding pass, Gate, Departure...',  4),
(8,  'Booking a Hotel',          'EN-TV-02', N'Đặt phòng khách sạn',         'Reservation, Room type, Check-out date...',    4),
(9,  'Farm Animals',     'EN-AN-01', N'Động vật nông trại',      'Cow, Pig, Chicken, Horse...', 6),
(10, 'Basic Colors',     'EN-CL-01', N'Màu sắc cơ bản',          'Red, Blue, Green, Yellow...', 7);
SET IDENTITY_INSERT tblLesson OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLetter ON;
INSERT INTO tblLetter (ID, character, type, audioURL, exampleWord, exampleAudioURL, tblLanguageID) VALUES
(1,  'A', 'vowel',     'https://cdn.smarteng.io/audio/en/a.mp3',         'Apple',      'https://cdn.smarteng.io/audio/en/apple.mp3',      1),
(2,  'B', 'consonant', 'https://cdn.smarteng.io/audio/en/b.mp3',         'Ball',       'https://cdn.smarteng.io/audio/en/ball.mp3',       1),
(3,  'C', 'consonant', 'https://cdn.smarteng.io/audio/en/c.mp3',         'Cat',        'https://cdn.smarteng.io/audio/en/cat.mp3',        1),
(4,  'D', 'consonant', 'https://cdn.smarteng.io/audio/en/d.mp3',         'Dog', 'https://cdn.smarteng.io/audio/en/dog.mp3',        1),
(5,  'E', 'vowel', 'https://cdn.smarteng.io/audio/en/e.mp3',         'Elephant', 'https://cdn.smarteng.io/audio/en/elephant.mp3',        1),
(6,  'F', 'consonant','https://cdn.smarteng.io/audio/en/f.mp3',         'Fish','https://cdn.smarteng.io/audio/en/fish.mp3',   1),
(7,  'G', 'consonant',    'https://cdn.smarteng.io/audio/en/g.mp3',         'Goat','https://cdn.smarteng.io/audio/en/goat.mp3', 1),
(8,  'H', 'consonant',     'https://cdn.smarteng.io/audio/en/h.mp3',         'Hat',      'https://cdn.smarteng.io/audio/en/hat.mp3',      1),
(9,  'I', 'vowel', 'https://cdn.smarteng.io/audio/en/i.mp3',         'Ice',        'https://cdn.smarteng.io/audio/en/ice.mp3',        1),
(10, 'J', 'consonant',     'https://cdn.smarteng.io/audio/en/j.mp3',         'Juice',   'https://cdn.smarteng.io/audio/en/juice.mp3',   1),
(11, 'K', 'consonant', 'https://cdn.smarteng.io/audio/en/k.mp3', 'Kite', 'https://cdn.smarteng.io/audio/en/kite.mp3', 1),
(12, 'L', 'consonant', 'https://cdn.smarteng.io/audio/en/l.mp3', 'Lion', 'https://cdn.smarteng.io/audio/en/lion.mp3', 1),
(13, 'M', 'consonant', 'https://cdn.smarteng.io/audio/en/m.mp3', 'Monkey', 'https://cdn.smarteng.io/audio/en/monkey.mp3', 1),
(14, 'N', 'consonant', 'https://cdn.smarteng.io/audio/en/n.mp3', 'Nest', 'https://cdn.smarteng.io/audio/en/nest.mp3', 1),
(15, 'O', 'vowel', 'https://cdn.smarteng.io/audio/en/o.mp3', 'Orange', 'https://cdn.smarteng.io/audio/en/orange.mp3', 1),
(16, 'P', 'consonant', 'https://cdn.smarteng.io/audio/en/p.mp3', 'Pig', 'https://cdn.smarteng.io/audio/en/pig.mp3', 1),
(17, 'Q', 'consonant', 'https://cdn.smarteng.io/audio/en/q.mp3', 'Queen', 'https://cdn.smarteng.io/audio/en/queen.mp3', 1),
(18, 'R', 'consonant', 'https://cdn.smarteng.io/audio/en/r.mp3', 'Rabbit', 'https://cdn.smarteng.io/audio/en/rabbit.mp3', 1),
(19, 'S', 'consonant', 'https://cdn.smarteng.io/audio/en/s.mp3', 'Sun', 'https://cdn.smarteng.io/audio/en/sun.mp3', 1),
(20, 'T', 'consonant', 'https://cdn.smarteng.io/audio/en/t.mp3', 'Tiger', 'https://cdn.smarteng.io/audio/en/tiger.mp3', 1),
(21, 'U', 'vowel', 'https://cdn.smarteng.io/audio/en/u.mp3', 'Umbrella', 'https://cdn.smarteng.io/audio/en/umbrella.mp3', 1),
(22, 'V', 'consonant', 'https://cdn.smarteng.io/audio/en/v.mp3', 'Van', 'https://cdn.smarteng.io/audio/en/van.mp3', 1),
(23, 'W', 'consonant', 'https://cdn.smarteng.io/audio/en/w.mp3', 'Water', 'https://cdn.smarteng.io/audio/en/water.mp3', 1),
(24, 'X', 'consonant', 'https://cdn.smarteng.io/audio/en/x.mp3', 'X-ray', 'https://cdn.smarteng.io/audio/en/x-ray.mp3', 1),
(25, 'Y', 'consonant', 'https://cdn.smarteng.io/audio/en/y.mp3', 'Yellow', 'https://cdn.smarteng.io/audio/en/yellow.mp3', 1),
(26, 'Z', 'consonant', 'https://cdn.smarteng.io/audio/en/z.mp3', 'Zebra', 'https://cdn.smarteng.io/audio/en/zebra.mp3', 1);
SET IDENTITY_INSERT tblLetter OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblVocabulary ON;
INSERT INTO tblVocabulary (ID, word, meaning, pronunciation, tblLanguageID) VALUES
(1,  'Hello',        N'Xin chào',              N'/həˈloʊ/',     1),
(2,  'Goodbye',      N'Tạm biệt',              N'/ɡʊdˈbaɪ/',    1),
(3,  'Thank you',    N'Cảm ơn',                N'/θæŋk juː/',   1),
(4,  'Apple',        N'Quả táo',               N'/ˈæp.əl/',     1),
(5,  'Water',        N'Nước',                  N'/ˈwɔː.tər/',   1),
(6,  'Dog',          N'Con chó',               N'/dɒɡ/',        1),
(7,  'Cat',          N'Con mèo',               N'/kæt/',        1),
(8,  'Red',          N'Màu đỏ',                N'/rɛd/',        1),
(9,  'Blue',         N'Màu xanh dương',        N'/bluː/',       1),
(10, 'Teacher',      N'Giáo viên',             N'/ˈtiː.tʃər/',  1);
SET IDENTITY_INSERT tblVocabulary OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLessonVocabulary ON;
INSERT INTO tblLessonVocabulary (ID, orderIndex, isReview, context, tblLessonID, tblVocabularyID) VALUES
(1,  1, 0, 'Used when meeting someone for the first time.',    1, 1),
(2,  2, 0, 'Used when parting from someone.',                  1, 2),
(3,  3, 1, 'Expressing gratitude in formal settings.',         1, 3),
(4,  1, 0, 'Common fruit discussed in food lessons.',          5, 4),
(5,  2, 0, 'Ordering water at a restaurant.',                  5, 5),
(6,  1, 0, 'A common pet that barks.',                         9, 6),
(7,  2, 1, 'A common pet that meows.',                         9, 7),
(8,  1, 0, 'The color of an apple.',                          10, 8),
(9,  1, 0, 'The color of the sky.',                           10, 9),
(10, 1, 0, 'A person who teaches in a school.',               10, 10);
SET IDENTITY_INSERT tblLessonVocabulary OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblQuestion ON;
INSERT INTO tblQuestion (ID, content, questionType, difficulty, options, correctAnswer, tblLessonID, tblLanguageID) VALUES
(1,  'What does "Hello" mean in Vietnamese?',
     'MULTIPLE_CHOICE', 'easy',
     N'["Tạm biệt","Xin chào","Cảm ơn","Xin lỗi"]',
     N'Xin chào', 1, 1),
(2,  'Good morning',
     'SPEAKING', 'easy',
     'Good morning',
     'Good morning', 1, 1),
(3,  'My name is John',
     'SPEAKING', 'medium',
     'My name is John',
     'My name is John', 2, 1),
(4,  'How do you say 7 in English?',
     'MULTIPLE_CHOICE', 'easy',
     '["Five","Six","Seven","Eight"]',
     'Seven', 3, 1),
(5,  'What number comes after eleven?',
     'MULTIPLE_CHOICE', 'easy',
     '["Ten","Twelve","Thirteen","Twenty"]',
     'Twelve', 4, 1),
(6,  'I would like a glass of water, please.',
     'SPEAKING', 'medium',
     'I would like a glass of water, please.',
     'I would like a glass of water, please.', 5, 1),
(7,  'Which of these is a vegetable?',
     'MULTIPLE_CHOICE', 'easy',
     '["Apple","Mango","Carrot","Grape"]',
     'Carrot', 6, 1),
(8,  'At the airport, what document do you need to board?',
     'MULTIPLE_CHOICE', 'medium',
     '["Passport only","Boarding pass","Driver license","Hotel booking"]',
     'Boarding pass', 7, 1),
(9,  'What animal says "meow"?',
     'MULTIPLE_CHOICE', 'easy',
     '["Dog","Cow","Cat","Horse"]',
     'Cat', 9, 1),
(10, 'What is the color of the sky?',
     'MULTIPLE_CHOICE', 'easy',
     '["Red","Green","Blue","Yellow"]',
     'Blue', 10, 1);
SET IDENTITY_INSERT tblQuestion OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblTest ON;
INSERT INTO tblTest (ID, title, type, timeLimit, rewardEXP, totalQuestions, tblLessonID) VALUES
(1,  'Greetings Quiz 1',          'LESSON_TEST', '00:10:00', 50,  5,  1),
(2,  'Introduction Test',         'LESSON_TEST', '00:08:00', 50,  4,  2),
(3,  N'Numbers 1–10 Quiz',         'LESSON_TEST', '00:05:00', 30,  5,  3),
(4,  N'Numbers 11–100 Quiz',       'LESSON_TEST', '00:07:00', 40,  5,  4),
(5,  'Food Ordering Test',        'LESSON_TEST', '00:10:00', 60,  6,  5),
(6,  'Fruits & Vegetables Quiz',  'LESSON_TEST', '00:08:00', 40,  5,  6),
(7,  'Airport Vocabulary Test',   'LESSON_TEST', '00:12:00', 70,  7,  7),
(8,  'Hotel Booking Quiz',        'LESSON_TEST', '00:10:00', 60,  6,  8),
(9,  'Animals Quiz',            'LESSON_TEST', '00:06:00', 40,  4,  9),
(10, 'Colors Test',             'LESSON_TEST', '00:08:00', 50,  5, 10);
SET IDENTITY_INSERT tblTest OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblTestQuestion ON;
INSERT INTO tblTestQuestion (ID, orderIndex, point, tblTestID, tblQuestionID) VALUES
(1,  1, 2.0, 1, 1),
(2,  2, 2.0, 1, 2),
(3,  1, 2.5, 2, 3),
(4,  1, 2.0, 3, 4),
(5,  2, 2.0, 4, 5),
(6,  1, 2.5, 5, 6),
(7,  1, 2.0, 6, 7),
(8,  1, 2.5, 7, 8),
(9,  1, 2.0, 9, 9),
(10, 1, 2.0, 10, 10);
SET IDENTITY_INSERT tblTestQuestion OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblTestResult ON;
INSERT INTO tblTestResult (ID, score, isPassed, lastAccessed, completionPercentage, itemsNumber, tblTestID, tblProfileID) VALUES
(1,  9.5, 1, '10:15:00', 100, 5, 1, 1),
(2,  7.0, 1, '14:32:00', 100, 4, 2, 2),
(3,  6.0, 1, '09:05:00', 100, 5, 3, 3),
(4,  10.0,1, '08:50:00', 100, 5, 4, 4),
(5,  4.5, 0, '20:10:00', 80,  5, 5, 5),
(6,  8.0, 1, '16:45:00', 100, 5, 6, 6),
(7,  7.5, 1, '11:30:00', 100, 7, 7, 7),
(8,  9.0, 1, '13:00:00', 100, 6, 8, 8),
(9,  5.0, 0, '17:20:00', 75,  4, 9, 1),
(10, 8.5, 1, '19:00:00', 100, 5, 10, 3);
SET IDENTITY_INSERT tblTestResult OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblStudyProgress ON;
INSERT INTO tblStudyProgress (ID, status, lastAccessed, completionPercentage, completedAt, tblLessonID, tblProfileID) VALUES
(1,  1, '10:00:00', '100%', '10:10:00', 1,  1),
(2,  1, '14:00:00', '100%', '14:08:00', 2,  2),
(3,  0, '09:00:00', '60%',  NULL,        3,  3),
(4,  1, '08:30:00', '100%', '08:45:00', 4,  4),
(5,  0, '20:00:00', '20%',  NULL,        5,  5),
(6,  1, '16:30:00', '100%', '16:42:00', 6,  6),
(7,  0, '11:00:00', '80%',  NULL,        7,  7),
(8,  1, '12:45:00', '100%', '12:58:00', 8,  8),
(9,  1, '17:00:00', '100%', '17:06:00', 9,  3),
(10, 0, '18:30:00', '40%',  NULL,        10, 1);
SET IDENTITY_INSERT tblStudyProgress OFF;
GO

-- ============================================================
PRINT 'Seed data inserted successfully! (10 records per table)';
GO
