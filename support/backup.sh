#!/bin/sh

FECHA=`date +%Y%m%d`
FOLDER=`dirname $0`
su postgres -c "pg_dump -Fc commercials > $FOLDER/db_$FECHA.dump"
