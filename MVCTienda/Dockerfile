# Imagen oficial actualizada (Eclipse Temurin) compatible con cgroupv2 en Render
FROM tomcat:9.0-jdk11-temurin

# Elimina las aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu aplicación compilada a la raíz de Tomcat
COPY MVCTienda/build/web /usr/local/tomcat/webapps/ROOT

# Indica a Render explícitamente que solo use el puerto 8080
ENV PORT=8080
EXPOSE 8080

# Comando de arranque de Tomcat
CMD ["catalina.sh", "run"]
