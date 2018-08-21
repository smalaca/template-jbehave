Narrative:
Jako Klient
Chcę mieć możliwość użycia karty kredytowej
Aby opłacić Koszyk

Scenario:

Given w koszyku znajdują się produkty 
When podane zostaną poprawne dane karty kredytowej
Then następuje blokada środków na karcie kredytowej
And Klient informowany jest o pomyślności płatności
And Klient informowany jest o przyjęciu koszyka do realizacji

Scenario:

Given w koszyku znajdują się produkty 
When podane zostaną niepoprawne dane karty kredytowej
Then Klient informowany jest niepowodzeniu operacji blokady środków

Scenario:

Given Blokada środków na karcie była pomyśla 
When Klient decyduje się na zapisanie informacji o karcie
Then Dane karty zostają zapisane w profilu Klienta  

