# BSU-HU-CS-310-Final-Project

To compile this program first you have to type make into the command line. After all of the files run through then you can put in the command for what you want to do in the database. 

For Examples:

# Adding Items
java Project CreateItem item "Item description" 10.00

# Make Purchases
java Project CreatePurchase item 10

# Make shipments
java Project CreateShipment item 4 "2014-09-03"

# Get Items
java Project GetItems item

# Get All Items
java Project GetItems %

# Get Shipments
java Project GetShipments item

# Get All Shipments
java Project GetShipments %

# Get Purchases
java Project GetPurchases item

# Get All Purchases
java Project GetPurchases %

# Update Items
java Project UpdateItem item 1.50

# Deleting Item, Shipment, and Purchase
java Project DeleteItem item
java Project DeleteShipment item
java Project DeletePurchase item


The the items available command does not work for this program all of the other ones will work. If you wish to remove the object files simply type in make clean.








