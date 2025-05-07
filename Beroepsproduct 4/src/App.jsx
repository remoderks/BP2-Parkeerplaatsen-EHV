import React from "react";
import StartButton from "./components/StartButton";
import MeasurementList from "./components/MeasurementList";
import StatusIndicator from "./components/StatusIndicator";
import Layout from "./components/Layout";

export default function App() {
  return (
    <Layout>
      <div className="flex flex-col items-center space-y-4">
        <StatusIndicator />
        <StartButton />
        <MeasurementList />
      </div>
    </Layout>
  );
}
