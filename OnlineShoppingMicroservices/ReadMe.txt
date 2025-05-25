# For more details -
https://github.com/SaiUpadhyayula/spring-boot-microservices

# Eureka server URL
http://localhost:8761/

# Actuator URL for circuit breaker state
http://localhost:8081/actuator/health/

# Order service through API Gateway
http://localhost:8181/api/order
body - {
           "orderLineItemsDtoList": [
               {
                   "skuCode" : "Iphone",
                   "price" : 1200,
                   "quantity" : 8
               }
           ]
       }

# Zipkin Url
http://localhost:9411