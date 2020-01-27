# Locating Services at Runtime Using Service Discovery

# Eureka Fashboard

http://localhost:8761/

![Alt text](images/eureka.png?raw=true "Title")

# Tollrate Billboard

http://localhost:8081/dashboard?stationId=1,

Note: You can call different services: http://localhost:8081/dashboard?stationId=3

![Alt text](images/Billboard.png?raw=true "Title")


# Fastpass Service

http://localhost:8082/customerdetails?fastpassid=101


![Alt text](images/fastpass.png?raw=true "Title")


Now Simply call the ``http://localhost:8081/hystrix.stream`` and ``http://localhost:8082/hystrix.stream``, make sure you're able to see the streams.

Now, down the eureka-tollrate-service and hit the ``http://localhost:8081/dashboard?stationId=1``, fallback will call

Make sure ``hystrix-dashboard`` is running and hit the ``http://localhost:8085/hystrix``

Now pass the value ``http://localhost:8081/hystrix.stream`` to stream and you should be able to see the circuit closed.

Now down the ``eureka-tollrate-service` and check the fallback is working.

# Turbibe into hystrix-dashboard

Add turbine dependency and add @EnableTurbine and make sure below properties are added

```
turbine.app-config=tollrate-billboard,fastpass-console
turbine.aggregator.clusterConfig=TOLLRATE-BILLBOARD,FASTPASS-CONSOLE 
```
Now hit the ``http://localhost:8085/hystrix`` and ``http://localhost:8081/hystrix.stream?cluster=FASTPASS-CONSOLE`` and ``http://localhost:8082/hystrix.stream?cluster=TOLLRATE-BILLBOARD``

# hystrix turbine

When you want to use the ``hystrix-turbine`` server, you need to comment the @EnableTurbine of the HystrixDashboardApplication of ``hystrix-dashboard`` module.

Now make sure below properties are enabled of the ``eureka-fastpass-console`` and ``eureka-tollrate-billboard``. 

```
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

Now hit the ``http://localhost:8085/hystrix`` and give ``http://localhost:8087``, you should be able to see hystrix dashboard