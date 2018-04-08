# utah-legislature-tracker

Collect and present public information on the actions of the Utah Legislature.

## ut-leg-service

### Run background service

```sh
python3 app.py
```

Goto `http://localhost:9090/v1/ui` for the swagger docs.

Info on connexion routing: <https://github.com/zalando/connexion>

## Backend TODO

* BACK: pull legislators
* BACK: get a set of bills by criteria x,y,z
* BACK: see legs votes on bills in committees and the floor

## ut-leg-ui

### Run UI

```sh
cd ut-leg-ui
yarn start
```

## UI TODO

* UI: Display legs
* UI: display the bills
* UI: display this info nicely
