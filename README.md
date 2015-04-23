# Distributed Spring



### Problems being explored:

* Can't send custom remote messages via Spring Cloud Bus

 *To Reproduce:*

1. Update `modules/config-server/src/main/resources/bootstrap.yml` to have a proper path (we'll eventually make this relative)
2. Start `ConfigServer.java` - give it about 6 seconds to boot
3. Start `WebApp.java`
4. Navigate to http://localhost:8081/dummy/name to see the name from `config-server`'s `demo.app.properties`
5. Navigate to http://localhost:8081/dummy/foo to trigger sending a remote event
6. Check logs on Config-Server -- nothing! Should be a log entry from `CustomEventListener.java`
