from ibmcom/db2
RUN yum install vim -y
RUN mkdir /var/custom
RUN mkdir /scripts
COPY ./createschema.sh /var/custom
COPY ./scripts /scripts
RUN chmod 777 /scripts/*
RUN chmod 777 /var/custom/createschema.sh
