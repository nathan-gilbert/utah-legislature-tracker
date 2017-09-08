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
    with open(".open-states.key", 'r') as keyFile:
        key = keyFile.readline().strip()

    sunlight.config.API_KEY = key
    ut_metadata = sunlight.openstates.state_metadata(state='ut')
    ut_legislators = sunlight.openstates.legislators(state='ut')
    ut_bills = sunlight.openstates.bills(state='ut')

    print("UT Legislators: " + str(len(ut_legislators)))
    print("UT Bills: " + str(len(ut_bills)))
    for bill in ut_bills:
        print(bill)
        print(bill["id"])
        bill_info = sunlight.openstates.bill_detail(state='ut', session='2017', bill_id=bill["bill_id"], chamber=bill['chamber'])
        print(bill_info)
