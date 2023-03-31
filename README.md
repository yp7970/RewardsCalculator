### Rewards Calculator



A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.



A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Sample data is provided in the data.sql which gets picked up automatically

```  GET /customers/{customerId}/rewards ```  to get the monthly rewards for a given customerId

```  GET /customers/rewards ```  to get the monthly rewards for all the customers