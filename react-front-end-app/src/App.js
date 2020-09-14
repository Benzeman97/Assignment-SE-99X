import React from 'react';
import './App.css';
import ProductTable from './components/ProductTable';
import PriceCalculator from './components/PriceCalculator';
import 'bootstrap/dist/css/bootstrap.min.css';
import Title from './components/Title';
import { ProductProvider } from './context';

function App() {
  return (
    <div className="App">
      <ProductProvider>
       <PriceCalculator/> 
       </ProductProvider>
       
    </div>
  );
}

export default App;
