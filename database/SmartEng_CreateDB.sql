-- ============================================================
-- SmartEng Database - SQL Server Creation Script
-- Generated from ERD Diagram
-- ============================================================

USE master;
GO

-- Drop database if exists and recreate
IF EXISTS (SELECT name FROM sys.databases WHERE name = N'SmartEngDB')
BEGIN
    ALTER DATABASE SmartEngDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE SmartEngDB;
END
GO

CREATE DATABASE SmartEngDB;
GO

USE SmartEngDB;
GO

-- ============================================================
-- TABLE: tblLanguage
-- (No FK dependencies)
-- ============================================================
CREATE TABLE tblLanguage (
    ID          INT          NOT NULL IDENTITY(1,1),
    code        VARCHAR(25)  NULL,
    name        VARCHAR(50)  NULL,
    iconURL     VARCHAR(200) NULL,
    CONSTRAINT PK_tblLanguage PRIMARY KEY (ID)
);
GO

-- ============================================================
-- TABLE: tblUser
-- (No FK dependencies)
-- ============================================================
CREATE TABLE tblUser (
    ID          INT          NOT NULL IDENTITY(1,1),
    username    VARCHAR(25)  NULL,
    password    VARCHAR(25)  NULL,
    position    VARCHAR(25)  NULL,
    CONSTRAINT PK_tblUser PRIMARY KEY (ID)
);
GO

-- ============================================================
-- TABLE: tblLeague
-- (No FK dependencies)
-- ============================================================
CREATE TABLE tblLeague (
    ID          INT          NOT NULL IDENTITY(1,1),
    name        VARCHAR(100) NULL,
    iconURL     VARCHAR(255) NULL,
    CONSTRAINT PK_tblLeague PRIMARY KEY (ID)
);
GO

-- ============================================================
-- TABLE: tblDailyMission
-- (No FK dependencies)
-- ============================================================
CREATE TABLE tblDailyMission (
    ID          INT          NOT NULL IDENTITY(1,1),
    title       VARCHAR(100) NULL,
    description VARCHAR(500) NULL,
    targetValue BIGINT       NULL,
    reward      INT          NULL,
    CONSTRAINT PK_tblDailyMission PRIMARY KEY (ID)
);
GO

-- ============================================================
-- TABLE: tblPersonalSetting
-- FK: tblUserID -> tblUser(ID)
-- ============================================================
CREATE TABLE tblPersonalSetting (
    ID                    INT          NOT NULL IDENTITY(1,1),
    dailyTimeGoal         VARCHAR(50)  NULL,
    isNotificationEnabled BIT          NULL,
    isDarkMode            BIT          NULL,
    isSoundEnabled        BIT          NULL,
    tblUserID             INT          NULL,
    CONSTRAINT PK_tblPersonalSetting PRIMARY KEY (ID),
    CONSTRAINT FK_PersonalSetting_User FOREIGN KEY (tblUserID)
        REFERENCES tblUser(ID)
);
GO

-- ============================================================
-- TABLE: tblProfile
-- FK: userID -> tblUser(ID)
--     tblLeagueID -> tblLeague(ID)
--     tblLanguageID -> tblLanguage(ID)
-- ============================================================
CREATE TABLE tblProfile (
    ID            INT          NOT NULL IDENTITY(1,1),
    displayName   VARCHAR(50)  NULL,
    avatarURL     VARCHAR(250) NULL,
    level         BIGINT       NULL,
    totalEXP      INT          NULL,
    currentStreak INT          NULL,
    heartCount    INT          NULL,
    prizes        VARCHAR(500) NULL,
    userID        INT          NULL,
    tblLeagueID   INT          NULL,
    tblLanguageID INT          NULL,
    CONSTRAINT PK_tblProfile PRIMARY KEY (ID),
    CONSTRAINT FK_Profile_User FOREIGN KEY (userID)
        REFERENCES tblUser(ID),
    CONSTRAINT FK_Profile_League FOREIGN KEY (tblLeagueID)
        REFERENCES tblLeague(ID),
    CONSTRAINT FK_Profile_Language FOREIGN KEY (tblLanguageID)
        REFERENCES tblLanguage(ID)
);
GO

-- ============================================================
-- TABLE: tblAccessHistory
-- FK: tblUserID -> tblUser(ID)
-- ============================================================
CREATE TABLE tblAccessHistory (
    ID          INT          NOT NULL IDENTITY(1,1),
    accessTime  INT          NULL,
    IpAddress   VARCHAR(50)  NULL,
    deviceInfo  VARCHAR(50)  NULL,
    actionType  VARCHAR(50)  NULL,
    timeStamp   DATETIME     NULL,
    duration    INT          NULL,
    tblUserID   INT          NULL,   -- INT to match tblUser.ID (PK)
    CONSTRAINT PK_tblAccessHistory PRIMARY KEY (ID),
    CONSTRAINT FK_AccessHistory_User FOREIGN KEY (tblUserID)
        REFERENCES tblUser(ID)
);
GO

