#Author: Nathan Alan Gilbert
#Last Modified: Sunday December 11 5:20:08 PM MST 2016
""" <Module Docstring> - Replace with documentation """
import sunlight

if __name__ == "__main__":
    key = ""
    with open(".sunlight.key", 'r') as keyFile:
        key = keyFile.readline().strip()

    sunlight.config.API_KEY = key
    ut_legs = sunlight.openstates.legislators(state='ut')
    print(ut_legs)

    for leg in ut_legs:
        for key in leg:
            print(key + " " + leg[key])

