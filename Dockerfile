FROM adoptopenjdk/openjdk8:ubi

WORKDIR /app

ARG JAR_FILE

# Copia o arquivo JAR para o diretório workdir no container
COPY target/${JAR_FILE} /app/fmproject.jar
# script da documentação do docker compose para garantir que o banco
# ou alguma porta especifica esteja up antes de subir a aplicação
COPY wait-for-it.sh /wait-for-it.sh
# rodando o wait for it
RUN chmod +x /wait-for-it.sh


ENTRYPOINT ["java", "-jar", "fmproject.jar"]


EXPOSE 8080

