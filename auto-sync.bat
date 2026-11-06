@echo off
cd /d E:\daw1_programacion
git add .
git diff-index --quiet HEAD || git commit -m "Auto-sync: %date% %time%"
git push  