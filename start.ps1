# Schnellstart-Skript für NeuralChef
# Dieses Skript startet die Anwendung mit Maven

# Prüfe ob Maven installiert ist
if (Get-Command mvn -ErrorAction SilentlyContinue) {
    Write-Host "Starte NeuralChef mit Maven..." -ForegroundColor Green
    mvn spring-boot:run
} else {
    Write-Host "Maven ist nicht installiert!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Bitte installiere Maven von: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    Write-Host "Oder verwende einen Package Manager:" -ForegroundColor Yellow
    Write-Host "  - Chocolatey: choco install maven" -ForegroundColor Cyan
    Write-Host "  - Scoop: scoop install maven" -ForegroundColor Cyan
    exit 1
}
