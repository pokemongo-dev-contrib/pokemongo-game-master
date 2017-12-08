# Pokemon Go Game Master

[![ghit.me](https://ghit.me/badge.svg?repo=BrunnerLivio/pokemongo-game-master)](https://ghit.me/repo/BrunnerLivio/pokemongo-game-master)
[![Online Users in pokemongo-game-master Discord Server](https://discordapp.com/api/guilds/293741011665027073/embed.png)](https://discord.gg/ssVqwvX)


This repository is collection of the decoded GAME_MASTER-protobuf files

## Contribute

This project heavily relies on the help of you. So please contribute. 

### Guidelines

#### Add a new Version
The file system should be order like this
```
└── versions
    ├── latest-version.txt
    ├── 0.57.1
    │   ├── GAME_MASTER.json
    │   └── GAME_MASTER.protobuf
    ├── 0.57.2
    │   ├── GAME_MASTER.json
    │   └── GAME_MASTER.protobuf
    └── latest
        ├── GAME_MASTER.json
        └── GAME_MASTER.protobuf
```
##### Add latest version
1. Copy the latest protobuf file into versions/latest directory and rename it to `GAME_MASTER.protobuf`
2. See [Generating a new json file](#generating-a-new-json-file)
3. Add a folder, named like the latest version (e.g. `0.57.3`)
4. Copy the GAME_MASTER.protobuf and GAME_MASTER.json into the folder (Now your protobuf file should exist 2 times in the project)
4. Update latest version in `versions/latest-version.txt`
6. Create pull request


##### Add older version
1. Add a folder, named like the version (e.g. `0.57.3`)
2. Copy the protobuf file into the new folder and rename it to `GAME_MASTER.protobuf`
3. See [Generating a new json file](#generating-a-new-json-file) step 3
4. Create pull request


### Generating a new json file
1. Copy a protobuf file to the latest directory
  * Gamemaster file can be found on android devices at [internal storage OR sd card]/Android/data/com.ninaticlabs.pokemongo/files/remote_config_cache
2. Build and run the json converter (requires java and maven)
  * `mvn package`
  * or using docker
    * WINDOWS: `docker run -it --rm --name my-maven-project -v "C:%HOMEPATH%\.m2":/root/.m2 -v "%cd%":/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-8 mvn clean package exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="versions/latest/GAME_MASTER.protobuf versions/latest/GAME_MASTER.json"`
    * WINDOWS POWERSHELL: Same as above but you will need to put in the full path to your home directory for %HOMEPATH% and current dirrectory fo %cd%
    * *NIX: ` docker run -it --rm --name my-maven-project -v "$HOME/.m2":/root/.m2 -v `pwd`:/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-8 mvn clean package exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="versions/latest/GAME_MASTER.protobuf versions/latest/GAME_MASTER.json"`

3. (Optional) Generate the file manually
  * WINDOWS: `java -cp target\pokemongo-game-master-2.15.0.jar com.pokebattler.gamemaster.GenerateJSON versions\latest\GAME_MASTER.protobuf versions\latest\GAME_MASTER.json`
  * *NIX: `java -cp target/pokemongo-game-master-2.15.0.jar com.pokebattler.gamemaster.GenerateJSON versions/latest/GAME_MASTER.protobuf versions/latest/GAME_MASTER.json`

  
#### Commits

We use [this standard](https://github.com/erlang/otp/wiki/Writing-good-commit-messages).

## Third party
If you want to have the latest GAME_MASTER version as a developer, you can use 
```
wget https://raw.githubusercontent.com/BrunnerLivio/pokemongo-game-master/master/versions/latest/GAME_MASTER.json
```
### Projects

- [pokemongo-data-normalizer](https://github.com/BrunnerLivio/pokemongo-data-normalizer) - GAME_MASTER data normalized to better processable data
- [pokemongo-web-api](https://github.com/BrunnerLivio/pokemongo-web-api) - Web API for GAME_MASTER data
- [Pogonium](https://github.com/truimagz/pogonium) - Pokédex for Pokemon Go



## Contributors

Livio Brunner <<a href="mailto:contact@brunnerliv.io">contact@brunnerliv.io</a>>

Ryan Barker <<a href="mailto:celandro@gmail.com">celandro@gmail.com</a>>
