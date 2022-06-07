Endpoints:
-  
Список кодов доступных валют:

`GET /currencies`

GIF по результату сравнения валюты из запроса с USD:

`GET /currencies/{currencyCode}`

GIF по результату сравнения заданной в application.properties валюты по умолчанию с USD:

`GET /`

Запуск .jar:
---

В Environment variable необходимо добавить 2 записи с API-ключами с следующими ключами:

`openExchangeRatesToken`

`giphyToken`

Запуск:
```
java -jar exchangeRateWithGiphy.jar
```

Запуск Docker:
-
Выполнить команду из папке проекта
```
docker build -t exchangeratewithgiphy .  
```
Запуск контейнера
```
docker run --env openExchangeRatesToken=[yourOpenExchangeRatesToken] --env giphyToken=[yourGiphyToken] -p 8080:8080 exchangeratewithgiphy:latest
```