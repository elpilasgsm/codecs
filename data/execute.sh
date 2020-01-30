#!/bin/bash

set -e
dos2unix -q execution_order
while read IDM_F  ; do
    mysql -h127.0.0.1 --user=root --password=$1 < "$IDM_F"
done < execution_order
