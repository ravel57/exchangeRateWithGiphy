FROM openjdk:11
EXPOSE 8080
RUN mkdir /app
COPY ./exchangeRateWithGiphy.jar /app
CMD java -jar /app/exchangeRateWithGiphy.jar