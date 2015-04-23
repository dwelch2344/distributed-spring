# Distributed Spring



### Problems being explored:

* Can't send custom remote messages via Spring Cloud Bus

 *To Reproduce:*

# Update `modules/config-server/src/main/resources/bootstrap.yml` to have a proper path (we'll eventually make this relative)
# Start `ConfigServer.java` - give it about 6 seconds to boot
# Start `WebApp.java`
# Navigate to http://localhost:8081/dummy/name to see the name from `config-server`'s `demo.app.properties`
# Navigate to http://localhost:8081/dummy/foo to trigger sending a remote event
# Check logs on Config-Server -- nothing! Should be a log entry from `CustomEventListener.java`
