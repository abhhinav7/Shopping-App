+---------------+        +-------------+        +------------+       +---------------+        +-----------------+
|    Coupon     |        |   Orders    |        |   Product  |       |  Transaction  |        |      User       |
+---------------+        +-------------+        +------------+       +---------------+        +-----------------+
| id: Long      |<----+  | id: Long   |        | id: Long   |  +--->| transactionId:|<-------| id: Long        |
| user: User    |     |  | user: User |        | name: String|  |    | user: User    |        | username: String|
| code: String  |     |  | product:   |        | price: double| |    | order: Orders |        | email: String   |
| discountPerc. |      |  | Product    |       | qtyAvailable|  |    | amount: double|        +-----------------+
| availability  |     |  | quantity:  |        +------------+   |    | status: String|
+---------------+     |  | Integer    |                         |    | description:  |
                      |  | amount:    |                         |    | String        |
                      |  | double     |                         |    +-----------------+
                      |  | orderDate: |
                      |  | LocalDate  |
                      |  | coupon:    |
                      |  | String     |
                      +--+------------+
