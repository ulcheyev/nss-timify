# nss-sem
## Popis
Aplikace je součásti semestrálního projektu z předmětu NSS. 

[Dokumentace]()

[Low fidelity prototyp]()

[Ednpoint documentation in Swagger]()
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
- v aplikaci byli použité následující patterny: – **builder** (DTO – TaskDTO apod.), **DTO**, **funkcionální programování (např. promises – user-microservices), **observer** (frontend – navbar), **controller-service-repository pattern**, **inversion of control** (backend), **dependency injection** (backend), **provider pattern** (frontend – UserStore, AppRouter)
- seznam UC je v dokumentaci
- cloud sluzby – google cloud


## Inicializační postup:

### Deploy aplikace:
Klonovat projekt:

    git clone https://github.com/dgonzo27/react-docker-gcp.git
    
Otevřit, napr v intellij IDEA

V libovolnem pořadí pustit nasledující komponenty: *UserMicroserviceApplication*, *ApiGatewayApplication*, *DyscoveryServerApplication*


Přejít do složky frontend a instalovat node moduly:

    npm install
    
Pustit lokálně:

    npm run build
    npm start
    
Docker image:

    docker build -t react-docker-gcp:latest .
    
Pustit the docker image:

    docker run -p 8080:443 react-docker-gcp:latest
    
Otevřit:

    http://localhost:8080
