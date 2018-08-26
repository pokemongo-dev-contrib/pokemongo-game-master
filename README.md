# pokemongo-game-master

[![Build Status](https://travis-ci.org/pokemongo-dev-contrib/pokemongo-game-master.svg?branch=master)](https://travis-ci.org/pokemongo-dev-contrib/pokemongo-game-master)

[![Online Users in pokemongo-game-master Discord Server](https://discordapp.com/api/guilds/293741011665027073/embed.png)](https://discord.gg/ssVqwvX)

This repository is collection of the decoded GAME_MASTER-protobuf files

## Getting Started

If you want to have the latest GAME_MASTER version simply execute the following
shell command. This downloads the latest GAME_MASTER.json file of this repository.

```bash

wget https://raw.githubusercontent.com/pokemongo-dev-contrib/pokemongo-game-master/master/versions/latest/GAME_MASTER.json

```

## NodeJS

Make sure you install the package by running `npm i pokemongo-game-master`.
Simply request the versions in the formats `protobuf` or `json` inside your code.
Behind the scenes it will fetch the data from this repository and returns
the value in a promise.

```JavaScript

const gameMaster = require('pokemongo-game-master');

gameMaster.getVersion('latest', 'json').then(console.log); // Returns as object: { itemTemplates: [ ... ], timestampMs: '1512514949791' }
gameMaster.getVersion('0.83.3', 'protobuf').then(console.log); // Returns the version 0.83.3 as string

```

## Community Day JSON files

Community day JSON files are found in the special folder. They contain
the moves that were available on that particular community day

## Contrib

The contrib file is for 3rd party generated json files. Currently historical
Pokebattler json files can be found there
