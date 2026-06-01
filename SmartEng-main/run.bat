@echo off
echo Compiling...
javac -cp "lib/mssql-jdbc.jar;src" src/dao/*.java src/view/user/*.java src/view/admin/*.java src/view/study/*.java src/view/profile/*.java src/view/leaderboard/*.java src/view/practice/*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compile failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Running...
java -cp "lib/mssql-jdbc.jar;src" view.user.LoginFrm
pause
