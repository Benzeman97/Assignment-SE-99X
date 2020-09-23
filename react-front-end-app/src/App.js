import React from 'react';
import './App.css';
import PriceCalculator from './components/PriceCalculator';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Switch,Route } from 'react-router-dom';


function App() {
  return (
    
      <React.Fragment>

        <Switch>
          <Route exact path="/" component={PriceCalculator}/>
        </Switch>
        
       </React.Fragment>
  
  );
}

export default App;
