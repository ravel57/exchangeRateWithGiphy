Endpoints:
-  
Список кодов доступных валют:

```GET /currencies```

GIF по результату сравнения валюты из запроса с USD:

```GET /currencies/{currencyCode}```

GIF по результату сравнения заданной в application.properties валюты по умолчанию с USD:

```GET /```

Запуск:
---

В Environment variable необходимо добавить 2 записи с API-ключами с следующими названиями:

```openExchangeRatesToken```

```giphyToken```

Запуск .jar
```
java -jar exchangeRateWithGiphy-0.0.1-SNAPSHOT.jar
```
