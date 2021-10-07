import React from "react";
import Banner from "../Banner/banner";
import "../homescreen/homescreen.css";
import Row from "../Rows/Rows";
import Navbar from "./../Navbar/Navbar";
import requests from "./../../Requests";

function Homescreen() {
  return (
    <div className="homeScreen">
      <Navbar />
      <Banner />

      <Row title="Trending" fetchURL={requests.fetchTrending} />
      <Row title="Top Rated" fetchURL={requests.fetchTopRated} />
      <Row
        title="Netflix Originals"
        fetchURL={requests.fetchNetflixOriginals}
        isLargeRow
      />
      <Row title="Action Movies" fetchURL={requests.fetchActionMovies} />
      <Row title="Comedy Movies" fetchURL={requests.fetchComedyMovies} />
      <Row title="Horror Movies" fetchURL={requests.fetchHorrorMovies} />
      <Row title="Romance Movies" fetchURL={requests.fetchRomanceMovies} />
      <Row title="Documentaries" fetchURL={requests.fetchDocumentaries} />
    </div>
  );
}

export default Homescreen;
