#!/bin/sh
echo "starting container $hostname"
if [ -z "$DATASOURCE_URL" ]; then
  echo "Container failed to start, pls pass -e DATASOURCE_URL=jdbc:postgresql://localhost:5432/vendutou"
  exit 1
fi

if [ -z "$DATASOURCE_USERNAME" ]; then
  echo "Container failed to start, pls pass -e DATASOURCE_USERNAME=username"
  exit 1
fi

if [ -z "$DATASOURCE_PASSWORD" ]; then
  echo "Container failed to start, pls pass -e DATASOURCE_PASSWORD=password"
  exit 1
fi

echo "starting container with $DATASOURCE_URL  - $DATASOURCE_USERNAME - $DATASOURCE_PASSWORD"
java -jar transactions-0.0.1-SNAPSHOT.jar
exec "$@"