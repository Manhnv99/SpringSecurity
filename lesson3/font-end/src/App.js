
import './App.css';
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path="/login" element= <Login/> />
                <Route path="/register" element = <Register/> />
                <Route path="/home" element = <Home/> />
            </Routes>
        </Router>
    </div>
  );
}

export default App;
