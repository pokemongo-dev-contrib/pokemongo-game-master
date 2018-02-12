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
 -v, --version                     : The version you want to add
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
        -v|--version)        VERSION_TO_ADD=$2 && shift 2;;
        --latest)            LATEST=true && shift 1;;
        *)                   break;;
    esac    
done

if [ "${PROTOBUF_FILE}" == "" ]; then
    print_error_and_exit "Missing protobuf file"
fi

if [ "${VERSION_TO_ADD}" == "" ]; then
    print_error_and_exit "Missing version to add"
fi

NEW_VERSION_FOLDER="${VERSIONS_FOLDER}/${VERSION_TO_ADD}"
NEW_PROTOBUF_PATH="${NEW_VERSION_FOLDER}/GAME_MASTER.protobuf"
NEW_JSON_PATH="${NEW_VERSION_FOLDER}/GAME_MASTER.json"

echo $NEW_PROTOBUF_PATH

# Add folder only if needed
mkdir -p $NEW_VERSION_FOLDER

# Check if file is already in the destination
if [ "$(stat -c "%d:%i" $PROTOBUF_FILE)" != "$(stat -c "%d:%i" $NEW_PROTOBUF_PATH)" ]; then
  cp $PROTOBUF_FILE $NEW_PROTOBUF_PATH
fi

docker run -it --rm --name my-maven-project -v "$HOME/.m2":/root/.m2 -v `pwd`:/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-8 mvn clean package exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="${PROTOBUF_FILE} versions/${VERSION_TO_ADD}/GAME_MASTER.json"

if [ "$LATEST" == true ] ; then
    cp -Tr "$NEW_VERSION_FOLDER/" "$VERSIONS_FOLDER/latest"
fi
