@echo off
cd /d C:\C:\Users\gmerreb1003\Documents\Programacion
git add .
git diff-index --quiet HEAD || git commit -m "Auto-sync: %date% %time%"
git push  