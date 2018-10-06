# ut-leg-worker

A set of scripts to collect and analyze data from OpenStates

## APIs

* [Open States](http://docs.openstates.org/en/latest/api/v2/index.html)
* [OpenFEC](https://api.open.fec.gov/developers/)
* [Ballotpedia](https://ballotpedia.org/API-documentation)

## Usage

### Create the database

Assumes you have postgresql installed and have ran this command:

```psql
CREATE DATABASE utleg;
```

Next run the init script.

```sh
./init_db.py
```

### Run the worker