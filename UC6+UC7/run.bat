@echo off
echo Compiling...
if not exist out mkdir out
javac -d out -cp "lib/mssql-jdbc.jar;src" src/dao/*.java src/model/*.java src/view/practice/*.java src/view/leaderboard/*.java src/view/admin/*.java src/view/user/*.java Main.java
if %ERRORLEVEL% NEQ 0 (
    echo Compile failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Running...
java -cp "lib/mssql-jdbc.jar;out" Main
pause
