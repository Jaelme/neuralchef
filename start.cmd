@echo off
REM Schnellstart-Skript für NeuralChef
REM Dieses Skript startet die Anwendung mit Maven

where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Starte NeuralChef mit Maven...
    mvn spring-boot:run
) else (
    echo Maven ist nicht installiert!
    echo.
    echo Bitte installiere Maven von: https://maven.apache.org/download.cgi
    echo Oder verwende einen Package Manager:
    echo   - Chocolatey: choco install maven
    echo   - Scoop: scoop install maven
    exit /b 1
)
