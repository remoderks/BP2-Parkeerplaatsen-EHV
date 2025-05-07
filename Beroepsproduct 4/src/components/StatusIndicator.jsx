import React, { useEffect, useState } from "react";

export default function StatusIndicator() {
  const [online, setOnline] = useState(false);

  useEffect(() => {
    const socket = new WebSocket("ws://145.49.127.247:1880/ws/groep3");

    socket.onmessage = (event) => {
      const msg = JSON.parse(event.data);
      if (msg.status) {
        setOnline(msg.status === "online");
      }
    };

    return () => socket.close();
  }, []);

  return (
    <div className="flex items-center space-x-2">
      <div
        className={`w-3 h-3 rounded-full ${online ? "bg-green-400" : "bg-red-500"}`}
        title={online ? "Verbonden" : "Geen verbinding"}
      ></div>
      <span className="text-sm">{online ? "Verbonden" : "Geen verbinding"}</span>
    </div>
  );
}
