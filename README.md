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

#### Commits

We use [this standard](https://github.com/erlang/otp/wiki/Writing-good-commit-messages).

## Third party
If you want to have the latest GAME_MASTER version as a developer, you can use 
```
wget https://raw.githubusercontent.com/BrunnerLivio/pokemongo-game-master/master/versions/latest/pokemongo.json
```

## Contributors

Livio Brunner <<a href="mailto:contact@brunnerliv.io">contact@brunnerliv.io</a>>