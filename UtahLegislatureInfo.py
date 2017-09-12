""" Main class for handling sunlight API calls """
import argparse
import sunlight
import json

def current_house_bills():
    """ house bills """
    house_bills = {}
    for bill in ut_bills:
        if bill["chamber"] == "lower":
            bill_info = sunlight.openstates.bill_detail(state='ut', session='2017', bill_id=bill["bill_id"], chamber=bill['chamber'])
            house_bills[bill["bill_id"]] = bill_info
    return house_bills

def current_senate_bills():
    """ senate bills """

    senate_bills = {}
    for bill in ut_bills:
        if bill["chamber"] == "upper":
            bill_info = sunlight.openstates.bill_detail(state='ut', session='2017', bill_id=bill["bill_id"], chamber=bill['chamber'])
            senate_bills[bill["bill_id"]] = bill_info
    return senate_bills

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-v", "--verbose", action="store_true", help="increase output verbosity")
    parser.add_argument("-c", "--config", type=str, help="increase output verbosity", default="config.json")
    args = parser.parse_args()

    config = None
    with open(args.config, 'rb') as config_file:
        config_file = json.load(config_file)

    key = ""
    with open(".open-states.key", 'r') as keyFile:
        key = keyFile.readline().strip()

    sunlight.config.API_KEY = key
    ut_metadata = sunlight.openstates.state_metadata(state='ut')
    ut_legislators = sunlight.openstates.legislators(state='ut')
    ut_bills = sunlight.openstates.bills(state='ut')

    print("UT Legislators: " + str(len(ut_legislators)))
    print("UT Bills: " + str(len(ut_bills)))

    house_bills = current_house_bills()
    senate_bills = current_senate_bills()

    for bill in house_bills:
        print(bill)