-- ============================================================
-- TABLE: tblPersonsMission
-- FK: tblDailyMissionID -> tblDailyMission(ID)
--     tblProfileID -> tblProfile(ID)
-- ============================================================
CREATE TABLE tblPersonsMission (
    ID                INT          NOT NULL IDENTITY(1,1),
    assignedDate      DATE         NULL,
    currentProgress   VARCHAR(50)  NULL,
    isCompleted       BIT          NULL,
    isRewardClaimed   BIT          NULL,
    tblDailyMissionID INT          NULL,
    tblProfileID      INT          NULL,
    CONSTRAINT PK_tblPersonsMission PRIMARY KEY (ID),
    CONSTRAINT FK_PersonsMission_DailyMission FOREIGN KEY (tblDailyMissionID)
        REFERENCES tblDailyMission(ID),
    CONSTRAINT FK_PersonsMission_Profile FOREIGN KEY (tblProfileID)
        REFERENCES tblProfile(ID)
);
GO

-- ============================================================
-- TABLE: tblTopic
-- FK: tblLanguageID -> tblLanguage(ID)
-- ============================================================
CREATE TABLE tblTopic (
    ID            INT          NOT NULL IDENTITY(1,1),
    name          VARCHAR(25)  NULL,
    description   VARCHAR(500) NULL,
    orderIndex    INT          NULL,
    tblLanguageID INT          NULL,
    CONSTRAINT PK_tblTopic PRIMARY KEY (ID),
    CONSTRAINT FK_Topic_Language FOREIGN KEY (tblLanguageID)
        REFERENCES tblLanguage(ID)
);
GO

-- ============================================================
-- TABLE: tblLetter
-- FK: tblLanguageID -> tblLanguage(ID)
-- ============================================================
CREATE TABLE tblLetter (
    ID              INT          NOT NULL IDENTITY(1,1),
    character       VARCHAR(50)  NULL,
    type            VARCHAR(50)  NULL,
    audioURL        VARCHAR(255) NULL,
    exampleWord     VARCHAR(100) NULL,
    exampleAudioURL VARCHAR(255) NULL,
    tblLanguageID   INT          NULL,
    CONSTRAINT PK_tblLetter PRIMARY KEY (ID),
    CONSTRAINT FK_Letter_Language FOREIGN KEY (tblLanguageID)
        REFERENCES tblLanguage(ID)
);
GO

-- ============================================================
-- TABLE: tblVocabulary
-- FK: tblLanguageID -> tblLanguage(ID)
-- ============================================================
CREATE TABLE tblVocabulary (
    ID            INT          NOT NULL IDENTITY(1,1),
    word          VARCHAR(100) NULL,
    meaning       VARCHAR(250) NULL,
    pronunciation VARCHAR(100) NULL,
    tblLanguageID INT          NULL,
    CONSTRAINT PK_tblVocabulary PRIMARY KEY (ID),
    CONSTRAINT FK_Vocabulary_Language FOREIGN KEY (tblLanguageID)
        REFERENCES tblLanguage(ID)
);
GO

-- ============================================================
-- TABLE: tblLesson
-- FK: tblTopicID -> tblTopic(ID)
-- ============================================================
CREATE TABLE tblLesson (
    ID          INT          NOT NULL IDENTITY(1,1),
    name        VARCHAR(50)  NULL,
    code        VARCHAR(50)  NULL,
    description VARCHAR(250) NULL,
    des         VARCHAR(500) NULL,
    tblTopicID  INT          NULL,
    CONSTRAINT PK_tblLesson PRIMARY KEY (ID),
    CONSTRAINT FK_Lesson_Topic FOREIGN KEY (tblTopicID)
        REFERENCES tblTopic(ID)
);
GO

-- ============================================================
-- TABLE: tblTest
-- FK: tblLessonID -> tblLesson(ID)
-- ============================================================
CREATE TABLE tblTest (
    ID             INT           NOT NULL IDENTITY(1,1),
    title          VARCHAR(100)  NULL,
    type           VARCHAR(50)   NULL,
    timeLimit      TIME(7)       NULL,
    rewardEXP      BIGINT        NULL,
    totalQuestions INT           NULL,
    tblLessonID    INT           NULL,
    CONSTRAINT PK_tblTest PRIMARY KEY (ID),
    CONSTRAINT FK_Test_Lesson FOREIGN KEY (tblLessonID)
        REFERENCES tblLesson(ID)
);
GO

