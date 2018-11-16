FROM payara/server-full:5.183


ADD drivers/mysql-connector-java-8.0.13.jar javadb/lib
COPY drivers/grizzly-npn-bootstrap.jar /opt/payara5/glassfish/lib/grizzly-npn-bootstrap.jar

RUN echo "AS_ADMIN_PASSWORD=admin" < /opt/pwdfile && \
    ./bin/asadmin start-domain domain1 && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile \ 
    create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource \ 
    --property user=root:password=root:DatabaseName=gamifikator:ServerName=db:port=3306:useSSL=false:allowPublicKeyRetrieval=true pool_gamifikator && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid pool_gamifikator jdbc/gamifikator && \
\
    ./bin/asadmin --interactive=false --user admin --passwordfile=/opt/pwdfile \
    create-javamail-resource --mailhost smtp.gmail.com --mailuser gamifikator.noreply --fromaddress gamifikator.noreply@gmail.com  \
    --property mail.smtp.user=gamifikator.replay@gmail.com:mail.smtp.password=gamifikator:mail.smtp.auth=true:mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory:mail.smtp.socketFactory.port=465:mail.smtp.port=465 mail/amt && \
\
    ./bin/asadmin stop-domain domain1

