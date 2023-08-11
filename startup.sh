#!/bin/bash
ls -l && java \
    -Dserver.port=80 -Duser.timezone=GMT-3 \
    -Duser.language=pt \
    -Duser.country=BR \
    -Xmx1G \
    -Xms256m \
    -Dspring.profiles.active=test \
    -jar app.war
