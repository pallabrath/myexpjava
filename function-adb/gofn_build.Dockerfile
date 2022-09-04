FROM oraclelinux:8
RUN yum -y install golang && yum -y clean all  && rm -rf /var/cache
