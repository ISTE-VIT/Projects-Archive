import React, { useState, useEffect } from "react";
import "../Banner/banner.css";

import requests from "./../../Requests";
import axios from "../../axios";

function Banner() {
  const [movie, setMovie] = useState();

  useEffect(() => {
    async function fetchData() {
      //   const request = await axios.get(requests.fetchNetflixOriginals);
      //   setMovie(
      //     request.data.results[
      //       Math.floor(Math.random() * request.data.results.length - 1)
      //     ]
      //   );

      const request = await axios.get(requests.fetchNetflixOriginals);
      setMovie(
        request.data.results[
          Math.floor(Math.random() * request.data.results.length - 1)
        ]
      );
    }
    fetchData();
  }, []);

  function truncate(string, n) {
    return string?.length > n ? string.substr(0, n - 1) + "..." : string;
  }

  return (
    <header
      className="banner"
      style={{
        backgroundImage: `url("https://image.tmdb.org/t/p/original/${movie?.backdrop_path}")`,
        backgroundSize: "cover",
        backgroundPosition: "center center",
      }}
    >
      <div className="banner__contents">
        <div className="banner__title">
          {movie?.name || movie?.title || movie?.original_name}
        </div>
        <div className="banner__buttons">
          <button className="banner__button">play</button>
          <button className="banner__button">My list</button>
        </div>
        <h1 className="banner__description">
          {truncate(movie?.overview, 150)}
        </h1>
      </div>
      <div className="banner__fadeBottom" />
    </header>
  );
}

export default Banner;
