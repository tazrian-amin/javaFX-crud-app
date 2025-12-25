@echo off
REM Computer Parts Resale Store - Compile and Run Script for Windows
REM This script compiles and runs the JavaFX application

REM Configuration - UPDATE THIS PATH!
set JAVAFX_PATH=C:\path\to\javafx-sdk\lib
set SRC_DIR=src
set BIN_DIR=bin

echo ==================================
echo Computer Parts Resale Store
echo ==================================
echo.

REM Check if JavaFX path is configured
if "%JAVAFX_PATH%"=="C:\path\to\javafx-sdk\lib" (
    echo WARNING: Please update JAVAFX_PATH in this script!
    echo    Download JavaFX SDK from: https://openjfx.io/
    echo    Then edit this script and set the correct path.
    echo.
    pause
)

REM Create bin directory if it doesn't exist
if not exist "%BIN_DIR%" (
    mkdir "%BIN_DIR%"
    echo Created %BIN_DIR% directory
)

REM Compile
echo Compiling Java files...
javac --module-path "%JAVAFX_PATH%" --add-modules javafx.controls -d "%BIN_DIR%" "%SRC_DIR%\model\*.java" "%SRC_DIR%\service\*.java" "%SRC_DIR%\*.java"

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo.
    echo Running application...
    echo.
    
    REM Run
    java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls -cp "%BIN_DIR%" MainApp
) else (
    echo Compilation failed!
    pause
    exit /b 1
)

pause

