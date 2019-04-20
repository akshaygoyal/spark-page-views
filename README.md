
## Page Views

Page Views is a sample spark project to analyse various pages viewed by users.
The project is written assuming following environment
* Spark : 2.3.1
* Scala : 2.11.8
* Redis : 2.3.0

### Instructions to execute

* Make sure you have the environment ready as mentioned above
* Build the jar using `sbt compile assembly`
* Spark submit the job (refer example below):
    
        spark-submit --deploy-mode client --name page-views --files <input-file-path-in-parquet> --class Main target/scala-2.11/page-views.jar <input-file-path-in-parquet> <page-set-output-location> <page-count-output-location>
    
* Your output will be written to <page-set-output-location> and <page-count-output-location> locations in parquet format
* Also you'll be able to find data written to Redis on localhost/127.0.0.1 at port 6379. 
* Run `keys *` in `redis-cli` to find all the keys
* Run `GET <key>` to find value corresponding to key `<key>`