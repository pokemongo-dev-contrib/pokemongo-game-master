call mvn clean
call mvn package exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="v2_GAME_MASTER.protobuf GAME_MASTER.json --oldmode"
call mvn exec:java -Dexec.mainClass="com.pokebattler.gamemaster.GenerateJSON" -Dexec.args="v2_GAME_MASTER.protobuf v2_GAME_MASTER.json"
mv GAME_MASTER.json versions/latest/.
mv v2_GAME_MASTER.json versions/latest/.
mv *.protobuf versions/latest/.
