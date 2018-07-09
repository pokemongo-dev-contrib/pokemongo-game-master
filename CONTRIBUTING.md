# Contribute

## Add a new version

Make sure you have Docker installed. You can generate a new
GAME_MASTER.json using the following command. It will create a
new folder `versions/0.69.9`. Append the `--latest` parameter
if it is the latest version.

```bash

docker build -t "$USER/pokemongo-game-master" .
docker run \
  -v versions:/var/lib/pokemongo-game-master/versions \
  -v GAME_MASTER.protobuf:/var/lib/pokemongo-game-master/
  -it "$USER/pokemongo-game-master" \
  -f ./GAME_MASTER.protobuf -v "0.69.9"

```

[Usage without Docker](Windows / Linux)](doc/ADDVERSION.md)
