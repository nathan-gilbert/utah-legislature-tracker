""" Main class for handling sunlight API calls """
import argparse
import json
import time
import sunlight

def current_house_bills(year):
    """ house bills """
    hb = {}
    for b in ut_bills:
        if b["chamber"] == "lower" and b["session"] == year:
            bill_info = sunlight.openstates.bill_detail(
                state='ut',
                session=b["session"],
                bill_id=b["bill_id"],
                chamber=b['chamber'])
            hb[b["bill_id"]] = bill_info
    return hb

def current_senate_bills(year):
    """ senate bills """
    sb = {}
    for b in ut_bills:
        if b["chamber"] == "upper" and b["session"] == year:
            bill_info = sunlight.openstates.bill_detail(
                state='ut',
                session=b["session"],
                bill_id=b["bill_id"],
                chamber=b['chamber'])
            sb[b["bill_id"]] = bill_info
    return sb

if __name__ == "__main__":
    start_time = time.time()
    this_year = '2018'
    parser = argparse.ArgumentParser()
    parser.add_argument("-v",
                        "--verbose",
                        action="store_true",
                        help="increase output verbosity"
                       )
    parser.add_argument("-c",
                        "--config",
                        type=str,
                        help="increase output verbosity",
                        default="config.json"
                       )
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

    house_bills = current_house_bills(this_year)
    print("Number of House Bills: " + str(len(house_bills)))
    senate_bills = current_senate_bills(this_year)
    print("Number of Senate Bills: " + str(len(senate_bills)))
    #for bill in house_bills:
    #    print(bill)
    print("--- {0} seconds ---".format(time.time() - start_time))
