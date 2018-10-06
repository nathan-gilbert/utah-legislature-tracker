#!/usr/local/bin/python3

# look at graphene for graphql querying
# https://github.com/graphql-python/graphene
# also need the open states api
# http://docs.openstates.org/en/latest/api/v2/index.html
from data_worker import DataWorker, WorkerType

class UtLegWorker(DataWorker):
    def __init__(self):
        DataWorker.__init__(self)
        self._worker_type = WorkerType.UTLEG

    def collect_data(self):
        pass
