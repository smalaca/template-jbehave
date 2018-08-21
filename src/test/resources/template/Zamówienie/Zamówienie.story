Narrative:
As a Klient
I want to złożyć zamówienie
So that I can kupić produkt



Scenario: Dodanie produktu do koszyka

Given Istnieje produkt
When Klient dodaje produkt do koszyka
Then Produkt został dodany do koszyka




Scenario: Rezerwacja produktu

Given Produkt znajduje się w koszyku
When Klient zatwierdza koszyk
Then System dokonał rezerwacji




Scenario: System informuje Klienta o możlwości odbióru paczki

Given Produkt został wysłany
When Produkt jest gotowy do odebrania
Then System poinformował Klienta o możlwości odbióru paczki