#!/usr/bin/env bash

MODE=0

if [ "$#" -eq  "1" ]
then
    MODE=$1
fi

WORKING_DIR=/home/vula/Downloads
PROJECT_DIR=/home/vula/IdeaProjects/road-to-toronto
ROUND=round_1c

cd ${WORKING_DIR}
javac ${PROJECT_DIR}/src/cj2019/${ROUND}/Solution.java ${PROJECT_DIR}/out/production/RoadToToronto/cj2019/${ROUND}/Solution.class
cp ${PROJECT_DIR}/out/production/RoadToToronto/cj2019/${ROUND}/Solution.class cj2019/${ROUND}/
python interactive_runner.py python testing_tool.py ${MODE} -- java cj2019.${ROUND}.Solution
