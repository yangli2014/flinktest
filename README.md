#FlowDocument DataSet processor
This is the demo process about processing DataSet in Flink. This program will randomly generate 100 FlowDocument in a 
list. The src_address will randomly from  192.168.6.0 to 192.168.6.20 and dst_port will randomly from 5000 to 5100. 

The list will be processed using Flink DataSet and displayed the top 5 ip addresses with the largest count of unique 
dst_port





##How to run it.
* Build the project using maven

`mvn compile package`
* Execute the program 

`java -jar target\OpenNMSFlink-1.0-SNAPSHOT.jar`

##Added the StreamProcessor can be executed in IDEA
