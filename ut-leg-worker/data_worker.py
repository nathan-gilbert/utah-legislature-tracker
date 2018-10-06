#!/usr/local/bin/python3

from enum import Enum

class WorkerType(Enum):
    UNKNOWN = 1
    UTLEG = 2

class DataWorker:
    def __init__(self):
        self._worker_type = WorkerType.UNKNOWN

    def collect_data(self):
        pass
