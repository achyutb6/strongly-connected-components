UTD CS5V81-001 - Short Project 10

Project By : Achyut Bhandiwad (aab180004) and Saurav Sharma (sxs179830)

Submitted on: 11/18/2018

This Project was implemented as part of the course CS5V81-001 Implementation of Data Structures and Algorithms at University of Texas at Dallas.

The goal of the project was to:
   Implement the expected O(n) algorithm for the k largest elements (select)
   of an array, and compare its performance with the algorithm using
   priority queues that we designed for the same problem on streams.
   Use k=n/2 (median), and try large values of n: 16M, 32M, 64M, 128M, 256M.

File Name: SP11.java
Package name: aab180004

The following tasks are implemented: 
1. Implement the expected O(n) algorithm for the k largest elements (select)
   of an array, and compare its performance with the algorithm using
   priority queues that we designed for the same problem on streams.
   Use k=n/2 (median), and try large values of n: 16M, 32M, 64M, 128M, 256M.



+------------------------------------------------------+
|                        Results                       |
+------------------------------------------------------+
| Input size | Select O(n) algorithm | Select using PQ |
+------------+-----------------------+-----------------+
| 16M        |          483          |      26507      |
+------------+-----------------------+-----------------+
| 32M        |          729          |      75114      |
+------------+-----------------------+-----------------+
| 64M        |          1217         |     230397      |
+------------+-----------------------+-----------------+
| 128M       |          1654         |     724060      |
+------------+-----------------------+-----------------+
| 256M       |          2644         |    2335957      |
+------------+-----------------------+-----------------+


