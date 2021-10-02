# COVID19-India Viz Dashboard 

[![UI ](https://img.shields.io/badge/Deployed-%20---->-blue?style=for-the-badge&logo=appveyor)](https://share.streamlit.io/indrap24/covid-19-india-viz/main/dashboard.py)
[![Streamlit App](https://static.streamlit.io/badges/streamlit_badge_black_red.svg)](https://share.streamlit.io/indrap24/covid-19-india-viz/main/dashboard.py)


## Overview
As we all know our country is still facing SARS-CoV-2 (n-coronavirus),the scenes of suffering in our country are hard to comprehend. A second wave beginning in March 2021 was much larger than the first, with shortages of vaccines, hospital beds, oxygen cylinders and other medicines in parts of the country. By late April, India led the world in new and active cases. On 30 April 2021, it became the first country to report over 400,000 new cases in a 24-hour period. Health experts believe that India's figures have been underreported due to several factors.

### This dashboard is an effort to analyze the cumulative data of confirmed, deaths, recovered and active cases over time.



## Datasets used

Source Website: [Covid19 India API](https://api.covid19india.org)

The dataset apis we used:
- https://api.covid19india.org/csv/latest/state_wise.csv
- https://api.covid19india.org/csv/latest/case_time_series.csv

## Requirement
  
* Pre-requisites:
	-  Python 3.7 + (We used Python 3.9)
	-  Dependencies from [requirements.txt]()

* Directions to Install locally

   - Clone this repository<br>
   ```bash
   git clone https://github.com/IndraP24/COVID-19-India-Viz.git
   ```
   - Navigate to this repository, create a Virtual Environment and activate it: <br>
   ```bash
  cd path/to/cloned/repo
  python3 -m venv env
  source env/bin/activate
  ```
   - Install the python dependencies from the **requirements.txt** file:
    ```bash
    pip install -r requirements.txt
     ```

## Usage

To run the app locally, navigate to the project directory and run the following command:
```bash
streamlit run dashboard.py
```


## Contributors

|                                                                                         <a href="https://github.com/indrap24"><img src="https://avatars.githubusercontent.com/u/64627762?s=400&u=0223a819d07fd06064c40e024e5692e61df6c16d&v=4" width=150px height=150px /></a>                                                                                         |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|                                                                                                                                        **[Indrashis Paul](https://www.linkedin.com/in/indrashis-paul-ba84b6194/)**                                                                                                                                        |
| <a href="https://www.linkedin.com/in/indrashis-paul-ba84b6194/"><img src="https://mpng.subpng.com/20180324/vhe/kisspng-linkedin-computer-icons-logo-social-networking-ser-facebook-5ab6ebfe5f5397.2333748215219374063905.jpg" width="32px" height="32px"></a> |

|                                                                                         <a href="https://github.com/imraghavagr"><img src="https://avatars.githubusercontent.com/u/52325383?v=4" width=150px height=150px /></a>                                                                                         |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
|                                                                                                                                        **[Raghav Agarwal](https://www.linkedin.com/in/raghav-a-30b020102/)**                                                                                                                                        |
| <a href="https://www.linkedin.com/in/raghav-a-30b020102/"><img src="https://mpng.subpng.com/20180324/vhe/kisspng-linkedin-computer-icons-logo-social-networking-ser-facebook-5ab6ebfe5f5397.2333748215219374063905.jpg" width="32px" height="32px"></a> |
