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

# Request Payloads

### Product

{
    "id": 2,
    "name": "iPhone X",
    "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
    "price": 899.0,
    "quantity": 34.0,
    "sales": null
}

### Sale

{
    "id": 2,
    "product": {
        "id": 2,
        "name": "iPhone X",
        "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
        "price": 899.0,
        "quantity": 34.0,
        "sales": null
    },
    "quantity": 5.0,
    "saleDate": "2023-05-12T08:45:34.839+00:00"
}

# Response Payloads

### Product - All Products

{
    "status_code": 1,
    "message": "success",
    "data": [
        {
            "id": 1,
            "name": "iPhone 9",
            "description": "An apple mobile which is nothing like apple",
            "price": 549.0,
            "quantity": 94.0,
            "sales": null
        },
        {
            "id": 2,
            "name": "iPhone X",
            "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
            "price": 899.0,
            "quantity": 34.0,
            "sales": null
        },
        {
            "id": 3,
            "name": "Samsung Universe 9",
            "description": "Samsung's new variant which goes beyond Galaxy to the Universe",
            "price": 1249.0,
            "quantity": 36.0,
            "sales": null
        },
        {
            "id": 4,
            "name": "OPPOF19",
            "description": "OPPO F19 is officially announced on April 2021.",
            "price": 280.0,
            "quantity": 123.0,
            "sales": null
        },
        {
            "id": 5,
            "name": "Huawei P30",
            "description": "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.",
            "price": 499.0,
            "quantity": 32.0,
            "sales": null
        }
    ],
    "error_code": null,
    "error_message": null
}

### Product - Product By Id

{
    "status_code": 1,
    "message": "Success",
    "data": {
        "id": 2,
        "name": "iPhone X",
        "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
        "price": 899.0,
        "quantity": 34.0,
        "sales": null
    },
    "error_code": null,
    "error_message": null
}

### Product - Update Product

{
    "status_code": 1,
    "message": "Data Successfully Updated",
    "data": {
        "id": 2,
        "name": "iPhone 14",
        "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
        "price": 123.0,
        "quantity": 3.0,
        "sales": null
    },
    "error_code": null,
    "error_message": null
}

### Product - Update Product

{
    "status_code": 1,
    "message": "Data Successfully Deleted",
    "data": null,
    "error_code": null,
    "error_message": null
}

### Sale - All Products

{
    "status_code": 1,
    "message": "Success",
    "data": [
        {
            "id": 1,
            "product": {
                "id": 1,
                "name": "iPhone 9",
                "description": "An apple mobile which is nothing like apple",
                "price": 549.0,
                "quantity": 94.0,
                "sales": null
            },
            "quantity": 2.0,
            "saleDate": "2023-05-12T08:45:34.839+00:00"
        },
        {
            "id": 2,
            "product": {
                "id": 2,
                "name": "iPhone 14",
                "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
                "price": 123.0,
                "quantity": 3.0,
                "sales": null
            },
            "quantity": 5.0,
            "saleDate": "2023-05-12T08:45:34.839+00:00"
        },
        {
            "id": 3,
            "product": {
                "id": 3,
                "name": "Samsung Universe 9",
                "description": "Samsung's new variant which goes beyond Galaxy to the Universe",
                "price": 1249.0,
                "quantity": 36.0,
                "sales": null
            },
            "quantity": 1.0,
            "saleDate": "2023-05-12T08:45:34.839+00:00"
        },
        {
            "id": 4,
            "product": {
                "id": 4,
                "name": "OPPOF19",
                "description": "OPPO F19 is officially announced on April 2021.",
                "price": 280.0,
                "quantity": 123.0,
                "sales": null
            },
            "quantity": 10.0,
            "saleDate": "2023-05-12T08:45:34.839+00:00"
        },
        {
            "id": 5,
            "product": {
                "id": 5,
                "name": "Huawei P30",
                "description": "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.",
                "price": 499.0,
                "quantity": 32.0,
                "sales": null
            },
            "quantity": 9.0,
            "saleDate": "2023-05-12T08:45:34.839+00:00"
        }
    ],
    "error_code": null,
    "error_message": null
}

### Sale - Sale By Id

{
    "status_code": 1,
    "message": "Success",
    "data": {
        "id": 2,
        "product": {
            "id": 2,
            "name": "iPhone 14",
            "description": "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
            "price": 123.0,
            "quantity": 3.0,
            "sales": null
        },
        "quantity": 5.0,
        "saleDate": "2023-05-12T08:45:34.839+00:00"
    },
    "error_code": null,
    "error_message": null
}

### Sale - Update Sale

{
    "status_code": 1,
    "message": "Success",
    "data": {
        "id": 4,
        "product": {
            "id": 5,
            "name": "Huawei P30",
            "description": "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.",
            "price": 499.0,
            "quantity": 32.0,
            "sales": null
        },
        "quantity": 11.0,
        "saleDate": "2023-05-12T08:45:34.839+00:00"
    },
    "error_code": null,
    "error_message": null
}

### Sale - Delete Sale

{
    "status_code": 1,
    "message": "Success",
    "data": null,
    "error_code": null,
    "error_message": null
}

### Revenue - Total Revenue

{
    "status_code": 1,
    "message": "Success",
    "data": 63582.0,
    "error_code": null,
    "error_message": null
}

### Revenue - Revenue By Product

{
    "status_code": 1,
    "message": "Success",
    "data": 640.0,
    "error_code": null,
    "error_message": null
}
