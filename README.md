# Distributed Spring

An example of a distributed microservices architecture with Spring Boot and Netflix OSS.



# Getting started

These modules rely on RabbitMQ for passing messages between each node. The easiest way to get rabbit up and running
 for the demo is to use Docker. If your docker install is running on a host other than (boot2docker's) `192.168.59.103`, 
 specify a `DOCKER_IP` environment variable. Then you can just spin up the default RabbitMQ install using: 
```docker run -d -e RABBITMQ_NODENAME=demoappRabbit --name demoappRabbit -p 5672:5672 -p 7000:15672 rabbitmq:3-management```

*Tip:* You can connecto to the rabbit web console at `http://${DOCKER_IP}:7000/` with username/password `guest`/`guest`
 and watch the connections tab to see your apps get registered. 

### Things of note:

* Sending custom events from the web project's `DummyController` to the config-server's `CustomEventListener`