# Pokemon Go Game Master
This repository is collection of the decoded GAME_MASTER-protobuf files

## Contribute

This project heavily relies on the help of you. So please contribute. 

### Guidelines

#### Add a new Version
The file system should be order like this
```
└── versions
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
When you create a pull request, make sure to create a new folder in 
the `versions`-directory. If the protobuf file is the latest version, 
call the folder `latest`. If you want to add not the latest version, 
call the folder like the version of the protobuf file (e.g. `0.57.3`).

Make sure to add always a protobuf file and a json file.

### Generating a new json file
1. Copy a protobuf file to the latest directory
  * Gamemaster file can be found on android devices at [internal storage OR sd card]/Android/data/com.ninaticlabs.pokemongo/files/remote_config_cache
2. Build the json converter (requires java and maven)
  * mvn package
3. Generate the file
  * java -cp target\pokemongo-game-master-2.7.0.jar com.pokebattler.gamemaster.GenerateJSON versions\latest\GAME_MASTER.protobuf > versions\latest\GAME_MASTER.json

#### Commits

We use [this standard](https://github.com/erlang/otp/wiki/Writing-good-commit-messages).

## Third party
If you want to have the latest GAME_MASTER version as a developer, you can use 
```
wget https://raw.githubusercontent.com/BrunnerLivio/pokemongo-game-master/master/versions/latest/pokemongo.json
```

## Contributors

Livio Brunner <<a href="mailto:contact@brunnerliv.io">contact@brunnerliv.io</a>>
Ryan Barker <<a href="mailto:celandro@gmail.com">celandro@gmail.com</a>
