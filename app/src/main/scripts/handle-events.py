import datetime
import pandas as pd
from icalendar import Calendar, Event


def getDataFrameFromIcs(path):
    g = open(path, 'rb')
    gcal = Calendar.from_ical(g.read())
    outer_list = []
    for component in gcal.walk():
        row = []
        if component.name == "VEVENT":
            row.append(component.get('summary'))
            row.append(component.get('dtstart').dt.date().strftime('%Y-%m-%d'))
            row.append(component.get('description'))
            outer_list.append(row)
    g.close()
    return outer_list


path2019 = 'app/src/main/assets/calendar2019.ics'
path2020 = 'app/src/main/assets/calendar2020.ics'

events2019 = getDataFrameFromIcs(path2019)
events2020 = getDataFrameFromIcs(path2020)

all_events = events2019 + events2020
headers = ['ShortDescription', 'Date', 'LongDescription']
df = pd.DataFrame(data=all_events, columns=headers)

df.to_json('app/src/main/assets/events_data.json', orient='records')
