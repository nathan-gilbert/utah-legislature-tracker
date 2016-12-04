#Author: Nathan Alan Gilbert
#Last Modified: Sunday December 4 4:36:14 PM MST 2016
#
""" <Module Docstring> - Replace with documentation """
import sunlight

if __name__ == "__main__":
    ut_legs = sunlight.openstates.legislators(state='ut')
    print(ut_legs)

    for leg in ut_legs:
        for key in leg:
            print(key + " " + leg[key])

