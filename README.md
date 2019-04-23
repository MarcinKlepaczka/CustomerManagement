# CustomerManagement

1. Explanation. 

  The task was implemented according to provided 'specification'. Due too lack of time not all code was covered with unit tests and   javadocs. 

The microservice was implemented in 'layered' architecture style with 3 layers: service (API), business (logic), persistance (DB access) 

By default application starts at port 50000 with context: /customer-management

2. Docker

Dockerfile is provided in 'Docker' folder along with the application .jar. In oder to build and run container following commands shall be executed: 
  
        i)  docker build -t customer_management:latest .
        ii) docker run -d  -p 50000:50000 customer_management

3. Swagger

Swagger endpoints are available at: 

1. API-Docs 
  {localhost/docker-ip}:50000/customer_management/v2/api-docs

2. Swagger UI 
  {localhost/docker-ip}:50000/customer_management/swagger-ui.html
