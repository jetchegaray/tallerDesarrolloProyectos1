tallerDesarrolloProyectos1
==========================

TP para taller 1 FIUBA

## Configurar Ubuntu

### Instalar git

```bash
sudo apt-get install git-core
```

### Instalar java 7

Puede que primero necesiten hacer `sudo apt-get update`

```bash
sudo apt-get install openjdk-7-jdk
sudo update-java-alternatives -s java-1.7.0-openjdk-i386 
```

### Instalar mongodb

```bash
sudo apt-get install mongodb
```

### Instalar sbt (manejador de dependencias)

sbt maneja las dependencias del proyecto (incluyendo play), instalándolo ya no hace falta que instalen play por separado ni que modifiquen su PATH

```bash
sudo apt-get install curl
cd
curl http://scalasbt.artifactoryonline.com/scalasbt/sbt-native-packages/org/scala-sbt/sbt/0.12.3/sbt.deb -o sbt.deb
sudo dpkg -i sbt.deb
```

### Clonar repositorio

```bash
git clone git@github.com:jetchegaray/tallerDesarrolloProyectos1.git
```

### Iniciar aplicación

```bash
cd tallerDesarrolloProyectos1
sbt run
```

ir a `http://localhost:9000/mockup/Dashboard`
