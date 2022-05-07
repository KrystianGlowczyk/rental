# rental

Aby uruchomić aplikacje należy, po pobraniu repozytorium w katalogu rental wywołać 3 polecenia:
1. mvn clean install  
2. cd target  
3. java -jar rental-1.jar

Aplikacja ma wbudowanego Swaggera, więc można można w przeglądarce wpisać: http://localhost:8080/swagger-ui/index.html#/ 


Przykładowe curl:
1. curl -X POST "http://localhost:8080/reservations" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dateFrom\": \"2022-05-07\", \"dateTo\": \"2022-05-08\", \"id_facility\": 1, \"id_landlord\": 1, \"id_tenant\": 2}"
2. curl -X PUT "http://localhost:8080/reservations/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dateFrom\": \"2022-05-09\", \"dateTo\": \"2022-05-15\", \"id_facility\": 1, \"id_landlord\": 1, \"id_tenant\": 2}"
3. curl -X GET "http://localhost:8080/reservations/facility?id=1&page=1&sort=DESC" -H "accept: */*"
4. curl -X GET "http://localhost:8080/reservations/tenant?name=Antek%20O&page=1&sort=ASC" -H "accept: */*"
