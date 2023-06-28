# My first microservice app
## Technology used:
- Spring cloud (Eureka server, gateway)
- Spring data (Mongodb)
- kafka
- docker compose (now only for kafka and zookeper server, but in future i will dockerize all services)
- React

## Architecture of app
![image](https://github.com/SkaYXVIII/blog-microservices/assets/72752940/6116500e-0c67-4869-bb73-376d9cef6a7d)
\
 My app allows user to create posts and add comments do these post. I mainly focus on architecture of this app to got more familiar with microservices world.
 Services comunicate with each other asynchronously through kafka messege queue. I plan to add also some synchrounous comunication using feign client or webclient (webflux). 

 ## plans to expand the application
 - dockerize services
 - manage containers due to kubernetes
 - auth by google

  
