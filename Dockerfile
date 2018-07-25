FROM maven:3.2-jdk-8

WORKDIR /var/lib/pokemongo-game-master

COPY pom.xml .
COPY . .
ENTRYPOINT [ "./bin/add-version.sh" ]
