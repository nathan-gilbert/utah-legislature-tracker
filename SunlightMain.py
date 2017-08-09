""" Main class for handling sunlight API calls """
import argparse
import sunlight

#TODO
def grab_current_house_bills():
    """ house bills """
    pass

#TODO
def grab_current_senate_bills():
    """ senate bills """
    pass

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-v", "--verbose", action="store_true", help="increase output verbosity")
    parser.parse_args()

    key = ""
    with open(".sunlight.key", 'r') as keyFile:
        key = keyFile.readline().strip()

    sunlight.config.API_KEY = key
    ut_metadata = sunlight.openstates.state_metadata(state='ut')
    ut_legislators = sunlight.openstates.legislators(state='ut')
    ut_bills = sunlight.openstates.bills(state='ut')

    print(ut_bills)

    # total_leg = 0
    # for leg in ut_legs:
    #     print("-"*50)
    #     if leg["active"]:
    #         total_leg += 1
    #         for key in leg:
    #             print(key + " " + str(leg[key]))
    # print("-"*50)
    # print("Total Legislators: " + str(total_leg))
