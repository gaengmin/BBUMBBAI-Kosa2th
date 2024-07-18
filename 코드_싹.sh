#!/bin/bash

project_root=$(pwd)
output_file="Code_Shark.txt"
source_dir=$(find "$project_root" -type d \( -path "*/src/main/java" -o -path "*/src" \) -print -quit)

if [ -f "$output_file" ]; then
    rm "$output_file"
fi

find "$source_dir" -name "*.java" -not -path "*/test/*" -exec cat {} + | grep -v '^import' >> "$output_file"