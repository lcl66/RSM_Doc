import logging
import sys


class ElapsedTimeFormatter(logging.Formatter):
    def format(self, record):
        duration_milliseconds = record.relativeCreated
        hours, rem = divmod(duration_milliseconds / 1000, 3600)
        minutes, seconds = divmod(rem, 60)
        if hours:
            record.elapsedTime = "{:0>2}:{:0>2}:{:05.2f}".format(
                int(hours), int(minutes), seconds
            )
        else:
            record.elapsedTime = "{:0>2}:{:05.2f}".format(int(minutes), seconds)
        return super(ElapsedTimeFormatter, self).format(record)


def logging_start(level=None, filename="rsm-logging.log"):
    for h in logging.getLogger().handlers:
        if isinstance(h, logging.StreamHandler):
            if h.stream == sys.stdout:
                return  # don't dupe
    formatter = ElapsedTimeFormatter(
        fmt="[{elapsedTime}] {levelname:s}: {message:s}",
        style="{",
    )
    handler = logging.StreamHandler(sys.stdout)
    handler.setFormatter(formatter)
    logging.getLogger().addHandler(handler)
    if filename is not None:
        filehandler = logging.FileHandler(filename)
        filehandler.setFormatter(formatter)
        logging.getLogger().addHandler(filehandler)
    if level is not None:
        logging.getLogger().setLevel(level)
