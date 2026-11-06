@echo off
cd /d %~dp0
git add .
git diff-index --quiet HEAD || git commit -m "Auto-sync: %date% %time%"
git push