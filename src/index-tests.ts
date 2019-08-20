import * as PokemongoGameMaster from './index';

PokemongoGameMaster
    .getVersion('latest', 'protobuf')
    .then((data: string) => { });

PokemongoGameMaster
    .getVersion('0.85.3', 'json')
    .then((data: object) => { });

PokemongoGameMaster
    .getLatestVersionName()
    .then((data: string) => { });