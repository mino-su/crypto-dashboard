#!/bin/sh

# shellcheck disable=SC2068
exec java ${JAVA_OPTS} -jar /app.jar ${@}