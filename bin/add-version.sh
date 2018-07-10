#!/bin/bash
set -e
SCRIPTDIR=$(cd "$(dirname "$0")" && pwd)
ROOTFOLDER="${SCRIPTDIR}/.."
VERSIONS_FOLDER="${ROOTFOLDER}/versions"

#######################################
# Prints the error and exits the program
# Arguments:
#   None
# Returns:
#   None
#######################################
print_error_and_exit()
{
    UNEXPECTED_TRAP_MESSAGE=""

    echo -e "\nError: $1"
    exit 1
}

#######################################
# Prints information about this program
# Arguments:
#   None
# Returns:
#   None
#######################################
print_usage()
{
    cat <<EOF
pokemongo-game-master add version

Required arguments:
 -h, --help                        : Prints help usage
 -f, --file                        : The protobuf file to convert to JSON and to add
 -t, --timestamp                   : The custom timestamp
 --latest                          : Adds the file to the latest version

Sample usage:
./add-version.sh -f ./GAME_MASTER.protobuf -v "0.83.2-1" --latest

EOF
    return 0;
}

while true
do
    case "$1" in
        -f|--file)           PROTOBUF_FILE=$2 && shift 2;;
        -h|--help)           print_usage && exit 1;;
        -t|--timestamp)      TIMESTAMP=$2 && shift 2;;
        --latest)            LATEST=true && shift 1;;
        *)                   break;;
    esac
done

if [ "${PROTOBUF_FILE}" == "" ]; then
    print_error_and_exit "Missing protobuf file"
fi



# Generate JSON
mvn clean package exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="${PROTOBUF_FILE} GAME_MASTER.json"

if [ "${TIMESTAMP}" == "" ]; then
    # Get timestampMs from new JSON file
    json_content=$(cat GAME_MASTER.json)
    TIMESTAMP=$(echo $json_content | grep -oP '"timestampMs": "([0-9]+)"'  | grep -oP "[0-9]+")
fi

NEW_VERSION_FOLDER="${VERSIONS_FOLDER}/${TIMESTAMP}"
NEW_PROTOBUF_PATH="${NEW_VERSION_FOLDER}/GAME_MASTER.protobuf"
NEW_JSON_PATH="${NEW_VERSION_FOLDER}/GAME_MASTER.json"


# Add folder only if needed
mkdir -p $NEW_VERSION_FOLDER

cp $PROTOBUF_FILE $NEW_PROTOBUF_PATH
cp -rf GAME_MASTER.json $NEW_JSON_PATH

git add "$NEW_PROTOBUF_PATH" "$NEW_JSON_PATH"

if [ "$LATEST" == true ] ; then
    # Copy into latest folder
    cp -Tr "$NEW_VERSION_FOLDER/" "$VERSIONS_FOLDER/latest"
    # Update latest version.txt
    rm -f versions/latest-version.txt
    echo $TIMESTAMP >> versions/latest-version.txt
    git add "$VERSIONS_FOLDER/latest/GAME_MASTER.protobuf" "$VERSIONS_FOLDER/latest/GAME_MASTER.json" "$VERSIONS_FOLDER/latest-version.txt"
fi

git commit -m "Update GAME_MASTER with timestamp ${TIMESTAMP}"

echo "DONE"
echo "Run 'git push origin master --tags' to push the current changes"

