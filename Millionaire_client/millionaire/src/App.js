
import 'bootstrap/dist/css/bootstrap.css';
import {
  BrowserRouter as Router,
  Link,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import { Form } from './questions/Form';
import { QuestionsTable } from './questions/QuestionsTable'
import { Question } from './questions/Question';
import './style.css';

function App() {

  return (
    <Router>
    <div className='container'>
    <nav className="navbar navbar-expand-lg mb-1">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item border bg-success mx-3">
              <Link to={"/questions"} className="nav-link text-light clr">
                GET
              </Link>
            </li>
            <li className="nav-item border bg-primary mx-3">
              <Link to={"/create"} className="nav-link text-light clr">
                POST
              </Link>
            </li>
          </ul>
        </nav>

      <Routes>
          <Route index element={<Navigate to={"/"} />} />
          <Route path="/">
            <Route index element={<Form />} />
            <Route path="questions" element={<QuestionsTable />} />
            <Route path="/api/questions/:id" element={<Form />} />
            <Route path="create" element={<Form />} />
            <Route path="/api/question/:id" element={<Question />} />
          </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
