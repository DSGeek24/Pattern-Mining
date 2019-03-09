For any pattern mining task, we would need to generate the frequent item sets. We have a large Amazon transaction database that consists of a set of reviewer ID's. Each line corresponds to a transaction, with items(reviewer ID's) seperated by space.  Using Apriori algorithm, we mine k+ frequent item sets given a minimum support count from this large transaction database. (Downward closure property also used).

Navigate to the project folder in the command prompt

Execute the following command in the command prompt

> java -jar Project1_Apriori.jar 5 3 transactionDB.txt out_s=5_k=3+.txt

s- minimum support and k= number of elements

Eg: s = 3, 𝑘 = 2
This would yield all itemsets appearing at least 3 times and containing at least 2 elements. 

Please make sure to give all the required fields of support,k, input file path and output file path else the program may not return any result. 
