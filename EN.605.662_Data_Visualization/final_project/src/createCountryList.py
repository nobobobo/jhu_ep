import pandas as pd 
import numpy as np
import plotly.express as px
from geopy.geocoders import Nominatim

geolocator = Nominatim(user_agent="dv_fianl_pj")
def geolocate(country):
    try:
        # Geolocate the center of the country
        loc = geolocator.geocode(country)
        # And return latitude and longitude
        return (loc.latitude, loc.longitude)
    except:
        # Return missing value
        return np.nan

def createCountryList(df, country_col='country'): 
    lat = []
    lng = []
    country_list = df[country_col].unique()

    for country in country_list:
        loc = geolocate(country)
        lat.append(loc[0])
        lng.append(loc[1])

    country_df = pd.DataFrame(country_list, columns=['country'])
    country_df['Lat'] = lat
    country_df['Lng'] = lng
    print(country_df)
    country_df.to_csv('data/country.csv', index=False)
    print('converted')

if __name__ == '__main__':
    df = pd.read_csv('data/country_vaccinations.csv')
    createCountryList(df)

