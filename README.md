### JavaFX project for beauty saloon with postgresql database. Connection by JDBC lib

### [figma](https://www.figma.com/file/gzx8QFwKm6lN11LvRi4s8H/%D0%9F%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%BD%D0%B0%D1%8F-%D0%B4%D0%B5%D1%8F%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%BE%D1%81%D1%82%D1%8C-4-%D1%81%D0%B5%D0%BC?node-id=0-1)

## Database Connection Config

Config at [src/main/java/com/example/ogrebeauty/repository/DatabaseInfo.java](https://github.com/mesor21/OgreBeauty/blob/main/src/main/java/com/example/ogrebeauty/repository/DatabaseInfo.java)

## Docker Create Database

```docker run --name postgreDB -e POSTGRES_PASSWORD=postgresql -p 5432:5432 -d postgres```

```docker exec -it postgreDB bash```

```psql -U postgres```

```CREATE DATABASE ogrebeauty;```
