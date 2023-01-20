#
# Aggregate donor model input files/uec/non-abm model outputs to new zone structure
# This python file is being called in bin\runRSMInputAggregator.cmd
#
# inputs:
#   rsm_main_dir: RSM main directory
#   org_model_dir: Donor model directory
#   agg_zones: Aggregated zones
#   ext_zones: External zones
#
# outputs:
#   aggregated csv files

import sys
import os
import logging
from sandag_rsm.data_load.zones import load_mgra_data
from sandag_rsm.logging import logging_start
from sandag_rsm.sampler import rsm_household_sampler
from sandag_rsm.zone_agg import (
    aggregate_zones,
    make_crosswalk,
    mark_centroids,
    merge_zone_data,
)
from sandag_rsm.input_agg import agg_input_files

rsm_main_dir = sys.argv[1]
org_model_dir = sys.argv[2]
agg_zones = sys.argv[3]
ext_zones = sys.argv[4]

logging_start(
    filename=os.path.join(rsm_main_dir, "logFiles", "rsm-logging.log"), level=logging.INFO
)
logging.info("start logging rsm_input_aggregator")


#   Input Aggregation
agg_input_files(
    model_dir = org_model_dir, 
    rsm_dir = rsm_main_dir,
    taz_cwk_file = "taz_crosswalk.csv",
    mgra_cwk_file = "mgra_crosswalk.csv",
    agg_zones = agg_zones,
    ext_zones = ext_zones,
    input_files = ["microMgraEquivMinutes.csv", "microMgraTapEquivMinutes.csv", 
    "walkMgraTapEquivMinutes.csv", "walkMgraEquivMinutes.csv", "bikeTazLogsum.csv",
    "bikeMgraLogsum.csv", "zone.term", "zones.park", "tap.ptype", "accessam.csv",
    "ParkLocationAlts.csv", "CrossBorderDestinationChoiceSoaAlternatives.csv", 
    "TourDcSoaDistanceAlts.csv", "DestinationChoiceAlternatives.csv", "SoaTazDistAlts.csv",
    "TripMatrices.csv", "transponderModelAccessibilities.csv", "crossBorderTours.csv", 
    "internalExternalTrips.csv", "visitorTours.csv", "visitorTrips.csv", "householdAVTrips.csv", 
    "crossBorderTrips.csv", "TNCTrips.csv", "airport_out.SAN.csv", "airport_out.CBX.csv", 
    "TNCtrips.csv"]
	)

logging.info("finished logging rsm_input_aggregator")