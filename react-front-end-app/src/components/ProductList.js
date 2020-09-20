import React from 'react';
import Product from './Product';

function ProductList({value}) {
    const {products}=value;


    return (
        <div className="container-fluid">
         
        {
            products.map(product=>{
                return <Product key={product.productId} product={product} value={value}/>
            })
          
        }
        </div>
    );
}

export default ProductList;