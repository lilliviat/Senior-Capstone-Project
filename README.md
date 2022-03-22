# DEEP Capstone Project
## Important Technologies for Data Ingestion
- Apache Kafka
- Docker
- Visual Studio Code
- Azure Data Explorer

## Installing Apache Kafka
### Steps to install kafka on Windows
1. Apache Kafka can be installed directly from its site:
  https://kafka.apache.org/downloads
2. Go to the Downloads folder found on the website and select Binary file.
3. Extract the file and move the extracted folder to the directory where you wish to keep the files.
4. Copy the path of the Kafka folder. Now go to config inside kafka folder and open zookeeper.properties file. Copy the path against the field dataDir and add /zookeeper-data to the path.
5. Now in the same folder config open *server.properties* and scroll down to *log.dirs* and paste the path. To the path add */kafka-logs*
6. This completes the configuration of zookeeper and kafka server. Now open command prompt and change the directory to the kafka folder. First start zookeeper using the command given below:
> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
7. Now open another command prompt and change the directory to the kafka folder. Run kafka server using the command:
> .\bin\windows\kafka-server-start.bat .\config\server.properties
Now Kafka is installed and ready to stream.

The following link contains the instructions for installing kafka:
[Install Kafka and Configure Kafka Topics](https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/)
