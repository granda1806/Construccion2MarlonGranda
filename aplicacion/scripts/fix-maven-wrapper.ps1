<#
PowerShell helper: downloads `.mvn/wrapper/maven-wrapper.jar` if it is missing.
Usage: run from repository root in PowerShell:
  .\scripts\fix-maven-wrapper.ps1

This script reads `.mvn/wrapper/maven-wrapper.properties` for a `wrapperUrl`.
If not found, it falls back to a known Takari wrapper JAR location.
#>

$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
$repoRoot = Resolve-Path (Join-Path $scriptDir '..')
$wrapperDir = Join-Path $repoRoot '.mvn\wrapper'
if (-not (Test-Path $wrapperDir)) {
    New-Item -ItemType Directory -Path $wrapperDir -Force | Out-Null
}

$propsPath = Join-Path $wrapperDir 'maven-wrapper.properties'
$defaultWrapperUrl = 'https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar'

$wrapperUrl = $null
if (Test-Path $propsPath) {
    try {
        $content = Get-Content -Raw -Path $propsPath -ErrorAction Stop
        if ($content -match 'wrapperUrl\s*=\s*(\S+)') { $wrapperUrl = $matches[1] }
    } catch {
        Write-Host "Could not read ${propsPath}: $_"
    }
}

if (-not $wrapperUrl) { $wrapperUrl = $defaultWrapperUrl }

$jarPath = Join-Path $wrapperDir 'maven-wrapper.jar'
if (Test-Path $jarPath) {
    Write-Host "maven-wrapper.jar already exists at: $jarPath"
    return 0
}

Write-Host "Downloading maven-wrapper.jar from:`n  $wrapperUrl`ninto:`n  $jarPath"
try {
    Invoke-WebRequest -Uri $wrapperUrl -OutFile $jarPath -UseBasicParsing -ErrorAction Stop
    Write-Host "Downloaded maven-wrapper.jar successfully."
    return 0
} catch {
    Write-Host "Failed to download maven-wrapper.jar: $_"
    Write-Host "You can download manually from: $wrapperUrl and place it into: $jarPath"
    return 1
}
