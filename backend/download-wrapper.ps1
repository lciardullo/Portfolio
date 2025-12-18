# Script para descargar Maven Wrapper JAR
$wrapperDir = ".mvn\wrapper"
$wrapperJar = "$wrapperDir\maven-wrapper.jar"
$wrapperUrl = "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.1.1/maven-wrapper-3.1.1.jar"

if (-not (Test-Path $wrapperDir)) {
    New-Item -ItemType Directory -Path $wrapperDir -Force | Out-Null
}

if (-not (Test-Path $wrapperJar)) {
    Write-Host "Descargando Maven Wrapper JAR..." -ForegroundColor Yellow
    try {
        Invoke-WebRequest -Uri $wrapperUrl -OutFile $wrapperJar
        Write-Host "Maven Wrapper descargado exitosamente!" -ForegroundColor Green
    } catch {
        Write-Host "Error al descargar Maven Wrapper: $_" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "Maven Wrapper JAR ya existe." -ForegroundColor Green
}

