#Author: Nathan Alan Gilbert
#Last Modified: Sunday December 11 5:54:05 PM MST 2016
""" Main class for handling sunlight API calls """
import sunlight

if __name__ == "__main__":
    key = ""
    with open(".sunlight.key", 'r') as keyFile:
        key = keyFile.readline().strip()

    sunlight.config.API_KEY = key
    ut_legs = sunlight.openstates.legislators(state='ut')

    total_leg = 0
    for leg in ut_legs:
        print("-"*50)
        if leg["active"]:
            total_leg += 1
            for key in leg:
                print(key + " " + str(leg[key]))

    print("-"*50)
    print("Total Legislators: " + str(total_leg))
