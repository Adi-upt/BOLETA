# Usa una imagen oficial de Tomcat con Java 11 (que coincide con el JDK que usaste en NetBeans)
FROM tomcat:9.0-jdk11-openjdk

# Elimina las aplicaciones web predeterminadas de Tomcat para mayor seguridad y limpieza
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia la carpeta 'build/web' de tu proyecto Ant a la carpeta de aplicaciones de Tomcat
# Lo renombramos a ROOT para que tu app cargue directamente en la URL principal (ej. tusitio.onrender.com/)
COPY MVCTienda/build/web /usr/local/tomcat/webapps/ROOT

# Expone el puerto 8080 (el puerto por defecto de Tomcat)
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]
