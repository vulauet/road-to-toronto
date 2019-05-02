#!/usr/bin/env bash

MODE=0

if [ "$#" -eq  "1" ]
then
    MODE=$1
fi

WORKING_DIR=/home/vula/Downloads
cd ${WORKING_DIR}
cp ../Projects/road-to-toronto/out/production/RoadToToronto/cj2019/round_1b/Solution.class cj2019/round_1b/
python interactive_runner.py python testing_tool.py ${MODE} -- java cj2019.round_1b.Solution
