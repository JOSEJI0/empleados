@echo off
echo === Limpiando versiones anteriores ===
docker-compose down

echo === Compilando nuevo JAR ===
call mvnw clean package -DskipTests

echo === Construyendo y levantando App + Base de Datos ===
docker-compose up --build -d

echo === LISTO! Todo el ecosistema esta corriendo ===
echo App en: http://localhost:8080
echo DB en puerto: 3306 (Usuario: root / Pass: root)
pause