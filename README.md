# Projekt Chat w Javie
# Opis
Projekt Chat w Javie jest prostą aplikacją umożliwiającą komunikację tekstową pomiędzy klientami poprzez serwer przy użyciu protokołu TCP. Dodatkowo, implementuje również komunikację za pomocą dodatkowego kanału UDP oraz opcję multicast.

# Funkcje
1.Klienci mogą łączyć się z serwerem poprzez protokół TCP.\
2.Serwer przyjmuje wiadomości od każdego klienta i rozsyła je do pozostałych, włączając identyfikator/nick klienta.\
3.Serwer jest wielowątkowy, co oznacza, że każde połączenie od klienta działa w swoim wątku, zapewniając poprawną obsługę wielu klientów jednocześnie.\
4.Dodatkowy kanał UDP jest dostępny dla komunikacji między klientami:\
  -Klient i serwer otwierają dodatkowy kanał UDP na tym samym numerze portu co TCP.\
  -Po wpisaniu komendy 'U' przez klienta, wiadomość jest przesyłana przez UDP na serwer, który następnie rozsyła ją do pozostałych klientów.\
  -Wiadomość symuluje dane multimedialne, takie jak ASCII Art.\
