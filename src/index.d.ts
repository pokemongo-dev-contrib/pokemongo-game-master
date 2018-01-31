// Type definitions for pokemongo-game-master 1.0.0
// Project: https://github.com/BrunnerLivio/pokemongo-game-master
// Definitions by: Livio Brunner <https://github.com/BrunnerLivio>
// Definitions: https://github.com/DefinitelyTyped/DefinitelyTyped

/**
 * Collection of the decoded GAME_MASTER-protobuf files
 */
export class PokemongoGameMaster {
    static getVersion(version: string, format: "protobuf" | "json"): Promise<string|object>;
    static getLatestVersionName(): Promise<string>;
}
