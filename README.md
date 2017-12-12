# pokemongo-game-master

[![Online Users in pokemongo-game-master Discord Server](https://discordapp.com/api/guilds/293741011665027073/embed.png)](https://discord.gg/ssVqwvX)


This repository is collection of the decoded GAME_MASTER-protobuf files

## Contribute

This project heavily relies on the help of you. So please contribute. 

### Add a new version

#### Dependencies

- [Docker](https://www.docker.com/)

You can use the `./bin/add-version.sh` script (Linux only) 

```bash
cd pokemongo-game-master
cp ~/your/GAME_MASTER.protobuf .

# Latest version
./bin/add-version.sh -f ./GAME_MASTER.protobuf -v "0.85.3" --latest # Adds the new version "0.85.3", which is the latest version

# Older Version
./bin/add-version.sh -f ./GAME_MASTER.protobuf -v "0.82.1" # Adds the new version "0.82.1", which is the latest version
```


[Or from scratch (Windows / Linux)](doc/ADDVERSION.md)

### Commits

We use [this standard](https://github.com/erlang/otp/wiki/Writing-good-commit-messages).

## Third party
If you want to have the latest GAME_MASTER version as a developer, you can use 
```bash
wget https://raw.githubusercontent.com/BrunnerLivio/pokemongo-game-master/master/versions/latest/GAME_MASTER.json
```

### Projects

- [pokemongo-data-normalizer](https://github.com/BrunnerLivio/pokemongo-data-normalizer) - GAME_MASTER data normalized to better processable data
- [pokemongo-web-api](https://github.com/BrunnerLivio/pokemongo-web-api) - Web API for GAME_MASTER data



## Contributors

Livio Brunner <<a href="mailto:contact@brunnerliv.io">contact@brunnerliv.io</a>>

Ryan Barker <<a href="mailto:celandro@gmail.com">celandro@gmail.com</a>>
