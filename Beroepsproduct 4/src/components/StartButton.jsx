import React, { useState } from "react";

export default function StartButton() {
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleStart = async () => {
    setLoading(true);
    setMessage("");
    try {
      const res = await fetch("http://145.49.127.247:1880/groep3", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ command: "start" }),
      });
      const data = await res.json();
      if (data.status === "ok") {
        setMessage("Metingen gestart.");
      } else {
        setMessage("Fout bij starten.");
      }
    } catch (err) {
      setMessage("Verbindingsfout.");
    }
    setLoading(false);
  };

  return (
    <div className="flex flex-col items-center">
      <button
        onClick={handleStart}
        disabled={loading}
        className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded disabled:opacity-50"
      >
        Start temperatuurmeting
      </button>
      {message && <p className="mt-2 text-sm">{message}</p>}
    </div>
  );
}
