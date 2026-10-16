FROM tomcat:9.0-jdk11-temurin

# 1. Desactiva el puerto 8005 para eliminar el WARNING "Invalid shutdown command" en Render
RUN sed -i 's/port="8005"/port="-1"/g' /usr/local/tomcat/conf/server.xml

# 2. Limpia las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# 3. Copia tu app tanto en ROOT (/) como en /MVCTienda para que ningún botón dé 404
COPY MVCTienda/build/web /usr/local/tomcat/webapps/ROOT
COPY MVCTienda/build/web /usr/local/tomcat/webapps/MVCTienda

ENV PORT=8080
EXPOSE 8080

CMD ["catalina.sh", "run"]
