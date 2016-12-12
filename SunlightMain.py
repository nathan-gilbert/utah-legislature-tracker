#Author: Nathan Alan Gilbert
#Last Modified: Sunday December 11 6:07:22 PM MST 2016
""" Main class for handling sunlight API calls """
import argparse
import sunlight

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-v", "--verbose", action="store_true", help="increase output verbosity")
    parser.parse_args()

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
