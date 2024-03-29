# nss-sem
## Popis
Aplikace je součásti semestrálního projektu z předmětu NSS.

[Aplikace Timify](http://34.125.160.101:3000/)

[Dokumentace](https://github.com/ulcheyev/nss-timify/blob/main/nss_timify_documentation.pdf)

[Low fidelity prototyp ve Figme](https://www.figma.com/proto/VgDQumqtyGxfPraBurKkr0/Untitled?page-id=0%3A1&type=design&node-id=4-49&viewport=296%2C436%2C0.24&t=p0D6ibuid7sMLY5m-1&scaling=min-zoom&starting-point-node-id=4%3A49&mode=design)

[Ednpoint documentation in Swagger](http://34.125.160.101:8085/swagger-ui/index.html)
## Tým:
- Iurii Lebedev
- Kyrylo Herasymenko
- Yevgeniy Ulchenkov
- Ekaterina Gulina

## Požadavky:

- volba jazyků: backend - Java, frontend – React, HTML, CSS
- readme v gitu s popisem co je hotové a kde se funkcionalita nachází
- applikace využívá relačnou DB
- applikace využívá cache (TaskController)
- aplikace je zabezpečená pomocí OAuth2 (User)
- aplikace využívá REST
- aplikace je nasazená na produkční server Google Cloud
- aplikace využívá mikroservisní architekturu
- inicializační postup – viz. další podkapitolu
- v aplikaci byli použité následující patterny: – **builder** (DTO – TaskDTO apod.), **DTO**, **funkcionální programování** (např. promises – user-microservices), **observer** (frontend – navbar), **controller-service-repository pattern**, **inversion of control** (backend), **dependency injection** (backend), **provider pattern** (frontend – UserStore, AppRouter)
- seznam UC je v dokumentaci
- cloud sluzby – google cloud

## Další věci:
- Load balancer


## Inicializační postup:

### Deploy aplikace:
Klonovat projekt:

    git clone https://github.com/dgonzo27/react-docker-gcp.git
    
Otevřit, napr v intellij IDEA

V libovolnem pořadí pustit jednotlivé komponenty


Přejít do složky frontend a instalovat node moduly:

    npm install
    
Pustit lokálně:

    npm run build
    npm start
    
Pustit the docker image (v hlavním souboru):

    docker compose up
    
Otevřit:

    http://localhost:8080
