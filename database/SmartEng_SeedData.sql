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
(1,  'First Step',         'Hoàn thành 1 bài học trong ngày',               1,   20),
(2,  'Word Collector',     'Học 10 từ vựng mới trong ngày',                 10,  30),
(3,  'Practice Master',    'Làm 3 bài luyện tập trong ngày',                3,   50),
(4,  'Streak Keeper',      'Duy trì chuỗi ngày học ít nhất 7 ngày',         7,  100),
(5,  'Perfect Score',      'Đạt điểm tuyệt đối trong 1 bài kiểm tra',       1,   80),
(6,  'Speed Learner',      'Hoàn thành 1 bài học trong vòng 5 phút',        1,   40),
(7,  'Question Champion',  'Trả lời đúng 20 câu hỏi liên tiếp',            20,   60),
(8,  'Daily Goal',         'Đạt mục tiêu thời gian học trong ngày',         1,   25),
(9,  'Alphabet Hero',      'Ôn tập toàn bộ bảng chữ cái phát âm',          26,   35),
(10, 'Social Learner',     'Hoàn thành bài học trong top 10 bảng xếp hạng', 1,   70);
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
(1,  'Greetings',       'Học cách chào hỏi và giới thiệu bản thân',       1,  1),
(2,  'Numbers',         'Học các con số và đếm số trong tiếng Anh',        2,  1),
(3,  'Food & Drink',    'Từ vựng về ẩm thực và thức uống',                3,  1),
(4,  'Travel',          'Từ vựng và hội thoại liên quan đến du lịch',      4,  1),
(5,  'Family',          'Từ vựng về gia đình và các mối quan hệ',         5,  1),
(6,  'Hiragana Basics', 'Học bảng chữ Hiragana cơ bản',                   1,  2),
(7,  'Katakana',        'Học bảng chữ Katakana cơ bản',                   2,  2),
(8,  'K-Greetings',     'Chào hỏi và giao tiếp cơ bản tiếng Hàn',        1,  3),
(9,  'Les Couleurs',    'Học các màu sắc trong tiếng Pháp',                1,  4),
(10, 'Zahlen',          'Học số đếm trong tiếng Đức',                      1,  5);
SET IDENTITY_INSERT tblTopic OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLesson ON;
INSERT INTO tblLesson (ID, name, code, description, des, tblTopicID) VALUES
(1,  'Hello & Goodbye',          'EN-GR-01', 'Chào hỏi cơ bản',             'Học cách nói Hello, Hi, Good morning, Goodbye...', 1),
(2,  'Introducing Yourself',     'EN-GR-02', 'Tự giới thiệu bản thân',      'My name is..., I am from..., Nice to meet you', 1),
(3,  'Numbers 1–10',             'EN-NB-01', 'Số từ 1 đến 10',              'One, Two, Three... Ten với phát âm chuẩn',    2),
(4,  'Numbers 11–100',           'EN-NB-02', 'Số từ 11 đến 100',            'Eleven, Twelve... Twenty, Thirty... Hundred', 2),
(5,  'Ordering Food',            'EN-FD-01', 'Gọi món ăn',                  'I would like..., Can I have..., The bill please', 3),
(6,  'Fruits & Vegetables',      'EN-FD-02', 'Rau củ quả',                  'Apple, Banana, Carrot, Broccoli...',          3),
(7,  'At the Airport',           'EN-TV-01', 'Tại sân bay',                 'Check-in, Boarding pass, Gate, Departure...',  4),
(8,  'Booking a Hotel',          'EN-TV-02', 'Đặt phòng khách sạn',         'Reservation, Room type, Check-out date...',    4),
(9,  'あいさつ (Aisatsu)',        'JA-HG-01', 'Chào hỏi tiếng Nhật',        'おはようございます、こんにちは、さようなら',   6),
(10, '가족 (Gajok - Family)',    'KO-GR-01', 'Gia đình tiếng Hàn',          '아버지, 어머니, 형, 동생, 누나...',            8);
SET IDENTITY_INSERT tblLesson OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblLetter ON;
INSERT INTO tblLetter (ID, character, type, audioURL, exampleWord, exampleAudioURL, tblLanguageID) VALUES
(1,  'A', 'vowel',     'https://cdn.smarteng.io/audio/en/a.mp3',         'Apple',      'https://cdn.smarteng.io/audio/en/apple.mp3',      1),
(2,  'B', 'consonant', 'https://cdn.smarteng.io/audio/en/b.mp3',         'Ball',       'https://cdn.smarteng.io/audio/en/ball.mp3',       1),
(3,  'C', 'consonant', 'https://cdn.smarteng.io/audio/en/c.mp3',         'Cat',        'https://cdn.smarteng.io/audio/en/cat.mp3',        1),
(4,  'あ', 'hiragana', 'https://cdn.smarteng.io/audio/ja/a.mp3',         'あめ (ame)', 'https://cdn.smarteng.io/audio/ja/ame.mp3',        2),
(5,  'い', 'hiragana', 'https://cdn.smarteng.io/audio/ja/i.mp3',         'いぬ (inu)', 'https://cdn.smarteng.io/audio/ja/inu.mp3',        2),
(6,  'ㄱ', 'consonant','https://cdn.smarteng.io/audio/ko/g.mp3',         '가방 (gabang)','https://cdn.smarteng.io/audio/ko/gabang.mp3',   3),
(7,  'ㅏ', 'vowel',    'https://cdn.smarteng.io/audio/ko/a.mp3',         '아버지 (abeoji)','https://cdn.smarteng.io/audio/ko/abeoji.mp3', 3),
(8,  'A', 'vowel',     'https://cdn.smarteng.io/audio/fr/a.mp3',         'Avion',      'https://cdn.smarteng.io/audio/fr/avion.mp3',      4),
(9,  'D', 'consonant', 'https://cdn.smarteng.io/audio/en/d.mp3',         'Dog',        'https://cdn.smarteng.io/audio/en/dog.mp3',        1),
(10, 'E', 'vowel',     'https://cdn.smarteng.io/audio/en/e.mp3',         'Elephant',   'https://cdn.smarteng.io/audio/en/elephant.mp3',   1);
SET IDENTITY_INSERT tblLetter OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblVocabulary ON;
INSERT INTO tblVocabulary (ID, word, meaning, pronunciation, tblLanguageID) VALUES
(1,  'Hello',        'Xin chào',              '/həˈloʊ/',     1),
(2,  'Goodbye',      'Tạm biệt',              '/ɡʊdˈbaɪ/',    1),
(3,  'Thank you',    'Cảm ơn',                '/θæŋk juː/',   1),
(4,  'Apple',        'Quả táo',               '/ˈæp.əl/',     1),
(5,  'Water',        'Nước',                  '/ˈwɔː.tər/',   1),
(6,  'こんにちは',    'Xin chào (ban ngày)',   '/kon.ni.chi.wa/', 2),
(7,  'ありがとう',    'Cảm ơn',                '/a.ri.ga.to/', 2),
(8,  '안녕하세요',    'Xin chào (lịch sự)',    '/an.njʌŋ.ha.se.jo/', 3),
(9,  'Bonjour',      'Xin chào (tiếng Pháp)', '/bɔ̃.ʒuʁ/',    4),
(10, 'Danke',        'Cảm ơn (tiếng Đức)',    '/ˈdaŋ.kə/',    5);
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
(6,  1, 0, 'Standard daytime greeting in Japanese.',           9, 6),
(7,  2, 1, 'Saying thank you after receiving something.',      9, 7),
(8,  1, 0, 'Polite greeting used in Korean daily life.',      10, 8),
(9,  1, 0, 'Common French morning greeting.',                  1, 9),
(10, 1, 0, 'Used to thank someone in German context.',         1, 10);
SET IDENTITY_INSERT tblLessonVocabulary OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblQuestion ON;
INSERT INTO tblQuestion (ID, content, questionType, difficulty, options, correctAnswer, tblLessonID, tblLanguageID) VALUES
(1,  'What does "Hello" mean in Vietnamese?',
     'MULTIPLE_CHOICE', 'easy',
     '["Tạm biệt","Xin chào","Cảm ơn","Xin lỗi"]',
     'Xin chào', 1, 1),
