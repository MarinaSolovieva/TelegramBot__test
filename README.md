## ** Spring boot application - Telegram Travel bot**

##### 1. Main class is TelegramBotApplication.

##### 2. Instruction for creation test database exists in DDL.sql and DML.sql files.

##### 3. Telegram bot settings: 
    - name: Travel_bot
    - token: 1643219739:AAG3l1qHvmunl_XWJX9_7G9kD9bOUU9xiBA

#### 4. Rest webservice address:
    - localhost:8081/cities
    
#### 5. You can use postman_collection.json to load CRUD api request for testing in Postman.

#### 6. Examples of using rest api:
    - Get: curl --location --request GET 'http://localhost:8081/cities?name=%D0%BC%D0%BE%D1%81%D0%BA%D0%B2%D0%B0'
    
    - Create: curl --location --request POST 'http://localhost:8081/cities' \
              --header 'Content-Type: application/json' \
              --data-raw '{
              "name": "Москва",
              "description": "Там холодно"
              }'
              
    - Update: curl --location --request PUT 'http://localhost:8081/cities/12' \
            --header 'Content-Type: application/json' \
            --data-raw '{
            "name": "Москва",
            "description": "Стало немного теплее"
            }'
            
    - Delete: curl --location --request DELETE 'http://localhost:8081/cities/12'