-- ============================================================
-- TABLE: tblQuestion
-- FK: tblLessonID -> tblLesson(ID)
--     tblLanguageID -> tblLanguage(ID)
-- ============================================================
CREATE TABLE tblQuestion (
    ID            INT           NOT NULL IDENTITY(1,1),
    content       VARCHAR(500)  NULL,
    questionType  VARCHAR(50)   NULL,
    difficulty    VARCHAR(20)   NULL,
    options       VARCHAR(2000) NULL,
    correctAnswer VARCHAR(250)  NULL,
    tblLessonID   INT           NULL,
    tblLanguageID INT           NULL,
    CONSTRAINT PK_tblQuestion PRIMARY KEY (ID),
    CONSTRAINT FK_Question_Lesson FOREIGN KEY (tblLessonID)
        REFERENCES tblLesson(ID),
    CONSTRAINT FK_Question_Language FOREIGN KEY (tblLanguageID)
        REFERENCES tblLanguage(ID)
);
GO

-- ============================================================
-- TABLE: tblTestResult
-- FK: tblTestID -> tblTest(ID)
--     tblProfileID -> tblProfile(ID)
-- ============================================================
CREATE TABLE tblTestResult (
    ID                   INT        NOT NULL IDENTITY(1,1),
    score                FLOAT      NULL,
    isPassed             BIT        NULL,
    lastAccessed         TIME(7)    NULL,
    completionPercentage INT        NULL,
    itemsNumber          INT        NULL,
    tblTestID            INT        NULL,
    tblProfileID         INT        NULL,
    CONSTRAINT PK_tblTestResult PRIMARY KEY (ID),
    CONSTRAINT FK_TestResult_Test FOREIGN KEY (tblTestID)
        REFERENCES tblTest(ID),
    CONSTRAINT FK_TestResult_Profile FOREIGN KEY (tblProfileID)
        REFERENCES tblProfile(ID)
);
GO

-- ============================================================
-- TABLE: tblStudyProgress
-- FK: tblLessonID -> tblLesson(ID)
--     tblProfileID -> tblProfile(ID)
-- ============================================================
CREATE TABLE tblStudyProgress (
    ID                   INT         NOT NULL IDENTITY(1,1),
    status               BIT         NULL,
    lastAccessed         TIME(7)     NULL,
    completionPercentage VARCHAR(10) NULL,
    completedAt          TIME(7)     NULL,
    tblLessonID          INT         NULL,
    tblProfileID         INT         NULL,
    CONSTRAINT PK_tblStudyProgress PRIMARY KEY (ID),
    CONSTRAINT FK_StudyProgress_Lesson FOREIGN KEY (tblLessonID)
        REFERENCES tblLesson(ID),
    CONSTRAINT FK_StudyProgress_Profile FOREIGN KEY (tblProfileID)
        REFERENCES tblProfile(ID)
);
GO

-- ============================================================
-- TABLE: tblLessonVocabulary  (junction: Lesson <-> Vocabulary)
-- FK: tblLessonID -> tblLesson(ID)
--     tblVocabularyID -> tblVocabulary(ID)
-- ============================================================
CREATE TABLE tblLessonVocabulary (
    ID              INT          NOT NULL IDENTITY(1,1),
    orderIndex      INT          NULL,
    isReview        BIT          NULL,
    context         VARCHAR(250) NULL,
    tblLessonID     INT          NULL,
    tblVocabularyID INT          NULL,
    CONSTRAINT PK_tblLessonVocabulary PRIMARY KEY (ID),
    CONSTRAINT FK_LessonVocabulary_Lesson FOREIGN KEY (tblLessonID)
        REFERENCES tblLesson(ID),
    CONSTRAINT FK_LessonVocabulary_Vocabulary FOREIGN KEY (tblVocabularyID)
        REFERENCES tblVocabulary(ID)
);
GO

-- ============================================================
-- TABLE: tblTestQuestion  (junction: Test <-> Question)
-- FK: tblTestID -> tblTest(ID)
--     tblQuestionID -> tblQuestion(ID)
-- ============================================================
CREATE TABLE tblTestQuestion (
    ID            INT   NOT NULL IDENTITY(1,1),
    orderIndex    INT   NULL,
    point         FLOAT NULL,
    tblTestID     INT   NULL,
    tblQuestionID INT   NULL,
    CONSTRAINT PK_tblTestQuestion PRIMARY KEY (ID),
    CONSTRAINT FK_TestQuestion_Test FOREIGN KEY (tblTestID)
        REFERENCES tblTest(ID),
    CONSTRAINT FK_TestQuestion_Question FOREIGN KEY (tblQuestionID)
        REFERENCES tblQuestion(ID)
);
GO

-- ============================================================
-- DONE
-- ============================================================
PRINT 'SmartEng Database created successfully!';
GO
