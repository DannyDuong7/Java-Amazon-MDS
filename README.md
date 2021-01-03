# Java-Amazon-MDS
CS3345 Project using multi-dimensional search

Implement the operations described below that are required to perform multidimensional search. Starter code (MDS.java) is provided. Do not change the signatures of
methods declared to be public. Driver code (P3Driver.java) is also provided along with
several test cases.

Consider the web site of a seller like Amazon. They carry tens of thousands of products,
and each product has many attributes (Name, Size, Description, Keywords, Manufacturer,
Price, etc.). The search engine allows users to specify attributes of products that they are
seeking, and shows products that have most of those attributes. To make search efficient,
the data is organized using appropriate data structures, such as balanced trees. But, if
products are organized by Name, how can search by price implemented efficiently? The
solution, called indexing in databases, is to create a new set of references to the objects
for each search field, and organize them to implement search operations on that field
efficiently. As the objects change, these access structures must be kept consistent.
