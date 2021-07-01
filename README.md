# About
Server provides REST API which combines ERSTE transparent accounts balance with average cost of selected products.
Average const is provided by Czech Statistic Center. This API has 2 purposes. 
First it can say how much product people can buy with current balance.
Second it is possible to evaluate how people are rich in some products. 
In future, it can be extended and combine more than one product for evaluation.
It is also possible to track the "consumer basket" for real evaluation of inflation.

Use path **/combinedProducts** for get the combination of account number with amount of product and product description.

Example of response body:

    {
        "combinedResults": [
            {
                "accountNumber": "000000-0109213309",
                "products": [
                    {
                        "amount": 1402.2306067263817,
                        "description": "Vepřová pečeně s kostí [1 kg]",
                        "place": "Česká republika"
                    }
                ]
            }
        ]
    }
    
Whole documentation of API is available [here](http://localhost:8080/swagger-ui.html) after launch of application.
    
## Code hierarchy

    ├── config                  # Hold configuration of the project
    ├── controller              # Handles REST API requests
    ├── model                   # Application's dynamic data structure
    ├── scheduler               # Controls unattended background program execution
    ├── service                 # Main business logic
    └── util                    # Tools and utilities
    
    
For testing please download [Postman](https://www.postman.com/downloads/)
