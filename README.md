# DemoApp

Demo REST Application developed as part of coding test for Spark Support Infotech (P) Ltd.

# Base URL

http://localhost:8081/demoapp/api/v1.0/

# Endpoints

### Product

1. GET: /product -> Get All Products

2. GET: /product/{id} -> Get Specific Product

3. POST: /product -> Add Product (along with request body)

4. PUT: /product/{id} -> Update Product (along with request body)

5. DELETE: /product/{id} -> Delete Specific Product

### Revenue 

1. GET: /revenue -> Get Total Revenue

2. GET: /revenue/product/{id} -> Get Revenue Of A Specific Product

### Sale

1. GET: /sale -> Get All Sale

2. GET: /sale/{id} -> Get Specific Sale

3. POST: /sale -> Add Sale (along with request body)

4. PUT: /sale/{id} -> Update Sale (along with request body)

5. DELETE: /sale/{id} -> Delete Specific Sale


# User Credentials

1. username: hitler, paassword: adolf123, role: ADMIN

2. username: marx, password: karlmarx, role: USER

3. username: guevara, password: ernesto, role: MANAGER



> For enabling pagination in */v1.0/product* endpoint, add below-mentioned header in the request. Also, make sure that the queries in the **db-queries.txt** are executed in the database.
`header value:- data-source:'db'`

