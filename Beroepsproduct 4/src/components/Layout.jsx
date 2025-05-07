import React from "react";
import aasaLogo from "../assets/aasa-logo.png"; // Voeg logo toe in assets-map

export default function Layout({ children }) {
  return (
    <div className="min-h-screen bg-gradient-to-b from-black via-blue-900 to-black text-white p-4">
      <header className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Vloeistofmodule – Groep 3</h1>
        <img src={aasaLogo} alt="AASA Logo" className="h-12" />
      </header>
      <main>{children}</main>
      <footer className="mt-8 text-sm text-center opacity-60">&copy; AASA 2025</footer>
    </div>
  );
}
