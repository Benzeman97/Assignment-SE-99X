import React, { Component } from 'react';
import Title from './Title';
import ProductColumns from './ProductColumns';
import ProductList from './ProductList';
import {ProductConsumer} from '../context';
import ProductTotals from './ProductTotals';


class PriceCalculator extends Component {
    render() {
        return (
            <section>
                <ProductConsumer>
                    { value=>{
                return(
                 <React.Fragment>
                <Title name="Product" title="List"/>
                <ProductColumns/>
                <ProductList value={value}/>
                <ProductTotals value={value}/>
                </React.Fragment>
                );
                }}
                </ProductConsumer>
            </section>
        );
    }
}

export default PriceCalculator;