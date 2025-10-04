FROM bitnamilegacy/wildfly:36


COPY ./build/libs/web2.war /opt/bitnami/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080

CMD ["/opt/bitnami/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