(2,  'Fill in the blank: "Good ___, how are you?"',
     'FILL_IN', 'easy',
     '[]',
     'morning', 1, 1),
(3,  'Arrange the words: [my / is / name / John]',
     'WORD_ORDER', 'medium',
     '["my","is","name","John"]',
     'My name is John', 2, 1),
(4,  'How do you say 7 in English?',
     'MULTIPLE_CHOICE', 'easy',
     '["Five","Six","Seven","Eight"]',
     'Seven', 3, 1),
(5,  'What number comes after eleven?',
     'MULTIPLE_CHOICE', 'easy',
     '["Ten","Twelve","Thirteen","Twenty"]',
     'Twelve', 4, 1),
(6,  '"I would like a ___ of water, please." (glass/cup/bowl)',
     'FILL_IN', 'medium',
     '[]',
     'glass', 5, 1),
(7,  'Which of these is a vegetable?',
     'MULTIPLE_CHOICE', 'easy',
     '["Apple","Mango","Carrot","Grape"]',
     'Carrot', 6, 1),
(8,  'At the airport, what document do you need to board?',
     'MULTIPLE_CHOICE', 'medium',
     '["Passport only","Boarding pass","Driver license","Hotel booking"]',
     'Boarding pass', 7, 1),
(9,  'こんにちは means:',
     'MULTIPLE_CHOICE', 'easy',
     '["Good night","Good morning","Hello","Goodbye"]',
     'Hello', 9, 2),
(10, '안녕하세요 is used when:',
     'MULTIPLE_CHOICE', 'easy',
     '["Saying goodbye","Asking for help","Greeting someone politely","Ordering food"]',
     'Greeting someone politely', 10, 3);
SET IDENTITY_INSERT tblQuestion OFF;
GO

-- ============================================================
SET IDENTITY_INSERT tblTest ON;
INSERT INTO tblTest (ID, title, type, timeLimit, rewardEXP, totalQuestions, tblLessonID) VALUES
(1,  'Greetings Quiz 1',          'LESSON_TEST', '00:10:00', 50,  5,  1),
(2,  'Introduction Test',         'LESSON_TEST', '00:08:00', 50,  4,  2),
(3,  'Numbers 1–10 Quiz',         'LESSON_TEST', '00:05:00', 30,  5,  3),
(4,  'Numbers 11–100 Quiz',       'LESSON_TEST', '00:07:00', 40,  5,  4),
(5,  'Food Ordering Test',        'LESSON_TEST', '00:10:00', 60,  6,  5),
(6,  'Fruits & Vegetables Quiz',  'LESSON_TEST', '00:08:00', 40,  5,  6),
(7,  'Airport Vocabulary Test',   'LESSON_TEST', '00:12:00', 70,  7,  7),
(8,  'Hotel Booking Quiz',        'LESSON_TEST', '00:10:00', 60,  6,  8),
(9,  'Japanese Greeting Test',    'LESSON_TEST', '00:06:00', 40,  4,  9),
(10, 'Korean Family Words Test',  'LESSON_TEST', '00:08:00', 50,  5, 10);
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
