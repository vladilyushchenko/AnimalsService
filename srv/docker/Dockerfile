FROM adoptopenjdk/openjdk8:ubi
COPY ../target/animalsservice-exec.jar animalsservice/animalsservice-exec.jar
COPY ../sqlite.db Cap/AnimalsService/sqlite.db
COPY animalsrun.sh /scripts/animalsrun.sh
ENTRYPOINT ["/scripts/animalsrun.sh"]