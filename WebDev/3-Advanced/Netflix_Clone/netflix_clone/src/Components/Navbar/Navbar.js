import React, { useState, useEffect } from "react";
import "../Navbar/Navbar.css";

export default function Navbar() {
  const [show, handleshow] = useState(false);

  const transitionNavbar = () => {
    if (window.scrollY > 100) {
      handleshow(true);
    } else {
      handleshow(false);
    }
  };

  useEffect(() => {
    window.addEventListener("scroll", transitionNavbar);
    return window.addEventListener("scroll", transitionNavbar);
  }, []);

  return (
    <div className={`nav ${show && "nav__black"}`}>
      <div className="nav__contents">
        <img
          className="nav__logo"
          src="http://assets.stickpng.com/images/580b57fcd9996e24bc43c529.png"
          alt=""
        />
        <img
          className="nav__avatar"
          src="data:image/png;base64,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"
          alt=""
        />
      </div>
    </div>
  );
}
