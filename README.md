# dropwizard-websockets-svc2scv

Websockets communication between Dropwizard services

## Start server

    java -jar server\target\server-1.0-SNAPSHOT.jar server server\dev.yml
    
## Start client 1

    java -jar client\target\client-1.0-SNAPSHOT.jar server client\dev1.yml

## Start client 2

    java -jar client\target\client-1.0-SNAPSHOT.jar server client\dev2.yml


## Post a message to the producer

    curl -H "Content-Type: application/json" -X POST -d '{"text":"Hello World!"}' http://localhost:8080/messages
