# Script para iniciar el backend
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot"

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Iniciando Portfolio Backend..." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Yellow

if (-not (Test-Path $env:JAVA_HOME)) {
    Write-Host "ERROR: JAVA_HOME no encontrado en: $env:JAVA_HOME" -ForegroundColor Red
    Write-Host "Presiona cualquier tecla para salir..."
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
    exit 1
}

Write-Host "`nEjecutando Maven Wrapper..." -ForegroundColor Green
Write-Host "El servidor se iniciará en http://localhost:8080" -ForegroundColor Cyan
Write-Host "Presiona Ctrl+C para detener el servidor`n" -ForegroundColor Yellow

try {
    .\mvnw.cmd spring-boot:run
} catch {
    Write-Host "`n========================================" -ForegroundColor Red
    Write-Host "ERROR al iniciar el servidor:" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    Write-Host "========================================`n" -ForegroundColor Red
    Write-Host "Presiona cualquier tecla para salir..."
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
}

