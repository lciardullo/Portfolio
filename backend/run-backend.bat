@echo off
echo ========================================
echo Iniciando Portfolio Backend...
echo ========================================
echo.

set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot

echo JAVA_HOME: %JAVA_HOME%
echo.
echo El servidor se iniciara en http://localhost:8080
echo Presiona Ctrl+C para detener el servidor
echo.

call mvnw.cmd spring-boot:run

if errorlevel 1 (
    echo.
    echo ========================================
    echo ERROR al iniciar el servidor
    echo ========================================
    pause
)

