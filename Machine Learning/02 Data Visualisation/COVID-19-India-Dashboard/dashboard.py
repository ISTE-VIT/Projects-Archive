import streamlit as st
import pandas as pd
import plotly.express as px
import plotly.graph_objects as go

df = pd.read_csv("https://api.covid19india.org/csv/latest/state_wise.csv")
df.drop(["Migrated_Other", "State_code", "Delta_Confirmed",
         "Delta_Recovered", "Delta_Deaths", "State_Notes"], axis=1, inplace=True)
df = df[df["Confirmed"] != 0]

df['Date'] = df['Last_Updated_Time'].astype('datetime64[ns]')

st.set_page_config(page_title='Streamlit Dashboard',
                   layout='wide',
                   page_icon='💹')

st.markdown("<h1 style='text-align: center; color: red;'>COVID19 DASHBOARD - INDIA</h1>",
            unsafe_allow_html=True)

st.markdown("---")

st.markdown("<p style='text-align: justify;'>As we all know our country is still facing SARS-CoV-2 (n-coronavirus),the scenes of suffering in our country are hard to comprehend. A second wave beginning in March 2021 was much larger than the first, with shortages of vaccines, hospital beds, oxygen cylinders and other medicines in parts of the country. By late April, India led the world in new and active cases. On 30 April 2021, it became the first country to report over 400,000 new cases in a 24-hour period. Health experts believe that India's figures have been underreported due to several factors.</p>", unsafe_allow_html=True)

st.markdown("<h4 style='text-align: justify; color: blue;'>This dashboard is an effort to analyze the cumulative data of confirmed, deaths, recovered and active cases over time.</h4>", unsafe_allow_html=True)


st.markdown("<h2 style='text-align: center;'>CASES ACROSS INDIA</h2>",
            unsafe_allow_html=True)

# kpi 1

con, rec, det, act = st.beta_columns(4)

with con:
    st.markdown("<h3 style='text-align: center;'>Confirmed Cases</h3>",
                unsafe_allow_html=True)
    num1 = df['Confirmed'][0]
    st.markdown(
        f"<h2 style='text-align: center; color: blue;'>{num1}</h2>", unsafe_allow_html=True)

with rec:
    st.markdown("<h3 style='text-align: center;'>Recovered Cases</h3>",
                unsafe_allow_html=True)
    num2 = df['Recovered'][0]
    st.markdown(
        f"<h2 style='text-align: center; color: green;'>{num2}</h2>", unsafe_allow_html=True)

with det:
    st.markdown("<h3 style='text-align: center;'>Deceased Cases</h3>",
                unsafe_allow_html=True)
    num3 = df['Deaths'][0]
    st.markdown(
        f"<h2 style='text-align: center; color: red;'>{num3}</h2>", unsafe_allow_html=True)

with act:
    st.markdown("<h3 style='text-align: center;'>Active Cases</h3>",
                unsafe_allow_html=True)
    num3 = df['Active'][0]
    st.markdown(
        f"<h2 style='text-align: center; color: orange;'>{num3}</h2>", unsafe_allow_html=True)

st.markdown("---")

# kpi2

df1 = pd.read_csv(
    "https://api.covid19india.org/csv/latest/case_time_series.csv")

st.markdown("<h2 style='text-align: center;'>Visualizing Total and Daily Cases</h2>",
            unsafe_allow_html=True)

first_chart, second_chart = st.beta_columns(2)

with first_chart:
    fig = px.line(df1, x="Date", y=["Total Confirmed",
                                    "Total Deceased", "Total Recovered"], title="Total Confirmed, Recovered and Deceased")
    fig.update_layout(height=600)
    st.plotly_chart(fig, use_container_width=True)

with second_chart:
    fig = px.line(df1, x="Date", y=["Daily Confirmed",
                                    "Daily Deceased", "Daily Recovered"], title="Daily Confirmed, Recovered and Deceased")
    fig.update_layout(height=600)
    st.plotly_chart(fig, use_container_width=True)

st.markdown("---")

# kpi3

st.markdown("<h2 style='text-align: center;'>Visualizing top 5 States</h2>",
            unsafe_allow_html=True)

df2 = df[1:].sort_values('Confirmed', ascending=False)

fig = go.Figure(data=[
    go.Bar(name='Confirmed',
                x=df2['State'][:5], y=df2['Confirmed'][:5]),
    go.Bar(name='Deaths',
                x=df2['State'][:5], y=df2['Deaths'][:5]),
    go.Bar(name='Recovered',
                x=df2['State'][:5], y=df2['Recovered'][:5]), ])
st.plotly_chart(fig, use_container_width=True)


first_chart, second_chart = st.beta_columns(2)

with first_chart:
    st.markdown("<h3 style='text-align: center;'>Total Confirmed Cases</h3>",
                unsafe_allow_html=True)
    fig = px.pie(df2, values=df2["Confirmed"][:5],
                 names=df2['State'][:5])
    st.plotly_chart(fig)

with second_chart:
    st.markdown("<h3 style='text-align: center;'>Total Recovered Cases</h3>",
                unsafe_allow_html=True)
    fig = px.pie(df2, values=df2["Recovered"][:5],
                 names=df2['State'][:5])
    st.plotly_chart(fig)


first_chart, second_chart = st.beta_columns(2)

with first_chart:
    st.markdown("<h3 style='text-align: center;'>Total Active Cases</h3>",
                unsafe_allow_html=True)
    fig = px.pie(df2, values=df2["Active"][:5],
                 names=df2['State'][:5])
    st.plotly_chart(fig)

with second_chart:
    st.markdown("<h3 style='text-align: center;'>Total Deceased Cases</h3>",
                unsafe_allow_html=True)
    fig = px.pie(df2, values=df2["Deaths"][:5],
                 names=df2['State'][:5])
    st.plotly_chart(fig)

# Scattermap
lat_lon = pd.read_csv("lat_lon_india.csv")
df.drop(0, axis=0, inplace=True)
df.reset_index(drop=True, inplace=True)
df["lat"] = lat_lon['latitude']
df["lon"] = lat_lon['longitude']

fig = px.scatter_mapbox(df, lat="lat", lon="lon", hover_name="State", hover_data=["Confirmed", "Recovered", "Deaths", "Active"],
                        color_discrete_sequence=["darkblue"], zoom=4, height=700)

fig.update_layout(mapbox_style="open-street-map")
fig.update_layout(margin={"r": 0, "t": 0, "l": 0, "b": 0})
st.markdown("<h2 style='text-align: center;'>State-wise detailed Scattermap</h2>",
            unsafe_allow_html=True)
st.plotly_chart(fig, use_container_width=True)
