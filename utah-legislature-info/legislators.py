import sunlight

if __name__ == "__main__":
    key = ""
    with open(".open-states.key", 'r') as keyFile:
        key = keyFile.readline().strip()
    sunlight.config.API_KEY = key
    ut_legislators = sunlight.openstates.legislators(state='ut')

    for leg in ut_legislators:
        print(leg)
