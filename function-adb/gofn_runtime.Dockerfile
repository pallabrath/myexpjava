FROM oraclelinux:8
WORKDIR /function
RUN curl https://download.oracle.com/otn_software/linux/instantclient/217000/oracle-instantclient-basiclite-21.7.0.0.0-1.el8.x86_64.rpm --output oracle-instantclient-basiclite-21.7.0.0.0-1.el8.x86_64.rpm
RUN yum -y install oracle-instantclient-basiclite-21.7.0.0.0-1.el8.x86_64.rpm && yum -y clean all  && rm -rf /var/cache
RUN rm -f oracle-instantclient-basiclite-21.7.0.0.0-1.el8.x86_64.rpm 