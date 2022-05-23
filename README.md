# Explicação

Utilizei como linguagens de programação o Java, o Typescript e o Angular 13. O principal framework de backend foi o Spring boot com Spring Data e Scheduler e no Front End foi o Angular e diversos módulos (principalmente os de PrimeNG).

Eu dividi o backend em módulos para melhor isolar cada aspecto da aplicação. Cada módulo possui seus próprios elementos (com controller, model, dto, etc) com espaço para melhorias subsequentes. 

No front, a abordagem foi minimalista, buscando entregar um produto utilizável dentro do prazo estabelecido. Dividí o front em duas telas, uma para a conta, onde o usuário poderia ver seu saldo, e outra para as transferências.

O scheduler confere a cada 2 segundos (contado a partir do fim da última execução) se existe alguma transferência a ser feita e, se houver, ele o faz e desativa a transferência (tornando-se então um histórico).
