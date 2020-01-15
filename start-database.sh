#!/usr/bin/env bash

docker container run --name neo4j -d \
-p 7474:7474 -p 7473:7473 -p 7687:7687 \
-e NEO4J_AUTH=none \
-v $HOME/neo4j/data:data \
-v $HOME/neo4j/logs:/logs \
-m256m \
--cpus 1 \
neo4j

# If the container already exists, only starts this
if [ $? != 0 ]
then
  docker container start neo4j
fi

