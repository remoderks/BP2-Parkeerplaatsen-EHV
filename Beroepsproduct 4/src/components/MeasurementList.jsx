import React, { useEffect, useState } from "react";

export default function MeasurementList() {
  const [measurements, setMeasurements] = useState([]);
  const [battery, setBattery] = useState(null);

  useEffect(() => {
    const socket = new WebSocket("ws://145.49.127.247:1880/ws/groep3");

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data);

      // Gebruik temperature_4 als tijdelijke placeholder, pas dit kanaalnummer aan indien nodig!
      if (data["temperature_4"] !== undefined) {
        const newMeasurement = {
          temp: data["temperature_4"],
          timestamp: new Date().toISOString(),
          number: Date.now() % 1000,
        };
        setMeasurements((prev) => [newMeasurement, ...prev.slice(0, 4)]);
      }

      // Gebruik voltage_5 als tijdelijke placeholder voor batterijstatus
      if (data["voltage_5"] !== undefined) {
        setBattery(data["voltage_5"]);
      }
    };

    return () => socket.close();
  }, []);

  return (
    <div className="w-full max-w-md bg-white text-black rounded p-4 shadow">
      <h2 className="text-lg font-semibold mb-2">Laatste metingen</h2>
      {battery !== null && (
        <p className="text-sm mb-2 font-medium">
          Batterijstatus: {battery.toFixed(2)} V
        </p>
      )}
      {measurements.length === 0 ? (
        <p className="text-sm italic">Nog geen metingen beschikbaar.</p>
      ) : (
        <ul className="space-y-1">
          {measurements.map((m, idx) => (
            <li key={idx} className="text-sm">
              Meting {m.number}: {m.temp} °C @{" "}
              {new Date(m.timestamp).toLocaleTimeString()}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
