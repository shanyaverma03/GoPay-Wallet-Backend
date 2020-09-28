Information Regarding the services and their functions.

1. UserAuthenticationService- Port no. 9100
2. UserService- Port no. 9200
3. TransactionService- Port no. 9005
4. BankService- Port no. 9000

Functions with urls

1. UserAuthenticationService- Port no. 8089
   a. Register a user- http://localhost:8089/api/v1/auth/register
   b. Login- http://localhost:8089/api/v1/auth/login

2. UserService- Port no. 8085
   a. Save a user- http://localhost:8085/api/v1/user
      POST Mapping
   b. Update user- http://localhost:8085/api/v1/user/update/{id}
      PUT Mapping
   c. Delete user- http://localhost:8085/api/v1/user/delete/{id}
   d. Get User- http://localhost:8085/api/v1/user/{id}
   e. Get wallet balance- http://localhost:8085/api/v1/user/balance/{id}
   f. Get all users- http://localhost:8085/api/v1/user/all
   
3. TransactionService- Port no. 8087
   a. Save Transaction- http://localhost:8087/api/v1/transaction
   b. Get by id- http://localhost:8087/api/v1/transaction/{id}
   c. Get all- http://localhost:8087/api/v1/transaction/all