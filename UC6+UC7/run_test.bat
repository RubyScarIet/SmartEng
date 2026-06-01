@echo off
echo ===========================================
echo Bien dich cac file ma nguon va Test...
echo ===========================================
if not exist out mkdir out
javac -d out -cp "lib\mssql-jdbc.jar;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar;src" src\dao\*.java src\model\*.java src\test\unit\*.java

if errorlevel 1 (
    echo.
    echo [LOI] Co loi trong qua trinh bien dich (Compile failed)
    pause
    exit /b 1
)

echo.
echo ===========================================
echo Dang chay toan bo Unit Tests...
echo ===========================================
java -cp "out;lib\mssql-jdbc.jar;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore test.unit.TopicDAOTest test.unit.LessonDAOTest test.unit.QuestionDAOTest test.unit.StudyProgressDAOTest test.unit.ProfileDAOTest test.unit.LeagueDAOTest

echo.
pause
