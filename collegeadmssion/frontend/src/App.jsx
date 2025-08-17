// src/App.jsx
import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

import ChatPopup from "./chatbot/ChatPopup";
import Main from "./main/Main";
import Home from "./home/Home";

import "./App.css";

function App() {
  return (
    <Router>
      {/* Chatbot always available */}
      <ChatPopup />

      <Routes>
        {/* Default landing page → Main (Header + Home) */}
        <Route path="/" element={<Main />} />

        {/* Direct Home page route if needed */}
        <Route path="/home" element={<Home />} />

        {/* Fallback route → redirect unknown paths to "/" */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </Router>
  );
}

export default App;
