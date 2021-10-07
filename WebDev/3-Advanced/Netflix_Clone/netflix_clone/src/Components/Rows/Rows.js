import React, { useState, useEffect } from "react";
import axios from "../../axios";
import "./Rows.css";

function Row({ title, fetchURL }) {
  const [movies, setMovies] = useState([]);

  const base_url = "https://image.tmdb.org/t/p/original/";

  useEffect(() => {
    async function fetchData() {
      const request = await axios.get(fetchURL);
      setMovies(request.data.results);
      return request;
    }

    fetchData();
  }, [fetchURL]);

  function truncate(string, n) {
    return string?.length > n ? string.substr(0, n - 1) + "..." : string;
  }

  console.log(movies);

  return (
    <div className="row">
      <h2>{title}</h2>
      <div className="row_posters">
        {movies.map((movie) => (
          <div className="row__posters">
            <img
              className="row_poster"
              key={movie.id}
              src={`${base_url}${movie.backdrop_path}`}
              alt={movie.name}
            />
            <div className="movie__name">
              {/* {movie?.name || movie?.title || movie?.original_name} */}
              {truncate(
                movie?.name || movie?.title || movie?.original_name,
                15
              )}
            </div>
          </div>
        ))}
        {/* {movies.map((movie) => ( */}
      </div>
    </div>
  );
}

export default Row;
