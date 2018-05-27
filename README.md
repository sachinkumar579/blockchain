# blockchain
blockchain java app built as a RESTful service using maven

end points available : 

http://localhost:8080/blockchain/ - returns each block details. it contains the index,data,hash and previous hash code

http://localhost:8080/blockchain/add  - adds a new block to the chain 

http://localhost:8080/blockchain/delete - deletes the recently added block in the chain 

http://localhost:8080/blockchain/count - returns the total count of blocks in the chain 
