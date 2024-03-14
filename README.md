# Projekt Chat w Javie
# Opis
Projekt Chat w Javie jest prostą aplikacją umożliwiającą komunikację tekstową pomiędzy klientami poprzez serwer przy użyciu protokołu TCP. Dodatkowo, implementuje również komunikację za pomocą dodatkowego kanału UDP oraz opcję multicast.

# Funkcje
1. Klienci mogą łączyć się z serwerem poprzez protokół TCP.
2. Serwer przyjmuje wiadomości od każdego klienta i rozsyła je do pozostałych, włączając identyfikator/nick klienta.
3. Serwer jest wielowątkowy, co oznacza, że każde połączenie od klienta działa w swoim wątku, zapewniając poprawną obsługę wielu klientów jednocześnie.
4. Dodatkowy kanał UDP jest dostępny dla komunikacji między klientami:
  -Klient i serwer otwierają dodatkowy kanał UDP na tym samym numerze portu co TCP.
  -Po wpisaniu komendy 'U' przez klienta, wiadomość jest przesyłana przez UDP na serwer, który następnie rozsyła ją do pozostałych klientów.
  -Wiadomość symuluje dane multimedialne, takie jak ASCII Art.

# Komendy Klienta
U: Wysłanie wiadomości za pomocą kanału UDP.
M: Przełączenie się na tryb multicast.

# Multicast (opcjonalnie)
Opcja multicast jest dostępna poprzez wpisanie komendy M w kliencie.
W trybie multicast, wiadomości są przesyłane bezpośrednio do wszystkich klientów poprzez adres grupowy.
Serwer może, ale nie musi odbierać wiadomości multicast.

#Uwagi
Projekt Chat w Javie jest prostą implementacją chatu wykorzystującą protokoły TCP i UDP.
Zapewniono wielowątkową obsługę serwera oraz dodatkowe funkcje komunikacyjne z użyciem UDP i multicast.
